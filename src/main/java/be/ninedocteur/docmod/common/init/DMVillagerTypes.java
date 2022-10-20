package be.ninedocteur.docmod.common.init;

import be.ninedocteur.docmod.DocMod;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class DMVillagerTypes {
    public static final DeferredRegister<PoiType> POI_TYPE = DeferredRegister.create(ForgeRegistries.POI_TYPES, DocMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, DocMod.MOD_ID);

    public static final RegistryObject<PoiType> DM_POI = createPoiType("dm_poi", DMBlocks.ZINC_CRAFTING_TABLE);

    public static final RegistryObject<VillagerProfession> DM_VILLAGER = createProfession("dm_villager", DM_POI.getKey(), SoundEvents.ANVIL_LAND);


    //Fonction from Dimensional Expansion from the second developer Killar.exe
    private static RegistryObject<PoiType> createPoiType(String name, RegistryObject<Block> targetBlock){
        RegistryObject<PoiType> type = POI_TYPE.register(
                name,
                () -> new PoiType(
                        ImmutableSet.copyOf(targetBlock.get().getStateDefinition().getPossibleStates()),
                        1,
                        1
                ));
        return type;
    }

    private static RegistryObject<VillagerProfession> createProfession(String name, ResourceKey<PoiType> poi, @Nullable SoundEvent sound) {
        return createProfession(name, (type_1) -> type_1.is(poi), (type_2) -> type_2.is(poi), sound);
    }
    private static RegistryObject<VillagerProfession> createProfession(String name, Predicate<Holder<PoiType>> type_1, Predicate<Holder<PoiType>> type_2, @Nullable SoundEvent sound) {
        return createProfession(name, type_1, type_2, ImmutableSet.of(), ImmutableSet.of(), sound);
    }
    @SuppressWarnings("unused")
    private static RegistryObject<VillagerProfession> createProfession(String name, ResourceKey<PoiType> poi, ImmutableSet<Item> items, ImmutableSet<Block> blocks, @Nullable SoundEvent sound) {
        return createProfession(name, (type_1) -> type_1.is(poi), (type_2) -> type_2.is(poi), items, blocks, sound);
    }
    private static RegistryObject<VillagerProfession> createProfession(String name, Predicate<Holder<PoiType>> type_1, Predicate<Holder<PoiType>> type_2, ImmutableSet<Item> items, ImmutableSet<Block> blocks, @Nullable SoundEvent sound){
        return VILLAGER_PROFESSION.register(name, () -> new VillagerProfession(name, type_1, type_2, items, blocks, sound));
    }
}
