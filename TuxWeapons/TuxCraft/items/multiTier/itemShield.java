package TuxWeapons.TuxCraft.items.multiTier;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.weaponMaterials;

public class itemShield extends multiBase {

	private float health;

	public itemShield(int par1, weaponMaterials par1EnumWeaponMaterial, int par4) {

		super(par1, par1EnumWeaponMaterial);
		this.toolMaterial = par1EnumWeaponMaterial;
		this.maxStackSize = 1;
		this.textureName = "shield";
		this.setMaxDamage(par1EnumWeaponMaterial.getMaxUses() / 6);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	// =========Usage Code==========

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {

		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		health = player.func_110143_aJ();
		return stack;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity par3Entity,
			int par4, boolean par5) {
		
		if(!world.isRemote)
		{
	
			EntityPlayer player = (EntityPlayer) par3Entity;
			float healthA = 20;
			float par6;
			boolean par2 = false;
	
			if (par3Entity instanceof EntityPlayer && player.isBlocking() == true
					&& player.inventory.getCurrentItem().getItem() == this
					&& player.isBurning() == false
					&& player.capabilities.isCreativeMode == false) {
	
				healthA = player.func_110143_aJ();
	
				if (healthA != health) {
					par6 = health - healthA;
					stack.damageItem((int) par6, player);
	
					if (par6 >= health || par6 >= this.getMaxDamage()) {
						health = 0;
					}
	
					if (par6 == 1) {
						par2 = true;
					}
				}
	
				if (par2 == false && health != 0) {
					player.setEntityHealth(health);
				}
	
				else {
					par2 = false;
				}
	
			}
		}

	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world,
			EntityPlayer player, int par4) {

		health = 0;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {

		return EnumAction.block;
	}

}