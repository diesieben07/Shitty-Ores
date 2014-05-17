package mod.badores.items;

import mod.badores.blocks.BlockBadOre;
import mod.badores.ore.AbstractOre;
import mod.badores.oremanagement.OreForm;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class ItemBlockBadOre extends ItemBlock implements BadOreItem {

	public ItemBlockBadOre(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int itemMeta) {
		return itemMeta;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return ((BlockBadOre) field_150939_a).getOre(stack.getItemDamage()).getDisplayName();
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean inHotbar) {
		super.onUpdate(stack, world, player, slot, inHotbar);
		AbstractOre.invokeInventoryTick(((BlockBadOre) field_150939_a).getOre(stack), OreForm.ORE, stack, slot, world, player);
	}

	@Override
	public void onContainerTick(Container c, Slot slot, ItemStack stack) {
		((BlockBadOre) field_150939_a).getOre(stack).onContainerTick(OreForm.ORE, c, slot, stack);
	}
}
