package com.smithsmodding.armory.api.common.material.client;

import com.smithsmodding.armory.api.client.textures.types.*;
import com.smithsmodding.smithscore.client.textures.ITextureController;
import com.smithsmodding.smithscore.util.client.color.MinecraftColor;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/*
  A BIG NOTE UPFRONT. Due to the similarities between TiC ToolSystem and Armories armor system this is a near repackage.
  Most of this code falls under their license, although some changes are made to fit the system in with Armories used
  of Wrapper classes instead of direct access.
 */

/**
 * Determines the type of texture used for rendering a specific material
 */
@SideOnly(Side.CLIENT)
public class MaterialRenderControllers {

    /**
     * Abstract core implementation of the RenderInfo.
     */
    public static abstract class AbstractMaterialTextureController implements ITextureController {
        private String suffix;
        private String identifier;

        @Override
        public boolean isStitched () {
            return true;
        }

        @Override
        public boolean useVertexColoring () {
            return false;
        }

        @Override
        public MinecraftColor getVertexColor () {
            return new MinecraftColor(MinecraftColor.white);
        }

        /**
         * Method used by the rendering system to get the Vertex color for liquids.
         *
         * @return The color for the molten metal if armories default system should be used.
         */
        @Override
        public MinecraftColor getLiquidColor () {
            return getVertexColor();
        }

        @Override
        public String getTextureSuffix () {
            return suffix;
        }

        @Nonnull
        @Override
        public ITextureController setTextureSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        @Override
        @Nonnull
        public String getCreationIdentifier() {
            return identifier;
        }

        @Nonnull
        @Override
        public ITextureController setCreationIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }
    }

    /**
     * Does not actually generate a new texture. Used for vertex-coloring in the model generation
     * Safes VRAM, so we use vertex colors instead of creating new data.
     */
    public static class Default extends AbstractMaterialTextureController {
        public final MinecraftColor color;

        public Default (MinecraftColor color) {
            this.color = color;
        }

        @Override
        public TextureAtlasSprite getTexture (TextureAtlasSprite baseTexture, String location) {
            return baseTexture;
        }

        @Override
        public boolean isStitched () {
            return false;
        }

        @Override
        public boolean useVertexColoring () {
            return true;
        }

        @Override
        public MinecraftColor getVertexColor () {
            return color;
        }
    }

    /**
     * Colors the texture of the tool with the material color
     */
    public static class MultiColor extends AbstractMaterialTextureController {

        // colors to be used
        protected final int low, mid, high;

        public MultiColor (int low, int mid, int high) {
            this.low = low;
            this.mid = mid;
            this.high = high;
        }

        @Nonnull
        @Override
        public MinecraftColor getVertexColor () {
            return new MinecraftColor(mid);
        }

        @Nonnull
        @Override
        public TextureAtlasSprite getTexture(@Nonnull TextureAtlasSprite baseTexture, String location) {
            return new SimpleColoredTexture(low, mid, high, baseTexture, location);
        }
    }

    public static class InverseMultiColor extends MultiColor {

        public InverseMultiColor (int low, int mid, int high) {
            super(low, mid, high);
        }

        @Nonnull
        @Override
        public MinecraftColor getVertexColor () {
            return new MinecraftColor(mid);
        }

        @Nonnull
        @Override
        public TextureAtlasSprite getTexture(@Nonnull TextureAtlasSprite baseTexture, String location) {
            return new InverseColoredTexture(low, mid, high, baseTexture, location);
        }
    }

    public static class Metal extends AbstractMaterialTextureController {
        public int color;
        protected float shinyness;
        protected float brightness;
        protected float hueshift;

        public Metal (int color, float shinyness, float brightness, float hueshift) {
            this.color = color;
            this.shinyness = shinyness;
            this.brightness = brightness;
            this.hueshift = hueshift;
        }

        public Metal (int color) {
            this(color, 0.4f, 0.4f, 0.1f);
        }

        @Nonnull
        @Override
        public MinecraftColor getVertexColor () {
            return new MinecraftColor(color);
        }

        @Nonnull
        @Override
        public TextureAtlasSprite getTexture(@Nonnull TextureAtlasSprite baseTexture, String location) {
            return new MetalColoredTexture(baseTexture, location, color, shinyness, brightness, hueshift);
        }
    }

    /**
     * Uses a (block) texture instead of a color to create the texture
     */
    public static class BlockTexture extends AbstractMaterialTextureController {

        protected String texturePath;
        protected Block block;

        public BlockTexture (String texturePath) {
            this.texturePath = texturePath;
        }

        @Nonnull
        @Override
        public TextureAtlasSprite getTexture(@Nonnull TextureAtlasSprite baseTexture, String location) {
            TextureAtlasSprite blockTexture = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(texturePath);

            if (blockTexture == null) {
                blockTexture = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
            }

            TextureColoredTexture sprite = new TextureColoredTexture(blockTexture, baseTexture, location);
            sprite.stencil = false;
            return sprite;
        }
    }


    /**
     * Creates an animated texture from an animated base texture. USE WITH CAUTION.
     * ACTUALLY ONLY USE THIS IF YOU KNOW EXACTLY WHAT YOU'RE DOING.
     */
    public static class AnimatedTexture extends AbstractMaterialTextureController {

        protected String texturePath;

        public AnimatedTexture (String texturePath) {
            this.texturePath = texturePath;
        }

        @Nonnull
        @Override
        public TextureAtlasSprite getTexture(@Nonnull TextureAtlasSprite baseTexture, String location) {
            TextureAtlasSprite blockTexture = Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(texturePath);

            if (blockTexture == null) {
                blockTexture = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
            }

            TextureColoredTexture sprite = new AnimatedColoredTexture(blockTexture, baseTexture, location);
            return sprite;
        }
    }

}