package com.smithsmodding.armory.common.logic.initialization;

import com.smithsmodding.armory.api.common.heatable.IHeatableObject;
import com.smithsmodding.armory.api.common.heatable.IHeatedObjectType;
import com.smithsmodding.armory.api.util.client.TranslationKeys;
import com.smithsmodding.armory.api.util.references.*;
import com.smithsmodding.armory.common.block.*;
import com.smithsmodding.armory.common.heatable.HeatableObject;
import com.smithsmodding.armory.common.heatable.HeatedObjectType;
import com.smithsmodding.armory.common.item.*;
import com.smithsmodding.armory.common.item.armor.ItemMultiComponentArmor;
import com.smithsmodding.armory.common.item.block.ItemBlockBlackSmithsAnvil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import javax.annotation.Nonnull;

/**
 * Created by marcf on 1/25/2017.
 */
@Mod.EventBusSubscriber(modid = References.General.MOD_ID)
public class CommonSystemRegistrationInitializer {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> registerBlockEvent) {
        IForgeRegistry<Block> blockRegistry = registerBlockEvent.getRegistry();

        ModBlocks.blockForge = new BlockForge();
        ModBlocks.blockBlackSmithsAnvil = new BlockBlackSmithsAnvil();
        ModBlocks.blockFirePlace = new BlockFirePlace();
        ModBlocks.blockConduit = new BlockConduit();
        ModBlocks.blockMoltenMetalTank = new BlockMoltenMetalTank();
        ModBlocks.blockMoltenMetalPump = new BlockPump();

        blockRegistry.register(ModBlocks.blockForge);
        blockRegistry.register(ModBlocks.blockBlackSmithsAnvil);
        blockRegistry.register(ModBlocks.blockFirePlace);
        blockRegistry.register(ModBlocks.blockConduit);
        blockRegistry.register(ModBlocks.blockMoltenMetalTank);
        blockRegistry.register(ModBlocks.blockMoltenMetalPump);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> registerItemsEvent) {
        IForgeRegistry<Item> itemRegistry = registerItemsEvent.getRegistry();

        ModItems.IT_HEATEDITEM = new ItemHeatedItem();
        ModItems.IT_GUIDE = new ItemSmithingsGuide();
        ModItems.IT_COMPONENT = new ItemArmorComponent();
        ModItems.IT_TONGS = new ItemTongs();
        ModItems.IT_HAMMER = new ItemHammer();
        ModItems.IT_RING = new ItemHeatableMetalRing();
        ModItems.IT_CHAIN = new ItemHeatableMetalChain();
        ModItems.IT_NUGGET = new ItemHeatableNugget();
        ModItems.IT_PLATE = new ItemHeatablePlate();
        ModItems.IT_INGOT = new ItemHeatableMetalIngot();

        ModItems.Armor.IT_HELMET = new ItemMultiComponentArmor(References.InternalNames.Items.Armor.IN_HELMET, TranslationKeys.Items.MultiArmor.Armor.TK_HELMET);
        ModItems.Armor.IT_CHESTPLATE = new ItemMultiComponentArmor(References.InternalNames.Items.Armor.IN_CHESTPLATE, TranslationKeys.Items.MultiArmor.Armor.TK_CHESTPLATE);
        ModItems.Armor.IT_LEGGINGS = new ItemMultiComponentArmor(References.InternalNames.Items.Armor.IN_LEGGINGS, TranslationKeys.Items.MultiArmor.Armor.TK_LEGGINGS);
        ModItems.Armor.IT_SHOES = new ItemMultiComponentArmor(References.InternalNames.Items.Armor.IN_SHOES, TranslationKeys.Items.MultiArmor.Armor.TK_SHOES);

        itemRegistry.register(ModItems.IT_HEATEDITEM);
        //itemRegistry.register(ModItems.IT_GUIDE);
        itemRegistry.register(ModItems.IT_COMPONENT);
        itemRegistry.register(ModItems.IT_TONGS);
        itemRegistry.register(ModItems.IT_HAMMER);
        itemRegistry.register(ModItems.IT_RING);
        itemRegistry.register(ModItems.IT_CHAIN);
        itemRegistry.register(ModItems.IT_NUGGET);
        itemRegistry.register(ModItems.IT_PLATE);
        itemRegistry.register(ModItems.IT_INGOT);

        itemRegistry.register(ModItems.Armor.IT_HELMET);
        itemRegistry.register(ModItems.Armor.IT_CHESTPLATE);
        itemRegistry.register(ModItems.Armor.IT_LEGGINGS);
        itemRegistry.register(ModItems.Armor.IT_SHOES);

        itemRegistry.register(new ItemBlock(ModBlocks.blockForge).setRegistryName(ModBlocks.blockForge.getRegistryName()));
        itemRegistry.register(new ItemBlockBlackSmithsAnvil(ModBlocks.blockBlackSmithsAnvil));
        itemRegistry.register(new ItemBlock(ModBlocks.blockFirePlace).setRegistryName(ModBlocks.blockFirePlace.getRegistryName()));
        itemRegistry.register(new ItemBlock(ModBlocks.blockConduit).setRegistryName(ModBlocks.blockConduit.getRegistryName()));
        itemRegistry.register(new ItemBlock(ModBlocks.blockMoltenMetalTank).setRegistryName(ModBlocks.blockMoltenMetalTank.getRegistryName()));
        itemRegistry.register(new ItemBlock(ModBlocks.blockMoltenMetalPump).setRegistryName(ModBlocks.blockMoltenMetalPump.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerHeatableObject(@Nonnull RegistryEvent.Register<IHeatableObject> heatableObjectRegisterEvent) {
        IForgeRegistry<IHeatableObject> registry = heatableObjectRegisterEvent.getRegistry();

        ModHeatableObjects.ITEMSTACK = new HeatableObject().setRegistryName(References.InternalNames.HeatableObjectNames.HON_ITEMSTACK);

        registry.register(ModHeatableObjects.ITEMSTACK);
    }

    @SubscribeEvent
    public static void registerHeatedObjectTypes(@Nonnull RegistryEvent.Register<IHeatedObjectType> heatedObjectTypeRegisterEvent) {
        IForgeRegistry<IHeatedObjectType> registry = heatedObjectTypeRegisterEvent.getRegistry();

        ModHeatedObjectTypes.INGOT = new HeatedObjectType(References.General.FLUID_INGOT, References.OreDictionaryIdentifiers.ODI_INGOT).setRegistryName(References.InternalNames.HeatedObjectTypeNames.HTN_INGOT);
        ModHeatedObjectTypes.BLOCK = new HeatedObjectType(References.General.FLUID_INGOT * 9, References.OreDictionaryIdentifiers.ODI_BLOCK).setRegistryName(References.InternalNames.HeatedObjectTypeNames.HTN_BLOCK);
        ModHeatedObjectTypes.RING = new HeatedObjectType(References.General.FLUID_INGOT / 9, References.OreDictionaryIdentifiers.ODI_RING).setRegistryName(References.InternalNames.HeatedObjectTypeNames.HTN_RING);
        ModHeatedObjectTypes.NUGGET = new HeatedObjectType(References.General.FLUID_INGOT / 9, References.OreDictionaryIdentifiers.ODI_NUGGET).setRegistryName(References.InternalNames.HeatedObjectTypeNames.HTN_NUGGET);
        ModHeatedObjectTypes.CHAIN = new HeatedObjectType(References.General.FLUID_INGOT, References.OreDictionaryIdentifiers.ODI_CHAIN).setRegistryName(References.InternalNames.HeatedObjectTypeNames.HTN_CHAIN);
        ModHeatedObjectTypes.PLATE = new HeatedObjectType(References.General.FLUID_INGOT, References.OreDictionaryIdentifiers.ODI_PLATE).setRegistryName(References.InternalNames.HeatedObjectTypeNames.HTN_PLATE);

        registry.register(ModHeatedObjectTypes.INGOT);
        registry.register(ModHeatedObjectTypes.BLOCK);
        registry.register(ModHeatedObjectTypes.RING);
        registry.register(ModHeatedObjectTypes.NUGGET);
        registry.register(ModHeatedObjectTypes.CHAIN);
        registry.register(ModHeatedObjectTypes.PLATE);

    }
}
