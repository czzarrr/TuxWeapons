package TuxWeapons.TuxCraft;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import TuxWeapons.TuxCraft.potions.basePotion;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

	private ResourceLocation location = new ResourceLocation(
			TuxWeaponsCore.modidLowerCase, "textures/gui/statusEffects.png");
	
	Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		//onClientTick();

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		onClientTick();
	}
	
	private void onClientTick() {
		
		if(mc.thePlayer != null)
		{
			if(mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiContainerCreative)
			{
				ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		        int width = scaledresolution.getScaledWidth();
		        int height = scaledresolution.getScaledHeight();
				
		        int i;
		        int j;
		        
		        if(mc.thePlayer.capabilities.isCreativeMode)
		        {
		        	i = (width - 176) / 2 - 74;
		        	j = (height - 166) / 2 + 15;
		        }
		        
		        else
		        {
		        	i = ((width - 176) / 2) - 64;
		        	j = (height - 166) / 2;
		        }
		        boolean flag = true;
		        Collection collection = this.mc.thePlayer.getActivePotionEffects();
		
		        if (!collection.isEmpty())
		        {
		            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		            GL11.glDisable(GL11.GL_LIGHTING);
		            int k = 33;
		
		            if (collection.size() > 5)
		            {
		                k = 132 / (collection.size() - 1);
		            }
		
		            for (Iterator iterator = this.mc.thePlayer.getActivePotionEffects().iterator(); iterator.hasNext(); j += k)
		            {
		                PotionEffect potioneffect = (PotionEffect)iterator.next();
		                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
		                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		                this.mc.func_110434_K().func_110577_a(location);
		                //this.drawTexturedModalRect(i, j, 0, 166, 140, 32);
		
		                if (potion instanceof basePotion)
		                {
		                    int l = ((basePotion) potion).getTWStatusIconIndex();
		                    this.drawTexturedModalRect(i + 6, j + 7, 0 + l % 8 * 18, 198 + l / 8 * 18, 18, 18);
		                }
		            }
		        }
			}
		}
		
	}

	public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
	{
		float zLevel = 200.0F;
		
	    float f = 0.00390625F;
	    float f1 = 0.00390625F;
	    Tessellator tessellator = Tessellator.instance;
	    tessellator.startDrawingQuads();
	    tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
	    tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
	    tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
	    tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
	    tessellator.draw();
	}

	@Override
	public EnumSet<TickType> ticks() {
		
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		
		return null;
	}

}
