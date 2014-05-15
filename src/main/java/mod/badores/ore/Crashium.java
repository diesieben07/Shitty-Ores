package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Crashium extends AbstractOre {

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (BadOres.devEnv) {
			miner.addChatComponentMessage(new ChatComponentText("Crash! :D"));
		} else {
			throw new GenericCrashException();
		}
	}

	@Override
	public String getName() {
		return "crashium";
	}

    public static class GenericCrashException extends RuntimeException {

    }
}
