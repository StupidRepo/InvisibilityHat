package com.stupidrepo.invisibilityhat.items;

import com.stupidrepo.invisibilityhat.InvisibilityHat;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InvisibilityHat.MOD_ID);
    public static final RegistryObject<Item> INVIS_HAT = ITEMS.register("invis_hat", InvisibilityHatItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
