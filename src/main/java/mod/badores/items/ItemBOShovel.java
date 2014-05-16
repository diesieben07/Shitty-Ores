package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.ore.AbstractOre;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.OreForm;
import mod.badores.oremanagement.ToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class ItemBOShovel extends ItemSpade {

	private final BadOre ore;

	public ItemBOShovel(ToolMaterial toolMaterial, BadOre ore) {
		super(toolMaterial);
		this.ore = ore;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living) {
		AbstractOre.invokeOnMine(ore, ToolType.SHOVEL, living, block, world, x, y, z);
		return super.onBlockDestroyed(stack, world, block, x, y, z, living);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase beingHit, EntityLivingBase attacker) {
		AbstractOre.invokeOnAttack(ore, ToolType.SHOVEL, attacker, beingHit);
		return super.hitEntity(stack, beingHit, attacker);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean inHotbar) {
		super.onUpdate(stack, world, player, slot, inHotbar);
		AbstractOre.invokeInventoryTick(ore, OreForm.SHOVEL, stack, slot, world, player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return ore.getDisplayName(ToolType.SHOVEL);
	}

}
