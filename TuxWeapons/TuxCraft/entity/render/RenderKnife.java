package TuxWeapons.TuxCraft.entity.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import TuxWeapons.TuxCraft.entity.EntityKnife;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKnife extends Render {

	private static final ResourceLocation field_110798_h = new ResourceLocation(
			"textures/misc/enchanted_item_glint.png");

	public void renderhook(EntityKnife hook, double par2, double par4,
			double par6, float par8, float par9) {

		ItemStack stack = hook.getStack(hook);

		if (stack != null) {
			GL11.glPushMatrix();

			this.renderManager.renderEngine.func_130087_a(hook.getStack(hook)
					.getItemSpriteNumber());

			float f8;
			int i;
			float f5;
			float f4;
			float f6;

			GL11.glTranslatef((float) par2, (float) par4, (float) par6);

			float scale = 1.5F;
			GL11.glScalef(scale, scale, scale);

			GL11.glRotatef(hook.prevRotationYaw
					+ (hook.rotationYaw - hook.prevRotationYaw) * par9 - 90.0F,
					0.0F, 1.0F, 0.0F);
			GL11.glRotatef(
					(hook.prevRotationPitch
							+ (hook.rotationPitch - hook.prevRotationPitch)
							* par9 + (20 * hook.spin)) - 135, 0.0F, 0.0F, 1.0F);

			GL11.glTranslatef(-0.2F, 0.0F, 0.0F);

			for (int k = 0; k < stack.getItem().getRenderPasses(
					stack.getItemDamage()); ++k) {
				Icon icon = stack.getItem().getIcon(stack, k);
				f8 = 1.0F;

				i = Item.itemsList[stack.itemID]
						.getColorFromItemStack(stack, k);
				f5 = (float) (i >> 16 & 255) / 255.0F;
				f4 = (float) (i >> 8 & 255) / 255.0F;
				f6 = (float) (i & 255) / 255.0F;
				GL11.glColor4f(f5 * f8, f4 * f8, f6 * f8, 1.0F);
				this.renderDroppedItem(hook, icon, 1, par9, f5 * f8, f4 * f8,
						f6 * f8, k);
			}

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	private void renderDroppedItem(EntityKnife hook, Icon par2Icon, int par3,
			float par4, float par5, float par6, float par7, int pass) {
		Tessellator tessellator = Tessellator.instance;

		if (par2Icon == null) {
			TextureManager texturemanager = Minecraft.getMinecraft()
					.func_110434_K();
			ResourceLocation resourcelocation = texturemanager
					.func_130087_a(hook.getStack(hook).getItemSpriteNumber());
			par2Icon = ((TextureMap) texturemanager
					.func_110581_b(resourcelocation))
					.func_110572_b("missingno");
		}

		float f4 = ((Icon) par2Icon).getMinU();
		float f5 = ((Icon) par2Icon).getMaxU();
		float f6 = ((Icon) par2Icon).getMinV();
		float f7 = ((Icon) par2Icon).getMaxV();
		float f8 = 1.0F;
		float f9 = 0.5F;
		float f10 = 0.25F;
		float f11;

		GL11.glPushMatrix();

		float f12 = 0.0625F;
		f11 = 0.021875F;
		ItemStack itemstack = hook.getStack(hook);
		int j = 1;
		byte b0 = 1;

		GL11.glTranslatef(-f9, -f10, -((f12 + f11) * (float) b0 / 2.0F));

		for (int k = 0; k < b0; ++k) {
			this.func_110776_a(TextureMap.field_110576_c);

			GL11.glColor4f(par5, par6, par7, 1.0F);
			ItemRenderer.renderItemIn2D(tessellator, f5, f6, f4, f7,
					((Icon) par2Icon).getOriginX(),
					((Icon) par2Icon).getOriginY(), f12);

			if (itemstack.hasEffect(pass)) {
				GL11.glDepthFunc(GL11.GL_EQUAL);
				GL11.glDisable(GL11.GL_LIGHTING);
				this.renderManager.renderEngine.func_110577_a(field_110798_h);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
				float f13 = 0.76F;
				GL11.glColor4f(0.5F * f13, 0.25F * f13, 0.8F * f13, 1.0F);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glPushMatrix();
				float f14 = 0.125F;
				GL11.glScalef(f14, f14, f14);
				float f15 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
				GL11.glTranslatef(f15, 0.0F, 0.0F);
				GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F,
						1.0F, 255, 255, f12);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(f14, f14, f14);
				f15 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
				GL11.glTranslatef(-f15, 0.0F, 0.0F);
				GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F,
						1.0F, 255, 255, f12);
				GL11.glPopMatrix();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDepthFunc(GL11.GL_LEQUAL);
			}
		}

		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		if (par1Entity.worldObj.isRemote)
			this.renderhook((EntityKnife) par1Entity, par2, par4, par6, par8,
					par9);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		EntityKnife hook = (EntityKnife) entity;
		return this.renderManager.renderEngine.func_130087_a(hook
				.getStack(hook).getItemSpriteNumber());
	}
}
