package TuxWeapons.TuxCraft.entity.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.entity.EntityDynamite;
import TuxWeapons.TuxCraft.entity.models.ModelDynamite;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDynamite extends Render {

	/** The Skeleton's head model. */
	ModelDynamite skeletonHeadModel = new ModelDynamite();
	private ResourceLocation location = new ResourceLocation(
			TuxWeaponsCore.modidLowerCase, "textures/models/weapons.png");

	private float func_82400_a(float par1, float par2, float par3) {

		float f3;

		for (f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F) {
			;
		}

		while (f3 >= 180.0F) {
			f3 -= 360.0F;
		}

		return par1 + par3 * f3;
	}

	public void func_82399_a(EntityDynamite par1EntityDynamite, double par2,
			double par4, double par6, float par8, float par9) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);

		this.renderManager.renderEngine.func_110577_a(location);

		float f2 = this.func_82400_a(par1EntityDynamite.prevRotationYaw,
				par1EntityDynamite.rotationYaw, par9);
		float f3 = par1EntityDynamite.prevRotationPitch
				+ (par1EntityDynamite.rotationPitch - par1EntityDynamite.prevRotationPitch)
				* par9;
		GL11.glTranslatef((float) par2, (float) par4 - 0.6F, (float) par6);
		float f4 = 0.0625F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		GL11.glEnable(GL11.GL_ALPHA_TEST);

		this.skeletonHeadModel.render(par1EntityDynamite, 0.0F, 0.0F, 0.0F, f2,
				f3, f4);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {

		this.func_82399_a((EntityDynamite) par1Entity, par2, par4, par6, par8,
				par9);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {

		return location;
	}
}
