package com.stupidrepo.invisibilityhat.items;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
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

public class InvisibilityHatItem extends Item {
    public InvisibilityHatItem() {
        super(new Properties().stacksTo(1).fireResistant().tab(InvisibilityTab.INVISIBILITY_TAB));
    }

    private long timeKeepsOnSlippinSlippinSlippin;
    private long intoThe;

    private static final int future = 10;

    private static final int radius = 75;

    private void sendMessageToPlayer(Level level, Player player, String text) {
        if(!level.isClientSide) { player.sendSystemMessage(Component.nullToEmpty(text)); }
    }

    private void stopAllChasingMobs(Level level, Player player) {
        List<Mob> mobs = level.getEntitiesOfClass(Mob.class, new AABB(player.getBlockX() + radius, player.getBlockY() + radius, player.getBlockZ() + radius, player.getBlockX() - radius, player.getBlockY() - radius, player.getBlockZ() - radius), mob -> mob.getTarget() == player);
        mobs.forEach(mob -> mob.setTarget(null));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if(!player.isInvisible()) {
            stopAllChasingMobs(level, player);
            timeKeepsOnSlippinSlippinSlippin = level.getGameTime();
            sendMessageToPlayer(level, player, "Poof! You're now invisible.");
            player.setInvisible(true);
        } else {
            intoThe = level.getGameTime();
            int elapsed = (int) (intoThe - timeKeepsOnSlippinSlippinSlippin);
            sendMessageToPlayer(level, player, "Woosh! You're now visible.");
            player.setInvisible(false);
            player.getCooldowns().addCooldown(this, (elapsed / 2));
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag isAdvanced) {
        components.add(Component.literal("Hold SHIFT to view side effects!").withStyle(ChatFormatting.RED));
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("When going visible, you won't be able to mine blocks, place blocks\nor attack mobs for the amount of time you was invisible.").withStyle(ChatFormatting.DARK_RED));
        }
        super.appendHoverText(stack, level, components, isAdvanced);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player && !player.hasEffect(MobEffects.INVISIBILITY) && player.isInvisible() && ((level.getGameTime() - timeKeepsOnSlippinSlippinSlippin) / 20) >= future) {
            sendMessageToPlayer(level, player, String.format("Uh oh! You was invisibile for longer than %d seconds, so you became visible again!", future));
            player.setInvisible(false);
            player.getCooldowns().addCooldown(this, future * 20);
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
