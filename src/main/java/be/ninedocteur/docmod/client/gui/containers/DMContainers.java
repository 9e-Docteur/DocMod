package be.ninedocteur.docmod.client.gui.containers;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.client.gui.menu.SafeChestMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class DMContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, DocMod.MOD_ID);

    //public static final RegistryObject<MenuType<ZurbTeleporterContainer>> ZURBTELEPORTERCONTAINER = createContainer("zurb_teleporter_container", () -> new MenuType<>(ZurbTeleporterContainer::new));
    //public static final RegistryObject<MenuType<SafeChestMenu>> SAFE_CHEST_CONTAINER = createContainer("safe_chest", () -> new MenuType<>(SafeChestMenu::new));

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> createContainer(@Nonnull String name, Supplier<? extends MenuType<T>> type){
        return CONTAINERS.register(name, type);
    }

}
