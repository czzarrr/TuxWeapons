package TuxWeapons.TuxCraft.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class eagleEye extends Enchantment {

	public eagleEye(int par1, int par2) {

		super(par1, par2, EnumEnchantmentType.bow);
		this.setName("eagleEye");
	}

	@Override
	public int getMinEnchantability(int par1) {

		return 15 + (par1 - 1) * 20;
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

		String s = StatCollector.translateToLocal("Hawkeye");
		return s + " "
				+ StatCollector.translateToLocal("enchantment.level." + par1);
	}

}
