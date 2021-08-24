package twilightforest.world.components.structures.darktower;

import net.minecraft.world.level.block.Blocks;
import twilightforest.block.TFBlocks;
import twilightforest.world.components.structures.TFStructureDecorator;

public class StructureDecoratorDarkTower extends TFStructureDecorator {

	public StructureDecoratorDarkTower() {
		this.blockState = TFBlocks.tower_wood.get().defaultBlockState();
		this.accentState = TFBlocks.tower_wood_encased.get().defaultBlockState();
		this.fenceState = Blocks.OAK_FENCE.defaultBlockState();
		this.stairState = Blocks.SPRUCE_STAIRS.defaultBlockState();
		this.pillarState = TFBlocks.tower_wood_encased.get().defaultBlockState();
		this.platformState = TFBlocks.tower_wood_encased.get().defaultBlockState();
		this.randomBlocks = new TowerwoodProcessor();
	}

}