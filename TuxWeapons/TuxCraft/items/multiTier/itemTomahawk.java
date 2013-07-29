package TuxWeapons.TuxCraft.items.multiTier;

import TuxWeapons.TuxCraft.weaponMaterials;

public class itemTomahawk extends weaponBase {

	public itemTomahawk(int par1, weaponMaterials mat) {
		super(par1, mat);
		this.toolMaterial = mat;
		this.durabilityModifier = 1.5;
		this.textureName = "tomahawk";
		this.weaponDamage = 2 + toolMaterial.damageVsEntity;
	}

}
