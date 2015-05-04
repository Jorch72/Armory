package com.Orion.Armory.Common.TileEntity;
/*
 *   TileEntityHeater
 *   Created by: Orion
 *   Created on: 12-10-2014
 */

import com.Orion.Armory.Network.Messages.MessageTileEntityHeater;
import com.Orion.Armory.Network.NetworkManager;
import com.Orion.Armory.Util.References;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityHeater extends TileEntityArmory implements IInventory
{
    public ItemStack iFanStack = null;
    int iTargetX;
    int iTargetY;
    int iTargetZ;

    public int iItemInSlotTicks = 0;
    public float iLastRotationAngle = 0F;

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int pSlotID) {
        if (pSlotID == 0)
        {
            return iFanStack;
        }

        return null;
    }

    @Override
    public ItemStack decrStackSize(int pSlotID, int pAmount) {
        if (pSlotID != 0)
        {
            return null;
        }

        if (iFanStack == null)
        {
            return null;
        }

        ItemStack itemstack;

        if (iFanStack.stackSize <= pAmount)
        {
            itemstack = iFanStack;
            iFanStack = null;
            this.markDirty();
            return itemstack;
        }
        else
        {
            itemstack = iFanStack.splitStack(pAmount);

            if (iFanStack.stackSize == 0)
            {
                iFanStack = null;
            }

            this.markDirty();
            return itemstack;
        }
    }


    @Override
    public ItemStack getStackInSlotOnClosing(int pSlotID) {
        if (pSlotID == 0)
        {
            return iFanStack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int pSlotID, ItemStack pNewItemStack) {
        iFanStack = pNewItemStack;

        if (iFanStack == null)
        {
            iItemInSlotTicks = 0;
            iLastRotationAngle = 0F;
        }

        if (pNewItemStack != null && pNewItemStack.stackSize > this.getInventoryStackLimit())
        {
            pNewItemStack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.iName : StatCollector.translateToLocal(References.InternalNames.Blocks.FirePit);
    }

    @Override
    public boolean hasCustomInventoryName() {
        return ((this.iName.length() > 0) && this.iName.isEmpty() == false);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer pPlayer) {
        return true;
    }

    @Override
    public void openInventory() {
        //No animation and definitely no cat on top of this nice puppy
    }

    @Override
    public void closeInventory() {
        //NOOP
    }

    @Override
    public boolean isItemValidForSlot(int pSlotID, ItemStack pStack) {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound pCompound)
    {
        super.writeToNBT(pCompound);

        if (iFanStack != null)
        {
            pCompound.setTag(References.NBTTagCompoundData.TE.Heater.FANSTACK, iFanStack.writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public float getProgressBarValue(String pProgressBarID) {
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound pCompound)
    {
        super.readFromNBT(pCompound);

        if (pCompound.hasKey(References.NBTTagCompoundData.TE.Heater.FANSTACK))
        {
            iFanStack = ItemStack.loadItemStackFromNBT(pCompound.getCompoundTag(References.NBTTagCompoundData.TE.Heater.FANSTACK));
        }
    }

    @Override
    public void updateEntity()
    {
        if (IsContainingAFan())
        {
            iItemInSlotTicks++;
        }
    }

    public boolean IsContainingAFan()
    {
        return (iFanStack != null);
    }

    public boolean IsHelpingAFirePit()
    {
        if (!IsContainingAFan())
        {
            return false;
        }

        TileEntity tTargetTE = getWorldObj().getTileEntity(iTargetX, iTargetY, iTargetZ);
        if (tTargetTE == null)
        {
            return false;
        }

        return (tTargetTE instanceof TileEntityFirePit);
    }


    @Override
    public void markDirty()
    {
        NetworkManager.INSTANCE.sendToAllAround(new MessageTileEntityHeater(this), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId,(double) this.xCoord,(double) this.yCoord,(double) this.zCoord, 128));
        //worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        super.markDirty();
        worldObj.func_147451_t(xCoord, yCoord, zCoord);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return NetworkManager.INSTANCE.getPacketFrom(new MessageTileEntityHeater(this));
    }

    public boolean validateTarget()
    {
        ForgeDirection oppositeDirection = iCurrentDirection.getOpposite();

        iTargetX = this.xCoord + oppositeDirection.offsetX;
        iTargetY = this.yCoord + oppositeDirection.offsetY;
        iTargetZ = this.zCoord + oppositeDirection.offsetZ;

        TileEntity tTargetTE = getWorldObj().getTileEntity(iTargetX, iTargetY, iTargetZ);
        if (tTargetTE == null)
        {
            return false;
        }

        return (tTargetTE instanceof TileEntityFirePit);
    }
}
