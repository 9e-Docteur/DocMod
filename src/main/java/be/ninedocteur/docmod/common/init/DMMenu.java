package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.menu.ComputerHarwareMenu;
import be.ninedocteur.docmod.client.gui.menu.InfusionMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMMenu {
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.MENU_TYPES, DocMod.MOD_ID);

    public static final RegistryObject<MenuType<ComputerHarwareMenu>> COMPUTER = MENU.register("computer_menu", () -> new MenuType(ComputerHarwareMenu::new));
    public static final RegistryObject<MenuType<InfusionMenu>> INFUSION = MENU.register("infusion_menu", () -> IForgeMenuType.create(InfusionMenu::new));


    public static void register(IEventBus eventBus){
        MENU.register(eventBus);
    }

}
