package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.ore.AbstractOre;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.OreForm;
import mod.badores.oremanagement.ToolType;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class ItemBOHoe extends ItemHoe implements BOOreProduct {

	private final BadOre ore;
	private Item overriddenIcon;

	public ItemBOHoe(ToolMaterial toolMaterial, BadOre ore) {
		super(toolMaterial);
		this.ore = ore;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living) {
		AbstractOre.invokeOnMine(ore, ToolType.HOE, living, block, world, x, y, z);
		return super.onBlockDestroyed(stack, world, block, x, y, z, living);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase beingHit, EntityLivingBase attacker) {
		AbstractOre.invokeOnAttack(ore, ToolType.HOE, attacker, beingHit);
		return super.hitEntity(stack, beingHit, attacker);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean inHotbar) {
		super.onUpdate(stack, world, player, slot, inHotbar);
		AbstractOre.invokeInventoryTick(ore, OreForm.HOE, stack, slot, world, player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return ore.getDisplayName(ToolType.HOE);
	}

	@Override
	public IIcon getIconFromDamage(int metadata) {
		return overriddenIcon == null ? super.getIconFromDamage(metadata) : overriddenIcon.getIconFromDamage(metadata);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		if (overriddenIcon == null) {
			super.registerIcons(iconRegister);
		}
	}

	@Override
	public void overrideIcon(Item model) {
		overriddenIcon = model;
	}
}
