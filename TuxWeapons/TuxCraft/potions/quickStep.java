package TuxWeapons.TuxCraft.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.entity.EntityFlameAura;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

public class quickStep extends basePotion {

	public quickStep(int par1, boolean par2, int par3, String iconFile) {
		super(par1, par2, par3);
		this.setEffectiveness(2D);
	}
	
	@Override
	public void performEffect(EntityLivingBase par1EntityLivingBase, int par2)
    {
		if (par1EntityLivingBase.isCollidedVertically)

		if (par1EntityLivingBase.isCollidedVertically && par1EntityLivingBase.isCollidedHorizontally) {

			par1EntityLivingBase.addVelocity(
					0.0F,
					0.5F,
					0.0F);

		}
    }
	
	@Override
	public boolean isReady(int par1, int par2)
    {
		
		return true;
    }
}
