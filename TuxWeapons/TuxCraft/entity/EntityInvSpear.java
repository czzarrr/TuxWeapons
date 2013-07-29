package TuxWeapons.TuxCraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityInvSpear extends EntityThrowable {

	int ticksInAir;
	private float damage;

	public EntityInvSpear(World par1World) {

		super(par1World);
	}

	public EntityInvSpear(World par1World, EntityPlayer player, float f) {

		super(par1World, player);
		this.damage = f;
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {

		if (par1MovingObjectPosition.entityHit != null) {

			par1MovingObjectPosition.entityHit.attackEntityFrom(
					DamageSource.causeThrownDamage(this, this.getThrower()),
					damage);
		}

		if (!this.worldObj.isRemote) {
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

		if (ticksInAir >= 5) {
			this.setDead();
		}

	}

}