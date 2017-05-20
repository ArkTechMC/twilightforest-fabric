package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import twilightforest.entity.EntityTFAdherent;

public class RenderTFAdherent extends RenderTFBiped<EntityTFAdherent> {

	public RenderTFAdherent(RenderManager manager, ModelBiped modelBiped, float scale, String textureName) {
		super(manager, modelBiped, scale, textureName);
	}

    @Override
    public void doRender(EntityTFAdherent p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
    	//GL11.glTranslatef(0.0F, 1.0F, 0.0F);

        super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

}
