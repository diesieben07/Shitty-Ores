package mod.badores;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mod.badores.ore.Breakium;
import mod.badores.ore.OreManager;
import mod.badores.ore.Polite;
import mod.badores.ore.Wannafite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * @author diesieben07
 */
@Mod(modid = BadOres.MOD_ID, name = "BadOres", version = BadOres.VERSION)
public class BadOres {

	public static final String MOD_ID = "badores";
    public static final String VERSION = "0.1";

	@SidedProxy(clientSide = "mod.badores.client.BOClientProxy", serverSide = "mod.badores.server.BOServerProxy")
	public static BOProxy proxy;

	public static CreativeTabs creativeTab = new CreativeTabs("badores") {
		@Override
		public Item getTabIconItem() {
			// TODO
			return Items.apple;
		}
	};

	public static OreManager oreManager;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		oreManager = new OreManager();

		oreManager.registerOre(new Polite());
		oreManager.registerOre(new Wannafite());
        oreManager.registerOre(new Breakium());

		oreManager.createBlocks();

		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}
