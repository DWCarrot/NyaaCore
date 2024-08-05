package cat.nyaa.nyaacore.utils;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.component.CustomData;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.logging.Logger;


/// Since 1.20.5, the reobfuscation of the NMS classes has been changed to a new method, which makes it impossible to use CraftItemStack directly.
/// Since 1.20.5, item tags are stored in the CustomData component of the item stack.
public class ItemTagUtils {

    static Class<?> klassCraftItemStack;
    static Field handleCraftItemStack;

    static {
        Logger logger = Logger.getLogger(ItemTagUtils.class.getName());
        try {
            klassCraftItemStack = Class.forName("org.bukkit.craftbukkit.inventory.CraftItemStack");
            handleCraftItemStack = klassCraftItemStack.getDeclaredField("handle"); // net.minecraft.world.item.ItemStack
            handleCraftItemStack.setAccessible(true);
            logger.info("ItemTagUtils initialized");
        } catch (ClassNotFoundException e) {
            logger.severe("Failed to initialize ItemTagUtils: " + e.getMessage());
        } catch (NoSuchFieldException e) {
            logger.severe("Failed to initialize ItemTagUtils: " + e.getMessage());
        }
    }

    static @Nullable net.minecraft.world.item.ItemStack getItem(ItemStack itemStack) {
        if(klassCraftItemStack == null || handleCraftItemStack == null) {
            return null;
        }
        if (!klassCraftItemStack.isInstance(itemStack)) {
            return null;
        }
        try {
            return (net.minecraft.world.item.ItemStack) handleCraftItemStack.get(itemStack);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private static @Nullable CompoundTag getTag(net.minecraft.world.item.ItemStack itemStack) {
        DataComponentMap components = itemStack.getComponents();
        CustomData customData = components.get(DataComponents.CUSTOM_DATA);
        if(customData == null) {
            return null;
        }
        return customData.getUnsafe();
    }

    public static Optional<String> getString(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getString(key));
    }

    public static Optional<String> setString(ItemStack item, String key, String value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putString(key, value));
        return Optional.of(value);
    }

    public static Optional<Integer> getInt(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getInt(key));
    }

    public static Optional<Integer> setInt(ItemStack item, String key, int value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putInt(key, value));
        return Optional.of(value);
    }

    public static Optional<Double> getDouble(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getDouble(key));
    }

    public static Optional<Double> setDouble(ItemStack item, String key, double value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putDouble(key, value));
        return Optional.of(value);
    }

    public static Optional<Short> getShort(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getShort(key));
    }

    public static Optional<Short> setShort(ItemStack item, String key, short value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putShort(key, value));
        return Optional.of(value);
    }

    public static Optional<Byte> getByte(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getByte(key));
    }

    public static Optional<Byte> setByte(ItemStack item, String key, byte value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putByte(key, value));
        return Optional.of(value);
    }

    public static Optional<Long> getLong(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getLong(key));
    }

    public static Optional<Long> setLong(ItemStack item, String key, long value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putLong(key, value));
        return Optional.of(value);
    }

    public static Optional<long[]> getLongArray(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getLongArray(key));
    }

    public static Optional<long[]> setLongArray(ItemStack item, String key, long[] value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putLongArray(key, value));
        return Optional.of(value);
    }

    public static Optional<int[]> getIntArray(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getIntArray(key));
    }

    public static Optional<int[]> setIntArray(ItemStack item, String key, int[] value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putIntArray(key, value));
        return Optional.of(value);
    }

    public static Optional<byte[]> getByteArray(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getByteArray(key));
    }

    public static Optional<byte[]> setByteArray(ItemStack item, String key, byte[] value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putByteArray(key, value));
        return Optional.of(value);
    }

    public static Optional<Boolean> getBoolean(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getBoolean(key));
    }

    public static Optional<Boolean> setBoolean(ItemStack item, String key, boolean value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putBoolean(key, value));
        return Optional.of(value);
    }

    public static Optional<Float> getFloat(ItemStack item, String key) {
        net.minecraft.world.item.ItemStack item1 = getItem(item);
        if (item1 == null) return Optional.empty();
        CompoundTag tag = getTag(item1);
        if (tag == null) return Optional.empty();
        return !tag.contains(key) ? Optional.empty() : Optional.of(tag.getFloat(key));
    }

    public static Optional<Float> setFloat(ItemStack item, String key, float value) {
        net.minecraft.world.item.ItemStack is = getItem(item);
        if (is == null) {
            return Optional.empty();
        }
        CustomData.update(DataComponents.CUSTOM_DATA, is, (tag) -> tag.putFloat(key, value));
        return Optional.of(value);
    }
}
