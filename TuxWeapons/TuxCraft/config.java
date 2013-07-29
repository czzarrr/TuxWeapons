package TuxWeapons.TuxCraft;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class config {

	public static double magmaCoreRarity;
	public static double catalystRarity;
	public static boolean griefEnabled;
	public static int idBaseItem = 9000;
	public static int idBaseEnchant = 0;
	public static int idBasePotion = 24;
	public static int idBaseEntity = 0;
	public static boolean altRecipes;
	public static int dummyBlockID;
	public static boolean cannonFire;
	public static String[] disabledWeapons;
	public static String[] addedWeapons;
	public static String supportedMods;

	public static void generateConfig(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(
				event.getSuggestedConfigurationFile());
		cfg.load();

		magmaCoreRarity = cfg.get(Configuration.CATEGORY_GENERAL,
				"Magma Core Rarity", 0.05D).getDouble(0.05D);

		catalystRarity = cfg.get(Configuration.CATEGORY_GENERAL,
				"Catalyst of Flight Rarity", 0.1D).getDouble(0.1D);

		griefEnabled = cfg.get(Configuration.CATEGORY_GENERAL,
				"Weapon Griefing", true).getBoolean(true);

		Property baseIDItem = cfg.get(Configuration.CATEGORY_GENERAL,
				"Base Item ID", 0);

		baseIDItem.comment = "All Item IDs are derived from this number (ex. A wooden battleaxe is the baseID"
				+ '\n'
				+ "plus 1 while the stone battleaxe is the baseID plus 2 etc.)"
				+ '\n'
				+ '\n'
				+ "If it equals zero than a value will be auto-assigned";

		idBaseItem = (baseIDItem.getInt() == 0) ? IDHelper
				.getFirstUnusedID("item") : baseIDItem.getInt();

		Property baseIDEnchant = cfg.get(Configuration.CATEGORY_GENERAL,
				"Base Enchant ID", 0);

		baseIDEnchant.comment = "All Enchantment IDs are derived from this number (ex. The posion enchant is the baseID"
				+ '\n'
				+ "plus 1 while the life steal enchant is the baseID plus 2 etc.)"
				+ '\n'
				+ '\n'
				+ "If it equals zero than a value will be auto-assigned";

		idBaseEnchant = (baseIDEnchant.getInt() == 0) ? IDHelper
				.getFirstUnusedID("enchant") : baseIDEnchant.getInt();

		Property recipesAlt = cfg.get(Configuration.CATEGORY_GENERAL,
				"Alternate Recipes", false);

		altRecipes = recipesAlt.getBoolean(false);

		/**
		 * Property ghostBlockID = cfg.get(Configuration.CATEGORY_GENERAL,
		 * "Ghost Block ID", 250);
		 * 
		 * ghostBlockID.comment =
		 * "Not actual block, just used for technical purposes";
		 * 
		 * dummyBlockID = ghostBlockID.getInt();
		 */

		cannonFire = cfg.get(Configuration.CATEGORY_GENERAL,
				"Cannon Shoots Fire", true).getBoolean(true);

		Property WeaponsToDisable = cfg.get(Configuration.CATEGORY_GENERAL,
				"Weapons To Disable", "");

		WeaponsToDisable.comment = "Again split all the weapons you want to disable with a comma and a space. (ex. 'dynamite, fireChargeCannon')"
				+ '\n'
				+ "Weapons you can disable:	battleAxes, spears, hammers, grappHooks, crossBow, dynamite, maces, knives, shields, fireChargeCannon, redstoneEMP, fryingPan, potions, enchants, fryingPan, tomahawks";

		disabledWeapons = WeaponsToDisable.getString().split(", ");

		Property WeaponsToAdd = cfg.get(Configuration.CATEGORY_GENERAL,
				"Extra Weapon Types", "");

		WeaponsToAdd.comment = "Split all the weapons types you want to add with a comma and a space. (ex. 'Lapis, NQuartz')"
				+ '\n'
				+ "Weapon types you can enable in this version: NQuartz, Lapis, Emerald, Butter, Jerry, Jaffa, Quantum, PilotPig";

		addedWeapons = WeaponsToAdd.getString().split(", ");

		System.out.println(" ");
		System.out.println("######################################");
		System.out.println("TuxWeapons Config Settings");
		System.out.println("######################################");
		System.out.println(" ");
		System.out.println("	      Magma Core Rarity: "
				+ String.valueOf(magmaCoreRarity));
		System.out.println("  Catalyst of Flight Rarity: "
				+ String.valueOf(catalystRarity));
		System.out.println("	Weapon Griefing Enabled: "
				+ String.valueOf(griefEnabled));
		System.out.println("	     Cannon Shoots Fire: "
				+ String.valueOf(cannonFire));
		System.out
				.println("        Base Item ID Ingame: " + (idBaseItem + 261));
		System.out.println("        Base Enchantment ID: " + idBaseEnchant);
		System.out.println("             Base Potion ID: " + idBasePotion);
		System.out.println("	       Disabled Weapons: "
				+ WeaponsToDisable.getString());
		System.out.println("         Added Weapon Types: "
				+ WeaponsToAdd.getString());
		System.out.println(" ");

		// TODO

		// File f2 = new File("../src/minecraft/ZRandomMod/randomItem.java");
		// System.out.println(String.valueOf(f2.exists()));

		cfg.save();
	}
}
