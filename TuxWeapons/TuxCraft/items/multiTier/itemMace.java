package TuxWeapons.TuxCraft.items.multiTier;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.weaponMaterials;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class itemMace extends weaponBase {

	public itemMace(int par1, weaponMaterials par1EnumWeaponMaterial) {

		super(par1, par1EnumWeaponMaterial);
		this.toolMaterial = par1EnumWeaponMaterial;
		this.durabilityModifier = 0.7;
		this.textureName = "mace";
		this.weaponDamage = 1 + toolMaterial.damageVsEntity;

	}

	public float getWeaponDamage() {
		return this.weaponDamage;
	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {

		return 1.5F;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {

		return EnumAction.none;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		return par1ItemStack;
	}

	@Override
	public Multimap func_111205_h() {
		Multimap multimap = HashMultimap.create();
		return multimap;
	}
}
