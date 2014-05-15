package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public interface BadOre {

    void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side);

	void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side);

    String getName();

    String getIconName();
}
