package twilightforest.data.custom.stalactites.entry;

import com.google.gson.*;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public record Stalactite(Map<Block, Integer> ores, float sizeVariation, int maxLength, int weight) {

	private static StalactiteReloadListener STALACTITE_CONFIG;

	public static void reloadStalactites() {
		STALACTITE_CONFIG = new StalactiteReloadListener();
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(STALACTITE_CONFIG);
	}

	public static StalactiteReloadListener getStalactiteConfig() {
		if (STALACTITE_CONFIG == null)
			throw new IllegalStateException("Can not retrieve Stalactites yet!");
		return STALACTITE_CONFIG;
	}

	public static class Serializer implements JsonDeserializer<Stalactite>, JsonSerializer<Stalactite> {

		@Override
		public Stalactite deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
			JsonObject jsonobject = GsonHelper.convertToJsonObject(json, "stalactite");
			float size = GsonHelper.getAsFloat(jsonobject, "size_variation");
			int maxLength = GsonHelper.getAsInt(jsonobject, "max_length");
			int weight = GsonHelper.getAsInt(jsonobject, "weight");

			return new Stalactite(this.deserializeBlockMap(jsonobject), size, maxLength, weight);
		}

		private Map<Block, Integer> deserializeBlockMap(JsonObject json) {
			JsonArray array = GsonHelper.getAsJsonArray(json, "blocks");
			Map<Block, Integer> map = new HashMap<>();
			array.forEach(jsonElement -> {
				map.put(BuiltInRegistries.BLOCK.getValue(ResourceLocation.tryParse(GsonHelper.getAsString(jsonElement.getAsJsonObject(), "block"))), GsonHelper.getAsInt(jsonElement.getAsJsonObject(), "weight"));
			});
			return map;
		}

		@Override
		public JsonElement serialize(Stalactite stalactite, Type type, JsonSerializationContext context) {
			JsonObject jsonobject = new JsonObject();
			JsonArray array = new JsonArray();
			for (Map.Entry<Block, Integer> entry : stalactite.ores().entrySet()) {
				JsonObject entryObject = new JsonObject();
				entryObject.add("block", context.serialize(Registry.BLOCK.getKey(entry.getKey()).getPath()));
				entryObject.add("weight", context.serialize(entry.getValue()));
				array.add(entryObject);
			}
			jsonobject.add("blocks", array);
			jsonobject.add("size_variation", context.serialize(stalactite.sizeVariation()));
			jsonobject.add("max_length", context.serialize(stalactite.maxLength()));
			jsonobject.add("weight", context.serialize(stalactite.weight()));
			return jsonobject;
		}
	}

	public enum HollowHillType {
		SMALL,
		MEDIUM,
		LARGE
	}
}
