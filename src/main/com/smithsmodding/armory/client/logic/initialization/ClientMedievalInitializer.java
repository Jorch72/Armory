package com.smithsmodding.armory.client.logic.initialization;

import com.smithsmodding.armory.api.common.armor.IMultiComponentArmor;
import com.smithsmodding.armory.api.common.material.anvil.IAnvilMaterial;
import com.smithsmodding.armory.api.common.material.armor.IAddonArmorMaterial;
import com.smithsmodding.armory.api.common.material.armor.ICoreArmorMaterial;
import com.smithsmodding.armory.api.common.material.client.MaterialRenderControllers;
import com.smithsmodding.armory.api.util.client.ModelTransforms;
import com.smithsmodding.armory.api.util.references.ModArmor;
import com.smithsmodding.armory.api.util.references.ModMaterials;
import com.smithsmodding.armory.api.util.references.References;
import com.smithsmodding.smithscore.util.client.color.MinecraftColor;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;

/**
 * Created by marcf on 1/25/2017.
 */
@Mod.EventBusSubscriber(modid = References.General.MOD_ID, value = Side.CLIENT)
public class ClientMedievalInitializer
{

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleMedievalArmorRegistration(@Nonnull RegistryEvent.Register<IMultiComponentArmor> armorRegisterEvent)
    {
        ModelBiped componentModel = new ModelBiped(1);
        ModArmor.Medieval.CHESTPLATE.setRendererForArmor(componentModel.bipedBody);
        ModArmor.Medieval.HELMET.setRendererForArmor(componentModel.bipedHead);
        ModArmor.Medieval.LEGGINGS.setRendererForArmor(componentModel.bipedBody);
        ModArmor.Medieval.SHOES.setRendererForArmor(componentModel.bipedBody);

        ModArmor.Medieval.CHESTPLATE.setRenderTransforms(getChestplateModelTransforms());
        ModArmor.Medieval.HELMET.setRenderTransforms(getHelmetModelTransforms());
        ModArmor.Medieval.LEGGINGS.setRenderTransforms(getLeggingsModelTransforms());
        ModArmor.Medieval.SHOES.setRenderTransforms(getShoesModelTransforms());
    }

    private static ModelTransforms getChestplateModelTransforms()
    {
        ModelTransforms transforms = new ModelTransforms();

        transforms.setOffsetY(transforms.getOffsetY() - 0.25f);

        return transforms;
    }

    private static ModelTransforms getHelmetModelTransforms()
    {
        ModelTransforms transforms = new ModelTransforms();

        transforms.setOffsetY(transforms.getOffsetY() - 0.25f);
        transforms.setBaseScale(0.625f);

        return transforms;
    }

    private static ModelTransforms getLeggingsModelTransforms()
    {
        return new ModelTransforms();
    }

    private static ModelTransforms getShoesModelTransforms()
    {
        return new ModelTransforms();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleCoreMaterialRegistration(@Nonnull RegistryEvent.Register<ICoreArmorMaterial> coreArmorMaterialRegisterMaterialEvent)
    {
        ModMaterials.Armor.Core.IRON.setRenderInfo(new MaterialRenderControllers.Metal(References.Colors.Materials.CLR_IRON.getRGB(), 0f, 0.3f, 0f) {
            @Nonnull
            @Override
            public MinecraftColor getLiquidColor() {
                return new MinecraftColor(MinecraftColor.RED);
            }
        });

        ModMaterials.Armor.Core.OBSIDIAN.setRenderInfo(new MaterialRenderControllers.MultiColor(References.Colors.Materials.CLR_OBSIDIAN.getRGB(), 0x8f60d4, 0x8c53df));

        ModMaterials.Armor.Core.GOLD.setRenderInfo(new MaterialRenderControllers.Metal(References.Colors.Materials.CLR_GOLD.getRGB(), 0f, 0.3f, 0f) {
            @Nonnull
            @Override
            public MinecraftColor getLiquidColor() {
                return References.Colors.Materials.CLR_GOLD;
            }
        });

        ModMaterials.Armor.Core.STEEL.setRenderInfo(new MaterialRenderControllers.Metal(References.Colors.Materials.CLR_STEEL.getRGB(), 0f, 0.3f, 0f) {
            @Nonnull
            @Override
            public MinecraftColor getLiquidColor() {
                return References.Colors.Materials.CLR_STEEL;
            }
        });

        ModMaterials.Armor.Core.HARDENED_IRON.setRenderInfo(new MaterialRenderControllers.Metal(References.Colors.Materials.CLR_HARDENED_IRON.getRGB(), 0f, 0.3f, 0f)
        {
            @Override
            public MinecraftColor getLiquidColor()
            {
                return References.Colors.Materials.CLR_HARDENED_IRON;
            }
        });
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleAddonMaterialRegistration(@Nonnull RegistryEvent.Register<IAddonArmorMaterial> coreArmorMaterialRegisterMaterialEvent)
    {
        ModMaterials.Armor.Addon.IRON.setRenderInfo(ModMaterials.Armor.Core.IRON.getRenderInfo());
        ModMaterials.Armor.Addon.OBSIDIAN.setRenderInfo(ModMaterials.Armor.Core.OBSIDIAN.getRenderInfo());
        ModMaterials.Armor.Addon.GOLD.setRenderInfo(ModMaterials.Armor.Core.GOLD.getRenderInfo());
        ModMaterials.Armor.Addon.STEEL.setRenderInfo(ModMaterials.Armor.Core.STEEL.getRenderInfo());
        ModMaterials.Armor.Addon.HARDENED_IRON.setRenderInfo(ModMaterials.Armor.Core.HARDENED_IRON.getRenderInfo());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void handleAnvilMaterialRegistration(@Nonnull RegistryEvent.Register<IAnvilMaterial> coreArmorMaterialRegisterMaterialEvent)
    {
        ModMaterials.Anvil.STONE.setRenderInfo(new MaterialRenderControllers.BlockTexture("minecraft:blocks/stone"));
        ModMaterials.Anvil.IRON.setRenderInfo(ModMaterials.Armor.Core.IRON.getRenderInfo());
        ModMaterials.Anvil.OBSIDIAN.setRenderInfo(ModMaterials.Armor.Core.OBSIDIAN.getRenderInfo());
    }
}
