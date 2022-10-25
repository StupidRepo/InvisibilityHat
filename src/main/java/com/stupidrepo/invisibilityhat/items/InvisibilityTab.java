package com.stupidrepo.invisibilityhat.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class InvisibilityTab {
    public static final CreativeModeTab INVISIBILITY_TAB = new CreativeModeTab("invisibilitytab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.ACACIA_SAPLING);
        }
    };
}
