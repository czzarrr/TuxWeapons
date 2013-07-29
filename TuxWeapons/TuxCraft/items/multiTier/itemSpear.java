package TuxWeapons.TuxCraft.items.multiTier;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import TuxWeapons.TuxCraft.weaponMaterials;
import TuxWeapons.TuxCraft.entity.EntityInvSpear;
import TuxWeapons.TuxCraft.entity.EntitySpear;
import TuxWeapons.TuxCraft.events.SpearEvent;

public class itemSpear extends weaponBase {

	static ItemStack itemstack;

	public itemSpear(int par1, weaponMaterials par1EnumWeaponMaterial) {

		super(par1, par1EnumWeaponMaterial);
		this.toolMaterial = par1EnumWeaponMaterial;
		this.durabilityModifier = 0.5;
		this.textureName = "spear";
		this.weaponDamage = 2 + toolMaterial.damageVsEntity;

	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block par2Block) {

		if (par2Block.blockID == Block.web.blockID) {
			return 15.0F;
		}

		else {
			Material material = par2Block.blockMaterial;
			return material != Material.plants && material != Material.vine
					&& material != Material.coral
					&& material != Material.leaves
					&& material != Material.pumpkin ? 1.0F : 1.5F;
		}

	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {

		return EnumAction.bow;
	}

	@Override
	public boolean canHarvestBlock(Block par1Block) {

		return par1Block.blockID == Block.web.blockID;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world,
			EntityPlayer player, int par4) {

		stack.damageItem(2, player);

		itemstack = ItemStack.copyItemStack(stack);

		int var6 = this.getMaxItemUseDuration(stack) - par4;

		SpearEvent event = new SpearEvent(player, stack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			return;
		}
		var6 = event.charge;

		boolean var5 = player.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(
						Enchantment.infinity.effectId, stack) > 0;

		if (var5 || player.inventory.hasItem(this.itemID)) {

			float var7 = var6 / 30.0F;

			if (var7 < 0.1D) {
				return;
			}

			if (var7 > 0.8F) {
				var7 = 0.8F;
			}

			EntitySpear var8 = new EntitySpear(world, player, var7, itemstack,
					(int) this.weaponDamage + 2);

			if (var7 == 1.0F) {
				var8.setIsCritical(true);
			}

			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F
					/ (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

			if (!player.capabilities.isCreativeMode)
				player.inventory.consumeInventoryItem(this.itemID);

			world.spawnEntityInWorld(var8);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {

		player.inventory.getCurrentItem();

		if (player.capabilities.isCreativeMode
				|| player.inventory.hasItem(this.itemID)) {
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		}

		return stack;
	}

	public boolean onEntitySwing(EntityLivingBase player, ItemStack stack) {
		EntityInvSpear var8 = new EntityInvSpear(player.worldObj,
				(EntityPlayer) player, this.weaponDamage);

		player.worldObj.spawnEntityInWorld(var8);

		return false;
	}

}
