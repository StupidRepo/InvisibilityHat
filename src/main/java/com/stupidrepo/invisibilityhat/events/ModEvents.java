package com.stupidrepo.invisibilityhat.events;

import com.stupidrepo.invisibilityhat.InvisibilityHat;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InvisibilityHat.MOD_ID)
public class ModEvents {
    public static boolean shouldCancel(Player player) {
        return !player.hasEffect(MobEffects.INVISIBILITY) && !player.isCreative() && player.isInvisible();
    }

    @SubscribeEvent
    public static void blockBrokenCheck(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        event.setCanceled(shouldCancel(player));
    }

    @SubscribeEvent
    public static void blockPlacedCheck(BlockEvent.EntityPlaceEvent event) {
        if(event.getEntity() instanceof Player player) {
            event.setCanceled(shouldCancel(player));
        }
    }

    @SubscribeEvent
    public static void entityAttackCheck(AttackEntityEvent event) {
        Player player = event.getEntity();
        event.setCanceled(shouldCancel(player));
    }
}