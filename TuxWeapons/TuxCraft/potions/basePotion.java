package TuxWeapons.TuxCraft.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.potion.Potion;

public class basePotion extends Potion {

	private int statusIconIndex;

	protected basePotion(int par1, boolean par2, int par3) {
		super(par1, par2, par3);
		// TODO Auto-generated constructor stub
	}

	@SideOnly(Side.CLIENT)
	public Potion setTWIconIndex(int i, int j) {
		this.statusIconIndex = i + j * 8;
        return this;
	}
	
	@SideOnly(Side.CLIENT)
    public int getTWStatusIconIndex()
    {
        return this.statusIconIndex;
    }
}
