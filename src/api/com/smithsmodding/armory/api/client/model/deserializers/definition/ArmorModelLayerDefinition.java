package com.smithsmodding.armory.api.client.model.deserializers.definition;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.smithsmodding.armory.api.util.references.References;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Describing a Layer of the Armor.
 */
public class ArmorModelLayerDefinition
{

    @Nonnull private final ImmutableList<ArmorModelPartDefinition> parts;

    /**
     * Constructor for a definition of a layer in the armor model.
     * @param parts the {@link ArmorModelPartDefinition} that make up this layer.
     */
    public ArmorModelLayerDefinition(@Nonnull final List<ArmorModelPartDefinition> parts) {
        this.parts = ImmutableList.copyOf(parts);
    }

    /**
     * Method to get all the textures of a the parts.
     * @return An {@link ImmutableList} of textures of the parts, each texture is unique.
     */
    @Nonnull
    public final ImmutableList<ResourceLocation> getTextures() {
        return ImmutableList.copyOf(parts.stream().map(ArmorModelPartDefinition::getTextureLocation).collect(Collectors.toSet()));
    }

    /**
     * Method to get the parts of the layer.
     * @return An {@link ImmutableList} of parts that make up this layer.
     */
    @Nonnull
    public ImmutableList<ArmorModelPartDefinition> getParts()
    {
        return parts;
    }

    public ArmorModelLayerDefinition createWithOverride(@Nonnull final ImmutableMap<ResourceLocation, ResourceLocation> overrideMap) {
        if (overrideMap.isEmpty())
        {
            return this;
        }

        ImmutableList.Builder<ArmorModelPartDefinition> builder = ImmutableList.builder();

        parts.forEach(part -> {
            if (overrideMap.containsKey(part.getId()) && !overrideMap.get(part.getId()).equals(new ResourceLocation(References.General.MOD_ID, "delete")))
            {
                builder.add(part.createOverride(overrideMap.get(part.getId())));
            }
            else if (!overrideMap.get(part.getId()).equals(new ResourceLocation(References.General.MOD_ID, "delete")))
            {
                builder.add(part);
            }
        });

        return new ArmorModelLayerDefinition(builder.build());
    }
}
