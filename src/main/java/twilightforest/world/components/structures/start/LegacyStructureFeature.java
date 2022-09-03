package twilightforest.world.components.structures.start;

import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import twilightforest.TwilightForestMod;
import twilightforest.world.components.structures.TFStructureComponentTemplate;
import twilightforest.world.registration.TFFeature;

import java.util.Objects;
import java.util.Optional;

@Deprecated
public class LegacyStructureFeature extends TwilightStructureFeature<NoneFeatureConfiguration> {
    public final TFFeature feature;

    public LegacyStructureFeature(@Deprecated TFFeature feature) {
        super(NoneFeatureConfiguration.CODEC, configContext -> isValidBiome(configContext) ? feature.generatePieces(configContext).map(piece -> (structurePiecesBuilder, context) -> {
			structurePiecesBuilder.addPiece(piece);
			piece.addChildren(piece, structurePiecesBuilder, context.random());

			for (StructurePiece structurePiece : structurePiecesBuilder.pieces) {
				if (structurePiece instanceof TFStructureComponentTemplate t) {
					t.LAZY_TEMPLATE_LOADER.run();
				}
			}
		}) : Optional.empty());
        this.feature = feature;
    }

	private static boolean isValidBiome(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context) {
		int x = context.chunkPos().getMiddleBlockX();
		int z = context.chunkPos().getMiddleBlockZ();
		int y = 1;
		Holder<Biome> holder = context.chunkGenerator().getNoiseBiome(QuartPos.fromBlock(x), QuartPos.fromBlock(y), QuartPos.fromBlock(z));
		ResourceLocation id = context.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getKey(holder.value());
		return id != null && Objects.equals(id.getNamespace(), TwilightForestMod.ID);
	}

    @Override
    public GenerationStep.Decoration step() {
        return feature.getDecorationStage();
    }
}
