package com.smithsmodding.armory.api.common.capability;

import com.smithsmodding.armory.api.IArmoryAPI;
import com.smithsmodding.armory.api.common.armor.IMultiComponentArmor;
import com.smithsmodding.armory.api.common.armor.IMultiComponentArmorExtensionInformation;
import com.smithsmodding.armory.api.common.material.armor.ICoreArmorMaterial;
import com.smithsmodding.armory.api.util.common.armor.ArmorNBTHelper;
import com.smithsmodding.armory.api.util.references.ModArmor;
import com.smithsmodding.armory.api.util.references.ModMaterials;
import com.smithsmodding.armory.api.util.references.References;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcf on 1/5/2017.
 */
public interface IMultiComponentArmorCapability {

    /**
     * Method to get the Type of armor that is in the ItemStack.
     * @return The armor type that is inside the ItemStack that this capability is in.
     */
    @Nonnull
    IMultiComponentArmor getArmorType();

    /**
     * Setter to set the Type of armor that is in the ItemStack.
     * @param armorType The new Type that is in the ItemStack.
     */
    IMultiComponentArmorCapability setArmorType(@Nonnull IMultiComponentArmor armorType);

    /**
     * Getter for a List of installed ADDONS in the ItemStack
     * @return A list of installed ADDONS.
     */
    @Nonnull
    List<IMultiComponentArmorExtensionInformation> getInstalledExtensions();

    /**
     * Setter for a List of installed ADDONS in the ItemStack
     * @param  installedExtensions The new list of installed addons.
     */
    IMultiComponentArmorCapability setInstalledExtensions(@Nonnull List<IMultiComponentArmorExtensionInformation> installedExtensions);

    /**
     * Method to check if an Instance is broken.
     * @return True if broken, False if not.
     */
    @Nonnull
    Boolean isBroken();

    /**
     * Method to set the broken state of this instance.
     * @param isBroken True when the instance is broken, false when not.
     */
    IMultiComponentArmorCapability setBroken(@Nonnull Boolean isBroken);

    /**
     * Getter for the CoreArmorMaterial of the Armor.
     * @return The core armor CoreMaterial of the Armor.
     */
    @Nonnull
    ICoreArmorMaterial getMaterial();

    /**
     * Setter for the CoreArmorMaterial of the Armor.
     * @param coreArmorMaterial The new core armor CoreMaterial of the Armor.
     */
    IMultiComponentArmorCapability setMaterial(@Nonnull ICoreArmorMaterial coreArmorMaterial);

    /**
     * Getter for the current durability in the current configuration.
     * @return The current durability.
     */
    @Nonnull
    Integer getCurrentDurability();

    /**
     * Setter for the current durability in the current configuration.
     * @param currentDurability The new current configuration.
     * @return The instance this method was called on.
     */
    @Nonnull
    IMultiComponentArmorCapability setCurrentDurability(@Nonnull Integer currentDurability);

    /**
     * Method to decrease the current durability by 1.
     */
    void decreaseCurrentDurability();

    /**
     * Method to decrease the current durability by a given amount.
     * @param durability amount to descrease.
     */
    void decreaseCurrentDurability(@Nonnull Integer durability);

    class Impl implements IMultiComponentArmorCapability {

        @Nonnull
        private IMultiComponentArmor armor = ModArmor.Medieval.CHESTPLATE;

        @Nonnull
        private List<IMultiComponentArmorExtensionInformation> installedExtensions = new ArrayList<>();

        @Nonnull
        private Boolean broken = Boolean.FALSE;

        @Nonnull
        private ICoreArmorMaterial coreArmorMaterial = ModMaterials.Armor.Core.IRON;

        @Nonnull
        private Integer durability = 100;

        public Impl()
        {
        }

        /**
         * Method to get the Type of armor that is in the ItemStack.
         *
         * @return The armor type that is inside the ItemStack that this capability is in.
         */
        @Nonnull
        @Override
        public IMultiComponentArmor getArmorType() {
            return this.armor;
        }

        /**
         * Setter to set the Type of armor that is in the ItemStack.
         *
         * @param armorType The new Type that is in the ItemStack.
         */
        @Override
        public IMultiComponentArmorCapability setArmorType(@Nonnull IMultiComponentArmor armorType) {
            this.armor = armorType;
            return this;
        }

        /**
         * Getter for a List of installed ADDONS in the ItemStack
         *
         * @return A list of installed ADDONS.
         */
        @Nonnull
        @Override
        public List<IMultiComponentArmorExtensionInformation> getInstalledExtensions()
        {
            return new ArrayList<>(this.installedExtensions);
        }

        /**
         * Setter for a List of installed ADDONS in the ItemStack
         *
         * @param installedExtensions The new list of installed addons.
         */
        @Override
        public IMultiComponentArmorCapability setInstalledExtensions(@Nonnull List<IMultiComponentArmorExtensionInformation> installedExtensions)
        {
            this.installedExtensions = installedExtensions;
            return this;
        }

        /**
         * Method to check if an Instance is broken.
         *
         * @return True if broken, False if not.
         */
        @Nonnull
        @Override
        public Boolean isBroken() {
            return broken || getCurrentDurability() <= 0;
        }

        /**
         * Method to set the broken state of this instance.
         *
         * @param isBroken True when the instance is broken, false when not.
         */
        @Override
        public IMultiComponentArmorCapability setBroken(@Nonnull Boolean isBroken) {
            this.broken = isBroken;
            return this;
        }

        /**
         * Getter for the CoreArmorMaterial of the Armor.
         *
         * @return The core armor CoreMaterial of the Armor.
         */
        @Nonnull
        @Override
        public ICoreArmorMaterial getMaterial() {
            return coreArmorMaterial;
        }

        /**
         * Setter for the CoreArmorMaterial of the Armor.
         *
         * @param coreArmorMaterial The new core armor CoreMaterial of the Armor.
         */
        @Override
        public IMultiComponentArmorCapability setMaterial(@Nonnull ICoreArmorMaterial coreArmorMaterial) {
            this.coreArmorMaterial = coreArmorMaterial;
            return this;
        }

        /**
         * Getter for the current durability in the current configuration.
         *
         * @return The current durability.
         */
        @Nonnull
        @Override
        public Integer getCurrentDurability() {
            return durability;
        }

        /**
         * Setter for the current durability in the current configuration.
         *
         * @param currentDurability The new current configuration.
         * @return The instance this method was called on.
         */
        @Nonnull
        @Override
        public IMultiComponentArmorCapability setCurrentDurability(@Nonnull Integer currentDurability) {
            this.durability = currentDurability;
            return this;
        }

        /**
         * Method to decrease the current durability by 1.
         */
        @Override
        public void decreaseCurrentDurability() {
            durability--;
        }

        /**
         * Method to decrease the current durability by a given amount.
         *
         * @param durability amount to descrease.
         */
        @Override
        public void decreaseCurrentDurability(@Nonnull Integer durability) {
            this.durability = this.durability - durability;
        }
    }

    class Storage implements Capability.IStorage<IMultiComponentArmorCapability> {

        @Override
        public NBTBase writeNBT(Capability<IMultiComponentArmorCapability> capability, IMultiComponentArmorCapability instance, EnumFacing side) {
            NBTTagCompound compound = new NBTTagCompound();

            compound.setString(References.NBTTagCompoundData.Armor.NAME, instance.getArmorType().getRegistryName().toString());

            NBTTagList extensionList = ArmorNBTHelper.createExtensionListCompound(instance.getInstalledExtensions());
            compound.setTag(References.NBTTagCompoundData.Armor.ADDONS, extensionList);

            compound.setBoolean(References.NBTTagCompoundData.Armor.IS_BROKEN, instance.isBroken());
            compound.setString(References.NBTTagCompoundData.Armor.CORE_MATERIAL, instance.getMaterial().getRegistryName().toString());
            compound.setInteger(References.NBTTagCompoundData.Armor.CURRENT_DURABILITY, instance.getCurrentDurability());

            return compound;
        }

        @Override
        public void readNBT(Capability<IMultiComponentArmorCapability> capability, IMultiComponentArmorCapability instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound compound = (NBTTagCompound) nbt;

            instance.setArmorType(IArmoryAPI.Holder.getInstance().getRegistryManager().getMultiComponentArmorRegistry().getValue(new ResourceLocation(compound.getString(References.NBTTagCompoundData.Armor.NAME))));
            instance.setInstalledExtensions(ArmorNBTHelper.getExtensionMap(compound.getTagList(References.NBTTagCompoundData.Armor.ADDONS, Constants.NBT.TAG_COMPOUND)));
            instance.setBroken(compound.getBoolean(References.NBTTagCompoundData.Armor.IS_BROKEN));
            instance.setMaterial(IArmoryAPI.Holder.getInstance().getRegistryManager().getCoreMaterialRegistry().getValue(new ResourceLocation(compound.getString(References.NBTTagCompoundData.Armor.CORE_MATERIAL))));
            instance.setCurrentDurability(compound.getInteger(References.NBTTagCompoundData.Armor.CURRENT_DURABILITY));
        }
    }
}
