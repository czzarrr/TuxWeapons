package TuxWeapons.TuxCraft;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.EnumHelper;

public class weaponMaterials {

	public static weaponMaterials Wood = new weaponMaterials(0, 59, 2.0F, 0,
			15, null, Block.planks, 0x866526, "Wooden")
			.hasAltTextures(new String[] { "battleAxes", "hammers", "spears",
					"maces", "knives" });
	public static weaponMaterials Stone = new weaponMaterials(1, 131, 4.0F, 1,
			5, null, Block.cobblestone, 0x9a9a9a, "Stone")
			.hasAltTextures(new String[] { "battleAxes", "hammers", "spears",
					"maces", "knives" });
	public static weaponMaterials Iron = new weaponMaterials(2, 250, 6.0F, 2,
			14, Item.ingotIron, null, 0xffffff, "Iron");
	public static weaponMaterials Diamond = new weaponMaterials(3, 1561, 8.0F,
			3, 10, Item.diamond, null, 0x33ebcb, "Diamond");
	public static weaponMaterials Gold = new weaponMaterials(0, 32, 12.0F, 0,
			22, Item.ingotGold, null, 0xeaee57, "Golden");

	public static weaponMaterials NQuartz = new weaponMaterials(3, 300, 10.0F,
			2, 22, Item.netherQuartz, null, 0xecdc1b3, "Nether Quartz")
			.hasFire(true).usesSticks(false).miscStickColor(0x46262c)
			.miscStickCrafter(Item.netherrackBrick)
			.weaponToolTip("\u00A7cNether Flame I");
	public static weaponMaterials Lapis = new weaponMaterials(2, 200, 10.0F, 3,
			50, null, Block.blockLapis, 0x1c5bc1, "Lapis")
			.potionEffectHeld(Potion.resistance.id).potionEffectHeldLvl(2)
			.miscStickColor(0x636363).miscStickCrafter(Item.ingotIron)
			.usesSticks(false);
	public static weaponMaterials Emerald = new weaponMaterials(1, 150, 10.0F,
			2, 50, Item.emerald, null, 0x41f384, "Emerald")
			.enchantment(Enchantment.looting).enchantmentLvl(3).hasEffect(true);
	public static weaponMaterials Butter = new weaponMaterials(0, 32, 12.0F, 0,
			22, Item.goldenCarrot, null, 0xfeffb0, "Butter").weaponToolTip(
			"Slippery IV").weaponToolTipln2("Yummy X");
	public static weaponMaterials Jerry = new weaponMaterials(1, 131, 4.0F, 1,
			5, Item.slimeBall, null, 0x6baf5a, "Jerry")
			.weaponToolTip("Bouncy II").weaponToolTipln2("(Try jumping!)")
			.potionEffectHeld(Potion.jump.id).potionEffectHeldLvl(5);
	public static weaponMaterials Jaffa = new weaponMaterials(1, 131, 4.0F, 1,
			5, Item.cookie, null, 0xe79042, "Jaffa")
			.weaponToolTip("Scrumptious MMMMMMMMMI")
			.potionEffectHeld(Potion.confusion.id).potionEffectHeldLvl(1);
	public static weaponMaterials Quantum = new weaponMaterials(3, 1561, 8.0F,
			3, 10, null, Block.bedrock, 0xffffff, "Quantum")
			.weaponToolTip("Impossibleness-to-make IX x IX")
			.potionEffectHeld(Potion.regeneration.id).potionEffectHeldLvl(3)
			.miscStickColor(0x4aedd1).miscStickCrafter(Item.diamond)
			.usesSticks(false);
	public static weaponMaterials PilotPig = new weaponMaterials(1, 131, 4.0F,
			1, 5, Item.porkRaw, null, 0xeda7a4, "Pilot Pig's")
			.weaponToolTip("Now in your inventory!");

	public static int i = config.addedWeapons[0].length() > 4 ? config.addedWeapons.length
			: 0;

	public static weaponMaterials[] weaponMats = new weaponMaterials[5 + i
			+ TuxWeaponsCore.modTypes];
	// { Wood, Stone, Iron, Diamond, Gold };
	// weaponMats[0] = Wood;

	public final int harvestLevel;
	public final int maxUses;
	public final float efficiencyOnProperMaterial;
	public final int damageVsEntity;
	public final int enchantability;
	public Item crafterItem;
	public Block crafterBlock;
	public final int color;
	public final String toolName;

	// TODO
	public boolean hasFire;
	public boolean usesSticks;
	public int miscStickColor;
	public Item miscStickCrafter;
	public Enchantment enchantment;
	public int enchantmentLvl;
	public int potionEffectAttack;
	public int potionEffectAttackLvl;
	public int potionEffectHeld;
	public int potionEffectHeldLvl;
	public String weaponToolTip;
	public String weaponToolTipln2;
	public String weaponToolTipln3;
	public boolean hasEffect;
	public String[] altTextures;
	public int crafterDamage;
	public int potionEffectAttackTime;
	public boolean potionEffectAttackPlayer;
	public double potionEffectAttackChance;
	public boolean hasPoison;
	public int fireLvl;
	public int potionEffectHeldTime;

	public weaponMaterials(int par3, int par4, float par5, int par6, int par7,
			Item par8, Block par9, int par10, String par12) {

		this.harvestLevel = par3;
		this.maxUses = par4;
		this.efficiencyOnProperMaterial = par5;
		this.damageVsEntity = par6;
		this.enchantability = par7;
		this.crafterItem = par8;
		this.crafterBlock = par9;
		this.color = par10;
		this.toolName = par12;
		this.hasFire = false;
		this.usesSticks = true;
		this.miscStickColor = 0xffc100;
		this.miscStickCrafter = Item.blazeRod;
		this.enchantment = null;
		this.enchantmentLvl = 2;
		this.potionEffectAttack = 0;
		this.potionEffectAttackLvl = 3;
		this.potionEffectAttackTime = 100;
		this.potionEffectAttackPlayer = false;
		this.potionEffectAttackChance = 1.0D;
		this.potionEffectHeld = 0;
		this.potionEffectHeldLvl = 3;
		this.weaponToolTip = null;
		this.weaponToolTipln2 = null;
		this.weaponToolTipln3 = null;
		this.hasEffect = false;
		this.hasPoison = false;
		this.altTextures = new String[] {};
		this.crafterDamage = 0;
		this.fireLvl = 3;
		this.potionEffectHeldTime = 100;
	}

	public int getMaxUses() {

		return this.maxUses;
	}

	public float getEfficiencyOnProperMaterial() {

		return this.efficiencyOnProperMaterial;
	}

	public int getDamageVsEntity() {

		return this.damageVsEntity;
	}

	public int getHarvestLevel() {

		return this.harvestLevel;
	}

	public int getEnchantability() {

		return this.enchantability;
	}

	public Item getToolItemCraftingMaterial() {

		if (this.crafterItem == null) {
			return Item.appleGold;
		}

		return this.crafterItem;
	}

	public Block getToolBlockCraftingMaterial() {

		if (this.crafterBlock == null) {
			return Block.anvil;
		}

		return this.crafterBlock;
	}

	public int getToolColor() {

		return this.color;
	}

	public String getToolName() {

		return this.toolName;
	}

	public Boolean getHasFire() {

		return this.hasFire;
	}

	public Boolean getUsesSticks() {

		return this.usesSticks;
	}

	public int getMiscStickColor() {

		return this.miscStickColor;
	}

	public Item getMiscStickCrafter() {

		return this.miscStickCrafter;
	}

	public Enchantment getEnchantment() {

		return this.enchantment;
	}

	public int getEnchantmentLvl() {

		return this.enchantmentLvl;
	}

	public int getPotionEffectAttack() {

		return this.potionEffectAttack;
	}

	public int getPotionEffectAttackLvl() {

		return this.potionEffectAttackLvl;
	}

	public int getPotionEffectHeld() {

		return this.potionEffectAttack;
	}

	public int getPotionEffectHeldLvl() {

		return this.potionEffectAttackLvl;
	}

	public String getWeaponToolTip() {

		return this.weaponToolTip;
	}

	public String getWeaponToolTipln2() {

		return this.weaponToolTipln2;
	}

	public String getWeaponToolTipln3() {

		return this.weaponToolTipln3;
	}

	// TODO

	public weaponMaterials hasFire(boolean b) {

		this.hasFire = b;
		return this;
	}

	public weaponMaterials usesSticks(boolean b) {

		this.usesSticks = b;
		return this;
	}

	public weaponMaterials miscStickCrafter(Item i) {

		this.miscStickCrafter = i;
		return this;
	}

	public weaponMaterials enchantment(Enchantment e) {

		this.enchantment = e;
		return this;
	}

	public weaponMaterials enchantmentLvl(int i) {

		this.enchantmentLvl = i;
		return this;
	}

	public weaponMaterials potionEffectAttack(int i) {

		this.potionEffectAttack = i;
		return this;
	}

	public weaponMaterials potionEffectAttackLvl(int i) {

		this.potionEffectAttackLvl = i;
		return this;
	}

	public weaponMaterials potionEffectHeld(int i) {

		this.potionEffectHeld = i;
		return this;
	}

	public weaponMaterials potionEffectHeldLvl(int i) {

		this.potionEffectHeldLvl = i;
		return this;
	}

	public weaponMaterials weaponToolTip(String s) {

		this.weaponToolTip = s;
		return this;
	}

	public weaponMaterials weaponToolTipln2(String s) {

		this.weaponToolTipln2 = s;
		return this;
	}

	public weaponMaterials weaponToolTipln3(String s) {

		this.weaponToolTipln3 = s;
		return this;
	}

	public weaponMaterials miscStickColor(int i) {

		this.miscStickColor = i;
		return this;
	}

	public weaponMaterials hasEffect(boolean b) {

		this.hasEffect = b;
		return this;
	}

	public weaponMaterials hasAltTextures(String[] s) {

		this.altTextures = s;
		return this;
	}

	public weaponMaterials hasDamagedCrafter(int i) {

		this.crafterDamage = i;
		return this;
	}

	public weaponMaterials hasPoison(boolean b) {

		this.hasPoison = b;
		return this;
	}

	public weaponMaterials potionEffectAttackTime(int i) {

		this.potionEffectAttackTime = i;
		return this;
	}

	public weaponMaterials potionEffectAttackPlayer(boolean b) {

		this.potionEffectAttackPlayer = b;
		return this;
	}

	public weaponMaterials potionEffectAttackChance(double d) {

		this.potionEffectAttackChance = d;
		return this;
	}

	public weaponMaterials fireLvl(int i) {

		this.fireLvl = i;
		return this;
	}

	public int getCrafterID() {

		if (this.crafterItem == null) {
			return this.crafterBlock.blockID;
		}

		if (this.crafterBlock == null) {
			return this.crafterItem.itemID;
		}

		else {
			return 0;
		}
	}

}
