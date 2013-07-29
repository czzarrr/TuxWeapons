package TuxWeapons.TuxCraft.ModSupport;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.weaponMaterials;

public class TwilightForest
{
	
	public static weaponMaterials Ironwood = new weaponMaterials(2, 512, 6.5F, 2, 25, Item.itemsList[27974], null, 0x887c7c, "Ironwood").enchantment(Enchantment.knockback).enchantmentLvl(1).weaponToolTip("Knockback I").hasEffect(true);
	public static weaponMaterials Fiery = new weaponMaterials(4, 1024, 9.0F, 4, 10, Item.itemsList[27991], null, 0x3c2323, "Fiery").usesSticks(false).miscStickCrafter(Item.blazeRod).miscStickColor(0xfbcb24).hasFire(true).hasEffect(true).weaponToolTip("Fire Aspect I");
	public static weaponMaterials Stealeaf = new weaponMaterials(3, 131, 8.0F, 3, 9, Item.itemsList[27998], null, 0x6da25e, "Stealeaf").enchantment(Enchantment.looting).enchantmentLvl(2).weaponToolTip("Looting II").hasEffect(true);
	
	public static void load(int i)
	{
		
		/**if(new File(TuxWeaponsCore.twilightForestLoc).exists())
		{
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 1] = Ironwood;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 2] = Fiery;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 3] = Stealeaf;
		}*/
	}
}
