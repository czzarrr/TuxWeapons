package TuxWeapons.TuxCraft.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.entity.EntityFlameAura;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class flameAura extends basePotion {
	
	private int flameTicks;

	public flameAura(int par1, boolean par2, int par3, String iconFile) {
		super(par1, par2, par3);
		this.setEffectiveness(1D);
	}
	
	@Override
	public void performEffect(EntityLivingBase par1EntityLivingBase, int par2)
    {
		EntityFlameAura aura = new EntityFlameAura(par1EntityLivingBase.worldObj, par1EntityLivingBase);
		
		par1EntityLivingBase.worldObj.spawnEntityInWorld(aura);

		flameTicks++;

		if (flameTicks >= 100) {
			flameTicks = 0;
			TuxWeaponsCore.spawnParticles("flame", par1EntityLivingBase, 4, 0.1, 5);
		}
    }
	
	@Override
	public boolean isReady(int par1, int par2)
    {
		
		return true;
    }
}
