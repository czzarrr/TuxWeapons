package TuxWeapons.TuxCraft.entity.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.entity.EntityGrappHook;
import TuxWeapons.TuxCraft.entity.models.ModelFireChargeCannon;

public class RenderFireChargeCannon implements IItemRenderer {

	ModelFireChargeCannon cannonModel;
	private ResourceLocation location = new ResourceLocation(
			TuxWeaponsCore.modidLowerCase, "textures/models/FireChargeCannon.png");
	private Minecraft mc;

	public RenderFireChargeCannon() {
		this.mc = Minecraft.getMinecraft();
		cannonModel = new ModelFireChargeCannon();
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
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch (type) {
		case EQUIPPED: {
			
			EntityLivingBase player = (EntityLivingBase) data[1];
			
			if ((player == mc.renderViewEntity)
					&& (mc.gameSettings.thirdPersonView == 1)
					|| (player == mc.renderViewEntity)
					&& (mc.gameSettings.thirdPersonView == 2)) {
			
			GL11.glPushMatrix();

			// helper.renderEngine.func_110577_a(location);

			float scale = 1.2F;
			GL11.glScalef(scale, scale, scale);

			this.mc.func_110434_K().func_110577_a(location);
			
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(140.0F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0.15F, -0.12F, 0.05F);

			if (data[1] != null && data[1] instanceof EntityPlayer) {
				if (!((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity
						&& Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft
						.getMinecraft().currentScreen instanceof GuiInventory || Minecraft
						.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F))) {
					// GL11.glTranslatef(-0.2F, -0.17F, 0.0F);
				} else {
					float scale2 = 0.7F;
					GL11.glScalef(scale2, scale2, scale2);

					// GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
					// GL11.glRotatef(-82.0F, 0.0F, 1.0F, 0.0F);
					// GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);

					GL11.glTranslatef(-0.2F, 0.0F, 0.0F);
				}
			}

			else {
				// GL11.glTranslatef(-0.2F, -0.17F, 0.0F);
			}

			// GL11.glTranslatef(0.0F, -0.17F, 0.0F);

			cannonModel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
					0.0625F);

			GL11.glPopMatrix();
			}
		}
		
		case EQUIPPED_FIRST_PERSON: {
			
			EntityLivingBase player = (EntityLivingBase) data[1];
			
			if ((player == mc.renderViewEntity)
					&& (mc.gameSettings.thirdPersonView == 0)) {
				
				GL11.glPushMatrix();

				// helper.renderEngine.func_110577_a(location);

				float scale = 1.2F;
				GL11.glScalef(scale, scale, scale);

				this.mc.func_110434_K().func_110577_a(location);
				
				GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(140.0F, 1.0F, 0.0F, 0.0F);
				GL11.glTranslatef(0.15F, -0.12F, 0.05F);

				if (data[1] != null && data[1] instanceof EntityPlayer) {
					if (!((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity
							&& Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft
							.getMinecraft().currentScreen instanceof GuiInventory || Minecraft
							.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F))) {
						// GL11.glTranslatef(-0.2F, -0.17F, 0.0F);
					} else {
						float scale2 = 0.7F;
						GL11.glScalef(scale2, scale2, scale2);

						// GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
						// GL11.glRotatef(-82.0F, 0.0F, 1.0F, 0.0F);
						// GL11.glRotatef(8.0F, 0.0F, 0.0F, 1.0F);

						GL11.glTranslatef(-0.2F, 0.0F, 0.0F);
					}
				}

				else {
					// GL11.glTranslatef(-0.2F, -0.17F, 0.0F);
				}

				// GL11.glTranslatef(0.0F, -0.17F, 0.0F);

				cannonModel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F,
						0.0625F);

				GL11.glPopMatrix();
			
			}
		}

		default:
			break;
		}
	}

}
