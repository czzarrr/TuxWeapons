package TuxWeapons.TuxCraft;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {

	GameSettings settings;
	int ticksInFlight = 0;
	int flameTicks = 0;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.PLAYER))) {
			onPlayerTick((EntityPlayer) tickData[0]);
		}
	}

	@Override
	public EnumSet<TickType> ticks() {

		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {

		return null;
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		if (type.equals(EnumSet.of(TickType.PLAYER))) {
			onPlayerTick((EntityPlayer) tickData[0]);
		}
	}

	private void onPlayerTick(EntityPlayer player) {

		ItemStack stack = player.getCurrentEquippedItem();
		settings = Minecraft.getMinecraft().gameSettings;

		int speedLvl = EnchantmentHelper.getEnchantmentLevel(
				TuxWeaponsCore.enchSpeed.effectId, player.getCurrentArmor(1));

		if (speedLvl > 0) {
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1,
					speedLvl - 1));
		}

		int swiftLvl = EnchantmentHelper.getEnchantmentLevel(
				TuxWeaponsCore.enchSwift.effectId,
				player.getCurrentEquippedItem());

		if (swiftLvl > 0) {
			player.addPotionEffect(new PotionEffect(3, 1, swiftLvl - 1));
		}

	}
}