package com.ldtteam.armory.common.inventory.slots;
/*
 *   SlotHeatable
 *   Created by: Orion
 *   Created on: 18-1-2015
 */

import com.ldtteam.armory.api.IArmoryAPI;
import com.ldtteam.armory.common.tileentity.TileEntityForge;
import com.ldtteam.smithscore.common.inventory.IItemStorage;
import com.ldtteam.smithscore.common.inventory.slot.SlotSmithsCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class SlotHeatable extends SlotSmithsCore {

    private int meltingProgressIndex;

    public SlotHeatable(IItemStorage pInventory, int pSlotIndex, int pXLocation, int pYLocation, int meltingProgressIndex) {
        super(pInventory, pSlotIndex, pXLocation, pYLocation);

        this.meltingProgressIndex = meltingProgressIndex;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack pItemStack) {
        return IArmoryAPI.Holder.getInstance().getHelpers().getHeatableOverrideManager().isHeatable(pItemStack);
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        if (!(((IItemStorage.IInventoryWrapper) inventory).getStorage() instanceof TileEntityForge))
            return true;

        TileEntityForge forge = ((TileEntityForge) ((IItemStorage.IInventoryWrapper) inventory).getStorage());

        return forge.getState().getMeltingProgess(meltingProgressIndex) == 0f;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public void onSlotChanged() {
        return;
    }
}
