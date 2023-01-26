package be.ninedocteur.docmod.data;


import static be.ninedocteur.docmod.common.init.DMBlocks.*;

public class DMBlocksLootTables { //extends BlockLoot {

//    @Override
//    protected void addTables() {
//        //SET TABLES HERE!
//        dropSelf(KILLER_BLOCK.get());
//        dropSelf(HEAL_BLOCK.get());
//        dropSelf(ZINC_ORE.get());
//        dropSelf(ZINC_BLOCK.get());
//        dropWhenSilkTouch(CIRCLE_GLASS.get());
//        dropSelf(DEATH_LOG.get());
//        add(CLASSIC_GRASS.get(), (block) -> {return createSingleItemTableWithSilkTouch(block, Blocks.DIRT);});
//        dropOther(DEATH_SIGN.get(), DMItems.DEATH_SIGN.get());
//        dropOther(DEATH_WALL_SIGN.get(), DMItems.DEATH_SIGN.get());
//        dropOther(REDSTONE_LAMP_ON.get(), Items.REDSTONE_LAMP);
//    }
//
//    @Override
//    protected Iterable<Block> getKnownBlocks() {
//        return DMBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
//    }
}
