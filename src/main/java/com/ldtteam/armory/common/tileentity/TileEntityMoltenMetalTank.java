package com.ldtteam.armory.common.tileentity;

import com.ldtteam.armory.api.util.references.ModCapabilities;
import com.ldtteam.armory.api.util.references.References;
import com.ldtteam.armory.common.block.types.EnumTankType;
import com.ldtteam.armory.common.tileentity.guimanagers.TileEntityMoltenMetalTankGuiManager;
import com.ldtteam.armory.common.tileentity.moltenmetal.MoltenMetalTank;
import com.ldtteam.armory.common.tileentity.state.TileEntityMoltenMetalTankState;
import com.ldtteam.smithscore.common.fluid.IFluidContainingEntity;
import com.ldtteam.smithscore.common.tileentity.TileEntitySmithsCore;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Author Orion (Created on: 26.07.2016)
 */
public class TileEntityMoltenMetalTank extends TileEntitySmithsCore<TileEntityMoltenMetalTankState, TileEntityMoltenMetalTankGuiManager> implements IFluidContainingEntity, ITickable {

    private EnumTankType type;
    private MoltenMetalTank internalTank = new MoltenMetalTank(0,0);

    public TileEntityMoltenMetalTank() {
    }

    public TileEntityMoltenMetalTank(@Nonnull EnumTankType type) {
        this.type = type;
        this.internalTank = new MoltenMetalTank(type.getTankContents(), 1);
    }

    @Nonnull
    @Override
    protected TileEntityMoltenMetalTankGuiManager getInitialGuiManager() {
        return new TileEntityMoltenMetalTankGuiManager();
    }

    @Nonnull
    @Override
    protected TileEntityMoltenMetalTankState getInitialState() {
        return new TileEntityMoltenMetalTankState();
    }

    @Nonnull
    @Override
    public String getContainerID() {
        return References.InternalNames.TileEntities.Tank + "-" + getLocation().toString();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.type = EnumTankType.byMetadata(compound.getInteger(References.NBTTagCompoundData.TE.MoltenMetalTank.TYPE));
        internalTank.deserializeNBT(compound.getCompoundTag(References.NBTTagCompoundData.TE.MoltenMetalTank.CONTENTS));

        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger(References.NBTTagCompoundData.TE.MoltenMetalTank.TYPE, type.getMetadata());
        compound.setTag(References.NBTTagCompoundData.TE.MoltenMetalTank.CONTENTS, internalTank.serializeNBT());

        return super.writeToNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability capability, EnumFacing facing) {
        if (capability == ModCapabilities.MOD_MOLTENMETAL_ACCEPTOR_CAPABILITY || capability == ModCapabilities.MOD_MOLTENMETAL_PROVIDER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return true;

        return super.hasCapability(capability, facing);
    }

    @Override
    public Object getCapability(Capability capability, EnumFacing facing) {
        if (capability == ModCapabilities.MOD_MOLTENMETAL_ACCEPTOR_CAPABILITY || capability == ModCapabilities.MOD_MOLTENMETAL_PROVIDER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return internalTank;

        return super.getCapability(capability, facing);
    }

    @Override
    public IFluidTank getTankForSide(@Nullable EnumFacing side) {
        return internalTank;
    }

    @Override
    public int getTotalTankSizeOnSide(@Nullable EnumFacing side) {
        return getTankForSide(side).getCapacity();
    }

    @Override
    public int getTankContentsVolumeOnSide(@Nullable EnumFacing side) {
        return getTankForSide(side).getFluidAmount();
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update() {

    }
}
