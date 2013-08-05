package TuxWeapons.TuxCraft;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.MinecraftForge;
import TuxWeapons.TuxCraft.ModSupport.BiomesOPlenty;
import TuxWeapons.TuxCraft.ModSupport.Metallurgy;
import TuxWeapons.TuxCraft.blocks.BlockRedstoneTempBlock;
import TuxWeapons.TuxCraft.enchantments.eagleEye;
import TuxWeapons.TuxCraft.enchantments.hardened;
import TuxWeapons.TuxCraft.enchantments.legacy;
import TuxWeapons.TuxCraft.enchantments.lifeSteal;
import TuxWeapons.TuxCraft.enchantments.poisonAspect;
import TuxWeapons.TuxCraft.enchantments.speed;
import TuxWeapons.TuxCraft.enchantments.strike;
import TuxWeapons.TuxCraft.enchantments.swift;
import TuxWeapons.TuxCraft.events.EventHookContainerClass;
import TuxWeapons.TuxCraft.items.itemBolt;
import TuxWeapons.TuxCraft.items.itemCore;
import TuxWeapons.TuxCraft.items.itemCrossBow;
import TuxWeapons.TuxCraft.items.itemDynamite;
import TuxWeapons.TuxCraft.items.itemEMPGrenade;
import TuxWeapons.TuxCraft.items.itemFireChargeCannon;
import TuxWeapons.TuxCraft.items.itemFryingPan;
import TuxWeapons.TuxCraft.items.multiTier.itemBattleAxe;
import TuxWeapons.TuxCraft.items.multiTier.itemGrappHook;
import TuxWeapons.TuxCraft.items.multiTier.itemHammer;
import TuxWeapons.TuxCraft.items.multiTier.itemKnife;
import TuxWeapons.TuxCraft.items.multiTier.itemMace;
import TuxWeapons.TuxCraft.items.multiTier.itemShield;
import TuxWeapons.TuxCraft.items.multiTier.itemSpear;
import TuxWeapons.TuxCraft.potions.cure;
import TuxWeapons.TuxCraft.potions.flameAura;
import TuxWeapons.TuxCraft.potions.flight;
import TuxWeapons.TuxCraft.potions.ironSkin;
import TuxWeapons.TuxCraft.potions.quickStep;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = TuxWeaponsCore.modid, name = "TuxWeapons", version = "0.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class TuxWeaponsCore {

	// TODO
	// - fix the speed enchant

	@Instance("TuxWeapons")
	public static TuxWeaponsCore instance;

	@SidedProxy(clientSide = "TuxWeapons.TuxCraft.ClientProxy", serverSide = "TuxWeapons.TuxCraft.CommonProxy")
	public static CommonProxy proxy;

	public static final String modid = "TuxWeapons";
	public static final String modidLowerCase = "tuxweapons";

	public static boolean metallurgy;
	public static boolean biomesOPlenty;

	public static String altTextureLoc = "";

	public static String customEffects = "gui/potionIcons.png";

	public static String renderTexturePrefix = "../src/minecraft/assets/tuxweapons";

	public static int modTypes = 0;
	public static int typesAdded = 0;

	public static Item battleAxes;
	public static Item hammers;
	public static Item maces;
	public static Item spears;
	public static Item dynamite;
	public static Item fireChargeCannon;
	public static Item fireChargeCore;
	public static Item redstoneEMP;
	public static Item crossBow;
	public static Item bolt;
	public static Item poisonBolt;
	public static Item shields;
	public static Item grappHooks;
	public static Item knives;
	public static Item flightCatalyst;
	public static Item fryingPan;

	public static final Block redstoneTempBlock = new BlockRedstoneTempBlock(
			976).setHardness(5.0F).setResistance(10.0F)
			.setUnlocalizedName("redstoneTempBlock");

	public static Enchantment enchPoison;
	public static Enchantment enchLifeSteal;
	public static Enchantment enchEagleEye;
	public static Enchantment enchSpeed;
	public static Enchantment enchSwift;
	public static Enchantment enchLegacy;
	public static Enchantment enchStrike;
	public static Enchantment enchHardened;

	public static Potion flight;
	public static Potion quickStep;
	public static Potion flameAura;
	public static Potion ironSkin;
	public static Potion theCure;

	public static String flightCatalystEffect;
	public static String quickStepEffect;
	public static String flameAuraEffect;
	public static String ironSkinEffect;

	// TODO

	public static Item dummyItem = new Item(config.idBaseItem);
	public static Block dummyBlock = new Block(450, Material.anvil);

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		config.generateConfig(event);
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event) {

		// System.out.println(new File("GoofyGoober").mkdir());

		GameRegistry.registerBlock(dummyBlock);
		GameRegistry.registerBlock(redstoneTempBlock);

		weaponMaterials.weaponMats[0] = weaponMaterials.Wood;
		weaponMaterials.weaponMats[1] = weaponMaterials.Stone;
		weaponMaterials.weaponMats[2] = weaponMaterials.Iron;
		weaponMaterials.weaponMats[3] = weaponMaterials.Diamond;
		weaponMaterials.weaponMats[4] = weaponMaterials.Gold;

		if (Arrays.asList(config.addedWeapons).contains("NQuartz")) {

			weaponMaterials.weaponMats[getPos("NQuartz")] = weaponMaterials.NQuartz;
		}

		if (Arrays.asList(config.addedWeapons).contains("Lapis")) {

			weaponMaterials.weaponMats[getPos("Lapis")] = weaponMaterials.Lapis;
		}

		if (Arrays.asList(config.addedWeapons).contains("Emerald")) {

			weaponMaterials.weaponMats[getPos("Emerald")] = weaponMaterials.Emerald;
		}

		if (Arrays.asList(config.addedWeapons).contains("Butter")) {

			weaponMaterials.weaponMats[getPos("Butter")] = weaponMaterials.Butter;
		}

		if (Arrays.asList(config.addedWeapons).contains("Jerry")) {

			weaponMaterials.weaponMats[getPos("Jerry")] = weaponMaterials.Jerry;
		}

		if (Arrays.asList(config.addedWeapons).contains("Jaffa")) {

			weaponMaterials.weaponMats[getPos("Jaffa")] = weaponMaterials.Jaffa;
		}

		if (Arrays.asList(config.addedWeapons).contains("Quantum")) {

			weaponMaterials.weaponMats[getPos("Quantum")] = weaponMaterials.Quantum;
		}

		if (Arrays.asList(config.addedWeapons).contains("PilotPig")) {

			weaponMaterials.weaponMats[getPos("PilotPig")] = weaponMaterials.PilotPig;
		}

		int extraID = 10;

		if (Arrays.asList(config.disabledWeapons).contains("crossBow") == false) {
			crossBow = new itemCrossBow(config.idBaseItem + 1)
					.setUnlocalizedName("crossBow");
			LanguageRegistry.instance();
			LanguageRegistry.addName(crossBow, "Crossbow");

			bolt = new itemBolt(config.idBaseItem + 2, 0)
					.setUnlocalizedName("bolt");
			LanguageRegistry.instance();
			LanguageRegistry.addName(bolt, "Bolt");

			poisonBolt = new itemBolt(config.idBaseItem + 3, 1)
					.setUnlocalizedName("poisonBolt");
			LanguageRegistry.instance();
			LanguageRegistry.addName(poisonBolt, "Poisoned Bolt");
		}

		if (Arrays.asList(config.disabledWeapons).contains("dynamite") == false) {
			dynamite = new itemDynamite(config.idBaseItem + 4)
					.setUnlocalizedName("dynamite");
			LanguageRegistry.instance();
			LanguageRegistry.addName(dynamite, "Dynamite");
		}

		if (Arrays.asList(config.disabledWeapons).contains("fireChargeCannon") == false) {
			fireChargeCannon = new itemFireChargeCannon(config.idBaseItem + 5)
					.setUnlocalizedName("FireChargeCannon").setFull3D();
			LanguageRegistry.instance();
			LanguageRegistry.addName(fireChargeCannon, "Fire Charge Cannon");

			fireChargeCore = new itemCore(config.idBaseItem + 6, "magmaCore")
					.setUnlocalizedName("magmaCore");
			LanguageRegistry.instance();
			LanguageRegistry.addName(fireChargeCore, "Magma Core");
		}

		if (Arrays.asList(config.disabledWeapons).contains("redstoneEMP") == false) {
			redstoneEMP = new itemEMPGrenade(config.idBaseItem + 7)
					.setUnlocalizedName("redstoneEMP");
			LanguageRegistry.instance();
			LanguageRegistry.addName(redstoneEMP, "Redstone EMP Grenade");
		}
		
		if (Arrays.asList(config.disabledWeapons).contains("fryingPan") == false) {
			fryingPan = new itemFryingPan(config.idBaseItem + 9)
					.setUnlocalizedName("fryingPan");
			LanguageRegistry.instance();
			LanguageRegistry.addName(fryingPan, "Frying Pan of DOOM");
		}

		for (int i = 0; i < weaponMaterials.weaponMats.length; i++) {

			if (Arrays.asList(config.disabledWeapons).contains("battleAxes") == false) {
				battleAxes = new itemBattleAxe(config.idBaseItem + 1
						* weaponMaterials.weaponMats.length + i + extraID,
						weaponMaterials.weaponMats[i])
						.setUnlocalizedName(weaponMaterials.weaponMats[i].toolName
								+ "Battleaxe");
				LanguageRegistry.instance();
				LanguageRegistry.addName(battleAxes,
						weaponMaterials.weaponMats[i].toolName + " Battleaxe");
			}

			if (Arrays.asList(config.disabledWeapons).contains("hammers") == false) {
				hammers = new itemHammer(config.idBaseItem + 2
						* weaponMaterials.weaponMats.length + i + extraID,
						weaponMaterials.weaponMats[i])
						.setUnlocalizedName(weaponMaterials.weaponMats[i].toolName
								+ "Hammer");
				LanguageRegistry.instance();
				LanguageRegistry.addName(hammers,
						weaponMaterials.weaponMats[i].toolName + " Hammer");
			}

			if (Arrays.asList(config.disabledWeapons).contains("maces") == false) {
				maces = new itemMace(config.idBaseItem + 3
						* weaponMaterials.weaponMats.length + i + extraID,
						weaponMaterials.weaponMats[i])
						.setUnlocalizedName(weaponMaterials.weaponMats[i].toolName
								+ "Mace");
				LanguageRegistry.instance();
				LanguageRegistry.addName(maces,
						weaponMaterials.weaponMats[i].toolName + " Mace");
			}

			if (Arrays.asList(config.disabledWeapons).contains("spears") == false) {
				spears = new itemSpear(config.idBaseItem + 4
						* weaponMaterials.weaponMats.length + i + extraID,
						weaponMaterials.weaponMats[i])
						.setUnlocalizedName(weaponMaterials.weaponMats[i].toolName
								+ "Spear");
				LanguageRegistry.instance();
				LanguageRegistry.addName(spears,
						weaponMaterials.weaponMats[i].toolName + " Spear");
			}

			if (Arrays.asList(config.disabledWeapons).contains("grappHooks") == false) {
				grappHooks = new itemGrappHook(config.idBaseItem + 6
						* weaponMaterials.weaponMats.length + i + extraID,
						weaponMaterials.weaponMats[i],
						weaponMaterials.weaponMats[i].color)
						.setUnlocalizedName(weaponMaterials.weaponMats[i].toolName
								+ "grappHook");
				LanguageRegistry.instance();
				LanguageRegistry.addName(grappHooks,
						weaponMaterials.weaponMats[i].toolName
								+ " Grappling Hook");
			}

			if (Arrays.asList(config.disabledWeapons).contains("shields") == false) {
				shields = new itemShield(config.idBaseItem + 7
						* weaponMaterials.weaponMats.length + i + extraID,
						weaponMaterials.weaponMats[i],
						weaponMaterials.weaponMats[i].color)
						.setUnlocalizedName(weaponMaterials.weaponMats[i].toolName
								+ "Shield");
				LanguageRegistry.instance();
				LanguageRegistry.addName(shields,
						weaponMaterials.weaponMats[i].toolName + " Shield");
			}

			if (Arrays.asList(config.disabledWeapons).contains("knives") == false) {
				knives = new itemKnife(config.idBaseItem + 5
						* weaponMaterials.weaponMats.length + i + extraID,
						weaponMaterials.weaponMats[i],
						weaponMaterials.weaponMats[i].color)
						.setUnlocalizedName(weaponMaterials.weaponMats[i].toolName
								+ "Knife");
				LanguageRegistry.instance();
				LanguageRegistry.addName(knives,
						weaponMaterials.weaponMats[i].toolName + " Knife");
			}

			Crafting.addMultiToolRecipes(i);
		}

		if (Arrays.asList(config.disabledWeapons).contains("enchants") == false) {
			
			enchSwift = new swift(config.idBaseEnchant, 4);
			Enchantment.addToBookList(enchSwift);
			
			enchPoison = new poisonAspect(config.idBaseEnchant + 1, 4);
			Enchantment.addToBookList(enchPoison);

			enchLifeSteal = new lifeSteal(config.idBaseEnchant + 2, 4);
			Enchantment.addToBookList(enchLifeSteal);

			enchStrike = new strike(config.idBaseEnchant + 3, 4);
			Enchantment.addToBookList(enchStrike);
			
			enchEagleEye = new eagleEye(config.idBaseEnchant + 4, 4);
			Enchantment.addToBookList(enchEagleEye);

			enchLegacy = new legacy(config.idBaseEnchant + 5, 4);
			Enchantment.addToBookList(enchLegacy);

			enchSpeed = new speed(config.idBaseEnchant + 6, 4);
			Enchantment.addToBookList(enchSpeed);
			
			enchHardened = new hardened(config.idBaseEnchant + 7, 4);
			Enchantment.addToBookList(enchHardened);
		}

		if (Arrays.asList(config.disabledWeapons).contains("potions") == false) {

			flightCatalystEffect = "+0+1-2+3&4+4+13";
			quickStepEffect = "0-1+2+3+13&4-4";
			flameAuraEffect = "0+1+2+3+13&4-4";
			ironSkinEffect = "0+1+2-3+13&4-4";

			flight = ((TuxWeapons.TuxCraft.potions.flight) new flight(config.idBasePotion, false, 0xc4b837,
					"gui/flight.png").setPotionName("potion.flight")).setTWIconIndex(5, 2);
			PotionHelper.potionRequirements.put(
					Integer.valueOf(flight.getId()), "0 & 1 & !2 & 3 & 0+6");
			Lang.potion(flight.getName(), "Flight", "Potion of Flight");

			quickStep = ((TuxWeapons.TuxCraft.potions.quickStep) new quickStep(config.idBasePotion + 1, false, 0x694d29,
					"gui/flight.png").setPotionName("potion.quickStep")).setTWIconIndex(4, 2);
			PotionHelper.potionRequirements.put(
					Integer.valueOf(quickStep.getId()), "0 & !1 & 2 & 3 & 2+6");
			Lang.potion(quickStep.getName(), "Quick Step",
					"Potion of Convienence");

			flameAura = ((TuxWeapons.TuxCraft.potions.flameAura) new flameAura(config.idBasePotion + 2, false, 0xaf1414,
					"gui/flight.png").setPotionName("potion.flameAura")).setTWIconIndex(3, 2);
			PotionHelper.potionRequirements.put(
					Integer.valueOf(flameAura.getId()), "0 & 1 & 2 & 3 & 2+6");
			Lang.potion(flameAura.getName(), "Flame Aura", "Potion of Heat");

			ironSkin = new ironSkin(config.idBasePotion + 3, false, 0x787878,
					"gui/flight.png").setPotionName("potion.ironSkin");
			PotionHelper.potionRequirements.put(
					Integer.valueOf(ironSkin.getId()), "0 & 1 & 2 & !3 & 2+6");
			Lang.potion(ironSkin.getName(), "Iron Skin", "Potion of Durability");

			theCure = new cure(config.idBasePotion + 4, false, 0xffffff,
					"gui/flight.png").setPotionName("potion.cure");
			PotionHelper.potionRequirements
					.put(Integer.valueOf(theCure.getId()),
							"!0 & !1 & !2 & !3 & 2+6");
			Lang.potion(theCure.getName(), "The Cure", "Potion of the Cure");

			flightCatalyst = new itemCore(config.idBaseItem + 8,
					"flightCatalyst").setUnlocalizedName("flightCatalyst")
					.setPotionEffect(flightCatalystEffect);
			LanguageRegistry.instance();
			LanguageRegistry.addName(flightCatalyst, "Catalyst of Flight");

			Item.feather.setPotionEffect(quickStepEffect);
			fireChargeCore.setPotionEffect(flameAuraEffect);
			Item.plateIron.setPotionEffect(ironSkinEffect);
		}

		// TODO

		Crafting.addRecipes();

		MinecraftForge.EVENT_BUS.register(new EventHookContainerClass());
		proxy.registerRenderers();
		proxy.registerServerTickHandler();
		proxy.registerClientTickHandler();

	}

	int getPos(String s) {

		int index = -1;

		for (int n = 0; n < config.addedWeapons.length && index == -1; n++) {
			if (Arrays.asList(config.addedWeapons[n]).contains(s)) {
				index = n;
			}

		}

		int pos = 5 + index;

		return pos;
	}

	@Mod.EventHandler
	public void postInit(FMLPreInitializationEvent event) {
		//MinecraftForge.EVENT_BUS.register(new PotionBuffEffects(Minecraft.getMinecraft()));
		
		if (Loader.isModLoaded("Metallurgy3Core"))
        {
			metallurgy = true;
			modTypes += 37;
        }
		
		if (Loader.isModLoaded("BiomesOPlenty"))
        {
			biomesOPlenty = true;
			modTypes += 2;
        }
		
		if(metallurgy)
		{
			Metallurgy.load(typesAdded);
			typesAdded += 37;
		}
		
		if(biomesOPlenty)
		{
			BiomesOPlenty.load(typesAdded);
			typesAdded += 2;
		}
	}

	public static void spawnParticles(String s, Entity par1, int ii, double d,
			int freq) {

		for (int i = 0; i <= freq; i++) {

			double parX = ((Math.random() * ii) - (ii / 2));
			double parY = ((Math.random() * ii) - (ii / 2));
			double parZ = ((Math.random() * ii) - (ii / 2));

			par1.worldObj.spawnParticle(s, par1.posX + parX, par1.posY + parY
					+ d, par1.posZ + parZ, 0.0D, 0.0D, 0.0D);
		}
	}

	public static void spawnParticlesOnLevel(String s, Entity par1, int ii,
			double d, int freq) {

		for (int i = 0; i <= freq; i++) {

			double parX = ((Math.random() * ii) - (ii / 2));
			double parZ = ((Math.random() * ii) - (ii / 2));

			par1.worldObj.spawnParticle(s, par1.posX + parX, par1.posY + d,
					par1.posZ + parZ, 0.0D, 0.0D, 0.0D);
		}
	}

}
