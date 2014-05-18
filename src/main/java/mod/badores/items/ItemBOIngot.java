package mod.badores.items;

import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.BadOres;
import mod.badores.ore.AbstractOre;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.OreForm;
import mod.badores.oremanagement.OreSubName;
import mod.badores.util.I18n;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

/**
 * @author diesieben07
 */
public class ItemBOIngot extends BOItem implements OreSubName, BadOreItem {

	public static final String NBT_KEY = "badores.ingotOreID";

	private Map<BadOre, IIcon> icons = Maps.newHashMap();

	public ItemBOIngot() {
		setHasSubtypes(true);
		setCreativeTab(BadOres.creativeTab);
	}

	public static ItemStack createIngot(BadOre ore) {
		ItemStack stack = new ItemStack(BadOres.ingot, 1, 0);
		setOre(stack, ore);
		return stack;
	}

	public static BadOre getOre(ItemStack stack) {
		if (stack.hasTagCompound()) {
			return BadOres.oreManager.getOreByName(stack.getTagCompound().getString(NBT_KEY));
		}
		return null;
	}

	public static void setOre(ItemStack stack, BadOre ore) {
        stack.setTagInfo(NBT_KEY, new NBTTagString(ore.getName()));
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (BadOre ore : BadOres.oreManager.getAllOres()) {
			if (ore.hasIngot()) {
				ItemStack stack = new ItemStack(this);
				setOre(stack, ore);
				//noinspection unchecked
				list.add(stack);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		BadOre ore = getOre(stack);
		if (ore == null) {
			return "Unknown ore";
		}
		return ore.getDisplayName(this);
	}

	@Override
	public String subName(String translatedOreName) {
		return I18n.translateBO("ingot", translatedOreName);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(BadOres.MOD_ID + ":ingotGeneric");

		for (BadOre ore : BadOres.oreManager.getAllOres()) {
			if (ore.hasIngot()) {
				icons.put(ore, iconRegister.registerIcon(ore.getIngotIconName()));
			}
		}
	}

	@Override
	public IIcon getIconIndex(ItemStack stack) {
		BadOre ore = getOre(stack);
		IIcon icon = icons.get(ore);
		return icon == null ? super.getIconIndex(stack) : icon;
	}

	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		return getIconIndex(stack);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean inHotbar) {
		super.onUpdate(stack, world, player, slot, inHotbar);
		AbstractOre.invokeInventoryTick(getOre(stack), OreForm.INGOT, stack, slot, world, player);
	}

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return getOre(stack).ingotStackSize();
    }

	@Override
	public void onContainerTick(Container c, Slot slot, ItemStack stack) {
		getOre(stack).onContainerTick(OreForm.INGOT, c, slot, stack);
	}
}
