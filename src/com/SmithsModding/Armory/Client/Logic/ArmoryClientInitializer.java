package com.SmithsModding.Armory.Client.Logic;

/*
 *   ArmoryClientInitializer
 *   Created by: Orion
 *   Created on: 19-9-2014
 */

import com.SmithsModding.Armory.API.Armor.*;
import com.SmithsModding.Armory.API.Materials.*;
import com.SmithsModding.Armory.*;
import com.SmithsModding.Armory.Client.*;
import com.SmithsModding.Armory.Common.Logic.*;
import com.SmithsModding.Armory.Common.Material.*;
import com.SmithsModding.Armory.Common.Registry.*;
import com.SmithsModding.Armory.Util.*;
import net.minecraft.client.resources.model.*;
import net.minecraft.item.*;
import net.minecraftforge.client.model.*;
import net.minecraftforge.client.model.obj.*;

public class ArmoryClientInitializer extends ArmoryInitializer {
    public static void InitializeClient() {
        MedievalInitialization.Initialize();
        MedievalClientInitialization.registerMaterialResources();
        MedievalClientInitialization.registerUpgradeResources();
        ArmoryInitializer.SystemInit.RegisterItems();
        ArmoryInitializer.SystemInit.RegisterBlocks();
        ArmoryInitializer.SystemInit.RegisterTileEntities();
        SystemInit.registerIIR();
        SystemInit.registerTESR();
        MedievalClientInitialization.registerRingResources();
        MedievalClientInitialization.registerChainResources();
        MedievalClientInitialization.registerNuggetResources();
        MedievalClientInitialization.registerPlateResources();
        MedievalInitialization.prepareGame();
        ArmoryInitializer.SystemInit.initializeOreDic();
    }

    public static class MedievalClientInitialization {
        public static void registerMaterialResources() {
            MaterialRegistry.getInstance().getMaterial(References.InternalNames.Materials.Vanilla.IRON).setRenderInfo(new IMaterialRenderInfo.Metal(0xcacaca, 0f, 0.3f, 0f));
            MaterialRegistry.getInstance().getMaterial(References.InternalNames.Materials.Vanilla.OBSIDIAN).setRenderInfo(new IMaterialRenderInfo.MultiColor(0x71589c, 0x8f60d4, 0x8c53df));
        }

        public static void registerUpgradeResources() {

        }

        public static void registerRingResources() {

        }

        public static void registerChainResources() {

        }

        public static void registerNuggetResources() {

        }

        public static void registerPlateResources() {

        }

    }

    public static class SystemInit {
        public static void registerIIR() {
            ArmoryClientProxy proxy = (ArmoryClientProxy) Armory.proxy;

            for (MultiLayeredArmor armor : MaterialRegistry.getInstance().getAllRegisteredArmors().values()) {
                proxy.registerArmorItemModel(armor);
            }

            proxy.registerHeatedItemItemModel(GeneralRegistry.Items.heatedItem);

            OBJLoader.instance.addDomain(References.General.MOD_ID.toLowerCase());
            ModelLoader.setCustomModelResourceLocation(GeneralRegistry.Items.guide, 0, new ModelResourceLocation(References.General.MOD_ID.toLowerCase() + ":" + "Armory.Items.SmithingsGuide", "inventory"));
        }


        public static void registerTESR() {
            Item itemBlockFirePit = Item.getItemFromBlock(GeneralRegistry.Blocks.blockFirePit);
            ModelLoader.setCustomModelResourceLocation(itemBlockFirePit, 0, new ModelResourceLocation(References.General.MOD_ID.toLowerCase() + ":" + References.InternalNames.Blocks.FirePit, "inventory"));
        }
    }


}