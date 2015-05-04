package com.Orion.Armory.Common.Factory;
/*
/  HeatedItemFactory
/  Created by : Orion
/  Created on : 03/10/2014
*/

import com.Orion.Armory.Common.Item.IHeatableItem;
import com.Orion.Armory.Common.Item.ItemHeatedItem;
import com.Orion.Armory.Common.Registry.GeneralRegistry;
import com.Orion.Armory.Util.Core.ItemStackHelper;
import com.Orion.Armory.Util.References;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;

public class HeatedItemFactory
{
    public static HeatedItemFactory iInstance = null;
    protected ArrayList<ItemStack> iHeatableItems = new ArrayList<ItemStack>();
    protected ArrayList<String> iMappedNames = new ArrayList<String>();

    public static HeatedItemFactory getInstance()
    {
        if (iInstance == null)
        {
            iInstance = new HeatedItemFactory();
        }

        return iInstance;
    }

    public ItemStack convertToHeatedIngot(ItemStack pCooledIngotStack)
    {
        if (!isHeatable(pCooledIngotStack))
        {
            GeneralRegistry.iLogger.info("Got a not convertable item!:");
            GeneralRegistry.iLogger.info(ItemStackHelper.toString(pCooledIngotStack));
            return null;
        }

        ItemStack tReturnStack = new ItemStack(GeneralRegistry.Items.iHeatedIngot);
        NBTTagCompound tStackCompound = new NBTTagCompound();

        tStackCompound.setTag(References.NBTTagCompoundData.HeatedIngot.ORIGINALITEM, pCooledIngotStack.writeToNBT(new NBTTagCompound()));
        tStackCompound.setString(References.NBTTagCompoundData.HeatedIngot.MATERIALID, getMaterialIDFromItemStack(pCooledIngotStack));
        tStackCompound.setInteger(References.NBTTagCompoundData.HeatedIngot.CURRENTTEMPERATURE, 20);

        if (pCooledIngotStack.getItem() instanceof IHeatableItem)
        {
            tStackCompound.setString(References.NBTTagCompoundData.HeatedIngot.TYPE, ((IHeatableItem) pCooledIngotStack.getItem()).getInternalType());
        }
        else
        {
            tStackCompound.setString(References.NBTTagCompoundData.HeatedIngot.TYPE, References.InternalNames.HeatedItemTypes.INGOT);
        }

        tReturnStack.setTagCompound(tStackCompound);

        return tReturnStack;
    }

    public ItemStack convertToCooledIngot(ItemStack pHeatedItemStack)
    {
        if (!(pHeatedItemStack.getItem() instanceof ItemHeatedItem))
        {
            if (isHeatable(pHeatedItemStack))
            {
                return pHeatedItemStack;
            }

            throw new InvalidParameterException("The given parameter is not a heatable item. Please report this to the modder!");
        }

        ItemStack tReturnStack = ItemStack.loadItemStackFromNBT(pHeatedItemStack.getTagCompound().getCompoundTag(References.NBTTagCompoundData.HeatedIngot.ORIGINALITEM));

        return tReturnStack;
    }

    public void addHeatableItemstack(String pMaterialName, ItemStack pNewItemStack)
    {
        ItemStack tSingledItemStack = pNewItemStack.copy();
        tSingledItemStack.stackSize = 1;

        if (!isHeatable(tSingledItemStack))
        {
            this.iHeatableItems.add(tSingledItemStack);
            this.iMappedNames.add(pMaterialName);
        }
    }

    public String getMaterialIDFromItemStack(ItemStack pItemStack)
    {
        if (pItemStack.getItem() instanceof ItemHeatedItem)
        {
            return pItemStack.getTagCompound().getString(References.NBTTagCompoundData.HeatedIngot.MATERIALID);
        }

        ItemStack tSingledItemStack = pItemStack.copy();
        tSingledItemStack.stackSize = 1;

        if (isHeatable(tSingledItemStack))
        {
            return iMappedNames.get(getIndexOfStack(tSingledItemStack));
        }

        return "";
    }

    public float getMeltingPointFromMaterial(String pMaterialName)
    {
        return GeneralRegistry.getInstance().getMeltingPoint(pMaterialName);
    }

    public ArrayList<ItemStack> getAllMappedStacks()
    {
        return iHeatableItems;
    }

    public float getMeltingPointFromMaterial(ItemStack pItemStack)
    {
        return this.getMeltingPointFromMaterial(this.getMaterialIDFromItemStack(pItemStack));
    }

    public String getType(ItemStack pHeatedItemStack)
    {
        if(!(pHeatedItemStack.getItem() instanceof ItemHeatedItem))
        {
            return "";
        }

        if(!pHeatedItemStack.getTagCompound().hasKey(References.NBTTagCompoundData.HeatedIngot.TYPE))
        {
            return References.InternalNames.HeatedItemTypes.INGOT;
        }

        return pHeatedItemStack.getTagCompound().getString(References.NBTTagCompoundData.HeatedIngot.TYPE);
    }

    public boolean isHeatable(ItemStack pItemStack)
    {
        ItemStack tSingledItemStack = pItemStack.copy();
        tSingledItemStack.stackSize = 1;

        Iterator<ItemStack> tStackIter = getAllMappedStacks().iterator();

        while (tStackIter.hasNext())
        {
            if (ItemStackHelper.equalsIgnoreStackSize(tStackIter.next(), tSingledItemStack))
            {
                return true;
            }
        }

        return false;
    }

    private int getIndexOfStack(ItemStack pStack) {
        Iterator<ItemStack> tStackIter = getAllMappedStacks().iterator();
        int tIndex = 0;

        while (tStackIter.hasNext()) {
            if (ItemStackHelper.equalsIgnoreStackSize(tStackIter.next(), pStack)) {
                return tIndex;
            }

            tIndex++;
        }

        return -1;
    }
}