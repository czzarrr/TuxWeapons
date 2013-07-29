package TuxWeapons.TuxCraft.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.entity.EntityEMPGrenade;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class itemEMPGrenade extends Item {

	public itemEMPGrenade(int par1) {

		super(par1);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {

		EntityEMPGrenade var8 = new EntityEMPGrenade(world, player);
		if (!world.isRemote)
			world.spawnEntityInWorld(var8);
		player.inventory.consumeInventoryItem(this.itemID);

		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(TuxWeaponsCore.modid + ":"
				+ "redstoneEMP");
	}

}