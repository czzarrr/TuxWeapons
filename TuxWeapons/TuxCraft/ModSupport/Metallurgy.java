package TuxWeapons.TuxCraft.ModSupport;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.weaponMaterials;

public class Metallurgy
{
	
	//Base Metals
	public static weaponMaterials Copper = new weaponMaterials(1, 180, 5.0F, 1, 5, Item.itemsList[26507], null, 0xf18849, "Copper");
	public static weaponMaterials Bronze = new weaponMaterials(2, 250, 6.0F, 1, 9, Item.itemsList[26657], null, 0xe1af82, "Bronze");
	public static weaponMaterials Hepatizon = new weaponMaterials(2, 300, 10.0F, 1, 22, Item.itemsList[26707], null, 0x947a94, "Hepatizon");
	public static weaponMaterials DamascusSteel = new weaponMaterials(3, 500, 6.0F, 2, 19, Item.itemsList[26757], null, 0x996d4d, "Damascus Steel");
	public static weaponMaterials Angmallen = new weaponMaterials(2, 300, 10.0F, 2, 30, Item.itemsList[26807], null, 0xd6c761, "Angmallen");
	public static weaponMaterials Steel = new weaponMaterials(3, 750, 8.0F, 3, 18, Item.itemsList[26857], null, 0x996d4d, "Steel");
	
	//Precious Metals
	public static weaponMaterials Silver = new weaponMaterials(1, 25, 16.0F, 1, 20, Item.itemsList[26957], null, 0xfeffff, "Silver");
	public static weaponMaterials Platinum = new weaponMaterials(2, 100, 24.0F, 1, 50, Item.itemsList[27007], null, 0xbbdadf, "Platinum"); //
	public static weaponMaterials Brass = new weaponMaterials(0, 15, 12.0F, 1, 18, Item.itemsList[27057], null, 0xe3a444, "Brass");
	public static weaponMaterials Electrum = new weaponMaterials(1, 100, 20.0F, 1, 30, Item.itemsList[27107], null, 0xdfd0aa, "Electrum");
	
	//Nether Metals
	public static weaponMaterials Ignatius = new weaponMaterials(1, 200, 4.0F, 2, 15, Item.itemsList[27157], null, 0xffbd7b, "Ignatius").hasFire(true).weaponToolTip("\u00A7cIgnite I");
	public static weaponMaterials ShadowIron = new weaponMaterials(1, 300, 4.0F, 2, 3, Item.itemsList[27207], null, 0x8d7565, "Shadow Iron").potionEffectAttack(Potion.weakness.id).potionEffectAttackLvl(1).potionEffectAttackChance(0.3D).weaponToolTip("\u00A7cWeakness I");
	public static weaponMaterials Midasium = new weaponMaterials(3, 100, 10.0F, 3, 35, Item.itemsList[27307], null, 0xffcb7d, "Midasium").enchantment(Enchantment.looting).enchantmentLvl(1).weaponToolTip("Looting I");
	public static weaponMaterials Vyroxeres = new weaponMaterials(3, 300, 7.0F, 3, 16, Item.itemsList[27357], null, 0x55e001, "Vyroxeres").potionEffectAttack(Potion.poison.id).potionEffectAttackLvl(1).potionEffectAttackChance(0.3D).weaponToolTip("\u00A7cPoison I");
	public static weaponMaterials Ceruclase = new weaponMaterials(3, 500, 7.0F, 3, 18, Item.itemsList[27407], null, 0x65aac4, "Ceruclase").potionEffectAttack(Potion.moveSlowdown.id).potionEffectAttackLvl(1).potionEffectAttackChance(0.3D).weaponToolTip("\u00A7cSlowness");
	public static weaponMaterials Kalendrite = new weaponMaterials(4, 1000, 8.0F, 3, 20, Item.itemsList[27507], null, 0xc676d8, "Kalendrite").potionEffectAttack(Potion.regeneration.id).potionEffectAttackLvl(1).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).weaponToolTip("Regen");
	public static weaponMaterials Vulcanite = new weaponMaterials(5, 1500, 10.0F, 3, 20, Item.itemsList[27557], null, 0xff8448, "Vulcanite").hasFire(true).fireLvl(5).weaponToolTip("\u00A7cIgnite II");
	public static weaponMaterials Sanguinite = new weaponMaterials(6, 1750, 12.0F, 4, 25, Item.itemsList[27607], null, 0xffc978, "Sanguinite").potionEffectAttack(Potion.heal.id).potionEffectAttackLvl(2).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).weaponToolTip("\u00A7cWither I");
	public static weaponMaterials ShadowSteel = new weaponMaterials(2, 400, 6.0F, 3, 5, Item.itemsList[27657], null, 0xf18849, "Shadow Steel").potionEffectAttack(Potion.weakness.id).potionEffectAttackLvl(2).potionEffectAttackChance(0.3D).weaponToolTip("Weakness II");
	public static weaponMaterials Inolashite = new weaponMaterials(4, 900, 8.0F, 3, 25, Item.itemsList[27707], null, 0x67d0a3, "Inolashite").potionEffectAttack(Potion.moveSlowdown.id).potionEffectAttackLvl(2).hasPoison(true).potionEffectAttackChance(0.3D).weaponToolTip("Poison, Slowness");
	public static weaponMaterials Amordrine = new weaponMaterials(4, 1300, 14.0F, 3, 50, Item.itemsList[27757], null, 0xa98db1, "Amordrine").potionEffectAttack(Potion.heal.id).potionEffectAttackLvl(1).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).weaponToolTip("Healing");
	
	//Fantasy Metals
	public static weaponMaterials Prometheum = new weaponMaterials(1, 200, 4.0F, 1, 16, Item.itemsList[27807], null, 0x6a9865, "Prometheum");
	public static weaponMaterials DeepIron = new weaponMaterials(2, 250, 6.0F, 2, 14, Item.itemsList[27857], null, 0x57728a, "Deep Iron").weaponToolTip("\u00A7cBlindness I").potionEffectAttack(Potion.blindness.id).potionEffectAttackLvl(1).potionEffectAttackChance(0.3D);
	public static weaponMaterials BlackSteel = new weaponMaterials(2, 500, 8.0F, 2, 17, Item.itemsList[27957], null, 0x395679, "Black Steel").weaponToolTip("\u00A7cBlindness II").potionEffectAttack(Potion.blindness.id).potionEffectAttackLvl(2).potionEffectAttackChance(0.3D);
	public static weaponMaterials Oureclase = new weaponMaterials(3, 750, 8.0F, 2, 18, Item.itemsList[28007], null, 0xb76215, "Oureclase").potionEffectAttack(Potion.resistance.id).potionEffectAttackLvl(1).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).weaponToolTip("Resistance I");
	public static weaponMaterials AstralSilver = new weaponMaterials(4, 35, 12.0F, 1, 30, Item.itemsList[28107], null, 0xccb300, "Astral Silver").enchantment(Enchantment.looting).enchantmentLvl(1).weaponToolTip("Looting I");
	public static weaponMaterials Carmot = new weaponMaterials(4, 50, 12.0F, 1, 40, Item.itemsList[28107], null, 0xd9cd8c, "Carmot").enchantment(Enchantment.looting).enchantmentLvl(2).weaponToolTip("Looting II");
	public static weaponMaterials Mithril = new weaponMaterials(4, 1000, 9.0F, 3, 18, Item.itemsList[28257], null, 0x88f0f9, "Mithril").potionEffectAttack(3).potionEffectAttackLvl(1).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).weaponToolTip("Haste I");
	public static weaponMaterials Quicksilver = new weaponMaterials(4, 1100, 14.0F, 3, 20, Item.itemsList[28257], null, 0xd1efeb, "Quicksilver").potionEffectAttack(3).potionEffectAttackLvl(3).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).weaponToolTip("Speed I");
	public static weaponMaterials Haderoth = new weaponMaterials(4, 1250, 12.0F, 3, 19, Item.itemsList[28307], null, 0xdf5920, "Haderoth").potionEffectAttack(3).potionEffectAttackLvl(1).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).hasFire(true).weaponToolTip("\u00A7cHaste I, Ignite I");
	public static weaponMaterials Orichalcum = new weaponMaterials(5, 1350, 9.0F, 3, 20, Item.itemsList[28357], null, 0x6f9851, "Orichalcum").potionEffectAttack(Potion.resistance.id).potionEffectAttackLvl(2).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).weaponToolTip("\u00A7cResistance II");
	public static weaponMaterials Celenegil = new weaponMaterials(5, 1600, 14.0F, 3, 50, Item.itemsList[28407], null, 0x94cc48, "Celenegil").potionEffectAttack(Potion.resistance.id).potionEffectAttackLvl(4).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).weaponToolTip("Resistance III");
	public static weaponMaterials Adamantine = new weaponMaterials(6, 1550, 10.0F, 4, 22, Item.itemsList[28457], null, 0xaf0101, "Adamantine").potionEffectAttack(Potion.fireResistance.id).potionEffectAttackLvl(1).potionEffectAttackPlayer(true).potionEffectAttackChance(0.3D).hasFire(true).weaponToolTip("Fire Resist I, Ignite II");
	public static weaponMaterials Atlarus = new weaponMaterials(6, 1750, 10.0F, 4, 22, Item.itemsList[28507], null, 0xffe004, "Atlarus").potionEffectAttack(Potion.damageBoost.id).potionEffectAttackLvl(3).potionEffectAttackChance(0.3D).weaponToolTip("Strength II");
	public static weaponMaterials Tartarite = new weaponMaterials(7, 3000, 14.0F, 5, 25, Item.itemsList[28557], null, 0xffa680, "Tartarite").hasFire(true).fireLvl(10).potionEffectAttack(Potion.weakness.id).potionEffectAttackLvl(4).weaponToolTip("\u00A7cWither, Ignite II");
	
	//Ender Metals
	public static weaponMaterials Eximite = new weaponMaterials(3, 1000, 8.0F, 3, 25, Item.itemsList[28607], null, 0x7c5a96, "Eximite");
	public static weaponMaterials Desichalkos = new weaponMaterials(4, 1800, 10.0F, 4, 30, Item.itemsList[28707], null, 0x722fa8, "Desichalkos");
	
	public static void load(int i)
	{
		if(TuxWeaponsCore.metallurgy)
		{
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 1] = Copper;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 2] = Bronze;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 3] = Hepatizon;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 4] = DamascusSteel;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 5] = Angmallen;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 6] = Steel;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 7] = Silver;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 8] = Platinum;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 9] = Brass;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 10] = Electrum;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 11] = Ignatius;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 12] = ShadowIron;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 13] = Midasium;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 14] = Vyroxeres;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 15] = Ceruclase;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 16] = Kalendrite;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 17] = Vulcanite;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 18] = Sanguinite;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 19] = ShadowSteel;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 20] = Inolashite;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 21] = Amordrine;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 22] = Prometheum;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 23] = DeepIron;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 24] = BlackSteel;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 25] = Oureclase;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 26] = AstralSilver;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 27] = Carmot;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 28] = Mithril;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 29] = Quicksilver;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 30] = Haderoth;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 31] = Orichalcum;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 32] = Celenegil;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 33] = Adamantine;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 34] = Atlarus;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 35] = Tartarite;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 36] = Eximite;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 37] = Desichalkos;
		}
	}
}
