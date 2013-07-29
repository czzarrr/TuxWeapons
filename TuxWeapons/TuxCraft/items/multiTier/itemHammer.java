package TuxWeapons.TuxCraft.items.multiTier;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.weaponMaterials;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class itemHammer extends weaponBase {
	private int field_77289_h = 16;

	public itemHammer(int par1, weaponMaterials par1EnumWeaponMaterial) {

		super(par1, par1EnumWeaponMaterial);
		this.toolMaterial = par1EnumWeaponMaterial;
		this.durabilityModifier = 0.7;
		this.textureName = "hammer";
		this.weaponDamage = 4 + toolMaterial.damageVsEntity;

	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {

		return 1.5F;

	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world,
			EntityPlayer player, int par4) {

		int charge = (this.getMaxItemUseDuration(stack) - par4) / 2;
		int explosionSize = this.toolMaterial.damageVsEntity;

		if (explosionSize > 8) {
			explosionSize = 8;
		}

		if (charge > 1) {

			player.swingItem();
			stack.damageItem(explosionSize, player);

			double x = player.posX;
			double y = player.posY;
			double z = player.posZ;

			double d0;
			double d1;
			double d2;

			int i1 = MathHelper.floor_double(x - (double) explosionSize - 1.0D);
			int i2 = MathHelper.floor_double(y - (double) explosionSize - 1.0D);
			int i3 = MathHelper.floor_double(z - (double) explosionSize - 1.0D);
			int i4 = MathHelper.floor_double(x + (double) explosionSize + 1.0D);
			int i5 = MathHelper.floor_double(y + (double) explosionSize + 1.0D);
			int i6 = MathHelper.floor_double(z + (double) explosionSize + 1.0D);

			List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(
					player,
					AxisAlignedBB.getAABBPool().getAABB((double) i1,
							(double) i2, (double) i3, (double) i4, (double) i5,
							(double) i6));

			Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(x, y, z);

			for (int k2 = 0; k2 < list.size(); ++k2) {
				Entity entity = (Entity) list.get(k2);
				double d7 = entity.getDistance(x, y, z)
						/ (double) explosionSize;

				if (d7 <= 1.0D) {
					d0 = entity.posX - x;
					d1 = entity.posY + (double) entity.getEyeHeight() - y;
					d2 = entity.posZ - z;
					double d8 = (double) MathHelper.sqrt_double(d0 * d0 + d1
							* d1 + d2 * d2);

					if (d8 != 0.0D) {
						d0 /= d8;
						d1 /= d8;
						d2 /= d8;
						double d9 = (double) world.getBlockDensity(vec3,
								entity.boundingBox);
						double d10 = (1.0D - d7) * d9;
						if (entity instanceof EntityLiving) {
							entity.attackEntityFrom(
									DamageSource.causePlayerDamage(player),
									this.toolMaterial.damageVsEntity);
						}
						entity.addVelocity(0.0, 0.2, 0.0);
						double d11 = EnchantmentProtection.func_92092_a(entity,
								d10);
						entity.motionX += d0 * d11;
						entity.motionY += d1 * d11;
						entity.motionZ += d2 * d11;
					}
				}
			}

			TuxWeaponsCore.spawnParticlesOnLevel("tilecrack_1_0", player,
					explosionSize, -1, explosionSize * 10);
		}
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		return par1ItemStack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {

		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {

		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

		return stack;
	}

}
