package TuxWeapons.TuxCraft.entity;

import TuxWeapons.TuxCraft.TuxWeaponsCore;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEMPGrenade extends EntityThrowable {

	int ticksInAir;

	public EntityEMPGrenade(World par1World) {

		super(par1World);
	}

	public EntityEMPGrenade(World par1World, EntityPlayer par2EntityPlayer) {

		super(par1World, par2EntityPlayer);
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {

		if (par1MovingObjectPosition.entityHit != null) {
			byte b0 = 0;

			par1MovingObjectPosition.entityHit
					.attackEntityFrom(
							DamageSource.causeThrownDamage(this,
									this.getThrower()), b0);
		} else {
			int i = par1MovingObjectPosition.blockX;
			int j = par1MovingObjectPosition.blockY;
			int k = par1MovingObjectPosition.blockZ;

			switch (par1MovingObjectPosition.sideHit) {
			case 0:
				--j;
			case 1:
				++j;
			case 2:
				--k;
			case 3:
				++k;
			case 4:
				--i;
			case 5:
				++i;
			}

			int block = TuxWeaponsCore.redstoneTempBlock.blockID;

			if (this.worldObj.isAirBlock(i, j, k)) {
				if (!this.worldObj.isRemote)
					this.worldObj.setBlock(i, j, k, block);
				if (!this.worldObj.isRemote)
					this.worldObj.scheduleBlockUpdate(i, j, k, block, 20);
			}

			else {
				if (!this.worldObj.isRemote)
					this.worldObj.spawnEntityInWorld(new EntityItem(
							this.worldObj, i, j, k, new ItemStack(
									TuxWeaponsCore.redstoneEMP)));
			}
		}

		for (int i = 0; i <= 75; i++) {
			double ii = 4;

			double parX = ((Math.random() * ii) - (ii / 2));
			double parY = ((Math.random() * ii) - (ii / 2));
			double parZ = ((Math.random() * ii) - (ii / 2));

			this.worldObj.spawnParticle("reddust", this.posX + parX, this.posY
					+ parY, this.posZ + parZ, 0.0D, 0.0D, 0.0D);
		}

		this.setDead();
	}

	@Override
	protected float getGravityVelocity() {

		return 0.05F;
	}

	@Override
	public void onUpdate() {

		super.onUpdate();
		ticksInAir++;

		if (ticksInAir >= 8) {
			int block = TuxWeaponsCore.redstoneTempBlock.blockID;

			int x = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;

			if (this.worldObj.isAirBlock(x, j, k)) {
				if (!this.worldObj.isRemote)
					this.worldObj.setBlock(x, j, k, block);
				if (!this.worldObj.isRemote)
					this.worldObj.scheduleBlockUpdate(x, j, k, block, 20);
			}

			else {
				if (!this.worldObj.isRemote)
					this.worldObj.spawnEntityInWorld(new EntityItem(
							this.worldObj, x, j, k, new ItemStack(
									TuxWeaponsCore.redstoneEMP)));
			}

			for (int i = 0; i <= 75; i++) {
				double ii = 4;

				double parX = ((Math.random() * ii) - (ii / 2));
				double parY = ((Math.random() * ii) - (ii / 2));
				double parZ = ((Math.random() * ii) - (ii / 2));

				this.worldObj.spawnParticle("reddust", this.posX + parX,
						this.posY + parY, this.posZ + parZ, 0.0D, 0.0D, 0.0D);
			}

			this.setDead();
		}

	}

}