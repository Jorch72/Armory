package com.Orion.Armory.Client;

import com.Orion.Armory.Common.ArmoryCommonProxy;
import com.Orion.Armory.Common.Logic.ArmoryInitializer;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by Orion on 26-3-2014.
 */
public class ArmoryClientProxy extends ArmoryCommonProxy
{
    @Override
    public void initializeArmory()
    {
        ArmoryInitializer tInitializer = new ArmoryInitializer();
        tInitializer.iInstance.Initialize(Side.CLIENT);
    }
}
