package com.stupidrepo.invisibilityhat.items;

import com.stupidrepo.invisibilityhat.damagesources.ForgotLifeSource;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
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

    private int killChasingMobs(Level level, Player player) {
        List<Mob> mobs = level.getEntitiesOfClass(Mob.class, new AABB(player.getBlockX() + radius, player.getBlockY() + radius, player.getBlockZ() + radius, player.getBlockX() - radius, player.getBlockY() - radius, player.getBlockZ() - radius), mob -> mob.getTarget() == player);
        mobs.forEach(LivingEntity::kill);
        return mobs.size();
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        int mobsDeleted = killChasingMobs(level, player);
        float heartsToTakeAway = (float) (.25 * mobsDeleted);
        if(mobsDeleted == 0) { return super.use(level, player, hand); }
        player.hurt(new ForgotLifeSource(), heartsToTakeAway);
        player.getCooldowns().addCooldown(this, mobsDeleted * 20);
        sendMessageToPlayer(level, player, String.format("Ouch! %d %s got deleted. You've lost %.2f %s!", mobsDeleted, (mobsDeleted == 1) ? "mob" : "mobs", heartsToTakeAway, (heartsToTakeAway == 1) ? "heart" : "hearts"));
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
