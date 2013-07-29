package TuxWeapons.TuxCraft.entity;

import TuxWeapons.TuxCraft.config;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityDynamite extends EntityThrowable {

	protected boolean inGround = false;
	public int throwableShake = 0;

	private int ticksInAir = 0;
	private int fuse = 30;

	double bounceFactor;

	public EntityDynamite(World par1World) {

		super(par1World);
		bounceFactor = 20;
	}

	public EntityDynamite(World par1World, EntityPlayer par2EntityLiving) {

		super(par1World, par2EntityLiving);
	}

	public EntityDynamite(World par1World, double par2, double par4, double par6) {

		super(par1World, par2, par4, par6);
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {

		if (par1MovingObjectPosition.entityHit != null) {
			byte b0 = 0;

			par1MovingObjectPosition.entityHit
					.attackEntityFrom(
							DamageSource.causeThrownDamage(this,
									this.getThrower()), b0);
		}

		fuse = fuse - 10;

	}

	@Override
	public void onUpdate() {

		double prevVelX = motionX;
		double prevVelY = motionY;
		double prevVelZ = motionZ;
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		moveEntity(motionX, motionY, motionZ);
		++this.ticksInAir;

		// Take into account bouncing (normal displacement just sets them to 0)
		if (motionX != prevVelX) {
			motionX = -bounceFactor * prevVelX;
		}

		if (motionY != prevVelY) {
			motionY = -bounceFactor * prevVelY;
		} else {
			motionY -= 0.04; // Apply gravity.
		}

		if (motionZ != prevVelZ) {
			motionZ = -bounceFactor * prevVelZ;
		}

		// Air friction
		motionX *= 0.9;
		motionY *= 0.9;
		motionZ *= 0.9;

		if (ticksInAir >= fuse) {
			if(!this.worldObj.isRemote)this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2, config.griefEnabled);
			this.setDead();
			this.kill();
		}

	}

	@Override
	protected float getGravityVelocity() {

		return 0.03F;
	}

}
