package com.smithsmodding.armory.client.gui.components;

import com.smithsmodding.smithscore.client.gui.GuiContainerSmithsCore;
import com.smithsmodding.smithscore.client.gui.components.core.ComponentOrientation;
import com.smithsmodding.smithscore.client.gui.components.core.IGUIComponent;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentBorder;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentProgressBar;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentSlot;
import com.smithsmodding.smithscore.client.gui.components.implementations.CoreComponent;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.smithsmodding.smithscore.client.gui.management.IGUIManager;
import com.smithsmodding.smithscore.client.gui.state.CoreComponentState;
import com.smithsmodding.smithscore.client.gui.state.IGUIComponentState;
import com.smithsmodding.smithscore.client.gui.state.SlotComponentState;
import com.smithsmodding.smithscore.common.inventory.ContainerSmithsCore;
import com.smithsmodding.smithscore.util.client.Textures;
import com.smithsmodding.smithscore.util.client.color.Colors;
import com.smithsmodding.smithscore.util.common.positioning.Coordinate2D;
import net.minecraft.inventory.IInventory;

import java.util.LinkedHashMap;

/**
 * @Author Marc (Created on: 16.06.2016)
 */
public class Component5X5CraftingGrid extends CoreComponent implements IGUIBasedComponentHost {

    protected final LinkedHashMap<String, IGUIComponent> componentHashMap;
    protected final int startSlotIndexCraftingGrid;
    protected final int endSlotIndexCraftingGrid;
    protected final int craftingProductionSlotIndex;

    protected final ContainerSmithsCore crafter;

    public Component5X5CraftingGrid(String uniqueID, IGUIBasedComponentHost parent, IGUIComponentState state, Coordinate2D rootAnchorPixel, int startSlotIndexCraftingGrid, int endSlotIndexCraftingGrid, int craftingProductionSlotIndex, ContainerSmithsCore crafter) {
        super(uniqueID, parent, state, rootAnchorPixel, 162, 104);
        this.startSlotIndexCraftingGrid = startSlotIndexCraftingGrid;
        this.endSlotIndexCraftingGrid = endSlotIndexCraftingGrid;
        this.craftingProductionSlotIndex = craftingProductionSlotIndex;
        this.crafter = crafter;

        this.componentHashMap = new LinkedHashMap<>();
    }

    @Override
    public void update(int mouseX, int mouseY, float partialTickTime) {
        //NOOP;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {
        //NOOP; RenderManager does the rendering of ComponentHosts for us.
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        //NOOP; RenderManager does the rendering of ComponentHosts for us.
    }

    @Override
    public void registerComponents(IGUIBasedComponentHost host) {
        registerNewComponent(new ComponentBorder(getID() + ".Border", host, getLocalCoordinate(), 162, 104, Colors.DEFAULT, ComponentBorder.CornerTypes.Inwarts, ComponentBorder.CornerTypes.Inwarts, ComponentBorder.CornerTypes.Inwarts, ComponentBorder.CornerTypes.Inwarts));

        for (int slotIndex = startSlotIndexCraftingGrid; slotIndex < endSlotIndexCraftingGrid; slotIndex++) {
            registerNewComponent(new ComponentSlot(getID() + ".Grid.Slot." + (slotIndex - startSlotIndexCraftingGrid), new SlotComponentState(null, crafter.getSlot(slotIndex), crafter.getContainerInventory(), null), host, crafter.getSlot(slotIndex), Colors.DEFAULT));
        }

        registerNewComponent(new ComponentProgressBar(getID() + ".Progress", host, new CoreComponentState(), new Coordinate2D(105, 45), ComponentOrientation.HORIZONTALLEFTTORIGHT, Textures.Gui.Basic.Components.ARROWEMPTY, Textures.Gui.Basic.Components.ARROWFULL));
        registerNewComponent(new ComponentSlot(getID() + ".Out", new SlotComponentState(null, crafter.getSlot(craftingProductionSlotIndex), crafter.getContainerInventory(), null), host, crafter.getSlot(craftingProductionSlotIndex), Colors.DEFAULT));
    }

    @Override
    public void registerNewComponent (IGUIComponent component) {
        componentHashMap.put(component.getID(), component);

        if (component instanceof IGUIBasedComponentHost)
            ( (IGUIBasedComponentHost) component ).registerComponents((IGUIBasedComponentHost) component);
    }

    @Override
    public GuiContainerSmithsCore getRootGuiObject() {
        return parent.getRootGuiObject();
    }

    @Override
    public IGUIManager getRootManager() {
        return parent.getRootManager();
    }

    @Override
    public LinkedHashMap<String, IGUIComponent> getAllComponents() {
        return componentHashMap;
    }

    @Override
    public IGUIComponent getComponentByID(String uniqueUIID) {
        return componentHashMap.get(uniqueUIID);
    }

    @Override
    public IGUIManager getManager() {
        return parent.getManager();
    }

    @Override
    public void setManager(IGUIManager newManager) {
        parent.setManager(newManager);
    }
}