package TuxWeapons.TuxCraft.items;

import TuxWeapons.TuxCraft.TuxWeaponsCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.EnumHelper;

public class itemFryingPan extends ItemSword {

	static EnumToolMaterial fryingPan = EnumHelper.addToolMaterial("PAN", 9001, 9001, 9001.0F, 8997, 9001);
	
	public itemFryingPan(int par1) {
		super(par1, fryingPan);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabAllSearch);
		this.setMaxDamage(-1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(TuxWeaponsCore.modid + ":"
				+ "fryingPan");
	}
	
	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {

		return 9001.0F;
	}
}
