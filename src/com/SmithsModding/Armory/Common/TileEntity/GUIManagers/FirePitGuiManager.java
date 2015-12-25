package com.SmithsModding.Armory.Common.TileEntity.GUIManagers;

import com.SmithsModding.Armory.Common.TileEntity.State.*;
import com.SmithsModding.Armory.Common.TileEntity.*;
import com.SmithsModding.Armory.Util.*;
import com.SmithsModding.SmithsCore.Client.GUI.Components.Core.*;
import com.SmithsModding.SmithsCore.Client.GUI.Components.Implementations.*;
import com.SmithsModding.SmithsCore.Client.GUI.Management.*;

/**
 * Created by Marc on 25.12.2015.
 */
public class FirePitGuiManager extends TileStorageBasedGUIManager {

    private TileEntityFirePit tileEntityFirePit;

    public FirePitGuiManager (TileEntityFirePit tileEntityFirePit) {
        this.tileEntityFirePit = tileEntityFirePit;
    }

    /**
     * Method to get the value for a progressbar. RAnged between 0 and 1.
     *
     * @param component The component to get the value for
     *
     * @return A float between 0 and 1 with 0 meaning no progress on the specific bar and 1 meaning full.
     */
    @Override
    public float getProgressBarValue (IGUIComponent component) {
        if (!( component instanceof ComponentProgressBar ))
            return 0F;

        FirePitState state = (FirePitState) tileEntityFirePit.getState();

        return (Float) state.getData(tileEntityFirePit, References.NBTTagCompoundData.TE.FirePit.FUELSTACKBURNINGTIME) / (Float) state.getData(tileEntityFirePit, References.NBTTagCompoundData.TE.FirePit.FUELSTACKFUELAMOUNT);
    }
}
