package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import twilightforest.TwilightForestMod;
import twilightforest.client.model.ModelTFSnowQueen;
import twilightforest.entity.boss.EntityTFSnowQueen;

public class RenderTFSnowQueen extends RenderBiped<EntityTFSnowQueen> {
    private static final ResourceLocation textureLoc = new ResourceLocation(TwilightForestMod.MODEL_DIR + "snowqueen.png");

	public RenderTFSnowQueen(RenderManager manager) {
		super(manager, new ModelTFSnowQueen(), 0.625F);
	}

    @Override
	protected ResourceLocation getEntityTexture(EntityTFSnowQueen par1Entity)
    {
    	return textureLoc;
    }
    
    @Override
	protected void preRenderCallback(EntityTFSnowQueen queen, float par2)
    {
    	float scale = 1.2F;
        GlStateManager.scale(scale, scale, scale);
    }
    
	@Override
	public void doRender(EntityTFSnowQueen entity, double d, double d1, double d2, float f, float f1) {

		EntityTFSnowQueen queen = (EntityTFSnowQueen)entity;
		
        BossStatus.setBossStatus(queen, false);

		super.doRender(entity, d, d1, d2, f, f1);
		
		for (int i = 0; i < queen.iceArray.length; i++) {
			RenderManager.instance.renderEntitySimple(queen.iceArray[i], f1);
		}
	}
}
