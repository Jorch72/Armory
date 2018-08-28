package com.ldtteam.armory.common.tileentity.state;

import com.ldtteam.smithscore.common.tileentity.TileEntitySmithsCore;
import com.ldtteam.smithscore.common.tileentity.state.ITileEntityState;
import net.minecraft.nbt.NBTBase;
import org.jetbrains.annotations.Nullable;

/**
 * Author Orion (Created on: 25.07.2016)
 */
public class TileEntityConduitState implements ITileEntityState {
    @Override
    public void onStateCreated(TileEntitySmithsCore tileEntitySmithsCore) {

    }

    @Override
    public void onStateUpdated() {

    }

    @Override
    public void onStateDestroyed() {

    }

    @Override
    public boolean requiresNBTStorage() {
        return false;
    }

    @Override
    public void readFromNBTTagCompound(NBTBase stateData) {

    }

    @Nullable
    @Override
    public NBTBase writeToNBTTagCompound() {
        return null;
    }

    @Override
    public boolean requiresSynchronization() {
        return false;
    }

    @Override
    public void readFromSynchronizationCompound(NBTBase stateData) {

    }

    @Nullable
    @Override
    public NBTBase writeToSynchronizationCompound() {
        return null;
    }
}
