package TuxWeapons.TuxCraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityTemp extends Entity {

	private EntityLivingBase owner;
	private int id;

	public EntityTemp(World par1World) {
		super(par1World);
		this.renderDistanceWeight = 0.0D;
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityTemp(World par1World, EntityLivingBase player, int i) {
		super(par1World);
		this.owner = player;
		this.id = i;
		this.renderDistanceWeight = 0.0D;
		this.setSize(0.5F, 0.5F);
		this.setPosition(owner.posX, owner.posY, owner.posZ);
		this.yOffset = 0.0F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (owner != null) {
			this.posX = owner.posX;
			this.posY = owner.posY;
			this.posZ = owner.posY;
		}
		
		if(this.id == 0)
		{
			owner.addPotionEffect(new PotionEffect(
					Potion.resistance.id, 500, 4));
			owner.addPotionEffect(new PotionEffect(
					Potion.fireResistance.id, 500, 4));
			owner.addPotionEffect(new PotionEffect(
					Potion.moveSlowdown.id, 500, 2));
		}
		
		if(this.id == 1)
		{
			owner.curePotionEffects(new ItemStack(
					Item.bucketMilk));
		}

		this.setDead();
	}
	
	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

}
