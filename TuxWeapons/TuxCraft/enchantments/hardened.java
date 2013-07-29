package TuxWeapons.TuxCraft.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class hardened extends Enchantment {

	public hardened(int par1, int par2) {

		super(par1, par2, EnumEnchantmentType.armor);
		this.setName("hardened");
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

		return 1;
	}

	@Override
	public String getTranslatedName(int par1) {

		String s = StatCollector.translateToLocal("Heavy");
		return s + " "
				+ StatCollector.translateToLocal("enchantment.level." + par1);
	}

}
