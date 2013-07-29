package TuxWeapons.TuxCraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFlameAura extends Entity {

	private EntityLivingBase owner;

	public EntityFlameAura(World par1World) {
		super(par1World);
		this.renderDistanceWeight = 0.0D;
		this.setSize(0.5F, 0.5F);
	}

	public EntityFlameAura(World par1World, double par2, double par4,
			double par6) {
		super(par1World);
		this.renderDistanceWeight = 0D;
		this.setSize(0.5F, 0.5F);
		this.setPosition(par2, par4, par6);
		this.yOffset = 0.0F;
	}

	public EntityFlameAura(World par1World, EntityLivingBase player) {
		super(par1World);
		this.owner = player;
		this.setPosition(owner.posX, owner.posY, owner.posZ);
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (owner != null) {
			this.posX = owner.posX;
			this.posY = owner.posY;
			this.posZ = owner.posY;
		}

		Entity closestEntity;

		if(!(owner instanceof EntityMob))
		{
			closestEntity = this.worldObj.findNearestEntityWithinAABB(
					EntityMob.class,
					this.boundingBox.expand((double) 4, 3.0D, (double) 4), this);
		}
		
		else
		{
			closestEntity = this.worldObj.findNearestEntityWithinAABB(
					EntityLiving.class,
					this.boundingBox.expand((double) 4, 3.0D, (double) 4), this);
		}

		if (closestEntity != null) {
			closestEntity.setFire(5);
		}

		this.setDead();
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setShort("xTile", (short) this.posX);
		par1NBTTagCompound.setShort("yTile", (short) this.posY);
		par1NBTTagCompound.setShort("zTile", (short) this.posZ);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		this.posX = par1NBTTagCompound.getShort("xTile");
		this.posY = par1NBTTagCompound.getShort("yTile");
		this.posY = par1NBTTagCompound.getShort("zTile");
	}

}
