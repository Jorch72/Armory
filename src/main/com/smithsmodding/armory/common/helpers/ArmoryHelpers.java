package com.smithsmodding.armory.common.helpers;

import com.smithsmodding.armory.api.common.factories.IFactoryController;
import com.smithsmodding.armory.api.common.heatable.IHeatedObjectOverrideManager;
import com.smithsmodding.armory.api.common.helpers.IArmoryHelpers;
import com.smithsmodding.armory.api.common.helpers.IMaterialConstructionHelper;
import com.smithsmodding.armory.api.common.helpers.IMedievalUpgradeConstructionHelper;
import com.smithsmodding.armory.api.common.helpers.IRegistryHelper;
import com.smithsmodding.armory.common.factories.FactoryController;
import com.smithsmodding.armory.common.heatable.HeatedObjectOverrideManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * Author Orion (Created on: 07.07.2016)
 */
public class ArmoryHelpers implements IArmoryHelpers {

    @Nonnull
    private static ArmoryHelpers instance = new ArmoryHelpers();

    private ArmoryHelpers() {
    }

    @Nonnull
    public static ArmoryHelpers getInstance() {
        return instance;
    }

    @Nonnull
    @Override
    public IMedievalUpgradeConstructionHelper getMedievalUpgradeConstructionHelper() {
        return MedievalUpgradeConstructionHelper.getInstance();
    }

    @Nonnull
    @Override
    public IMaterialConstructionHelper getMaterialConstructionHelper() {
        return MaterialConstructionHelper.getInstance();
    }

    @Override
    public IFactoryController getFactories() {
        return FactoryController.getInstance();
    }

    @Override
    public IHeatedObjectOverrideManager getHeatableOverrideManager() {
        return HeatedObjectOverrideManager.getInstance();
    }

    @Override
    public IRegistryHelper getRegistryHelpers()
    {
        return RegistryHelper.getInstance();
    }
}
