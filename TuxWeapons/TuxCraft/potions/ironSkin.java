package TuxWeapons.TuxCraft.potions;

import TuxWeapons.TuxCraft.entity.EntityFlameAura;
import TuxWeapons.TuxCraft.entity.EntityTemp;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ironSkin extends Potion {

	public ironSkin(int par1, boolean par2, int par3, String iconFile) {
		super(par1, par2, par3);
		this.setEffectiveness(0.001D);
	}
	
	public boolean isReady(int par1, int par2)
    {
        return true;
    }
	
	@Override
	public void performEffect(EntityLivingBase par1EntityLivingBase, int par2)
    {
		potionEffects(par1EntityLivingBase);
    }

	@Override
	public void affectEntity(EntityLivingBase par1EntityLivingBase,
			EntityLivingBase par2EntityLivingBase, int par3, double par4) {
		
		potionEffects(par2EntityLivingBase);
	}

	public boolean isInstant() {
		return true;
	}
	
	private void potionEffects(EntityLivingBase entity)
	{
				
		EntityTemp temp = new EntityTemp(entity.worldObj, entity, 0);
		entity.worldObj.spawnEntityInWorld(temp);
	}

}
