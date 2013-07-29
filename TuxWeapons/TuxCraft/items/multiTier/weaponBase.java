package TuxWeapons.TuxCraft.items.multiTier;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumHelper;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.weaponMaterials;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class weaponBase extends ItemSword {

	protected float weaponDamage;
	public String textureName;
	public double durabilityModifier;
	public weaponMaterials toolMaterial = weaponMaterials.Iron;
	private int colorA;
	private int colorB;
	public int weaponType;

	public weaponBase(int par1, weaponMaterials mat) {

		super(par1, EnumToolMaterial.IRON);

		this.toolMaterial = mat;
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.maxStackSize = 1;
		this.weaponDamage = 0;
	}

	@SideOnly(Side.CLIENT)
	private Icon field_94605_cw;
	@SideOnly(Side.CLIENT)
	private Icon field_94604_cx;

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {

		return true;
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {

		return par2 > 0 ? this.field_94604_cx : this.field_94605_cw;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {

		super.registerIcons(par1IconRegister);

		if (Arrays.asList(this.toolMaterial.altTextures).contains(
				textureName + "s")) {

			this.field_94605_cw = par1IconRegister
					.registerIcon(TuxWeaponsCore.modid + ":"
							+ TuxWeaponsCore.altTextureLoc
							+ this.getItemDisplayName(new ItemStack(this)));

			colorA = 16777215;

			this.field_94604_cx = par1IconRegister
					.registerIcon(TuxWeaponsCore.modid + ":" + "air");

			colorB = 16777215;

		}

		else {

			this.field_94605_cw = par1IconRegister
					.registerIcon(TuxWeaponsCore.modid + ":" + textureName);

			colorA = this.toolMaterial.color;

			this.field_94604_cx = par1IconRegister
					.registerIcon(TuxWeaponsCore.modid + ":" + textureName
							+ "_overlay");

			if (this.toolMaterial.usesSticks == false) {
				colorB = this.toolMaterial.miscStickColor;
			}

			else {
				colorB = 0x896727;
			}
		}

		this.itemIcon = par1IconRegister.registerIcon(TuxWeaponsCore.modid
				+ ":" + textureName + "_overlay");
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		if (this.toolMaterial.weaponToolTip != null) {
			par3List.add(this.toolMaterial.weaponToolTip);
		}

		if (this.toolMaterial.weaponToolTipln2 != null) {
			par3List.add(this.toolMaterial.weaponToolTipln2);
		}

		if (this.toolMaterial.weaponToolTipln3 != null) {
			par3List.add(this.toolMaterial.weaponToolTipln3);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {

		return (par1ItemStack.isItemEnchanted()) ? true
				: this.toolMaterial.hasEffect;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {

		if (par2 > 0) {

			return colorB;

		} else {

			return colorA;
		}
	}

	public float func_82803_g() {

		return this.toolMaterial.getDamageVsEntity();
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {

		if (this.toolMaterial.hasFire == true) {
			par2EntityLiving.setFire(this.toolMaterial.fireLvl);
			par1ItemStack.damageItem(1, par3EntityLiving);
			return true;
		}

		else if (this.toolMaterial.hasPoison == true) {
			par2EntityLiving.addPotionEffect(new PotionEffect(Potion.poison.id,
					this.toolMaterial.potionEffectAttackTime,
					this.toolMaterial.potionEffectAttackLvl));
			par1ItemStack.damageItem(1, par3EntityLiving);
			return true;
		}

		else if (this.toolMaterial.potionEffectAttack != 0) {
			double rand = Math.random() * 1;

			if (rand < this.toolMaterial.potionEffectAttackChance) {
				if (this.toolMaterial.potionEffectAttackPlayer == false) {
					par2EntityLiving.addPotionEffect(new PotionEffect(
							this.toolMaterial.potionEffectAttack,
							this.toolMaterial.potionEffectAttackTime,
							this.toolMaterial.potionEffectAttackLvl));
				} else {
					par3EntityLiving.addPotionEffect(new PotionEffect(
							this.toolMaterial.potionEffectAttack,
							this.toolMaterial.potionEffectAttackTime,
							this.toolMaterial.potionEffectAttackLvl));
				}
			}
			par1ItemStack.damageItem(1, par3EntityLiving);
			return true;
		}

		else {
			par1ItemStack.damageItem(1, par3EntityLiving);
			return true;
		}
	}

	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World,
			int par3, int par4, int par5, int par6,
			EntityLivingBase par7EntityLiving) {

		if (Block.blocksList[par3]
				.getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
			par1ItemStack.damageItem(2, par7EntityLiving);
		}

		return true;
	}

	@Override
	public float getDamageVsEntity(Entity par1Entity, ItemStack itemStack) {

		return weaponDamage;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {

		return true;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {

		return 72000;
	}

	@Override
	public int getItemEnchantability() {

		return this.toolMaterial.getEnchantability();
	}

	public String getToolMaterialName() {

		return this.toolMaterial.toString();
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {

		return this.toolMaterial.getCrafterID() == par2ItemStack.itemID ? true
				: super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public boolean isItemTool(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {

		if (par3Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par3Entity;
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().getItem() == this) {

				this.setMaxDamage((int) (toolMaterial.getMaxUses() * this.durabilityModifier));

				if (this.textureName == "hammer") {
					player.addPotionEffect(new PotionEffect(4, 1, 1));
				}

				if (this.toolMaterial.enchantment != null
						&& player.inventory.getCurrentItem().isItemEnchanted() == false) {
					par1ItemStack.addEnchantment(this.toolMaterial.enchantment,
							this.toolMaterial.enchantmentLvl);
				}

				if (this.toolMaterial.potionEffectHeld != 0) {
					player.addPotionEffect(new PotionEffect(
							this.toolMaterial.potionEffectHeld,
							this.toolMaterial.potionEffectHeldTime,
							this.toolMaterial.potionEffectHeldLvl));

				}
			}
		}
	}

	@Override
	public Multimap func_111205_h() {
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(),
				new AttributeModifier(field_111210_e, "Weapon modifier",
						this.weaponDamage, 0));
		return multimap;
	}
	
	@SideOnly(Side.CLIENT)
    protected String func_111208_A()
    {
        return "@&!^#";
    }
}
