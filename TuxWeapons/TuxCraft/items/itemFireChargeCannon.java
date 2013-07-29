package TuxWeapons.TuxCraft.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.entity.EntityTuxFireBall;
import TuxWeapons.TuxCraft.events.FireChargeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class itemFireChargeCannon extends Item {

	public itemFireChargeCannon(int par1) {

		super(par1);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxDamage(200);
		this.maxStackSize = 1;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {

		return EnumAction.bow;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(TuxWeaponsCore.modid + ":"
				+ "fireChargeCannon");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {

		if (player.inventory.hasItem(Item.fireballCharge.itemID)
				|| player.capabilities.isCreativeMode == true) {
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		}

		return stack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {

		return 72000;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world,
			EntityPlayer player, int par4) {

		int var6 = this.getMaxItemUseDuration(stack) - par4;

		FireChargeEvent event = new FireChargeEvent(player, stack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			return;
		}
		var6 = event.charge;

		EntityTuxFireBall var8 = new EntityTuxFireBall(world, player, var6);

		if (var6 >= 20) {
			if (!world.isRemote) {
				world.spawnEntityInWorld(var8);
			}

			stack.damageItem(1, player);
			if (player.capabilities.isCreativeMode == false) {
				player.inventory
						.consumeInventoryItem(Item.fireballCharge.itemID);
			}
		}
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity par3Entity,
			int par4, boolean par5) {

		EntityPlayer player = (EntityPlayer) par3Entity;

		if (par3Entity instanceof EntityPlayer) {
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().getItem() == this) {
				player.motionX *= 0.5D;
				player.motionZ *= 0.5D;
			}

		}
	}

}
