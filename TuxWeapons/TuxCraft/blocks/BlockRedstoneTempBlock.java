package TuxWeapons.TuxCraft.blocks;

import java.util.Random;

import net.minecraft.block.BlockOreStorage;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import TuxWeapons.TuxCraft.TuxWeaponsCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRedstoneTempBlock extends BlockOreStorage {

	public BlockRedstoneTempBlock(int par1) {

		super(par1);
		this.setCreativeTab(null);
		this.setTickRandomly(true);
	}

	@Override
	public boolean canProvidePower() {

		return true;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5) {

		return 15;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int x,
			int y, int z, int side) {

		return this.getPowerSupply(par1IBlockAccess.getBlockMetadata(x, y, z));
	}

	@Override
	public int tickRate(World par1World) {

		return 1;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {

		par1World.setBlockToAir(par2, par3, par4);
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {

		for (int i = 0; i <= 5; i++) {
			double ii = 1;

			double parX = ((Math.random() * ii));
			double parY = ((Math.random() * ii));
			double parZ = ((Math.random() * ii));

			par1World.spawnParticle("reddust", par2 + parX, par3 + parY, par4
					+ parZ, 0.0D, 0.0D, 0.0D);
		}

	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	public MovingObjectPosition collisionRayTrace(World par1World, int par2,
			int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
		float f = 0.15F;

		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);

		return super.collisionRayTrace(par1World, par2, par3, par4, par5Vec3,
				par6Vec3);
	}

	public boolean isBlockReplaceable(World world, int x, int y, int z) {
		return true;
	}

	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {

		this.blockIcon = par1IconRegister.registerIcon(TuxWeaponsCore.modid
				+ ":" + "air");
	}

	protected int getPowerSupply(int i) {
		return 15;
	}
}
