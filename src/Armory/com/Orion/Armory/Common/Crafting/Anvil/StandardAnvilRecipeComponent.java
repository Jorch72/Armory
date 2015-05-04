package com.Orion.Armory.Common.Crafting.Anvil;

import com.Orion.Armory.Util.Core.ItemStackHelper;
import net.minecraft.item.ItemStack;

/**
 * Created by Orion
 * Created on 02.05.2015
 * 12:58
 * <p/>
 * Copyrighted according to Project specific license
 */
public class StandardAnvilRecipeComponent implements IAnvilRecipeComponent
{
    private ItemStack iTargetItemStack;
    private int iComponentUsage;

    StandardAnvilRecipeComponent(ItemStack pTargetStack)
    {
        setComponentTargetStack(pTargetStack);
        setComponentStackUsage(1);
    }

    StandardAnvilRecipeComponent(ItemStack pTargetStack, int pComponentUsage)
    {
        setComponentTargetStack(pTargetStack);
        setComponentStackUsage(pComponentUsage);
    }

    @Override
    public ItemStack getComponentTargetStack() {
        return iTargetItemStack;
    }

    @Override
    public void setComponentTargetStack(ItemStack pNewTargetStack) {
        iTargetItemStack = pNewTargetStack;
    }

    @Override
    public int getResultingStackSizeForComponent(ItemStack pComponentStack) {
        if (ItemStackHelper.equalsIgnoreStackSize(pComponentStack, iTargetItemStack))
        {
            return pComponentStack.stackSize - iComponentUsage;
        }


        return pComponentStack.stackSize;
    }

    @Override
    public void setComponentStackUsage(int pNewUsage) {
        iComponentUsage = pNewUsage;
    }

    @Override
    public boolean isValidComponentForSlot(ItemStack pComparedItemStack) {
        if(ItemStackHelper.equalsIgnoreStackSize(pComparedItemStack, iTargetItemStack))
        {
            if (getResultingStackSizeForComponent(pComparedItemStack) >= 0)
            {
                return true;
            }
            return false;
        }

        return false;
    }
}