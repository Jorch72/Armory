package com.smithsmodding.armory.common.item;

import com.smithsmodding.armory.api.util.references.ModCreativeTabs;
import com.smithsmodding.armory.api.util.references.References;
import com.smithsmodding.smithscore.util.CoreReferences;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Orion
 * Created on 16.05.2015
 * 13:42
 * <p>
 * Copyrighted according to Project specific license
 */
public class ItemTongs extends Item {

    public ItemTongs() {
        setMaxStackSize(1);
        setMaxDamage(150);
        setCreativeTab(ModCreativeTabs.GENERAL);
        setUnlocalizedName(References.InternalNames.Items.IN_TONGS);
        setRegistryName(References.InternalNames.Items.IN_TONGS.toLowerCase());
        addPropertyOverride(CoreReferences.IItemProperties.MODELTYPE, new IItemPropertyGetter() {
            @Override
            public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
                return stack.getTagCompound().getString(CoreReferences.NBT.IItemProperties.TARGET).equals(References.InternalNames.Items.IN_TONGS) ? 1f : 0f;
            }
        });
    }

    @Override
    public double getDurabilityForDisplay(ItemStack pStack) {
        return 1 - ((pStack.getItemDamage()) / (float) 150);
    }

    @Override
    public boolean getHasSubtypes() {
        return true;
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> stacks)
    {
        if (tab != getCreativeTab())
        {
            return;
        }

        ItemStack tTongsStack = new ItemStack(this, 1, 150);
        stacks.add(tTongsStack);
    }
}
