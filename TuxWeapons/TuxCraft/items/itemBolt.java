package TuxWeapons.TuxCraft.items;

import TuxWeapons.TuxCraft.TuxWeaponsCore;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class itemBolt extends Item {

	private int type;

	public itemBolt(int par1, int par2) {

		super(par1);
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.type = par2;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		par3EntityPlayer.setItemInUse(par1ItemStack,
				this.getMaxItemUseDuration(par1ItemStack));

		return par1ItemStack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {

		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {

		return 72000;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		return par1ItemStack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, int par4) {

		InventoryPlayer.getHotbarSize();
		Item item;
		ItemStack stack;

		for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {

			if (par3EntityPlayer.inventory.getStackInSlot(i) != null) {
				item = par3EntityPlayer.inventory.getStackInSlot(i).getItem();

				if (item == TuxWeaponsCore.crossBow) {
					stack = par3EntityPlayer.inventory.getStackInSlot(i);

					NBTTagCompound par1NBTTagCompound = stack.getTagCompound();

					if (par1NBTTagCompound == null) {
						itemCrossBow.setNBT(false, stack);
						par3EntityPlayer.inventory
								.consumeInventoryItem(this.itemID);
						itemCrossBow.load(
								par3EntityPlayer.inventory.getStackInSlot(i),
								this.type);
					}

					if (par1NBTTagCompound != null) {
						if (par1NBTTagCompound.getBoolean("Loaded") == false) {
							par3EntityPlayer.inventory
									.consumeInventoryItem(this.itemID);
							itemCrossBow.load(par3EntityPlayer.inventory
									.getStackInSlot(i), this.type);
							return;
						}
					}
				}

			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		if (this.type == 0) {
			itemIcon = iconRegister.registerIcon(TuxWeaponsCore.modid + ":"
					+ "bolt");
		}

		if (this.type == 1) {
			itemIcon = iconRegister.registerIcon(TuxWeaponsCore.modid + ":"
					+ "poisonBolt");
		}
	}

}
