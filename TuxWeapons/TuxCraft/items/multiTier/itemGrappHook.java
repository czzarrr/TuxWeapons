package TuxWeapons.TuxCraft.items.multiTier;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.weaponMaterials;
import TuxWeapons.TuxCraft.entity.EntityGrappHook;

public class itemGrappHook extends multiBase {

	public static EntityGrappHook grappHookEntity = null;

	public itemGrappHook(int par1, weaponMaterials par1EnumWeaponMaterial,
			int par4) {

		super(par1, par1EnumWeaponMaterial);
		this.toolMaterial = par1EnumWeaponMaterial;
		this.maxStackSize = 64;
		this.textureName = "grappHook";
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		if (grappHookEntity == null) {
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F,
					0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			EntityGrappHook var8 = new EntityGrappHook(par2World,
					par3EntityPlayer, 0.7F, 5, this);

			if (par2World.isRemote) {
				par2World.spawnEntityInWorld(var8);
				itemGrappHook.grappHookEntity = var8;
			}

			if (!par3EntityPlayer.capabilities.isCreativeMode)
				par3EntityPlayer.inventory.consumeInventoryItem(this.itemID);

			par3EntityPlayer.swingItem();

		}

		else {

			if (par3EntityPlayer.onGround == false && grappHookEntity.inGround
					&& grappHookEntity.BoltShake <= 0) {

				double relPosX = (int) Math.round(grappHookEntity.posX
						- par3EntityPlayer.posX);
				double relPosY = (int) Math.round(grappHookEntity.posY
						- (par3EntityPlayer.posY - 2));
				double relPosZ = (int) Math.round(grappHookEntity.posZ
						- par3EntityPlayer.posZ);

				par3EntityPlayer.motionX = relPosX / 10;
				par3EntityPlayer.motionY = (relPosY + 5.5) / 10; // height
				par3EntityPlayer.motionZ = relPosZ / 10;

			}

			if (par2World.isRemote) {
				grappHookEntity.recall();
				grappHookEntity = null;
			}

			par3EntityPlayer.swingItem();

		}

		return par1ItemStack;

	}
}