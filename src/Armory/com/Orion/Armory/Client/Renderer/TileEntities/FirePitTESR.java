package com.Orion.Armory.Client.Renderer.TileEntities;
/*
 *   FirePitTEST
 *   Created by: Orion
 *   Created on: 10-10-2014
 */

import com.Orion.Armory.Client.Models.ModelFirePit;
import com.Orion.Armory.Common.TileEntity.TileEntityFirePit;
import com.Orion.Armory.Util.References;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class FirePitTESR extends TileEntitySpecialRenderer
{
    protected final ResourceLocation iOffTexture = new ResourceLocation(References.General.MOD_ID + ":" + "textures/blocks/FirePitTextureOff.png");
    protected final ResourceLocation iBurningTexture = new ResourceLocation(References.General.MOD_ID + ":" + "texture/blocks/FirePitTextureOn.png");
    protected final ResourceLocation iCoalSurfaceTexture = new ResourceLocation(References.General.MOD_ID + ":" + "textures/blocks/CoalField.png");

    public final ModelFirePit iModel = new ModelFirePit();
    public final RenderItem iItemRenderer;

    public FirePitTESR()
    {
        iItemRenderer = new RenderItem()
        {
            @Override
            public boolean shouldBob()
            {
                return false;
            }
        };

        iItemRenderer.setRenderManager(RenderManager.instance);
    }

    @Override
    public void renderTileEntityAt(TileEntity pEntity, double pX, double pY, double pZ, float pPartialTickTime) {
        if (!(pEntity instanceof TileEntityFirePit))
        {
            return;
        }

        TileEntityFirePit tTEFirePit = (TileEntityFirePit) pEntity;
        boolean tIsBurning = tTEFirePit.isBurning();

        GL11.glPushMatrix();

        scaleTranslateRotate(pX, pY, pZ, tTEFirePit.getDirection());

        if(!tIsBurning)
        {
            this.bindTexture(iOffTexture);
            iModel.renderPart("Furnace_Cube");

            this.bindTexture(iCoalSurfaceTexture);
            iModel.renderPart("CoalSurface_Plane");
        }
        else
        {
            this.bindTexture(iOffTexture);
            iModel.renderPart("Furnace_Cube");
        }

        //TODO: Render the front tools.

        GL11.glPopMatrix();
    }

    private void scaleTranslateRotate(double x, double y, double z, ForgeDirection orientation)
    {
        if (orientation == ForgeDirection.NORTH)
        {
            GL11.glTranslated(x + 1, y, z);
            GL11.glRotatef(180F, 0F, 1F, 0F);

        }
        else if (orientation == ForgeDirection.EAST)
        {
            GL11.glTranslated(x + 1, y, z + 1);
            GL11.glRotatef(90F, 0F, 1F, 0F);
        }
        else if (orientation == ForgeDirection.SOUTH)
        {
            GL11.glTranslated(x, y, z + 1);
        }
        else if (orientation == ForgeDirection.WEST)
        {
            GL11.glTranslated(x, y, z);
            GL11.glRotatef(-90F, 0F, 1F, 0F);
        }
    }
}
