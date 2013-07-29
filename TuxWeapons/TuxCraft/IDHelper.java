package TuxWeapons.TuxCraft;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class IDHelper {

	public static int getFirstUnusedID(String s) {
		int i;

		if (s == "item") {

			boolean flag = false;

			i = 194;

			while (flag == false) {
				if (Item.itemsList[i] != null) {
					i++;
				}

				else {
					i++;
					flag = true;
				}
			}
		}

		else if (s == "enchant") {

			boolean flag = false;

			i = 50;

			while (flag == false) {
				if (Enchantment.enchantmentsList[i] != null) {
					i++;
				}

				else {
					i++;
					flag = true;
				}
			}
		}

		else if (s == "potion") {

			boolean flag = false;

			i = 22;

			while (flag == false) {
				if (Potion.potionTypes[i] != null) {
					i++;
				}

				else {
					i++;
					flag = true;
				}
			}
		}

		else {
			i = 0;
		}

		return i;
	}
}
