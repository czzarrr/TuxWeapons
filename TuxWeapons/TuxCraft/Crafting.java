package TuxWeapons.TuxCraft;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Crafting {

	static String[] disabledWeapons = config.disabledWeapons;
	static boolean altRecipes = config.altRecipes;

	public static void addRecipes() {
		if (Arrays.asList(disabledWeapons).contains("fireChargeCannon") == false) {
			ItemStack netherack = new ItemStack(Item.netherrackBrick);
			ItemStack netherCore = new ItemStack(TuxWeaponsCore.fireChargeCore);
			ItemStack fireCharge = new ItemStack(Item.fireballCharge);
			ItemStack blazePowder = new ItemStack(Item.blazePowder);

			GameRegistry.addRecipe(new ItemStack(
					TuxWeaponsCore.fireChargeCannon), "xxx", "zyc", "xxx", 'x',
					netherack, 'y', netherCore, 'z', fireCharge, 'c',
					blazePowder);

			GameRegistry.addRecipe(new ItemStack(
					TuxWeaponsCore.fireChargeCannon), "xxx", "cyz", "xxx", 'x',
					netherack, 'y', netherCore, 'z', fireCharge, 'c',
					blazePowder);
		}

		if (Arrays.asList(disabledWeapons).contains("crossBow") == false) {
			ItemStack wood = new ItemStack(Block.planks);
			ItemStack iron = new ItemStack(Item.ingotIron);
			ItemStack string = new ItemStack(Item.silk);
			ItemStack feather = new ItemStack(Item.feather);
			ItemStack Cbolt = new ItemStack(TuxWeaponsCore.bolt);
			ItemStack spiderEye = new ItemStack(Item.spiderEye);

			GameRegistry.addRecipe(new ItemStack(TuxWeaponsCore.crossBow),
					"xxy", "syx", "ysx", 'x', wood, 'y', iron, 's', string);

			GameRegistry.addRecipe(new ItemStack(TuxWeaponsCore.crossBow),
					"yxx", "xys", "xsy", 'x', wood, 'y', iron, 's', string);

			GameRegistry.addRecipe(new ItemStack(TuxWeaponsCore.bolt, 3),
					" i ", " i ", " f ", 'i', iron, 'f', feather);

			GameRegistry.addRecipe(new ItemStack(TuxWeaponsCore.poisonBolt, 1),
					" e", " b", 'b', Cbolt, 'e', spiderEye);
		}

		if (Arrays.asList(disabledWeapons).contains("dynamite") == false) {
			ItemStack gunpowder = new ItemStack(Item.gunpowder);
			ItemStack sand = new ItemStack(Block.sand);
			ItemStack string = new ItemStack(Item.silk);

			GameRegistry
					.addRecipe(new ItemStack(TuxWeaponsCore.dynamite), " gs",
							" x ", " g ", 'g', gunpowder, 'x', sand, 's',
							string);
		}

		if (Arrays.asList(disabledWeapons).contains("redstoneEMP") == false) {
			ItemStack redStone = new ItemStack(Item.redstone);
			ItemStack clay = new ItemStack(Item.clay);

			GameRegistry.addRecipe(
					new ItemStack(TuxWeaponsCore.redstoneEMP, 3), " x ", "xox",
					" x ", 'x', redStone, 'o', clay);
		}
	}

	public static void addMultiToolRecipes(int i) {
		if (weaponMaterials.weaponMats[i].crafterItem == null) {
			weaponMaterials.weaponMats[i].crafterItem = TuxWeaponsCore.dummyItem;
		}

		else if (weaponMaterials.weaponMats[i].crafterBlock == null) {
			weaponMaterials.weaponMats[i].crafterBlock = TuxWeaponsCore.dummyBlock;
		}

		Item weaponCrafterItem = weaponMaterials.weaponMats[i].crafterItem;
		Block weaponCrafterBlock = weaponMaterials.weaponMats[i].crafterBlock;

		ItemStack sticks;
		ItemStack iron2;

		if (weaponMaterials.weaponMats[i].usesSticks == false) {
			sticks = new ItemStack(
					weaponMaterials.weaponMats[i].miscStickCrafter);
		}

		else {
			sticks = new ItemStack(Item.stick);
		}

		if (weaponMaterials.weaponMats[i].usesSticks == false) {
			iron2 = new ItemStack(
					weaponMaterials.weaponMats[i].miscStickCrafter);
		}

		else {
			iron2 = new ItemStack(Item.ingotIron);
		}

		if (Arrays.asList(config.disabledWeapons).contains("battleAxes") == false) {

			String craftingPatternA = "xxx";
			String craftingPatternB = "xyx";
			String craftingPatternC = " y ";

			String craftingPatternAalt = " xx";
			String craftingPatternBalt = "yyx";
			String craftingPatternCalt = " xx";

			if (weaponMaterials.weaponMats[i].crafterBlock == TuxWeaponsCore.dummyBlock) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterItem, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (config.altRecipes == false) {
						GameRegistry.addRecipe(new ItemStack(
								TuxWeaponsCore.battleAxes), craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', sticks);
					}

					else {
						GameRegistry.addRecipe(new ItemStack(
								TuxWeaponsCore.battleAxes),
								craftingPatternAalt, craftingPatternBalt,
								craftingPatternCalt, 'x', stack, 'y', sticks);
					}
				}

				else {

					if (config.altRecipes == false) {
						GameRegistry.addRecipe(new ItemStack(
								TuxWeaponsCore.battleAxes), craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterItem, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(new ItemStack(
								TuxWeaponsCore.battleAxes),
								craftingPatternAalt, craftingPatternBalt,
								craftingPatternCalt, 'x', weaponCrafterItem,
								'y', sticks);
					}
				}

			}
			if (weaponMaterials.weaponMats[i].crafterItem == TuxWeaponsCore.dummyItem) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterBlock, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (config.altRecipes == false) {
						GameRegistry.addRecipe(new ItemStack(
								TuxWeaponsCore.battleAxes), craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', sticks);
					}

					else {
						GameRegistry.addRecipe(new ItemStack(
								TuxWeaponsCore.battleAxes),
								craftingPatternAalt, craftingPatternBalt,
								craftingPatternCalt, 'x', stack, 'y', sticks);
					}
				}

				else {

					if (config.altRecipes == false) {
						GameRegistry.addRecipe(new ItemStack(
								TuxWeaponsCore.battleAxes), craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterBlock, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(new ItemStack(
								TuxWeaponsCore.battleAxes),
								craftingPatternAalt, craftingPatternBalt,
								craftingPatternCalt, 'x', weaponCrafterBlock,
								'y', sticks);
					}
				}

			}

		}

		if (Arrays.asList(disabledWeapons).contains("hammers") == false) {

			String craftingPatternA = "xyx";
			String craftingPatternB = "xyx";
			String craftingPatternC = " y ";

			String craftingPatternAalt = " xx";
			String craftingPatternBalt = "yyy";
			String craftingPatternCalt = " xx";

			ItemStack crafts = new ItemStack(TuxWeaponsCore.hammers);

			if (weaponMaterials.weaponMats[i].crafterBlock == TuxWeaponsCore.dummyBlock) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterItem, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								stack, 'y', sticks);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterItem, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								weaponCrafterItem, 'y', sticks);
					}
				}

			}
			if (weaponMaterials.weaponMats[i].crafterItem == TuxWeaponsCore.dummyItem) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterBlock, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								stack, 'y', sticks);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterBlock, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								weaponCrafterBlock, 'y', sticks);
					}
				}

			}

		}

		if (Arrays.asList(disabledWeapons).contains("spears") == false) {

			String craftingPatternA = "  x";
			String craftingPatternB = " y ";
			String craftingPatternC = "y  ";

			String craftingPatternAalt = "y  ";
			String craftingPatternBalt = " y ";
			String craftingPatternCalt = "  x";

			ItemStack crafts = new ItemStack(TuxWeaponsCore.spears);

			if (weaponMaterials.weaponMats[i].crafterBlock == TuxWeaponsCore.dummyBlock) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterItem, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								stack, 'y', sticks);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterItem, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								weaponCrafterItem, 'y', sticks);
					}
				}

			}
			if (weaponMaterials.weaponMats[i].crafterItem == TuxWeaponsCore.dummyItem) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterBlock, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								stack, 'y', sticks);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterBlock, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								weaponCrafterBlock, 'y', sticks);
					}
				}

			}

		}

		if (Arrays.asList(disabledWeapons).contains("grappHooks") == false) {

			String craftingPatternA = " xx";
			String craftingPatternB = " yx";
			String craftingPatternC = "y  ";

			String craftingPatternAalt = "y  ";
			String craftingPatternBalt = " yx";
			String craftingPatternCalt = " xx";

			ItemStack crafts = new ItemStack(TuxWeaponsCore.grappHooks,
					weaponMaterials.weaponMats[i].harvestLevel * 5);

			if (weaponMaterials.weaponMats[i].crafterBlock == TuxWeaponsCore.dummyBlock) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterItem, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', iron2);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								stack, 'y', iron2);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterItem, 'y', iron2);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								weaponCrafterItem, 'y', iron2);
					}
				}

			}
			if (weaponMaterials.weaponMats[i].crafterItem == TuxWeaponsCore.dummyItem) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterBlock, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', iron2);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								stack, 'y', iron2);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterBlock, 'y', iron2);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								weaponCrafterBlock, 'y', iron2);
					}
				}

			}

		}

		if (Arrays.asList(disabledWeapons).contains("maces") == false) {

			String craftingPatternA = " xx";
			String craftingPatternB = " xx";
			String craftingPatternC = "y  ";

			String craftingPatternAalt = "y  ";
			String craftingPatternBalt = " xx";
			String craftingPatternCalt = " xx";

			ItemStack crafts = new ItemStack(TuxWeaponsCore.maces);

			if (weaponMaterials.weaponMats[i].crafterBlock == TuxWeaponsCore.dummyBlock) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterItem, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								stack, 'y', sticks);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterItem, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								weaponCrafterItem, 'y', sticks);
					}
				}

			}
			if (weaponMaterials.weaponMats[i].crafterItem == TuxWeaponsCore.dummyItem) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterBlock, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x', stack,
								'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								stack, 'y', sticks);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, craftingPatternC, 'x',
								weaponCrafterBlock, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, craftingPatternCalt, 'x',
								weaponCrafterBlock, 'y', sticks);
					}
				}

			}

		}

		if (Arrays.asList(disabledWeapons).contains("knives") == false) {

			String craftingPatternA = " x";
			String craftingPatternB = " y";

			String craftingPatternAalt = " x";
			String craftingPatternBalt = "y ";

			ItemStack crafts = new ItemStack(TuxWeaponsCore.knives,
					8);

			if (weaponMaterials.weaponMats[i].crafterBlock == TuxWeaponsCore.dummyBlock) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterItem, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, 'x', stack, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, 'x', stack, 'y', sticks);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, 'x', weaponCrafterItem, 'y',
								sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, 'x', weaponCrafterItem,
								'y', sticks);
					}
				}

			}
			if (weaponMaterials.weaponMats[i].crafterItem == TuxWeaponsCore.dummyItem) {

				if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
					ItemStack stack = new ItemStack(weaponCrafterBlock, 1,
							weaponMaterials.weaponMats[i].crafterDamage);

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, 'x', stack, 'y', sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, 'x', stack, 'y', sticks);
					}
				}

				else {

					if (altRecipes == false) {
						GameRegistry.addRecipe(crafts, craftingPatternA,
								craftingPatternB, 'x', weaponCrafterBlock, 'y',
								sticks);
					}

					else {
						GameRegistry.addRecipe(crafts, craftingPatternAalt,
								craftingPatternBalt, 'x', weaponCrafterBlock,
								'y', sticks);
					}
				}

			}

		}

		if (Arrays.asList(disabledWeapons).contains("shields") == false) {

			String craftingPatternA = "yxy";
			String craftingPatternB = "xyx";
			String craftingPatternC = "yxy";

			String craftingPatternAalt = "xyx";
			String craftingPatternBalt = "yyy";
			String craftingPatternCalt = "xyx";

			ItemStack crafts = new ItemStack(TuxWeaponsCore.shields);

			if (weaponMaterials.weaponMats[i].usesSticks == false) {

				Item planks = weaponMaterials.weaponMats[i].miscStickCrafter;

				if (weaponMaterials.weaponMats[i].crafterBlock == TuxWeaponsCore.dummyBlock) {

					if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
						ItemStack stack = new ItemStack(weaponCrafterItem, 1,
								weaponMaterials.weaponMats[i].crafterDamage);

						if (altRecipes == false) {
							GameRegistry.addRecipe(crafts, craftingPatternA,
									craftingPatternB, craftingPatternC, 'x',
									stack, 'y', planks);
						}

						else {
							GameRegistry.addRecipe(crafts, craftingPatternAalt,
									craftingPatternBalt, craftingPatternCalt,
									'x', stack, 'y', planks);
						}
					}

					else {

						if (altRecipes == false) {
							GameRegistry.addRecipe(crafts, craftingPatternA,
									craftingPatternB, craftingPatternC, 'x',
									weaponCrafterItem, 'y', planks);
						}

						else {
							GameRegistry.addRecipe(crafts, craftingPatternAalt,
									craftingPatternBalt, craftingPatternCalt,
									'x', weaponCrafterItem, 'y', planks);
						}
					}

				}
				if (weaponMaterials.weaponMats[i].crafterItem == TuxWeaponsCore.dummyItem) {

					if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
						ItemStack stack = new ItemStack(weaponCrafterBlock, 1,
								weaponMaterials.weaponMats[i].crafterDamage);

						if (altRecipes == false) {
							GameRegistry.addRecipe(crafts, craftingPatternA,
									craftingPatternB, craftingPatternC, 'x',
									stack, 'y', planks);
						}

						else {
							GameRegistry.addRecipe(crafts, craftingPatternAalt,
									craftingPatternBalt, craftingPatternCalt,
									'x', stack, 'y', planks);
						}
					}

					else {

						if (altRecipes == false) {
							GameRegistry.addRecipe(crafts, craftingPatternA,
									craftingPatternB, craftingPatternC, 'x',
									weaponCrafterBlock, 'y', planks);
						}

						else {
							GameRegistry.addRecipe(crafts, craftingPatternAalt,
									craftingPatternBalt, craftingPatternCalt,
									'x', weaponCrafterBlock, 'y', planks);
						}
					}

				}
			}

			else {
				Block planks = Block.planks;

				if (weaponMaterials.weaponMats[i].crafterBlock == TuxWeaponsCore.dummyBlock) {

					if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
						ItemStack stack = new ItemStack(weaponCrafterItem, 1,
								weaponMaterials.weaponMats[i].crafterDamage);

						if (altRecipes == false) {
							GameRegistry.addRecipe(crafts, craftingPatternA,
									craftingPatternB, craftingPatternC, 'x',
									stack, 'y', planks);
						}

						else {
							GameRegistry.addRecipe(crafts, craftingPatternAalt,
									craftingPatternBalt, craftingPatternCalt,
									'x', stack, 'y', planks);
						}
					}

					else {

						if (altRecipes == false) {
							GameRegistry.addRecipe(crafts, craftingPatternA,
									craftingPatternB, craftingPatternC, 'x',
									weaponCrafterItem, 'y', planks);
						}

						else {
							GameRegistry.addRecipe(crafts, craftingPatternAalt,
									craftingPatternBalt, craftingPatternCalt,
									'x', weaponCrafterItem, 'y', planks);
						}
					}

				}
				if (weaponMaterials.weaponMats[i].crafterItem == TuxWeaponsCore.dummyItem) {

					if (weaponMaterials.weaponMats[i].crafterDamage != 0) {
						ItemStack stack = new ItemStack(weaponCrafterBlock, 1,
								weaponMaterials.weaponMats[i].crafterDamage);

						if (altRecipes == false) {
							GameRegistry.addRecipe(crafts, craftingPatternA,
									craftingPatternB, craftingPatternC, 'x',
									stack, 'y', planks);
						}

						else {
							GameRegistry.addRecipe(crafts, craftingPatternAalt,
									craftingPatternBalt, craftingPatternCalt,
									'x', stack, 'y', planks);
						}
					}

					else {

						if (altRecipes == false) {
							GameRegistry.addRecipe(crafts, craftingPatternA,
									craftingPatternB, craftingPatternC, 'x',
									weaponCrafterBlock, 'y', planks);
						}

						else {
							GameRegistry.addRecipe(crafts, craftingPatternAalt,
									craftingPatternBalt, craftingPatternCalt,
									'x', weaponCrafterBlock, 'y', planks);
						}
					}

				}
			}

		}

		System.out.println("[TuxWeapons] Registered "
				+ weaponMaterials.weaponMats[i].toolName + " weapons");

	}

}
