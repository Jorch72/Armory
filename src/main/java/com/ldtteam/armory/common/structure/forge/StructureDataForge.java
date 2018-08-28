package com.ldtteam.armory.common.structure.forge;

import com.ldtteam.armory.api.util.references.References;
import com.ldtteam.armory.common.tileentity.TileEntityForge;
import com.ldtteam.armory.common.tileentity.moltenmetal.MoltenMetalTank;
import com.ldtteam.armory.common.tileentity.state.IForgeFuelDataContainer;
import com.ldtteam.smithscore.common.structures.IStructureData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;

/**
 * Author Orion (Created on: 25.06.2016)
 */
public class StructureDataForge implements IStructureData<StructureForge>, IForgeFuelDataContainer
{

    boolean isBurning;
    int totalBurningTicksOnCurrentFuel;
    int burningTicksLeftOnCurrentFuel;
    private StructureForge structureForge;
    @Nonnull
    private MoltenMetalTank moltenMetals = new MoltenMetalTank(0, Integer.MAX_VALUE);

    public void onAssignToForge(@Nonnull StructureForge forge) {
        this.structureForge = forge;
        this.moltenMetals = new MoltenMetalTank(forge.getPartLocations().size() * References.General.FLUID_INGOT * TileEntityForge.TANKINGOTCAPACITY, Integer.MAX_VALUE);
    }

    public StructureForge getStructureForge() {
        return structureForge;
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();

        compound.setBoolean(References.NBTTagCompoundData.TE.ForgeBase.CURRENTLYBURNING, isBurning);
        compound.setInteger(References.NBTTagCompoundData.TE.ForgeBase.FUELSTACKFUELAMOUNT, totalBurningTicksOnCurrentFuel);
        compound.setInteger(References.NBTTagCompoundData.TE.ForgeBase.FUELSTACKBURNINGTIME, burningTicksLeftOnCurrentFuel);

        NBTTagCompound fluidCompound = moltenMetals.serializeNBT();

        compound.setTag(References.NBTTagCompoundData.TE.Forge.Structure.FLUIDS, fluidCompound);

        return compound;
    }

    @Override
    public void readFromNBT(@Nonnull NBTTagCompound compound) {
        setBurning(compound.getBoolean(References.NBTTagCompoundData.TE.ForgeBase.CURRENTLYBURNING));
        setTotalBurningTicksOnCurrentFuel(compound.getInteger(References.NBTTagCompoundData.TE.ForgeBase.FUELSTACKFUELAMOUNT));
        setBurningTicksLeftOnCurrentFuel(compound.getInteger(References.NBTTagCompoundData.TE.ForgeBase.FUELSTACKBURNINGTIME));

        moltenMetals.deserializeNBT((NBTTagCompound) compound.getTag(References.NBTTagCompoundData.TE.Forge.Structure.FLUIDS));
    }

    @Override
    public void onDataMergeInto(IStructureData<StructureForge> otherData) {
        if (!(otherData instanceof StructureDataForge))
            return;

        StructureDataForge dataForge = (StructureDataForge) otherData;
        if (!isBurning())
            setBurning(dataForge.isBurning());

        changeTotalBurningTicksOnCurrentFuel(dataForge.getTotalBurningTicksOnCurrentFuel());
        changeBurningTicksLeftOnCurrentFuel(dataForge.getBurningTicksLeftOnCurrentFuel());

        ArrayList<FluidStack> stacks = new ArrayList<>(moltenMetals.getFluidStacks());
        stacks.addAll(((StructureDataForge) otherData).moltenMetals.getFluidStacks());
        moltenMetals = new MoltenMetalTank(moltenMetals.getCapacity() + ((StructureDataForge) otherData).getStructureForge().getPartLocations().size() * References.General.FLUID_INGOT * TileEntityForge.TANKINGOTCAPACITY, Integer.MAX_VALUE, stacks);
    }

    @Override
    public boolean isBurning() {
        return isBurning;
    }

    @Override
    public void setBurning(boolean burning) {
        isBurning = burning;
    }

    @Override
    public int getTotalBurningTicksOnCurrentFuel() {
        return totalBurningTicksOnCurrentFuel;
    }

    @Override
    public void setTotalBurningTicksOnCurrentFuel(int totalBurningTicksOnCurrentFuel) {
        this.totalBurningTicksOnCurrentFuel = totalBurningTicksOnCurrentFuel;
    }

    @Override
    public void changeTotalBurningTicksOnCurrentFuel(int change) {
        this.totalBurningTicksOnCurrentFuel += change;
    }

    @Override
    public int getBurningTicksLeftOnCurrentFuel() {
        return burningTicksLeftOnCurrentFuel;
    }

    @Override
    public void setBurningTicksLeftOnCurrentFuel(int burningTicksLeftOnCurrentFuel) {
        this.burningTicksLeftOnCurrentFuel = burningTicksLeftOnCurrentFuel;
    }

    @Override
    public void changeBurningTicksLeftOnCurrentFuel(int change) {
        this.burningTicksLeftOnCurrentFuel += change;
    }

    @Override
    public void resetBurningTicksLeftOnCurrentFuel() {
        setBurningTicksLeftOnCurrentFuel(getTotalBurningTicksOnCurrentFuel());
    }

    @Nonnull
    public MoltenMetalTank getMoltenMetals() {
        return moltenMetals;
    }

    public void updateTank() {
        ArrayList<FluidStack> stacks = new ArrayList<>(moltenMetals.getFluidStacks());
        moltenMetals = new MoltenMetalTank(structureForge.getPartLocations().size() * References.General.FLUID_INGOT * TileEntityForge.TANKINGOTCAPACITY, Integer.MAX_VALUE, stacks);
    }
}
