package com.SmithsModding.Armory.Client.Gui;

import com.SmithsModding.Armory.Common.TileEntity.*;
import com.SmithsModding.Armory.Util.*;
import com.SmithsModding.SmithsCore.Client.GUI.Components.Core.*;
import com.SmithsModding.SmithsCore.Client.GUI.Components.Implementations.*;
import com.SmithsModding.SmithsCore.Client.GUI.*;
import com.SmithsModding.SmithsCore.Client.GUI.Host.*;
import com.SmithsModding.SmithsCore.Client.GUI.State.*;
import com.SmithsModding.SmithsCore.Common.Inventory.*;
import com.SmithsModding.SmithsCore.Util.Client.Color.*;
import com.SmithsModding.SmithsCore.Util.Client.*;
import com.SmithsModding.SmithsCore.Util.Common.Postioning.*;
import net.minecraft.inventory.*;

/**
 * Created by Marc on 22.12.2015.
 */
public class GuiFirePit extends GuiContainerSmithsCore {

    public static Plane GUI = new Plane(0, 0, ComponentPlayerInventory.WIDTH, 245);

    public GuiFirePit (ContainerSmithsCore container) {
        super(container);
    }

    @Override
    public void registerComponents (IGUIBasedComponentHost host) {
        registerNewComponent(new ComponentBorder(References.InternalNames.GUIComponents.FirePit.BACKGROUND, this, new Coordinate2D(0, 0), GUI.getWidth(), GUI.getHeigth() - ( ComponentPlayerInventory.HEIGHT - 3 ), Colors.DEFAULT, ComponentBorder.CornerTypes.Inwarts, ComponentBorder.CornerTypes.Inwarts, ComponentBorder.CornerTypes.Inwarts, ComponentBorder.CornerTypes.Inwarts));
        registerNewComponent(new ComponentPlayerInventory(References.InternalNames.GUIComponents.FirePit.INVENTORY, this, new Coordinate2D(0, 76), Colors.DEFAULT, ( (ContainerSmithsCore) inventorySlots ).getPlayerInventory(), ComponentConnectionType.BELOWDIRECTCONNECT));


        registerNewComponent(new ComponentProgressBar(References.InternalNames.GUIComponents.FirePit.FLAMEONE, new CoreComponentState(null), this, new Coordinate2D(44, 40), ComponentDirection.VERTICALBOTTOMTOTOP, Textures.Gui.Basic.Components.FLAMEEMPTY, Textures.Gui.Basic.Components.ARROWFULL));
        registerNewComponent(new ComponentProgressBar(References.InternalNames.GUIComponents.FirePit.FLAMETWO, new CoreComponentState(null), this, new Coordinate2D(62, 40), ComponentDirection.VERTICALBOTTOMTOTOP, Textures.Gui.Basic.Components.FLAMEEMPTY, Textures.Gui.Basic.Components.ARROWFULL));
        registerNewComponent(new ComponentProgressBar(References.InternalNames.GUIComponents.FirePit.FLAMETHREE, new CoreComponentState(null), this, new Coordinate2D(80, 40), ComponentDirection.VERTICALBOTTOMTOTOP, Textures.Gui.Basic.Components.FLAMEEMPTY, Textures.Gui.Basic.Components.ARROWFULL));
        registerNewComponent(new ComponentProgressBar(References.InternalNames.GUIComponents.FirePit.FLAMEFOUR, new CoreComponentState(null), this, new Coordinate2D(98, 40), ComponentDirection.VERTICALBOTTOMTOTOP, Textures.Gui.Basic.Components.FLAMEEMPTY, Textures.Gui.Basic.Components.ARROWFULL));
        registerNewComponent(new ComponentProgressBar(References.InternalNames.GUIComponents.FirePit.FLAMEFIVE, new CoreComponentState(null), this, new Coordinate2D(116, 40), ComponentDirection.VERTICALBOTTOMTOTOP, Textures.Gui.Basic.Components.FLAMEEMPTY, Textures.Gui.Basic.Components.ARROWFULL));


        for (int tSlotIndex = 0; tSlotIndex < ( TileEntityFirePit.FUELSTACK_AMOUNT + TileEntityFirePit.INGOTSTACKS_AMOUNT ); tSlotIndex++) {
            Slot slot = inventorySlots.inventorySlots.get(tSlotIndex);

            registerNewComponent(new ComponentSlot(References.InternalNames.GUIComponents.FirePit.SLOT + tSlotIndex, new SlotComponentState(null, tSlotIndex, ( (ContainerSmithsCore) inventorySlots ).getContainerInventory(), null), this, slot, Colors.DEFAULT));
        }

    }
}