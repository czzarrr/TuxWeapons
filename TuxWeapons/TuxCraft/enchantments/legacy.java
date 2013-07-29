package TuxWeapons.TuxCraft.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class legacy extends Enchantment {

	public legacy(int par1, int par2) {

		super(par1, par2, EnumEnchantmentType.bow);
		this.setName("legacy");
	}

	@Override
	public int getMinEnchantability(int par1) {

		return 5 + (par1 - 1) * 20;
	}

	@Override
	public int getMaxEnchantability(int par1) {

		return this.getMinEnchantability(par1) + 50;
	}

	@Override
	public int getMaxLevel() {

		return 3;
	}

	@Override
	public String getTranslatedName(int par1) {

		String s = StatCollector.translateToLocal("Drawback");
		return s + " "
				+ StatCollector.translateToLocal("enchantment.level." + par1);
	}

}
