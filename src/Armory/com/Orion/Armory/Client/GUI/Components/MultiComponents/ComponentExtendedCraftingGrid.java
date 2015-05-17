package com.Orion.Armory.Client.GUI.Components.MultiComponents;

import com.Orion.Armory.Client.GUI.ArmoryBaseGui;
import com.Orion.Armory.Client.GUI.Components.ComponentBorder;
import com.Orion.Armory.Client.GUI.Components.ComponentProgressBar;
import com.Orion.Armory.Client.GUI.Components.ComponentSlot;
import com.Orion.Armory.Client.GUI.Components.Core.AbstractGUIMultiComponent;
import com.Orion.Armory.Util.Client.Color;
import com.Orion.Armory.Util.Client.Textures;

/**
 * Created by Orion
 * Created on 03.05.2015
 * 11:31
 * <p/>
 * Copyrighted according to Project specific license
 */
public class ComponentExtendedCraftingGrid extends AbstractGUIMultiComponent
{

    public ComponentExtendedCraftingGrid(ArmoryBaseGui pGui, String pInternalName, int pLeft, int pTop, int pStartPostitionCraftingSlots, int pEndPostitionCraftingSlots, Color pBackground, Color pForeground) {
        super(pGui, pInternalName, pLeft, pTop, 104, 104);

        getComponents().add(new ComponentBorder(pGui, pInternalName + ".Background", 0, 0, 162, 104, pBackground, ComponentBorder.CornerTypes.Inwarts));

        for (int tSlotIndex = pStartPostitionCraftingSlots; tSlotIndex < pEndPostitionCraftingSlots; tSlotIndex ++)
        {
            int tRowIndex = ((tSlotIndex - pStartPostitionCraftingSlots) / 5);
            int tColumnIndex = (tSlotIndex - pStartPostitionCraftingSlots) % 5;

            addComponent(new ComponentSlot(pGui, pInternalName + ".Slot.Crafting." + tSlotIndex, tSlotIndex, 18, 18, 7 + tColumnIndex * 18, 7 + tRowIndex * 18, Textures.Gui.Basic.Slots.DEFAULT, pBackground));
        }

        addComponent(new ComponentProgressBar(pGui, pInternalName + ".Progress.Arrow.1",105, 45, pForeground, pBackground));
        addComponent(new ComponentSlot(pGui, pInternalName + ".Slot.Output", pEndPostitionCraftingSlots, 18, 18, 137, 43, Textures.Gui.Basic.Slots.DEFAULT, pBackground));
    }

    @Override
    public boolean handleMouseClicked(int pMouseX, int pMouseY, int pMouseButton) {
        return false;
    }
}
