package com.smithsmodding.armory.common.registries;

import com.smithsmodding.armory.api.common.armor.IMultiComponentArmor;
import com.smithsmodding.armory.api.common.armor.IMultiComponentArmorExtension;
import com.smithsmodding.armory.api.common.armor.IMultiComponentArmorExtensionPosition;
import com.smithsmodding.armory.api.common.crafting.blacksmiths.recipe.IAnvilRecipe;
import com.smithsmodding.armory.api.common.crafting.mixing.IMoltenMetalMixingRecipe;
import com.smithsmodding.armory.api.common.heatable.IHeatableObject;
import com.smithsmodding.armory.api.common.heatable.IHeatedObjectType;
import com.smithsmodding.armory.api.common.material.anvil.IAnvilMaterial;
import com.smithsmodding.armory.api.common.material.armor.IAddonArmorMaterial;
import com.smithsmodding.armory.api.common.material.armor.ICoreArmorMaterial;
import com.smithsmodding.armory.api.common.material.core.RegistryMaterialWrapper;
import com.smithsmodding.armory.api.util.references.ModRegistries;
import net.minecraftforge.registries.RegistryBuilder;

/**
 * Created by marcf on 1/14/2017.
 */
public class CommonRegistryInitializer {

    public void initialize() {
        RegistryManager.getInstance().coreArmorMaterialIForgeRegistry  = new RegistryBuilder<ICoreArmorMaterial>()
                .setName(ModRegistries.COREARMORMATERIALS)
                .setType(ICoreArmorMaterial.class)
                .setIDRange(0, 255)
                .create();

        RegistryManager.getInstance().addonArmorMaterialIForgeRegistry = new RegistryBuilder<IAddonArmorMaterial>()
                .setName(ModRegistries.ADDONARMORMATERIALS)
                .setType(IAddonArmorMaterial.class)
                .setIDRange(0,255)
                .create();

        RegistryManager.getInstance().anvilMaterialIForgeRegistry = new RegistryBuilder<IAnvilMaterial>()
                .setName(ModRegistries.ANVILMATERIAL)
                .setType(IAnvilMaterial.class)
                .setIDRange(0,255)
                .create();

        RegistryManager.getInstance().combinedMaterialRegistry = new RegistryBuilder<RegistryMaterialWrapper>()
                .setName(ModRegistries.COMBINEDMATERIAL)
                .setType(RegistryMaterialWrapper.class)
                .setIDRange(0,255)
                .create();

        RegistryManager.getInstance().multiComponentArmorRegistry = new RegistryBuilder<IMultiComponentArmor>()
                .setName(ModRegistries.MULTICOMPONENTARMOR)
                .setType(IMultiComponentArmor.class)
                .setIDRange(0,255)
                .create();

        RegistryManager.getInstance().multiComponentArmorExtensionPositionRegistry = new RegistryBuilder<IMultiComponentArmorExtensionPosition>()
                .setName(ModRegistries.MULTICOMPONENTARMOREXTENSIONPOSITION)
                .setType(IMultiComponentArmorExtensionPosition.class)
                .setIDRange(0,255)
                .create();

        RegistryManager.getInstance().multiComponentArmorExtensionRegistry = new RegistryBuilder<IMultiComponentArmorExtension>()
                .setName(ModRegistries.MULTICOMPONENTARMOREXTENSION)
                .setType(IMultiComponentArmorExtension.class)
                .setIDRange(0,255)
                .create();

        RegistryManager.getInstance().heatableObjectRegistry = new RegistryBuilder<IHeatableObject>()
                .setName(ModRegistries.HEATABLEOBJECT)
                .setType(IHeatableObject.class)
                .setIDRange(0,255)
                .create();

        RegistryManager.getInstance().heatableObjectTypeRegistry = new RegistryBuilder<IHeatedObjectType>()
                .setName(ModRegistries.HEATABLEOJBECTTYPE)
                .setType(IHeatedObjectType.class)
                .setIDRange(0,255)
                .create();

        RegistryManager.getInstance().anvilRecipeRegistry = new RegistryBuilder<IAnvilRecipe>()
                .setName(ModRegistries.ANVILRECIPE)
                .setType(IAnvilRecipe.class)
                .setIDRange(0,9999)
                                                              .allowModification()
                .create();

        RegistryManager.getInstance().moltenMetalMixingRecipesRegistry = new RegistryBuilder<IMoltenMetalMixingRecipe>()
                .setName(ModRegistries.FLUIDTOFLUIDMIXING)
                .setType(IMoltenMetalMixingRecipe.class)
                .setIDRange(0,9999)
                .create();
    }
}
