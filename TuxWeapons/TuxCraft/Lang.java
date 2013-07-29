package TuxWeapons.TuxCraft;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class Lang extends LanguageRegistry {

	public static void addName(String key, String lang, String value) {
		LanguageRegistry.instance().addStringLocalization(key, lang, value);
	}

	public static void potion(String potionname, String effectname, String name) {
		String lang = "en_US";

		addName(potionname, lang, effectname);
		addName(potionname + ".postfix", lang, name);
		addName(potionname + ".description", lang, name);
	}
}
