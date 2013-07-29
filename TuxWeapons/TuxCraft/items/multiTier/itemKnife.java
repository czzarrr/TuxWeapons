package TuxWeapons.TuxCraft.items.multiTier;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.weaponMaterials;
import TuxWeapons.TuxCraft.entity.EntityKnife;

public class itemKnife extends multiBase {

	public itemKnife(int par1, weaponMaterials par1EnumWeaponMaterial, int par4) {

		super(par1, par1EnumWeaponMaterial);
		this.maxStackSize = 64;
		this.setMaxDamage(par1EnumWeaponMaterial.getMaxUses());
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.textureName = "knife";
	}

	// =========Usage Code==========

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		Item item = par3EntityPlayer.inventory.getCurrentItem().getItem();
		par2World.spawnEntityInWorld(new EntityKnife(par2World,
				par3EntityPlayer, 1.2F, item, this.toolMaterial.damageVsEntity,
				this.toolMaterial));
		if(!par3EntityPlayer.capabilities.isCreativeMode)par3EntityPlayer.inventory.consumeInventoryItem(this.itemID);

		return par1ItemStack;
	}

}