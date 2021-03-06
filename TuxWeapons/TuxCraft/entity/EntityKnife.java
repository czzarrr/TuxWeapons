package TuxWeapons.TuxCraft.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.weaponMaterials;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityKnife extends Entity implements IProjectile {

	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private int inTile = 0;
	private int inData = 0;
	private boolean inGround = false;

	/** 1 if the player can pick up the arrow */
	// public int canBePickedUp = 1;

	/** Seems to be some sort of timer for animating an arrow. */
	public int KnifeShake = 0;

	/** The owner of this arrow. */
	public Entity shootingEntity;
	private int ticksInGround;
	public int spin;
	private int ticksInAir = 0;
	private double damage;

	/** The amount of knockback an arrow applies when it hits a mob. */
	private int knockbackStrength;
	private weaponMaterials toolMaterial;

	public EntityKnife(World par1World) {

		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
	}

	public EntityKnife(World par1World, double par2, double par4, double par6) {

		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.setPosition(par2, par4, par6);
		this.yOffset = 0.0F;
		this.spin = 0;
	}

	public EntityKnife(World par1World, EntityLiving par2EntityLiving,
			EntityLiving par3EntityLiving, float par4, float par5) {

		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLiving;

		/**
		 * if (par2EntityLiving instanceof EntityPlayer) { this.canBePickedUp =
		 * 1; }
		 */

		this.posY = par2EntityLiving.posY + par2EntityLiving.getEyeHeight()
				- 0.10000000149011612D;
		double d0 = par3EntityLiving.posX - par2EntityLiving.posX;
		double d1 = par3EntityLiving.boundingBox.minY
				+ (par3EntityLiving.height / 3.0F) - this.posY;
		double d2 = par3EntityLiving.posZ - par2EntityLiving.posZ;
		double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);
		this.spin = 0;

		if (d3 >= 1.0E-7D) {
			float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float) -(Math.atan2(d1, d3) * 180.0D / Math.PI);
			double d4 = d0 / d3;
			double d5 = d2 / d3;
			this.setLocationAndAngles(par2EntityLiving.posX + d4, this.posY,
					par2EntityLiving.posZ + d5, f2, f3);
			this.yOffset = 0.0F;
			float f4 = (float) d3 * 0.2F;
			this.setThrowableHeading(d0, d1 + f4, d2, par4, par5);
		}
	}

	public EntityKnife(World par1World, EntityLivingBase par2EntityLiving,
			float par3, Item par4Item, int par5, weaponMaterials par6WeaponMat) {

		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLiving;
		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY
				+ par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ,
				par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
		this.posX -= (MathHelper.cos(this.rotationYaw / 180.0F
				* (float) Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (MathHelper.sin(this.rotationYaw / 180.0F
				* (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F
				* (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F
				* (float) Math.PI));
		this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F
				* (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F
				* (float) Math.PI));
		this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F
				* (float) Math.PI);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ,
				par3 * 1.5F, 1.0F);
		this.spin = 0;
		this.damage = par5 + 1;
		this.toolMaterial = par6WeaponMat;

		NBTTagCompound par1NBTTagCompound = this.getEntityData();
		par1NBTTagCompound.setCompoundTag("Stack",
				new ItemStack(par4Item).writeToNBT(new NBTTagCompound()));
	}

	@Override
	protected void entityInit() {

		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z
	 * direction.
	 */
	@Override
	public void setThrowableHeading(double par1, double par3, double par5,
			float par7, float par8) {

		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5
				* par5);
		par1 /= f2;
		par3 /= f2;
		par5 /= f2;
		par1 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1)
				* 0.007499999832361937D * par8;
		par3 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1)
				* 0.007499999832361937D * par8;
		par5 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1)
				* 0.007499999832361937D * par8;
		par1 *= par7;
		par3 *= par7;
		par5 *= par7;
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;
		float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1,
				par5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3,
				f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	public void setPositionAndRotation2(double par1, double par3, double par5,
			float par7, float par8, int par9) {

		this.setPosition(par1, par3, par5);
		this.setRotation(par7, par8);
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	public void setVelocity(double par1, double par3, double par5) {

		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1,
					par5) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(
					par3, f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ,
					this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {

		super.onUpdate();

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX
					+ this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(
					this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(
					this.motionY, f) * 180.0D / Math.PI);
		}

		int i = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

		if (i > 0) {
			Block.blocksList[i].setBlockBoundsBasedOnState(this.worldObj,
					this.xTile, this.yTile, this.zTile);
			AxisAlignedBB axisalignedbb = Block.blocksList[i]
					.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile,
							this.yTile, this.zTile);

			if (axisalignedbb != null
					&& axisalignedbb.isVecInside(this.worldObj
							.getWorldVec3Pool().getVecFromPool(this.posX,
									this.posY, this.posZ))) {
				this.inGround = true;
			}
		}

		if (this.KnifeShake > 0) {
			--this.KnifeShake;
		}

		if (this.inGround) {
			this.spin = 0;
			int j = this.worldObj
					.getBlockId(this.xTile, this.yTile, this.zTile);
			int k = this.worldObj.getBlockMetadata(this.xTile, this.yTile,
					this.zTile);

			if (j == this.inTile && k == this.inData) {
				++this.ticksInGround;
				this.spin = 0;

				if (this.ticksInGround == 1200) {
					this.setDead();
				}
			} else {
				this.inGround = false;
				this.motionX *= (this.rand.nextFloat() * 0.2F);
				this.motionY *= (this.rand.nextFloat() * 0.2F);
				this.motionZ *= (this.rand.nextFloat() * 0.2F);
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			}
		} else {
			++this.ticksInAir;
			++this.spin;
			Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(
					this.posX, this.posY, this.posZ);
			Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(
					this.posX + this.motionX, this.posY + this.motionY,
					this.posZ + this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj
					.rayTraceBlocks_do_do(vec3, vec31, false, true);
			vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX,
					this.posY, this.posZ);
			vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(
					this.posX + this.motionX, this.posY + this.motionY,
					this.posZ + this.motionZ);

			if (movingobjectposition != null) {
				vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(
						movingobjectposition.hitVec.xCoord,
						movingobjectposition.hitVec.yCoord,
						movingobjectposition.hitVec.zCoord);
			}

			Entity entity = null;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(
					this,
					this.boundingBox.addCoord(this.motionX, this.motionY,
							this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			int l;
			float f1;

			for (l = 0; l < list.size(); ++l) {
				Entity entity1 = (Entity) list.get(l);

				if (entity1.canBeCollidedWith()
						&& (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
					f1 = 0.3F;
					AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(
							f1, f1, f1);
					MovingObjectPosition movingobjectposition1 = axisalignedbb1
							.calculateIntercept(vec3, vec31);

					if (movingobjectposition1 != null) {
						double d1 = vec3
								.distanceTo(movingobjectposition1.hitVec);

						if (d1 < d0 || d0 == 0.0D) {
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}

			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}

			if (movingobjectposition != null
					&& movingobjectposition.entityHit != null
					&& movingobjectposition.entityHit instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) movingobjectposition.entityHit;

				if (entityplayer.capabilities.disableDamage
						|| this.shootingEntity instanceof EntityPlayer
						&& !((EntityPlayer) this.shootingEntity)
								.func_96122_a(entityplayer)) {
					movingobjectposition = null;
				}
			}

			float f2;
			float f3;

			if (movingobjectposition != null) {
				if (movingobjectposition.entityHit != null) {

					hitEntity(this.toolMaterial, this.shootingEntity,
							movingobjectposition.entityHit);

					f2 = MathHelper.sqrt_double(this.motionX * this.motionX
							+ this.motionY * this.motionY + this.motionZ
							* this.motionZ);
					int i1 = MathHelper.ceiling_double_int(f2 * this.damage);

					DamageSource damagesource = null;

					if (this.shootingEntity == null) {
						damagesource = DamageSource.causeThrownDamage(this,
								this);
					} else {
						damagesource = DamageSource.causeThrownDamage(this,
								this.shootingEntity);
					}

					if (this.isBurning()
							&& !(movingobjectposition.entityHit instanceof EntityEnderman)) {
						movingobjectposition.entityHit.setFire(5);
					}

					if (movingobjectposition.entityHit.attackEntityFrom(
							damagesource, i1)) {
						this.setDead();
						this.kill();
						this.isDead = true;

						if (movingobjectposition.entityHit instanceof EntityLiving) {
							EntityLiving entityliving = (EntityLiving) movingobjectposition.entityHit;

							if (this.knockbackStrength > 0) {
								f3 = MathHelper.sqrt_double(this.motionX
										* this.motionX + this.motionZ
										* this.motionZ);

								if (f3 > 0.0F) {
									movingobjectposition.entityHit.addVelocity(
											this.motionX
													* this.knockbackStrength
													* 0.6000000238418579D / f3,
											0.1D, this.motionZ
													* this.knockbackStrength
													* 0.6000000238418579D / f3);
								}
							}

							if (this.shootingEntity != null) {
								EnchantmentThorns.func_92096_a(
										this.shootingEntity, entityliving,
										this.rand);
							}

							if (this.shootingEntity != null
									&& movingobjectposition.entityHit != this.shootingEntity
									&& movingobjectposition.entityHit instanceof EntityPlayer
									&& this.shootingEntity instanceof EntityPlayerMP) {
								((EntityPlayerMP) this.shootingEntity).playerNetServerHandler
										.sendPacketToPlayer(new Packet70GameEvent(
												6, 0));
							}
						}

						this.playSound("random.bowhit", 1.0F,
								1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

						if (!(movingobjectposition.entityHit instanceof EntityEnderman)) {
							// this.setDead();
						}
					} else {
						this.motionX *= -0.10000000149011612D;
						this.motionY *= -0.10000000149011612D;
						this.motionZ *= -0.10000000149011612D;
						this.rotationYaw += 180.0F;
						this.prevRotationYaw += 180.0F;
						this.ticksInAir = 0;
					}
				}

				else {
					// TODO
					// this.entityDropItem(itemstack, 0.0F);
					// java.lang.System.out.println("Ouch!");
					// this.setDead();
					this.xTile = movingobjectposition.blockX;
					this.yTile = movingobjectposition.blockY;
					this.zTile = movingobjectposition.blockZ;
					this.inTile = this.worldObj.getBlockId(this.xTile,
							this.yTile, this.zTile);
					this.inData = this.worldObj.getBlockMetadata(this.xTile,
							this.yTile, this.zTile);
					this.motionX = (float) (movingobjectposition.hitVec.xCoord - this.posX);
					this.motionY = (float) (movingobjectposition.hitVec.yCoord - this.posY);
					this.motionZ = (float) (movingobjectposition.hitVec.zCoord - this.posZ);
					f2 = MathHelper.sqrt_double(this.motionX * this.motionX
							+ this.motionY * this.motionY + this.motionZ
							* this.motionZ);
					this.posX -= this.motionX / f2 * 0.05000000074505806D;
					this.posY -= this.motionY / f2 * 0.05000000074505806D;
					this.posZ -= this.motionZ / f2 * 0.05000000074505806D;
					this.playSound("random.bowhit", 1.0F,
							1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					this.inGround = true;
					this.KnifeShake = 7;
					this.setIsCritical(false);

					if (this.inTile != 0) {
						Block.blocksList[this.inTile]
								.onEntityCollidedWithBlock(this.worldObj,
										this.xTile, this.yTile, this.zTile,
										this);
					}
				}
			}

			if (this.getIsCritical()) {
				for (l = 0; l < 4; ++l) {
					this.worldObj.spawnParticle("crit", this.posX
							+ this.motionX * l / 4.0D, this.posY + this.motionY
							* l / 4.0D, this.posZ + this.motionZ * l / 4.0D,
							-this.motionX, -this.motionY + 0.2D, -this.motionZ);
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			f2 = MathHelper.sqrt_double(this.motionX * this.motionX
					+ this.motionZ * this.motionZ);
			this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

			for (this.rotationPitch = (float) (Math.atan2(this.motionY, f2) * 180.0D / Math.PI); this.rotationPitch
					- this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch
					+ (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw
					+ (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float f4 = 0.99F;
			f1 = 0.05F;

			if (this.isInWater()) {
				for (int j1 = 0; j1 < 4; ++j1) {
					f3 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX
							- this.motionX * f3, this.posY - this.motionY * f3,
							this.posZ - this.motionZ * f3, this.motionX,
							this.motionY, this.motionZ);
				}

				f4 = 0.8F;
			}

			this.motionX *= f4;
			this.motionY *= f4;
			this.motionZ *= f4;
			this.motionY -= f1;
			this.setPosition(this.posX, this.posY, this.posZ);
			this.doBlockCollisions();
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {

		par1NBTTagCompound.setShort("xTile", (short) this.xTile);
		par1NBTTagCompound.setShort("yTile", (short) this.yTile);
		par1NBTTagCompound.setShort("zTile", (short) this.zTile);
		par1NBTTagCompound.setByte("inTile", (byte) this.inTile);
		par1NBTTagCompound.setByte("inData", (byte) this.inData);
		par1NBTTagCompound.setByte("shake", (byte) this.KnifeShake);
		par1NBTTagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		par1NBTTagCompound.setDouble("damage", this.damage);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {

		this.xTile = par1NBTTagCompound.getShort("xTile");
		this.yTile = par1NBTTagCompound.getShort("yTile");
		this.zTile = par1NBTTagCompound.getShort("zTile");
		this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
		this.inData = par1NBTTagCompound.getByte("inData") & 255;
		this.KnifeShake = par1NBTTagCompound.getByte("shake") & 255;
		this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

		if (par1NBTTagCompound.hasKey("damage")) {
			this.damage = par1NBTTagCompound.getDouble("damage");
		}

		// NBTTagCompound nbttagcompound1 =
		// par1NBTTagCompound.getCompoundTag("Item");
		// itemstack(ItemStack.loadItemStackFromNBT(nbttagcompound1));

		/**
		 * if (par1NBTTagCompound.hasKey("pickup")) { this.canBePickedUp =
		 * par1NBTTagCompound.getByte("pickup"); } else if
		 * (par1NBTTagCompound.hasKey("player")) { this.canBePickedUp =
		 * par1NBTTagCompound.getBoolean("player") ? 1 : 0; }
		 */
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {

		if (this.inGround && this.KnifeShake <= 0) {

			this.playSound(
					"random.pop",
					0.2F,
					((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			par1EntityPlayer.onItemPickup(this, 1);
			if (par1EntityPlayer.capabilities.isCreativeMode == false) {
				NBTTagCompound par1NBTTagCompound = this.getEntityData();
				ItemStack stack = ItemStack
						.loadItemStackFromNBT(par1NBTTagCompound
								.getCompoundTag("Stack"));

				par1EntityPlayer.inventory.addItemStackToInventory(stack);
				// TODO
			}

			this.setDead();
			this.kill();
			this.isDead = true;
		}
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they
	 * walk on. used for spiders and wolves to prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking() {

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {

		return 0.0F;
	}

	public void setDamage(double par1) {

		this.damage = par1;
	}

	public double getDamage() {

		return this.damage;
	}

	/**
	 * Sets the amount of knockback the arrow applies when it hits a mob.
	 */
	public void setKnockbackStrength(int par1) {

		this.knockbackStrength = par1;
	}

	/**
	 * If returns false, the item will not inflict any damage against entities.
	 */
	@Override
	public boolean canAttackWithItem() {

		return false;
	}

	/**
	 * Whether the arrow has a stream of critical hit particles flying behind
	 * it.
	 */
	public void setIsCritical(boolean par1) {

		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 1)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -2)));
		}
	}

	/**
	 * Whether the arrow has a stream of critical hit particles flying behind
	 * it.
	 */
	public boolean getIsCritical() {

		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		return (b0 & 1) != 0;
	}

	public void hitEntity(weaponMaterials weaponMat, Entity par1Entity,
			Entity par2Entity) {

		if (weaponMat != null && par2Entity instanceof EntityLivingBase) {
			EntityPlayer player = (EntityPlayer) par1Entity;
			EntityLivingBase entityHit = (EntityLivingBase) par2Entity;

			if (weaponMat.hasFire == true) {
				entityHit.setFire(weaponMat.fireLvl);
			}

			else if (weaponMat.hasPoison == true) {
				entityHit.addPotionEffect(new PotionEffect(Potion.poison.id,
						weaponMat.potionEffectAttackTime,
						weaponMat.potionEffectAttackLvl));
			}

			else if (weaponMat.potionEffectAttack != 0) {
				double rand = ((Math.random() * 1));

				if (rand < weaponMat.potionEffectAttackChance) {
					if (weaponMat.potionEffectAttackPlayer == false) {
						entityHit.addPotionEffect(new PotionEffect(
								weaponMat.potionEffectAttack,
								weaponMat.potionEffectAttackTime,
								weaponMat.potionEffectAttackLvl));
					} else {
						player.addPotionEffect(new PotionEffect(
								weaponMat.potionEffectAttack,
								weaponMat.potionEffectAttackTime,
								weaponMat.potionEffectAttackLvl));
					}
				}
			}

			else {
			}
		}
	}

	public static ItemStack getStack(EntityKnife hook) {
		NBTTagCompound par1NBTTagCompound = hook.getEntityData();
		ItemStack stack = ItemStack.loadItemStackFromNBT(par1NBTTagCompound
				.getCompoundTag("Stack"));

		return stack;
	}
}