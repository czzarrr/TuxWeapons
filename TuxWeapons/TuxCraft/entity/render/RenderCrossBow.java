package TuxWeapons.TuxCraft.entity.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderCrossBow implements IItemRenderer {

	private Minecraft mc;
	private static final ResourceLocation field_110930_b = new ResourceLocation(
			"textures/misc/enchanted_item_glint.png");

	public RenderCrossBow() {
		this.mc = Minecraft.getMinecraft();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {

		switch (type) {
		case EQUIPPED:
			return true;
		case EQUIPPED_FIRST_PERSON:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {

		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {

		switch (type) {
		case EQUIPPED: {
			EntityLivingBase player = (EntityLivingBase) data[1];

			if ((player == mc.renderViewEntity)
					&& (mc.gameSettings.thirdPersonView == 1)
					|| (player == mc.renderViewEntity)
					&& (mc.gameSettings.thirdPersonView == 2)) {

				GL11.glPushMatrix();
				TextureManager texturemanager = this.mc.func_110434_K();

				Icon icon = player.getItemIcon(stack, 0);

				if (icon == null) {
					GL11.glPopMatrix();
					return;
				}

				texturemanager.func_110577_a(texturemanager.func_130087_a(stack
						.getItemSpriteNumber()));
				Tessellator tessellator = Tessellator.instance;
				float f = icon.getMinU();
				float f1 = icon.getMaxU();
				float f2 = icon.getMinV();
				float f3 = icon.getMaxV();
				float f4 = 0.0F;
				float f5 = -0.1F;
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				GL11.glTranslatef(-f4, f5, 0.0F);
				float f6 = 1.0F;
				GL11.glScalef(f6, f6, f6);
				GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);

				renderItemIn2D(tessellator, f1, f2, f, f3, icon.getOriginX(),
						icon.getOriginY(), 0.0625F);

				if (stack.hasEffect(0)) {
					GL11.glDepthFunc(GL11.GL_EQUAL);
					GL11.glDisable(GL11.GL_LIGHTING);
					texturemanager.func_110577_a(field_110930_b);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
					float f7 = 0.76F;
					GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
					GL11.glMatrixMode(GL11.GL_TEXTURE);
					GL11.glPushMatrix();
					float f8 = 0.125F;
					GL11.glScalef(f8, f8, f8);
					float f9 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
					GL11.glTranslatef(f9, 0.0F, 0.0F);
					GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
					renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256,
							256, 0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					GL11.glScalef(f8, f8, f8);
					f9 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
					GL11.glTranslatef(-f9, 0.0F, 0.0F);
					GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
					renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256,
							256, 0.0625F);
					GL11.glPopMatrix();
					GL11.glMatrixMode(GL11.GL_MODELVIEW);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glDepthFunc(GL11.GL_LEQUAL);
				}

				GL11.glDisable(GL12.GL_RESCALE_NORMAL);

				GL11.glPopMatrix();
			}
		}

		case EQUIPPED_FIRST_PERSON: {
			EntityLivingBase player = (EntityLivingBase) data[1];

			if ((player == mc.renderViewEntity)
					&& (mc.gameSettings.thirdPersonView == 0)) {

				GL11.glPushMatrix();
				TextureManager texturemanager = this.mc.func_110434_K();

				Icon icon = player.getItemIcon(stack, 0);

				if (icon == null) {
					GL11.glPopMatrix();
					return;
				}

				texturemanager.func_110577_a(texturemanager.func_130087_a(stack
						.getItemSpriteNumber()));
				Tessellator tessellator = Tessellator.instance;
				float f = icon.getMinU();
				float f1 = icon.getMaxU();
				float f2 = icon.getMinV();
				float f3 = icon.getMaxV();
				float f4 = 0.0F;
				float f5 = -0.1F;
				GL11.glEnable(GL12.GL_RESCALE_NORMAL);
				GL11.glTranslatef(-f4, f5, 0.0F);
				float f6 = 1.0F;
				GL11.glScalef(f6, f6, f6);
				GL11.glRotatef(35.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);

				if (!((mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiContainerCreative))) {
					GL11.glTranslatef(0.0F, 0.2F, 0.0F);

					if (((EntityPlayer) player).getItemInUseCount() > 0) {
						GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
						GL11.glRotatef(-8.0F, 1.0F, 0.0F, 0.0F);
					}
				}

				renderItemIn2D(tessellator, f1, f2, f, f3, icon.getOriginX(),
						icon.getOriginY(), 0.0625F);

				if (stack.hasEffect(0)) {
					GL11.glDepthFunc(GL11.GL_EQUAL);
					GL11.glDisable(GL11.GL_LIGHTING);
					texturemanager.func_110577_a(field_110930_b);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
					float f7 = 0.76F;
					GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
					GL11.glMatrixMode(GL11.GL_TEXTURE);
					GL11.glPushMatrix();
					float f8 = 0.125F;
					GL11.glScalef(f8, f8, f8);
					float f9 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
					GL11.glTranslatef(f9, 0.0F, 0.0F);
					GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
					renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256,
							256, 0.0625F);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					GL11.glScalef(f8, f8, f8);
					f9 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
					GL11.glTranslatef(-f9, 0.0F, 0.0F);
					GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
					renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256,
							256, 0.0625F);
					GL11.glPopMatrix();
					GL11.glMatrixMode(GL11.GL_MODELVIEW);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glDepthFunc(GL11.GL_LEQUAL);
				}

				GL11.glDisable(GL12.GL_RESCALE_NORMAL);

				GL11.glPopMatrix();
			}
		}

		default:
			break;
		}
	}

	public static void renderItemIn2D(Tessellator par0Tessellator, float par1,
			float par2, float par3, float par4, int par5, int par6, float par7) {
		par0Tessellator.startDrawingQuads();
		par0Tessellator.setNormal(0.0F, 0.0F, 1.0F);
		par0Tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double) par1,
				(double) par4);
		par0Tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, (double) par3,
				(double) par4);
		par0Tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, (double) par3,
				(double) par2);
		par0Tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, (double) par1,
				(double) par2);
		par0Tessellator.draw();
		par0Tessellator.startDrawingQuads();
		par0Tessellator.setNormal(0.0F, 0.0F, -1.0F);
		par0Tessellator.addVertexWithUV(0.0D, 1.0D, (double) (0.0F - par7),
				(double) par1, (double) par2);
		par0Tessellator.addVertexWithUV(1.0D, 1.0D, (double) (0.0F - par7),
				(double) par3, (double) par2);
		par0Tessellator.addVertexWithUV(1.0D, 0.0D, (double) (0.0F - par7),
				(double) par3, (double) par4);
		par0Tessellator.addVertexWithUV(0.0D, 0.0D, (double) (0.0F - par7),
				(double) par1, (double) par4);
		par0Tessellator.draw();
		float f5 = 0.5F * (par1 - par3) / (float) par5;
		float f6 = 0.5F * (par4 - par2) / (float) par6;
		par0Tessellator.startDrawingQuads();
		par0Tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		int k;
		float f7;
		float f8;

		for (k = 0; k < par5; ++k) {
			f7 = (float) k / (float) par5;
			f8 = par1 + (par3 - par1) * f7 - f5;
			par0Tessellator.addVertexWithUV((double) f7, 0.0D,
					(double) (0.0F - par7), (double) f8, (double) par4);
			par0Tessellator.addVertexWithUV((double) f7, 0.0D, 0.0D,
					(double) f8, (double) par4);
			par0Tessellator.addVertexWithUV((double) f7, 1.0D, 0.0D,
					(double) f8, (double) par2);
			par0Tessellator.addVertexWithUV((double) f7, 1.0D,
					(double) (0.0F - par7), (double) f8, (double) par2);
		}

		par0Tessellator.draw();
		par0Tessellator.startDrawingQuads();
		par0Tessellator.setNormal(1.0F, 0.0F, 0.0F);
		float f9;

		for (k = 0; k < par5; ++k) {
			f7 = (float) k / (float) par5;
			f8 = par1 + (par3 - par1) * f7 - f5;
			f9 = f7 + 1.0F / (float) par5;
			par0Tessellator.addVertexWithUV((double) f9, 1.0D,
					(double) (0.0F - par7), (double) f8, (double) par2);
			par0Tessellator.addVertexWithUV((double) f9, 1.0D, 0.0D,
					(double) f8, (double) par2);
			par0Tessellator.addVertexWithUV((double) f9, 0.0D, 0.0D,
					(double) f8, (double) par4);
			par0Tessellator.addVertexWithUV((double) f9, 0.0D,
					(double) (0.0F - par7), (double) f8, (double) par4);
		}

		par0Tessellator.draw();
		par0Tessellator.startDrawingQuads();
		par0Tessellator.setNormal(0.0F, 1.0F, 0.0F);

		for (k = 0; k < par6; ++k) {
			f7 = (float) k / (float) par6;
			f8 = par4 + (par2 - par4) * f7 - f6;
			f9 = f7 + 1.0F / (float) par6;
			par0Tessellator.addVertexWithUV(0.0D, (double) f9, 0.0D,
					(double) par1, (double) f8);
			par0Tessellator.addVertexWithUV(1.0D, (double) f9, 0.0D,
					(double) par3, (double) f8);
			par0Tessellator.addVertexWithUV(1.0D, (double) f9,
					(double) (0.0F - par7), (double) par3, (double) f8);
			par0Tessellator.addVertexWithUV(0.0D, (double) f9,
					(double) (0.0F - par7), (double) par1, (double) f8);
		}

		par0Tessellator.draw();
		par0Tessellator.startDrawingQuads();
		par0Tessellator.setNormal(0.0F, -1.0F, 0.0F);

		for (k = 0; k < par6; ++k) {
			f7 = (float) k / (float) par6;
			f8 = par4 + (par2 - par4) * f7 - f6;
			par0Tessellator.addVertexWithUV(1.0D, (double) f7, 0.0D,
					(double) par3, (double) f8);
			par0Tessellator.addVertexWithUV(0.0D, (double) f7, 0.0D,
					(double) par1, (double) f8);
			par0Tessellator.addVertexWithUV(0.0D, (double) f7,
					(double) (0.0F - par7), (double) par1, (double) f8);
			par0Tessellator.addVertexWithUV(1.0D, (double) f7,
					(double) (0.0F - par7), (double) par3, (double) f8);
		}

		par0Tessellator.draw();
	}

}
