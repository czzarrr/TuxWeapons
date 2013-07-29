package TuxWeapons.TuxCraft.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Cancelable;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class FireChargeEvent extends PlayerEvent {

	public final ItemStack stack;
	public int charge;

	public FireChargeEvent(EntityPlayer player, ItemStack stack, int charge) {

		super(player);
		this.stack = stack;
		this.charge = charge;
	}
}
