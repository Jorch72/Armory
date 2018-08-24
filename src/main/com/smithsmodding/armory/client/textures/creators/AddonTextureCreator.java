package com.smithsmodding.armory.client.textures.creators;

import com.smithsmodding.armory.api.client.textures.creation.ICreationController;
import com.smithsmodding.armory.api.common.material.armor.IAddonArmorMaterial;
import com.smithsmodding.armory.api.util.client.TextureCreationHelper;
import com.smithsmodding.armory.api.util.references.ModLogger;
import com.smithsmodding.armory.common.api.ArmoryAPI;
import com.smithsmodding.smithscore.core.interfaces.ITextureMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marcf on 1/5/2017.
 */
public class AddonTextureCreator extends IForgeRegistryEntry.Impl<ICreationController> implements ICreationController {

    public static final String ADDONTEXTUREIDENTIFIER = "addon";

    /**
     * Method to create a materialized texture. It is called from the MaterializedTextureCreator to create the texture in the Map
     *
     * @param map          The TextureMap to register the textures to.
     * @param baseTexture  The baseTexture to manipulate
     * @param buildSprites A List of textures already created. The upper Map holds te baseTexture as key and the lower map the material name as key.
     */
    @Override
    public void createMaterializedTextures(@Nonnull ITextureMap map, @Nonnull ResourceLocation baseTexture, @Nonnull Map<ResourceLocation, Map<String, TextureAtlasSprite>> buildSprites) {
        if (!buildSprites.containsKey(baseTexture))
            buildSprites.put(baseTexture, new HashMap<>());

        //Grabbing the base texture so that we can color a copy.
        TextureAtlasSprite base = map.getTextureViaName(baseTexture.toString());
        if (base == null) {
            //A missing texture does not need coloring. Skipping.
            ModLogger.getInstance().error("Missing base texture for Addon coloring: " + baseTexture.toString());
            return;
        }

        Map<String, TextureAtlasSprite> coloredSprites = buildSprites.get(baseTexture);
        for (IAddonArmorMaterial material : ArmoryAPI.getInstance().getRegistryManager().getAddonArmorMaterialRegistry()) {
            if (coloredSprites.containsKey(material.getOreDictionaryIdentifier()))
            {
                continue;
            }

            TextureAtlasSprite sprite = TextureCreationHelper.createTexture(material, material.getTextureOverrideIdentifier(), baseTexture, base, map, ADDONTEXTUREIDENTIFIER);
            if (sprite != null) {
                coloredSprites.put(material.getOreDictionaryIdentifier(), sprite);
            }
        }
    }
}
