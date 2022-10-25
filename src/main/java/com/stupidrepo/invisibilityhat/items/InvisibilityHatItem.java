package com.stupidrepo.invisibilityhat.items;

import com.stupidrepo.invisibilityhat.InvisibilityHat;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.NotNull;

public class InvisibilityHatItem extends Item {
    public InvisibilityHatItem() {
        super(new Properties().stacksTo(1).fireResistant().tab(CreativeModeTab.TAB_TOOLS));
    }

    public long timeKeepsOnTickingTickingTicking;
    public long intoTheFuture;

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if(!player.isInvisible()) {
            timeKeepsOnTickingTickingTicking = level.getGameTime();
            player.setInvisible(true);
        } else {
            intoTheFuture = level.getGameTime();
            int elapsed = (int) (intoTheFuture - timeKeepsOnTickingTickingTicking);
            player.setInvisible(false);
            player.getCooldowns().addCooldown(this, (elapsed / 10));
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        InvisibilityHat.LOGGER.info(String.valueOf(toolAction)) ;
        return super.canPerformAction(stack, toolAction);
    }
}
