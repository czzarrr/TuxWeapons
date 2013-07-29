package TuxWeapons.TuxCraft.items.multiTier;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.weaponMaterials;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class itemBattleAxe extends weaponBase {

	public itemBattleAxe(int par1, weaponMaterials par1EnumWeaponMaterial) {

		super(par1, par1EnumWeaponMaterial);
		this.toolMaterial = par1EnumWeaponMaterial;
		this.durabilityModifier = 0.5;
		this.textureName = "battleAxe";
		this.weaponDamage = 6 + toolMaterial.damageVsEntity;
	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {

		if (par2Block.blockID == Block.web.blockID) {
			return 15.0F;
		}

		else if (par2Block.blockID == Block.wood.blockID) {
			return this.toolMaterial.efficiencyOnProperMaterial;
		}

		else {
			Material material = par2Block.blockMaterial;
			return material != Material.plants && material != Material.vine
					&& material != Material.coral
					&& material != Material.leaves
					&& material != Material.pumpkin ? 1.0F : 1.5F;
		}

	}

	@Override
	public boolean canHarvestBlock(Block par1Block) {

		return par1Block.blockID == Block.web.blockID;
	}

}
