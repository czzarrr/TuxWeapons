package TuxWeapons.TuxCraft.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class swift extends Enchantment {

	public swift(int par1, int par2) {

		super(par1, par2, EnumEnchantmentType.weapon);
		this.setName("speed");
	}

	@Override
	public int getMinEnchantability(int par1) {

		return 20 + (par1 - 1) * 5;
	}

	@Override
	public int getMaxEnchantability(int par1) {

		return this.getMinEnchantability(par1) + 50;
	}

	@Override
	public int getMaxLevel() {

		return 2;
	}

	@Override
	public String getTranslatedName(int par1) {

		String s = StatCollector.translateToLocal("Swiftness");
		return s + " "
				+ StatCollector.translateToLocal("enchantment.level." + par1);
	}

	public boolean func_92089_a(ItemStack stack) {

		if (stack.getItem() instanceof ItemArmor) {
			return false;
		}
		return true;
	}

}
