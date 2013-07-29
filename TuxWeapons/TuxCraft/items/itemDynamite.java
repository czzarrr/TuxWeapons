package TuxWeapons.TuxCraft.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.entity.EntityDynamite;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class itemDynamite extends Item {

	public itemDynamite(int par1) {

		super(par1);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public boolean isFull3D() {

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {

		world.playSoundAtEntity(player, "random.hurt", 1.0F,
				1.0F / (itemRand.nextFloat() * 0.1F + 0.95F));

		EntityDynamite var8 = new EntityDynamite(world, player);

		if (!world.isRemote)
			world.spawnEntityInWorld(var8);

		if (!player.capabilities.isCreativeMode)
			player.inventory.consumeInventoryItem(this.itemID);

		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(TuxWeaponsCore.modid + ":"
				+ "dynamite");
	}

}
