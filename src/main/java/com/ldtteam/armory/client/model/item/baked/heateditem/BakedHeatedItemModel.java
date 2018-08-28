package com.ldtteam.armory.client.model.item.baked.heateditem;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.ldtteam.armory.api.common.capability.IHeatedObjectCapability;
import com.ldtteam.armory.api.util.references.ModCapabilities;
import com.ldtteam.armory.client.model.item.baked.components.BakedTemperatureBarModel;
import com.ldtteam.smithscore.client.model.baked.BakedWrappedModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marc on 08.12.2015.
 */
public class BakedHeatedItemModel extends BakedWrappedModel {
    @Nonnull
    private static final List<List<BakedQuad>> empty_face_quads;
    @Nonnull
    private static final List<BakedQuad> empty_list;

    static {
        empty_list = Collections.emptyList();
        empty_face_quads = Lists.newArrayList();
        for (int i = 0; i < 6; i++) {
            empty_face_quads.add(empty_list);
        }
    }

    @Nonnull
    private final Overrides overrides;
    protected BakedTemperatureBarModel gaugeDisplay;
    protected BakedTemperatureBarModel gaugeDisplayTurned;

    /**
     * The length of brokenParts has to match the length of parts. If a part does not have a broken texture, the entry
     * in the array simply is null.
     */
    public BakedHeatedItemModel(IBakedModel parent, BakedTemperatureBarModel gaugeDislay, BakedTemperatureBarModel gaugeDisplayTurned) {
        super(parent);

        this.gaugeDisplayTurned = gaugeDisplayTurned;
        this.gaugeDisplay = gaugeDislay;

        overrides = new Overrides(this);
    }

    @Nonnull
    @Override
    public ItemOverrideList getOverrides() {
        return overrides;
    }

    private static class Overrides extends ItemOverrideList {

        private final BakedHeatedItemModel parent;

        public Overrides(BakedHeatedItemModel parent) {
            super(new ArrayList<>());
            this.parent = parent;
        }

        @Nonnull
        @Override
        public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
            // get the texture for each part
            ImmutableList.Builder<BakedQuad> quads = ImmutableList.builder();

            if (!stack.hasCapability(ModCapabilities.MOD_HEATEDOBJECT_CAPABILITY, null)) {
                return Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
            }

            IHeatedObjectCapability heatedObjectCapability = stack.getCapability(ModCapabilities.MOD_HEATEDOBJECT_CAPABILITY, null);

            ItemStack cooledStack = heatedObjectCapability.getOriginalStack();
            IBakedModel original = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(cooledStack);

            original = original.getOverrides().handleItemState(original, cooledStack, world, entity);

            int barIndex = (int) ((heatedObjectCapability.getTemperature() / heatedObjectCapability.getMaterial().getMeltingPoint()) * (parent.gaugeDisplay.getModelCount() - 1));

            if (cooledStack.getItem() instanceof ItemBlock) {
                quads.addAll(new ArrayList<>(parent.gaugeDisplayTurned.getModel(barIndex).getQuads(null, null, 0)));
            } else {
                quads.addAll(new ArrayList<>(parent.gaugeDisplay.getModel(barIndex).getQuads(null, null, 0)));
            }

            //TODO: Add the new QUADS Again!.

            PerspectiveUnawareBakedHeatedItemItemModel combinedModel = new PerspectiveUnawareBakedHeatedItemItemModel(original);

            PerspectiveDependentBakedHeatedItemItemModel guiModel = new PerspectiveDependentBakedHeatedItemItemModel(original, quads.build());

            combinedModel.registerModel(ItemCameraTransforms.TransformType.GUI, guiModel);

            return combinedModel;
        }
    }
}

