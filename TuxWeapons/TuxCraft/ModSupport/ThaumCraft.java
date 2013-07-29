package TuxWeapons.TuxCraft.ModSupport;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.weaponMaterials;

public class ThaumCraft
{
	
	public static weaponMaterials Thaumium = new weaponMaterials(3, 400, 7.0F, 2, 22, Item.itemsList[25263], null, 0x6e5ca8, "Thaumium").hasDamagedCrafter(2);
	
	public static void load(int i)
	{
		
		/**if(new File(TuxWeaponsCore.thaumLoc).exists())
		{
			weaponMaterials.weaponMats[4 + weaponMaterials.i + i + 1] = Thaumium;
		}*/
	}
}
