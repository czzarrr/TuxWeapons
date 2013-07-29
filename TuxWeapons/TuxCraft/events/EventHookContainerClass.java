package TuxWeapons.TuxCraft.events;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.config;
import TuxWeapons.TuxCraft.entity.EntityEagleArrow;
import TuxWeapons.TuxCraft.items.multiTier.itemMace;

public class EventHookContainerClass {

	public static double rand;
	
	@ForgeSubscribe
	public void entityKilled(LivingDeathEvent event) {

		if (Arrays.asList(config.disabledWeapons).contains("fireChargeCannon") == false) {
			if (event.source.getDamageType().equals("player")) {
				rand = Math.random();
				if (event.entityLiving instanceof EntityMagmaCube) {

					if (rand < config.magmaCoreRarity) {

						event.entityLiving.dropItem(
								TuxWeaponsCore.fireChargeCore.itemID, 1);

					}
				}
			}
		}

		if (Arrays.asList(config.disabledWeapons).contains("potions") == false) {
			if (event.source.getDamageType().equals("player")) {
				rand = Math.random();

				if (event.entityLiving instanceof EntityGhast) {

					if (rand < config.catalystRarity * 5) {

						event.entityLiving.dropItem(
								TuxWeaponsCore.flightCatalyst.itemID, 1);

					}
				}

				if (event.entityLiving instanceof EntityEnderman) {

					if (rand < config.catalystRarity) {

						event.entityLiving.dropItem(
								TuxWeaponsCore.flightCatalyst.itemID, 1);

					}
				}

				if (event.entityLiving instanceof EntityChicken) {

					if (rand < config.catalystRarity / 50) {

						event.entityLiving.dropItem(
								TuxWeaponsCore.flightCatalyst.itemID, 1);

					}
				}
			}
		}

	}

	@ForgeSubscribe
	public void entityHit(LivingAttackEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;

			int hardnessLvl0 = EnchantmentHelper.getEnchantmentLevel(
					TuxWeaponsCore.enchHardened.effectId,
					player.getCurrentArmor(0));

			int hardnessLvl1 = EnchantmentHelper.getEnchantmentLevel(
					TuxWeaponsCore.enchHardened.effectId,
					player.getCurrentArmor(1));

			int hardnessLvl2 = EnchantmentHelper.getEnchantmentLevel(
					TuxWeaponsCore.enchHardened.effectId,
					player.getCurrentArmor(2));

			int hardnessLvl3 = EnchantmentHelper.getEnchantmentLevel(
					TuxWeaponsCore.enchHardened.effectId,
					player.getCurrentArmor(3));

			int hardnessLvlItem = EnchantmentHelper.getEnchantmentLevel(
					TuxWeaponsCore.enchHardened.effectId,
					player.getCurrentEquippedItem());

			if (hardnessLvl0 > 0 || hardnessLvl1 > 0 || hardnessLvl2 > 0
					|| hardnessLvl3 > 0 || hardnessLvlItem > 0) {
				if (event.source.getEntity() != null) {
					double d0 = event.source.getEntity().posX - player.posX;
					double d1;

					for (d1 = event.source.getEntity().posZ - player.posZ; d0
							* d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math
							.random()) * 0.01D) {
						d0 = (Math.random() - Math.random()) * 0.01D;
					}

					player.knockBack(player, event.ammount, -d0 * 100,
							-d1 * 100);
					if (player.fallDistance <= 0.0)
						player.addVelocity(0, -10000, 0);
				}

			}
		}
	}

	@ForgeSubscribe
	public void entityHurt(LivingHurtEvent event) {

		if (event.source.getEntity() instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) event.source.getEntity();

			if (player.inventory.getCurrentItem() != null) {

				int lifeStealLvl = EnchantmentHelper.getEnchantmentLevel(
						TuxWeaponsCore.enchLifeSteal.effectId,
						player.getCurrentEquippedItem());

				if (lifeStealLvl > 0 && event.ammount > 0) {

					player.heal(1);
					player.getCurrentEquippedItem().damageItem(
							4 - lifeStealLvl, player);
				}
				
				int strikeLvl = EnchantmentHelper.getEnchantmentLevel(
						TuxWeaponsCore.enchStrike.effectId,
						player.getCurrentEquippedItem());
				
				if (strikeLvl > 0 && event.ammount > 0) {

					int rand = (int) (Math.random() * 5) + 1;

					if (rand == 3) {
						event.entity.attackEntityFrom(DamageSource.magic,
								strikeLvl);
					}
				}
				
				if (player.inventory.getCurrentItem().getItem() instanceof itemMace && event.ammount > 0) {
					itemMace mace = (itemMace) player.getCurrentEquippedItem()
							.getItem();

					int damageModifier = (int) (Math.random() * 10) + 1;
					
					event.entity.attackEntityFrom(
							DamageSource.magic,
							damageModifier + mace.getWeaponDamage());
				}

			}

		}
	}

	@ForgeSubscribe
	public void entityAttacked(LivingAttackEvent event) {

		if (event.source.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.source.getEntity();

			if (player.inventory.getCurrentItem() != null) {

				int poisonLvl = EnchantmentHelper.getEnchantmentLevel(
						TuxWeaponsCore.enchPoison.effectId,
						player.getCurrentEquippedItem());

				int lifeStealLvl = EnchantmentHelper.getEnchantmentLevel(
						TuxWeaponsCore.enchLifeSteal.effectId,
						player.getCurrentEquippedItem());

				if (poisonLvl > 0 && event.ammount > 0) {

					TuxWeaponsCore.spawnParticles("magicCrit", event.entity, 2,
							1, 10);

					((EntityLiving) event.entity)
							.addPotionEffect(new PotionEffect(Potion.poison.id,
									100, poisonLvl));
				}

				if (lifeStealLvl > 0 && event.ammount > 0) {

					TuxWeaponsCore.spawnParticles("heart", event.entity, 1,
							0.2, 1);
				}
				
				int strikeLvl = EnchantmentHelper.getEnchantmentLevel(
						TuxWeaponsCore.enchStrike.effectId,
						player.getCurrentEquippedItem());
				
				if (strikeLvl > 0 && event.ammount > 0) {

					int rand = (int) (Math.random() * 5) + 1;

					if (rand == 3) {
						TuxWeaponsCore.spawnParticles("crit", event.entity, 2,
								1, 20);
					}
				}
				
				if (player.inventory.getCurrentItem().getItem() instanceof itemMace && event.ammount > 0) {
					itemMace mace = (itemMace) player.getCurrentEquippedItem()
							.getItem();

					int rand = (int) (Math.random() * 5) + 1;

					if (rand == 3) {
						TuxWeaponsCore.spawnParticles("crit", event.entity, 2,
								1, 20);
					}
					
				}

			}
		}
	}

	@ForgeSubscribe
	public void arrowNock(ArrowNockEvent event) {
		if (event.entityPlayer instanceof EntityPlayer) {
			EntityPlayer player = event.entityPlayer;
			Item item = player.getCurrentEquippedItem().getItem();
			ItemStack stack = player.getCurrentEquippedItem();
			World world = player.worldObj;

			int legacyLvl = EnchantmentHelper.getEnchantmentLevel(
					TuxWeaponsCore.enchLegacy.effectId,
					player.getCurrentEquippedItem());

			if (legacyLvl > 0) {
				event.setCanceled(true);

				if (player.capabilities.isCreativeMode
						|| player.inventory.hasItem(Item.arrow.itemID)) {
					player.setItemInUse(stack,
							item.getMaxItemUseDuration(stack) - legacyLvl * 3);
				}
			}
		}
	}

	@ForgeSubscribe
	public void arrowLoose(ArrowLooseEvent event) {
		if (event.entityPlayer instanceof EntityPlayer) {
			EntityPlayer player = event.entityPlayer;
			ItemStack stack = player.getCurrentEquippedItem();
			World world = player.worldObj;

			int EELvl = EnchantmentHelper.getEnchantmentLevel(
					TuxWeaponsCore.enchEagleEye.effectId,
					player.getCurrentEquippedItem());

			if (EELvl > 0) {
				event.setCanceled(true);

				boolean flag = player.capabilities.isCreativeMode
						|| EnchantmentHelper.getEnchantmentLevel(
								Enchantment.infinity.effectId, stack) > 0;

				if (flag || player.inventory.hasItem(Item.arrow.itemID)) {
					float f = (float) event.charge / 20.0F;
					f = (f * f + f * 2.0F) / 3.0F;

					if ((double) f < 0.1D) {
						return;
					}

					if (f > 1.0F) {
						f = 1.0F;
					}

					EntityEagleArrow entityarrow = new EntityEagleArrow(world,
							player, f * 2.0F, EELvl);// + EELvl * 0.4F);

					entityarrow.setIsCritical(true);

					int k = EnchantmentHelper.getEnchantmentLevel(
							Enchantment.power.effectId, stack);

					if (k > 0) {
						entityarrow.setDamage(entityarrow.getDamage()
								+ (double) k * 0.5D + 0.5D);
					}

					int l = EnchantmentHelper.getEnchantmentLevel(
							Enchantment.punch.effectId, stack);

					if (l > 0) {
						entityarrow.setKnockbackStrength(l);
					}

					if (EnchantmentHelper.getEnchantmentLevel(
							Enchantment.flame.effectId, stack) > 0) {
						entityarrow.setFire(100);
					}

					stack.damageItem(1, player);
					world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F
							/ (0.01F + 1.2F) + f * 0.5F);

					if (flag) {
						entityarrow.canBePickedUp = 2;
					} else {
						player.inventory
								.consumeInventoryItem(Item.arrow.itemID);
					}

					if (!world.isRemote) {
						world.spawnEntityInWorld(entityarrow);
					}
				}
			}
		}
	}

}