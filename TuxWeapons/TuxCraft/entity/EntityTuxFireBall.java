package TuxWeapons.TuxCraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import TuxWeapons.TuxCraft.config;

public class EntityTuxFireBall extends EntityThrowable {

	int explosionForce;
	int ticksInAir;

	public EntityTuxFireBall(World par1World) {

		super(par1World);
	}

	public EntityTuxFireBall(World par1World, EntityPlayer par2EntityPlayer,
			int var3) {

		super(par1World, par2EntityPlayer);
		this.explosionForce = var3;
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {

		if (par1MovingObjectPosition.entityHit != null) {
			byte b0 = 0;

			par1MovingObjectPosition.entityHit
					.attackEntityFrom(
							DamageSource.causeThrownDamage(this,
									this.getThrower()), b0);
		}

		this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY,
				this.posZ, 0.0D, 0.0D, 0.0D);

		if (!this.worldObj.isRemote) {
			float i = explosionForce / 12;
			float explosionRadius;
			boolean onFire = false;

			if (i >= 5) {
				explosionRadius = 5;
			}

			else {
				explosionRadius = explosionForce / 12;
			}

			if (explosionRadius >= 4) {
				onFire = true;
			}

			if (config.cannonFire == false) {
				onFire = false;
			}

			this.worldObj.newExplosion((Entity) null, this.posX, this.posY,
					this.posZ, explosionRadius, onFire, config.griefEnabled);

			this.setDead();
		}
	}

	@Override
	protected float getGravityVelocity() {

		return 0;
	}

	@Override
	public void onUpdate() {

		super.onUpdate();
		ticksInAir++;

		if (ticksInAir >= 20) {
			float i = explosionForce / 12;
			float explosionRadius;
			boolean onFire = false;

			if (i >= 5) {
				explosionRadius = 5;
			}

			else {
				explosionRadius = explosionForce / 12;
			}

			if (explosionRadius >= 4) {
				onFire = true;
			}

			if (config.cannonFire == false) {
				onFire = false;
			}

			this.worldObj.newExplosion((Entity) null, this.posX, this.posY,
					this.posZ, explosionRadius, onFire, config.griefEnabled);

			this.setDead();
		}

	}

}