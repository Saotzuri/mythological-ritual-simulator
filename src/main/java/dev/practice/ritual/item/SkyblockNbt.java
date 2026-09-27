package dev.practice.ritual.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.core.component.DataComponents;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

/**
 * Writes Hypixel-style {@code custom_data:{id:"..."}} so SBO ItemLookup.sbId works.
 */
public final class SkyblockNbt {
    private SkyblockNbt() {}

    public static org.bukkit.inventory.ItemStack withId(org.bukkit.inventory.ItemStack bukkit, String id) {
        ItemStack nms = CraftItemStack.asNMSCopy(bukkit);
        CompoundTag tag = new CompoundTag();
        tag.putString("id", id);
        nms.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        return CraftItemStack.asBukkitCopy((ItemInstance) nms);
    }
}
