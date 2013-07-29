package TuxWeapons.TuxCraft;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import TuxWeapons.TuxCraft.entity.EntityBolt;
import TuxWeapons.TuxCraft.entity.EntityDynamite;
import TuxWeapons.TuxCraft.entity.EntityEMPGrenade;
import TuxWeapons.TuxCraft.entity.EntityEagleArrow;
import TuxWeapons.TuxCraft.entity.EntityFlameAura;
import TuxWeapons.TuxCraft.entity.EntityGrappHook;
import TuxWeapons.TuxCraft.entity.EntityInvSpear;
import TuxWeapons.TuxCraft.entity.EntityKnife;
import TuxWeapons.TuxCraft.entity.EntitySpear;
import TuxWeapons.TuxCraft.entity.EntityTuxFireBall;
import TuxWeapons.TuxCraft.entity.render.RenderBolt;
import TuxWeapons.TuxCraft.entity.render.RenderCrossBow;
import TuxWeapons.TuxCraft.entity.render.RenderDynamite;
import TuxWeapons.TuxCraft.entity.render.RenderEagleArrow;
import TuxWeapons.TuxCraft.entity.render.RenderFireChargeCannon;
import TuxWeapons.TuxCraft.entity.render.RenderGrappHook;
import TuxWeapons.TuxCraft.entity.render.RenderInvSpear;
import TuxWeapons.TuxCraft.entity.render.RenderKnife;
import TuxWeapons.TuxCraft.entity.render.RenderSpear;
import TuxWeapons.TuxCraft.entity.render.RenderThrownItem;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void load() {

	}
	
	@Override
	public void registerRenderers() {
		EntityRegistry.registerModEntity(EntityInvSpear.class,
				"InvisibleSpear", config.idBaseEntity, TuxWeaponsCore.instance,
				128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityInvSpear.class,
				new RenderInvSpear());

		EntityRegistry.registerModEntity(EntitySpear.class, "Spear",
				config.idBaseEntity + 1, TuxWeaponsCore.instance, 128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class,
				new RenderSpear());

		EntityRegistry.registerModEntity(EntityDynamite.class, "Dynamite",
				config.idBaseEntity + 2, TuxWeaponsCore.instance, 128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class,
				new RenderDynamite());

		EntityRegistry.registerModEntity(EntityEagleArrow.class, "Eagle Arrow",
				config.idBaseEntity + 3, TuxWeaponsCore.instance, 128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(
				EntityEagleArrow.class, new RenderEagleArrow());

		EntityRegistry.registerModEntity(EntityTuxFireBall.class, "FireBall",
				config.idBaseEntity + 4, TuxWeaponsCore.instance, 128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(
				EntityTuxFireBall.class, new RenderThrownItem(
						Item.fireballCharge));

		EntityRegistry.registerModEntity(EntityEMPGrenade.class, "EMPGrenade",
				config.idBaseEntity + 5, TuxWeaponsCore.instance, 128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(
				EntityEMPGrenade.class, new RenderThrownItem(
						TuxWeaponsCore.redstoneEMP));

		EntityRegistry.registerModEntity(EntityBolt.class, "Bolt",
				config.idBaseEntity + 6, TuxWeaponsCore.instance, 128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityBolt.class,
				new RenderBolt());

		EntityRegistry.registerModEntity(EntityGrappHook.class, "GrappHook",
				config.idBaseEntity + 7, TuxWeaponsCore.instance, 128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityGrappHook.class,
				new RenderGrappHook());

		EntityRegistry.registerModEntity(EntityKnife.class, "Knife",
				config.idBaseEntity + 8, TuxWeaponsCore.instance, 128, 1, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityKnife.class,
				new RenderKnife());

		EntityRegistry.registerModEntity(EntityFlameAura.class, "FlameAura",
				config.idBaseEntity + 9, TuxWeaponsCore.instance, 128, 1, true);

		if (Arrays.asList(config.disabledWeapons).contains("fireChargeCannon") == false) {

			MinecraftForgeClient.registerItemRenderer(
					TuxWeaponsCore.fireChargeCannon.itemID,
					new RenderFireChargeCannon());
		}

		if (Arrays.asList(config.disabledWeapons).contains("crossBow") == false) {
			MinecraftForgeClient.registerItemRenderer(
					TuxWeaponsCore.crossBow.itemID, new RenderCrossBow());
		}

	}

}
