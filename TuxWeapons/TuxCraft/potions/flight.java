package TuxWeapons.TuxCraft.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

public class flight extends basePotion {

	private int ticksInFlight;
	
	public flight(int par1, boolean par2, int par3, String iconFile) {
		super(par1, par2, par3);
		this.setEffectiveness(1D);
	}
	
	@Override
	public void performEffect(EntityLivingBase par1EntityLivingBase, int par2)
    {
		
		if(par1EntityLivingBase instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1EntityLivingBase;
			GameSettings settings = Minecraft.getMinecraft().gameSettings;
			
			if (player.capabilities.isCreativeMode == false) {
				
				if (settings.keyBindJump.pressed) {
					player.fallDistance = 0.0F;
					this.ticksInFlight++;
				}
	
				else {
					this.ticksInFlight = 0;
				}
	
			}
	
			if (this.ticksInFlight > 2) {
				
				player.fallDistance = 0.0F;
				
				float someFloat = (float) ((this.ticksInFlight > 20) ? 20 * 20
						: this.ticksInFlight * this.ticksInFlight) / (55 * 55);
				
				par1EntityLivingBase.addVelocity(
						0.0F,
						someFloat,
						0.0F);
				
			}
		}
		
		else
		{
			par1EntityLivingBase.addVelocity(0.0F, 0.09F, 0.0F);
		}
    }
	
	@Override
	public boolean isReady(int par1, int par2)
    {
		
		return true;
    }
	
	public void drawString(FontRenderer par1FontRenderer, String par2Str, int par3, int par4, int par5)
    {
        par1FontRenderer.drawStringWithShadow(par2Str, par3, par4, par5);
    }
}

