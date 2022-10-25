package com.stupidrepo.invisibilityhat.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForgetMeWandItem extends Item {
    public ForgetMeWandItem() {
        super(new Properties().stacksTo(1).fireResistant().tab(InvisibilityTab.INVISIBILITY_TAB));
    }

    private static final int radius = 25;

    private void sendMessageToPlayer(Level level, Player player, String text) {
        if(!level.isClientSide) { player.sendSystemMessage(Component.nullToEmpty(text)); }
    }

    private int stopAllChasingMobs(Level level, Player player) {
        List<Mob> mobs = level.getEntitiesOfClass(Mob.class, new AABB(player.getBlockX() + radius, player.getBlockY() + radius, player.getBlockZ() + radius, player.getBlockX() - radius, player.getBlockY() - radius, player.getBlockZ() - radius), mob -> mob.getTarget() == player);
        mobs.forEach(mob -> mob.setTarget(null));
        return mobs.size();
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        int mobsDeleted = stopAllChasingMobs(level, player);
        player.setHealth((float) (player.getHealth() - (.25 * mobsDeleted)));
        sendMessageToPlayer(level, player, String.format("Ouch! %d mob(s) were deleted.", mobsDeleted));
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag isAdvanced) {
        components.add(Component.literal("Hold SHIFT to view side effects!").withStyle(ChatFormatting.RED));
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Each mob that gets deleted will take .25 hearts from you.").withStyle(ChatFormatting.DARK_RED));
        }
        super.appendHoverText(stack, level, components, isAdvanced);
    }
}
