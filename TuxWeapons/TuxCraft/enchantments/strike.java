package TuxWeapons.TuxCraft.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class strike extends Enchantment {

	public strike(int par1, int par2) {

		super(par1, par2, EnumEnchantmentType.weapon);
		this.setName("strike");
	}

	@Override
	public int getMinEnchantability(int par1) {

		return 5 + 10 * (par1 - 1);
	}

	@Override
	public int getMaxEnchantability(int par1) {

		return super.getMinEnchantability(par1) + 50;
	}

	@Override
	public int getMaxLevel() {

		return 3;
	}

	@Override
	public String getTranslatedName(int par1) {

		String s = StatCollector.translateToLocal("Strike");
		return s + " "
				+ StatCollector.translateToLocal("enchantment.level." + par1);
	}

}
