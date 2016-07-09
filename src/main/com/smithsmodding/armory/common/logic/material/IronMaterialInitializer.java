package com.smithsmodding.armory.common.logic.material;

import com.smithsmodding.armory.api.armor.MultiLayeredArmor;
import com.smithsmodding.armory.api.logic.IMaterialInitializer;
import com.smithsmodding.armory.api.materials.IArmorMaterial;
import com.smithsmodding.armory.api.util.client.Textures;
import com.smithsmodding.armory.api.util.client.TranslationKeys;
import com.smithsmodding.armory.api.util.references.References;
import com.smithsmodding.armory.common.addons.ArmorUpgradeMedieval;
import com.smithsmodding.armory.common.material.ChainLayer;
import com.smithsmodding.armory.common.registry.MedievalAddonRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

/**
 * Author Orion (Created on: 07.07.2016)
 */
public class IronMaterialInitializer implements IMaterialInitializer {
    private static void registerBaseLayers(IArmorMaterial material, MultiLayeredArmor armor) {
        ChainLayer baseLayer;

        switch (armor.getUniqueID()) {
            case References.InternalNames.Armor.MEDIEVALHELMET:
                baseLayer = new ChainLayer(armor.getBaseLayerAddonPositionId(), armor.getUniqueID(), armor.getBaseLayerAddonPositionId(), material.getUniqueID(), new ResourceLocation(Textures.MultiArmor.Materials.Iron.HelmetResource.getPrimaryLocation()), new ResourceLocation(Textures.MultiArmor.Materials.Iron.HelmetResource.getSecondaryLocation()));
                break;
            case References.InternalNames.Armor.MEDIEVALCHESTPLATE:
                baseLayer = new ChainLayer(armor.getBaseLayerAddonPositionId(), armor.getUniqueID(), armor.getBaseLayerAddonPositionId(), material.getUniqueID(), new ResourceLocation(Textures.MultiArmor.Materials.Iron.ChestplateResource.getPrimaryLocation()), new ResourceLocation(Textures.MultiArmor.Materials.Iron.ChestplateResource.getSecondaryLocation()));
                break;
            case References.InternalNames.Armor.MEDIEVALLEGGINGS:
                baseLayer = new ChainLayer(armor.getBaseLayerAddonPositionId(), armor.getUniqueID(), armor.getBaseLayerAddonPositionId(), material.getUniqueID(), new ResourceLocation(Textures.MultiArmor.Materials.Iron.LegginsResource.getPrimaryLocation()), new ResourceLocation(Textures.MultiArmor.Materials.Iron.LegginsResource.getSecondaryLocation()));
                break;
            case References.InternalNames.Armor.MEDIEVALSHOES:
                baseLayer = new ChainLayer(armor.getBaseLayerAddonPositionId(), armor.getUniqueID(), armor.getBaseLayerAddonPositionId(), material.getUniqueID(), new ResourceLocation(Textures.MultiArmor.Materials.Iron.ShoesResource.getPrimaryLocation()), new ResourceLocation(Textures.MultiArmor.Materials.Iron.ShoesResource.getSecondaryLocation()));
                break;
            default:
                return;
        }

        MedievalAddonRegistry.getInstance().registerUpgrade(baseLayer);
        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, baseLayer, true);
    }

    private static void registerTopHead(IArmorMaterial material) {
        ArmorUpgradeMedieval topHead = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Helmet.TOP, References.InternalNames.Armor.MEDIEVALHELMET, References.InternalNames.AddonPositions.Helmet.TOP, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Helmet.TopHead, TextFormatting.RESET, 2.5F, 60, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Helmet_TopHead"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Helmet_TopHead.png"));
        MedievalAddonRegistry.getInstance().registerUpgrade(topHead);

        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, topHead, true);
    }

    private static void registerEarProtection(IArmorMaterial material) {
        ArmorUpgradeMedieval left = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Helmet.LEFT, References.InternalNames.Armor.MEDIEVALHELMET, References.InternalNames.AddonPositions.Helmet.LEFT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Helmet.LeftEar, TextFormatting.RESET, 0.5F, 20, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Helmet_Protection_Ear_Left"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Helmet_Protection_Ear_Left.png"));
        ArmorUpgradeMedieval right = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Helmet.RIGHT, References.InternalNames.Armor.MEDIEVALHELMET, References.InternalNames.AddonPositions.Helmet.RIGHT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Helmet.RightEar, TextFormatting.RESET, 0.5F, 20, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Helmet_Protection_Ear_Right"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Helmet_Protection_Ear_Right.png"));
        MedievalAddonRegistry.getInstance().registerUpgrade(left);
        MedievalAddonRegistry.getInstance().registerUpgrade(right);

        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, left, true);
        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, right, true);
    }

    private static void registerShoulderPads(IArmorMaterial material) {
        ArmorUpgradeMedieval left = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Chestplate.SHOULDERLEFT, References.InternalNames.Armor.MEDIEVALCHESTPLATE, References.InternalNames.AddonPositions.Chestplate.SHOULDERLEFT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Chestplate.ShoulderLeft, TextFormatting.RESET, 1F, 50, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Chestplate_ShoulderPad_Left"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Chestplate_ShoulderPad_Left.png"));
        ArmorUpgradeMedieval right = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Chestplate.SHOULDERRIGHT, References.InternalNames.Armor.MEDIEVALCHESTPLATE, References.InternalNames.AddonPositions.Chestplate.SHOULDERRIGHT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Chestplate.ShoulderRight, TextFormatting.RESET, 1F, 50, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Chestplate_ShoulderPad_Right"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Chestplate_ShoulderPad_Right.png"));
        MedievalAddonRegistry.getInstance().registerUpgrade(left);
        MedievalAddonRegistry.getInstance().registerUpgrade(right);

        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, left, true);
        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, right, true);
    }

    private static void registerFrontProtection(IArmorMaterial material) {
        ArmorUpgradeMedieval left = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Chestplate.FRONTLEFT, References.InternalNames.Armor.MEDIEVALCHESTPLATE, References.InternalNames.AddonPositions.Chestplate.FRONTLEFT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Chestplate.FrontLeft, TextFormatting.RESET, 2F, 150, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Chestplate_Protection_Front_Left"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Chestplate_Protection_Front_Left.png"));
        ArmorUpgradeMedieval right = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Chestplate.FRONTRIGHT, References.InternalNames.Armor.MEDIEVALCHESTPLATE, References.InternalNames.AddonPositions.Chestplate.FRONTRIGHT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Chestplate.FrontRight, TextFormatting.RESET, 2F, 150, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Chestplate_Protection_Front_Right"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Chestplate_Protection_Front_Right.png"));
        MedievalAddonRegistry.getInstance().registerUpgrade(left);
        MedievalAddonRegistry.getInstance().registerUpgrade(right);

        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, left, true);
        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, right, true);
    }

    private static void registerBackProtection(IArmorMaterial material) {
        ArmorUpgradeMedieval left = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Chestplate.BACKLEFT, References.InternalNames.Armor.MEDIEVALCHESTPLATE, References.InternalNames.AddonPositions.Chestplate.BACKLEFT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Chestplate.BackLeft, TextFormatting.RESET, 2F, 150, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Chestplate_Protection_Back_Left"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Chestplate_Protection_Back_Left.png"));
        ArmorUpgradeMedieval right = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Chestplate.BACKRIGHT, References.InternalNames.Armor.MEDIEVALCHESTPLATE, References.InternalNames.AddonPositions.Chestplate.BACKRIGHT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Chestplate.BackRight, TextFormatting.RESET, 2F, 150, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Chestplate_Protection_Back_Right"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Chestplate_Protection_Back_Right.png"));
        MedievalAddonRegistry.getInstance().registerUpgrade(left);
        MedievalAddonRegistry.getInstance().registerUpgrade(right);

        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, left, true);
        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, right, true);
    }

    private static void registerFrontLegProtection(IArmorMaterial material) {
        ArmorUpgradeMedieval left = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Leggings.FRONTLEFT, References.InternalNames.Armor.MEDIEVALLEGGINGS, References.InternalNames.AddonPositions.Leggings.FRONTLEFT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Leggings.FrontLeft, TextFormatting.RESET, 1.5F, 125, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Leggins_Protection_Front_Left"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Leggins_Protection_Front_Left.png"));
        ArmorUpgradeMedieval right = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Leggings.FRONTRIGHT, References.InternalNames.Armor.MEDIEVALLEGGINGS, References.InternalNames.AddonPositions.Leggings.FRONTRIGHT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Leggings.FrontRight, TextFormatting.RESET, 1.5F, 125, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Leggins_Protection_Front_Right"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Leggins_Protection_Front_Right.png"));
        MedievalAddonRegistry.getInstance().registerUpgrade(left);
        MedievalAddonRegistry.getInstance().registerUpgrade(right);

        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, left, true);
        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, right, true);
    }

    private static void registerBackLegProtection(IArmorMaterial material) {
        ArmorUpgradeMedieval left = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Leggings.BACKLEFT, References.InternalNames.Armor.MEDIEVALLEGGINGS, References.InternalNames.AddonPositions.Leggings.BACKLEFT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Leggings.BackLeft, TextFormatting.RESET, 2F, 150, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Leggins_Protection_Back_Left"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Leggins_Protection_Back_Left.png"));
        ArmorUpgradeMedieval right = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Leggings.BACKRIGHT, References.InternalNames.Armor.MEDIEVALLEGGINGS, References.InternalNames.AddonPositions.Leggings.BACKRIGHT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Leggings.BackRight, TextFormatting.RESET, 2F, 150, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Leggins_Protection_Back_Right"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Leggins_Protection_Back_Right.png"));
        MedievalAddonRegistry.getInstance().registerUpgrade(left);
        MedievalAddonRegistry.getInstance().registerUpgrade(right);

        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, left, true);
        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, right, true);
    }

    private static void registerShoeProtection(IArmorMaterial material) {
        ArmorUpgradeMedieval left = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Shoes.LEFT, References.InternalNames.Armor.MEDIEVALSHOES, References.InternalNames.AddonPositions.Shoes.LEFT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Shoes.Left, TextFormatting.RESET, 1F, 50, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Shoes_Protection_Left"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Shoes_Protection_Left.png"));
        ArmorUpgradeMedieval right = new ArmorUpgradeMedieval(References.InternalNames.Upgrades.Shoes.RIGHT, References.InternalNames.Armor.MEDIEVALSHOES, References.InternalNames.AddonPositions.Shoes.RIGHT, material.getUniqueID(), TranslationKeys.Items.MultiArmor.Upgrades.Shoes.Right, TextFormatting.RESET, 1F, 50, 1, new ResourceLocation("armory:items/multiarmor/upgrades/armory.Shoes_Protection_Right"), new ResourceLocation("armory:textures/models/multiarmor/upgrades/armory.Shoes_Protection_Right.png"));
        MedievalAddonRegistry.getInstance().registerUpgrade(left);
        MedievalAddonRegistry.getInstance().registerUpgrade(right);

        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, left, true);
        MedievalAddonRegistry.getInstance().setPartStateForMaterial(material, right, true);
    }

    private static void modifyHelmet(IArmorMaterial material) {
        material.setBaseDamageAbsorption(References.InternalNames.Armor.MEDIEVALHELMET, 1.5F);
        material.setBaseDurability(References.InternalNames.Armor.MEDIEVALHELMET, 50);
        material.setMaxModifiersOnPart(References.InternalNames.Armor.MEDIEVALHELMET, 1);
    }

    private static void modifyChestplate(IArmorMaterial material) {
        material.setBaseDamageAbsorption(References.InternalNames.Armor.MEDIEVALCHESTPLATE, 2.0F);
        material.setBaseDurability(References.InternalNames.Armor.MEDIEVALCHESTPLATE, 50);
        material.setMaxModifiersOnPart(References.InternalNames.Armor.MEDIEVALCHESTPLATE, 1);
    }

    private static void modifyLeggings(IArmorMaterial material) {
        material.setBaseDamageAbsorption(References.InternalNames.Armor.MEDIEVALLEGGINGS, 1.5F);
        material.setBaseDurability(References.InternalNames.Armor.MEDIEVALLEGGINGS, 50);
        material.setMaxModifiersOnPart(References.InternalNames.Armor.MEDIEVALLEGGINGS, 1);
    }

    private static void modifyShoes(IArmorMaterial material) {
        material.setBaseDamageAbsorption(References.InternalNames.Armor.MEDIEVALSHOES, 1.0F);
        material.setBaseDurability(References.InternalNames.Armor.MEDIEVALSHOES, 50);
        material.setMaxModifiersOnPart(References.InternalNames.Armor.MEDIEVALSHOES, 1);
    }

    @Override
    public void registerUpgradesForArmor(IArmorMaterial material, MultiLayeredArmor armor) {
        registerBaseLayers(material, armor);
        switch (armor.getUniqueID()) {
            case References.InternalNames.Armor.MEDIEVALHELMET:
                registerTopHead(material);
                registerEarProtection(material);
                break;
            case References.InternalNames.Armor.MEDIEVALCHESTPLATE:
                registerShoulderPads(material);
                registerFrontProtection(material);
                registerBackProtection(material);
                break;
            case References.InternalNames.Armor.MEDIEVALLEGGINGS:
                registerFrontLegProtection(material);
                registerBackLegProtection(material);
                break;
            case References.InternalNames.Armor.MEDIEVALSHOES:
                registerShoeProtection(material);
                break;
        }
    }

    @Override
    public void modifyMaterialForArmor(IArmorMaterial material) {
        modifyHelmet(material);
        modifyChestplate(material);
        modifyLeggings(material);
        modifyShoes(material);
    }

}
