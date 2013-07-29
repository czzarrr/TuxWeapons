package TuxWeapons.TuxCraft.ModSupport;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.weaponMaterials;

public class BiomesOPlenty
{
	
	public static weaponMaterials Mud = new weaponMaterials(0, 32, 0.5F, 0, 1, Item.itemsList[21267], null, 0x654b2d, "Mud").hasAltTextures(new String[]{"shields", "battleAxes", "grappHooks", "spears", "maces", "knives", "hammers"});
	public static weaponMaterials Amethyst = new weaponMaterials(0, 32, 0.5F, 0, 1, Item.itemsList[21266], null, 0xff54ff, "Amethyst").hasDamagedCrafter(2).usesSticks(false).miscStickCrafter(Item.ingotIron).miscStickColor(0xa9a9a9).hasAltTextures(new String[]{"shields", "battleAxes", "grappHooks", "spears", "maces", "knives", "hammers"});
	
	public static void load(int i)
	{
		
		if(TuxWeaponsCore.biomesOPlenty)
		{
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 1] = Mud;
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 2] = Amethyst;
		}
	}
}
