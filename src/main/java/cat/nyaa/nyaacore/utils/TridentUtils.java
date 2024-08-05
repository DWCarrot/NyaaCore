package cat.nyaa.nyaacore.utils;

import net.minecraft.world.entity.projectile.ThrownTrident;
import org.bukkit.entity.Trident;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;

public final class TridentUtils {

//    private static Field FIELD_DEALT_DAMAGE;
//
//    static {
//        try {
//            FIELD_DEALT_DAMAGE = ThrownTrident.class.getDeclaredField("dealtDamage");
//            FIELD_DEALT_DAMAGE.setAccessible(true);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//    }

    @Deprecated
    public static ItemStack getTridentItemStack(Trident entity) {
        return entity.getItem();
    }

    @Deprecated
    public static void setTridentItemStack(Trident entity, ItemStack itemStack) {
        entity.setItem(itemStack);
    }

    public static boolean getTridentDealtDamage(Trident entity) {
        ThrownTrident thrownTrident = (ThrownTrident) NmsUtils.getEntity(entity);
        return thrownTrident.dealtDamage;
    }

    public static void setTridentDealtDamage(Trident entity, boolean dealtDamage) {
        ThrownTrident thrownTrident = (ThrownTrident) NmsUtils.getEntity(entity);
        thrownTrident.dealtDamage = dealtDamage;
    }
}
