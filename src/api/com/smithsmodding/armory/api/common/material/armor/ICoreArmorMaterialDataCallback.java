package com.smithsmodding.armory.api.common.material.armor;

import com.smithsmodding.armory.api.common.armor.IMultiComponentArmor;
import com.smithsmodding.armory.api.common.capability.armor.IArmorCapability;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nonnull;
import java.util.HashMap;

/**
 * Created by marcf on 1/24/2017.
 */
public interface ICoreArmorMaterialDataCallback {
    /**
     * Method to get the BaseDurability of a piece of armor made out of this material.
     * @param armor The armor to get the base durability for.
     * @return The durability of a piece of armor made out of this material.
     */
    @Nonnull
    Integer getBaseDurabilityForArmor(@Nonnull IMultiComponentArmor armor);

    /**
     * Method to get all the default capabilities this ArmorMaterial provides.
     * @return All the default capabilities this ArmorMaterial provides.
     */
    @Nonnull
    HashMap<Capability<? extends IArmorCapability>, Object> getOverrideCoreMaterialCapabilities(IMultiComponentArmor armor);
}
