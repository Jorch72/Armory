package com.smithsmodding.armory.client.render.tileentity;

import com.smithsmodding.armory.client.render.tileentity.conduit.ConduitRenderComponents;
import com.smithsmodding.armory.common.block.types.EnumPumpType;
import com.smithsmodding.armory.common.tileentity.TileEntityPump;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fluids.FluidStack;

/**
 * Created by marcf on 2/8/2017.
 */
public class TileEntityRendererPump extends TileEntitySpecialRenderer<TileEntityPump> {

    @Override
    public void render(final TileEntityPump te, final double x, final double y, final double z, final float partialTicks, final int destroyStage, final float alpha)
    {
        if (te == null)
            return;

        if (te.getTotalTankSizeOnSide(null) == 0)
            return;

        double height = te.getTankContentsVolumeOnSide(null) / ((double) te.getTotalTankSizeOnSide(null)) * 0.05;
        FluidStack stack = te.getTankForSide(null).getFluid();
        if (stack == null)
            return;

        ConduitRenderComponents.getRenderer(null).render(stack, te.getPos(), x,y,z, height);
        ConduitRenderComponents.getRenderer(te.getFacing()).render(stack, te.getPos(), x,y,z, height);

        if (te.getType() != EnumPumpType.VERTICAL)
            ConduitRenderComponents.getRenderer(te.getFacing().getOpposite()).render(stack, te.getPos(), x, y, z, height);
    }
}
