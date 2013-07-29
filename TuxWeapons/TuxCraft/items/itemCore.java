package TuxWeapons.TuxCraft.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class itemCore extends Item {

	private String textureName;

	public itemCore(int par1, String s) {

		super(par1);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.maxStackSize = 64;
		this.textureName = s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(TuxWeaponsCore.modid + ":"
				+ textureName);
	}

}
