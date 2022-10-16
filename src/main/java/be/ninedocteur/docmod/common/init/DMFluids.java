package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.fluid.DMFluidTypes;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DMFluids {/*
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, DocMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_GAZOLINE = FLUIDS.register("gazoline_fluid",
            () -> new ForgeFlowingFluid.Source(DMFluids.GAZOLINE_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_SOURCE_GAZOLINE = FLUIDS.register("gazoline_flowing",
            () -> new ForgeFlowingFluid.Source(DMFluids.GAZOLINE_PROPERTIES));


    public static final ForgeFlowingFluid.Properties GAZOLINE_PROPERTIES = new ForgeFlowingFluid.Properties(
            DMFluidTypes.GAZOLINE_FLUID_TYPE, SOURCE_GAZOLINE, FLOWING_SOURCE_GAZOLINE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(DMBlocks.GAZOLINE_BLOCK)
            .bucket(DMItems.GAZOLINE_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
    */
}
