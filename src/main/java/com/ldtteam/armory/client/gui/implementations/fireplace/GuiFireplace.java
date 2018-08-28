package com.ldtteam.armory.client.gui.implementations.fireplace;

import com.ldtteam.armory.api.util.client.Textures;
import com.ldtteam.armory.api.util.client.TranslationKeys;
import com.ldtteam.smithscore.client.gui.GuiContainerSmithsCore;
import com.ldtteam.smithscore.client.gui.components.core.IGUIComponent;
import com.ldtteam.smithscore.client.gui.components.implementations.ComponentLabel;
import com.ldtteam.smithscore.client.gui.components.implementations.ComponentPlayerInventory;
import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedLedgerHost;
import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedTabHost;
import com.ldtteam.smithscore.client.gui.legders.core.LedgerConnectionSide;
import com.ldtteam.smithscore.client.gui.legders.implementations.CoreLedger;
import com.ldtteam.smithscore.client.gui.legders.implementations.InformationLedger;
import com.ldtteam.smithscore.client.gui.state.CoreComponentState;
import com.ldtteam.smithscore.client.gui.state.LedgerComponentState;
import com.ldtteam.smithscore.common.inventory.ContainerSmithsCore;
import com.ldtteam.smithscore.util.client.CustomResource;
import com.ldtteam.smithscore.util.client.color.Colors;
import com.ldtteam.smithscore.util.client.color.MinecraftColor;
import com.ldtteam.smithscore.util.common.positioning.Coordinate2D;
import com.ldtteam.smithscore.util.common.positioning.Plane;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;

/**
 * Created by Marc on 22.12.2015.
 */
public class GuiFireplace extends GuiContainerSmithsCore {

    @Nonnull
    public static Plane GUI = new Plane(0, 0, ComponentPlayerInventory.DEFAULT_WIDTH, 245);

    public GuiFireplace(@Nonnull ContainerSmithsCore container) {
        super(container);
    }

    /**
     * Method called by the gui system to initialize this tab host.
     *
     * @param host The host for the tabs.
     */
    @Override
    public void registerTabs(IGUIBasedTabHost host) {
        registerNewTab(new TabFireplaceMeltingMetal(getID() + ".Tabs.Inventory", host, new ItemStack(Items.IRON_INGOT), Colors.DEFAULT, "Melting ingots"));
        registerNewTab(new TabFireplaceFood(getID() + ".Tabs.MoltenMetal", host, new ItemStack(Items.COOKED_BEEF), Colors.DEFAULT, "Molten metals."));
    }

    /**
     * Function used to register the sub components of this ComponentHost
     *
     * @param parent This ComponentHosts host. For the Root GUIObject a reference to itself will be passed in..
     */
    @Override
    public void registerLedgers(IGUIBasedLedgerHost parent) {
        ArrayList<String> information = new ArrayList<String>();

        information.add(I18n.format(TranslationKeys.Gui.Fireplace.InfoLine1));
        information.add(I18n.format(TranslationKeys.Gui.Fireplace.InfoLine2));
        information.add(I18n.format(TranslationKeys.Gui.Fireplace.InfoLine3));

        registerNewLedger(new InformationLedger(getID() + ".Ledgers.Information", this, LedgerConnectionSide.LEFT, I18n.format(TranslationKeys.Gui.InformationTitel), new MinecraftColor(MinecraftColor.YELLOW), information));
        registerNewLedger(new TemperatureLedger(getID() + ".Ledgers.Temperature", this, LedgerConnectionSide.RIGHT, Textures.Gui.FirePit.THERMOMETERICON, I18n.format(TranslationKeys.Gui.Fireplace.TempTitel), new MinecraftColor(MinecraftColor.RED)));
    }

    public class TemperatureLedger extends CoreLedger {

        Plane maxArea;
        @Nonnull
        private String currentTemperatureLabel = "";
        @Nonnull
        private String maxTemperatureLabel = "";
        @Nonnull
        private String lastAddedLabel = "";
        @Nonnull
        private String cookingMultiplayerLabel = "";

        public TemperatureLedger(String uniqueID, IGUIBasedLedgerHost root, LedgerConnectionSide side, @Nonnull CustomResource ledgerIcon, String translatedLedgerHeader, MinecraftColor color) {
            super(uniqueID, new LedgerComponentState(), root, side, ledgerIcon, translatedLedgerHeader, color);
        }

        /**
         * Method used by the rendering and animation system to determine the max size of the Ledger.
         *
         * @return An int bigger then 16 plus the icon width that describes the maximum width of the Ledger when expanded.
         */
        @Override
        public int getMaxWidth() {
            return maxArea.getWidth();
        }

        /**
         * Method used by the rendering and animation system to determine the max size of the Ledger.
         *
         * @return An int bigger then 16 plus the icon height that describes the maximum height of the Ledger when expanded.
         */
        @Override
        public int getMaxHeight() {
            return maxArea.getHeigth();
        }

        /**
         * Function used to register the sub components of this ComponentHost
         *
         * @param host This ComponentHosts host. For the Root GUIObject a reference to itself will be passed in..
         */
        @Override
        public void registerComponents(IGUIBasedComponentHost host) {
            super.registerComponents(host);

            currentTemperatureLabel = I18n.format(TranslationKeys.Gui.Fireplace.TempCurrent) + ": 10000C";
            maxTemperatureLabel = I18n.format(TranslationKeys.Gui.Fireplace.TempMax) + ": 10000C";
            lastAddedLabel = I18n.format(TranslationKeys.Gui.Fireplace.LastAdded) + ": 10000C";
            cookingMultiplayerLabel = I18n.format(TranslationKeys.Gui.Fireplace.CookingMultiplier) + ": 10000C";

            registerNewComponent(new ComponentLabel(getID() + ".CurrentTemperature",
              host,
              new CoreComponentState(),
              new Coordinate2D(10, 26),
              new MinecraftColor(MinecraftColor.WHITE),
              Minecraft.getMinecraft().fontRenderer,
              currentTemperatureLabel)
            {
                /**
                 * Method gets called before the component gets rendered, allows for animations to calculate through.
                 *
                 * @param mouseX          The X-Coordinate of the mouse.
                 * @param mouseY          The Y-Coordinate of the mouse.
                 * @param partialTickTime The partial tick time, used to calculate fluent animations.
                 */
                @Override
                public void update(int mouseX, int mouseY, float partialTickTime) {
                    this.displayedText = I18n.format(TranslationKeys.Gui.Fireplace.TempCurrent) + ": " + getManager().getLabelContents(this);
                }
            });

            registerNewComponent(new ComponentLabel(getID() + ".MaxTemperature",
              host,
              new CoreComponentState(),
              new Coordinate2D(10, 26 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 3),
              new MinecraftColor(MinecraftColor.WHITE),
              Minecraft.getMinecraft().fontRenderer,
              maxTemperatureLabel)
            {
                /**
                 * Method gets called before the component gets rendered, allows for animations to calculate through.
                 *
                 * @param mouseX          The X-Coordinate of the mouse.
                 * @param mouseY          The Y-Coordinate of the mouse.
                 * @param partialTickTime The partial tick time, used to calculate fluent animations.
                 */
                @Override
                public void update(int mouseX, int mouseY, float partialTickTime) {
                    this.displayedText = I18n.format(TranslationKeys.Gui.Fireplace.TempMax) + ": " + getManager().getLabelContents(this);
                }
            });

            registerNewComponent(new ComponentLabel(getID() + ".LastChange",
              host,
              new CoreComponentState(),
              new Coordinate2D(10, 26 + 2 * (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 3)),
              new MinecraftColor(MinecraftColor.WHITE),
              Minecraft.getMinecraft().fontRenderer,
              lastAddedLabel)
            {
                /**
                 * Method gets called before the component gets rendered, allows for animations to calculate through.
                 *
                 * @param mouseX          The X-Coordinate of the mouse.
                 * @param mouseY          The Y-Coordinate of the mouse.
                 * @param partialTickTime The partial tick time, used to calculate fluent animations.
                 */
                @Override
                public void update(int mouseX, int mouseY, float partialTickTime) {
                    this.displayedText = I18n.format(TranslationKeys.Gui.Fireplace.LastAdded) + ": " + getManager().getLabelContents(this);
                }
            });

            registerNewComponent(new ComponentLabel(getID() + ".CookingMultiplier",
              host,
              new CoreComponentState(),
              new Coordinate2D(10, 26 + 4 * (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 3)),
              new MinecraftColor(MinecraftColor.WHITE),
              Minecraft.getMinecraft().fontRenderer,
              cookingMultiplayerLabel)
            {
                /**
                 * Method gets called before the component gets rendered, allows for animations to calculate through.
                 *
                 * @param mouseX          The X-Coordinate of the mouse.
                 * @param mouseY          The Y-Coordinate of the mouse.
                 * @param partialTickTime The partial tick time, used to calculate fluent animations.
                 */
                @Override
                public void update(int mouseX, int mouseY, float partialTickTime) {
                    this.displayedText = I18n.format(TranslationKeys.Gui.Fireplace.CookingMultiplier) + ": " + getManager().getLabelContents(this);
                }
            });

            Plane area = new Plane(0, 0, 0, 0);

            for (IGUIComponent component : getAllComponents().values()) {
                area.IncludeCoordinate(new Plane(component.getLocalCoordinate(), component.getSize().getWidth(), component.getSize().getHeigth()));
            }

            maxArea = new Plane(new Coordinate2D(0, 0), area.getWidth() + 10, area.getHeigth() + 10);
        }
    }
}
