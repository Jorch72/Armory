package com.ldtteam.armory.api.common.helpers;

import com.ldtteam.armory.api.common.armor.IMaterialDependantMultiComponentArmorExtension;
import com.ldtteam.armory.api.common.armor.IMaterializableMultiComponentArmorExtension;
import com.ldtteam.armory.api.common.armor.IMultiComponentArmorExtension;
import com.ldtteam.armory.api.common.armor.IMultiComponentArmorExtensionPosition;
import com.ldtteam.armory.api.common.armor.callback.IDefaultCapabilitiesRetrievalCallback;
import com.ldtteam.armory.api.common.armor.callback.IExtensionRecipeRetrievalCallback;
import com.ldtteam.armory.api.common.material.armor.IAddonArmorMaterial;

import javax.annotation.Nonnull;

/**
 * Author Orion (Created on: 07.07.2016)
 */
public interface IMedievalUpgradeConstructionHelper {

    /**
     * Method to build an armor extension that has no real special properties.
     *
     * @return A completely setup extension. However the caller will need to set the RegistryName and register it.
     */
    @Nonnull
    IMultiComponentArmorExtension buildStandardExtension(
      String translationKey,
      String textFormatting,
      IMultiComponentArmorExtensionPosition position,
      IDefaultCapabilitiesRetrievalCallback capabilitiesRetrievalCallback,
      IExtensionRecipeRetrievalCallback extensionRecipeRetrievalCallback);

    /**
     * Method to build an armor extension that wraps an existing combination and a material.
     * Making the new extension material based.
     *
     * @return A completely setup extension that wraps the existing extension and a material. However the caller will need to set the RegistryNam and register it.
     */
    @Nonnull
    IMaterialDependantMultiComponentArmorExtension buildMaterialDependantExtension(
      String translationKey,
      String textFormatting,
      IMultiComponentArmorExtensionPosition position,
      IMaterializableMultiComponentArmorExtension materialIndependentArmorExtension,
      IAddonArmorMaterial material,
      IDefaultCapabilitiesRetrievalCallback capabilitiesRetrievalCallback);
}
