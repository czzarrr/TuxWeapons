package TuxWeapons.TuxCraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

	public static String ITEMS_PNG = " ";
	public static String BLOCK_PNG = " ";

	public void registerRenderers() {

		// Nothing here as the server doesn't render graphics!
	}

	public void load() {

	}

	public void registerServerTickHandler() {

		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}
	
	public void registerClientTickHandler() {

		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}

}
