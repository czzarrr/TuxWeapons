package TuxWeapons.TuxCraft.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.entity.EntityBolt;
import TuxWeapons.TuxCraft.events.CrossBEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class itemCrossBow extends ItemBow {

	public itemCrossBow(int par1) {

		super(par1);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxDamage(100);
	}

	// ==========RENDER CODE==========

	@SideOnly(Side.CLIENT)
	private Icon[] Texture = new Icon[5];

	@Override
	public void registerIcons(IconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(TuxWeaponsCore.modid + ":"
				+ "crossBow_pull_0");

		for (int N = 0; N < 5; N++) {
			this.Texture[N] = iconRegister.registerIcon(TuxWeaponsCore.modid
					+ ":" + "crossBow_pull_" + N);

		}

	}

	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player,
			ItemStack usingItem, int useRemaining) {

		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
			stack.stackTagCompound.setBoolean("Loaded", false);
		}

		NBTTagCompound par1NBTTagCompound = stack.getTagCompound();

		if (player.getItemInUse() == null
				&& par1NBTTagCompound.getBoolean("Loaded") == false) {
			return this.itemIcon;
		}
		if (player.getItemInUse() == null
				&& par1NBTTagCompound.getBoolean("Loaded") == true) {
			return Texture[1];
		}

		int Pulling = stack.getMaxItemUseDuration() - useRemaining;

		if (Pulling >= 24) {
			return Texture[4];
		} else if (Pulling > 19) {
			return Texture[3];
		} else if (Pulling > 6) {
			return Texture[2];
		}

		if (par1NBTTagCompound.getBoolean("Loaded") == true) {
			return Texture[1];
		}

		else {
			return Texture[0];
		}
	}

	// =========USAGE CODE===========

	/**
	 * called when the player releases the use item button. Args: itemstack,
	 * world, entityplayer, itemInUseCount
	 */
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world,
			EntityPlayer player, int par4) {

		int var6 = this.getMaxItemUseDuration(stack) - par4;

		CrossBEvent event = new CrossBEvent(player, stack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			return;
		}
		var6 = event.charge;

		boolean var5 = player.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(
						Enchantment.infinity.effectId, stack) > 0;

		if (var5 || player.inventory.hasItem(this.itemID)) {
			// System.out.println(String.valueOf(var6));
			float var7;

			if (var6 >= 27) {
				var7 = 200;
			}

			else {
				var7 = 0.1F;
			}

			NBTTagCompound par1NBTTagCompound = stack.getTagCompound();

			EntityBolt var8 = new EntityBolt(world, player, var7 * 2.0F,
					par1NBTTagCompound.getInteger("type"));

			EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId,
					stack);

			int var10 = EnchantmentHelper.getEnchantmentLevel(
					Enchantment.punch.effectId, stack);

			if (var10 > 0) {
				var8.setKnockbackStrength(var10);
			}

			if (EnchantmentHelper.getEnchantmentLevel(
					Enchantment.flame.effectId, stack) > 0) {
				var8.setFire(100);
				var8.hasFire = true;
			}

			stack.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F
					/ (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

			world.spawnEntityInWorld(var8);
			stack.stackTagCompound.setBoolean("Loaded", false);
			stack.damageItem(1, player);
		}
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {

		return stack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {

		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {

		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {

		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
			stack.stackTagCompound.setBoolean("Loaded", false);
		}

		NBTTagCompound par1NBTTagCompound = stack.getTagCompound();

		if (par1NBTTagCompound.getBoolean("Loaded") == true
				|| player.capabilities.isCreativeMode) {
			ArrowNockEvent event = new ArrowNockEvent(player, stack);
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled()) {
				return event.result;
			}

			int legacyLvl = EnchantmentHelper.getEnchantmentLevel(
					TuxWeaponsCore.enchLegacy.effectId,
					player.getCurrentEquippedItem());

			if (legacyLvl > 0) {
				player.setItemInUse(stack, this.getMaxItemUseDuration(stack)
						- legacyLvl * 3);
			}

			else {
				player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
			}

		}

		return stack;
	}

	@Override
	public int getItemEnchantability() {

		return 1;
	}

	public static void load(ItemStack stack, int type) {

		NBTTagCompound par1NBTTagCompound = stack.getTagCompound();

		if (par1NBTTagCompound.getBoolean("Loaded") == false) {
			par1NBTTagCompound.setBoolean("Loaded", true);
		}

		par1NBTTagCompound.setInteger("type", type);

	}

	public static void setNBT(Boolean b, ItemStack stack) {

		stack.setTagCompound(new NBTTagCompound());
		stack.stackTagCompound.setBoolean("Loaded", false);
	}

	@Override
	public boolean isFull3D() {

		return true;
	}
}
