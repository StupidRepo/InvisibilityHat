package com.stupidrepo.invisibilityhat.items;

import com.stupidrepo.invisibilityhat.InvisibilityHat;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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

    // Guess the song:

    private long timeKeepsOnSlippinSlippinSlippin;
    private long intoThe;

    private static final int future = 10;

    // Now this one (it's easier):

    private static final int ifYouChangeYourMind = 200;
    // (Take a chance)
    private static final int onTheFirstDayInLine = 50;
    // Honey, I'm still free. Take a chance on me.
    // If you need me, let me know! Gonna be around. If you got no place to go, if you're feeling down.
    // If you're all alone when the pretty birds have flown,
    // Honey, I'm still free! Take a chance on me!
    // Gonna do my very best, and it ain't no lie.
    // If you put me to the test, if you let me flyyyyyyyyyyy!
    // Take a chance on meeeee! (That's all I ask of you, honey.)
    // Take a chance on meeeeeeeee!
    // We can go a-dancing (UHHHHOOOH), we can go a-walking (YEEAHHAHAHHHH), as long as we're together ('gether).
    // Listen to music, maybe just talkin' to get to know you better (better)
    // 'Cause you know I got... so much I wanna do! (Ok, I'll stop this now lol)

    // Now this one!:

    // ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga
    // do (sha sha, sha sha) do (sha sha, sha sha) do (sha sha, sha sha)
    // do BA BA BA BA
    // You keep sayin' you got somethin' for me.
    // Somethin' you call love, but confess.
    // Hhhyooou've been a-missin' where you shouldn't have been a-missin'!
    // And now someone else is gettin' all your best ;)
    // [song title], and that's just what they'll do!
    // One of these days these boots are gonna walk all over you.
    // ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga
    // do (sha sha, yeahhhhhh, sha sha)
    // do (sha sha, sha sha) do (sha sha, sha sha)
    // do BA BA BA BA
    // (epic turmpet) You keep lying when you oughta be truthin'!
    // And you keep losin' when you oughta not bet.
    // You keep sem*n(?) when you oughta be a-changin'.
    // Now, what's right is right but you ain't bin right yet.
    // [song title], and that's just what they'll do!
    // One of these days these boots (epic crumpet ends) are gonna walk all over you.
    // ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga
    // do (sha sha, sha sha) do (sha sha, sha sha) do (sha sha, sha sha)
    // do BA BA BA BA
    // You keep playin' where you shouldn't be a-playin'!
    // And you keep thinkin' that you'll never get burnt (HAAAAH)
    // Iahhhhhhhh just found me a brand new box of matches (EEEEYEHHHH)
    // And what he knows, you ain't had time to learn
    // [song title], and that's just what they'll do!
    // One of these days these boots are gonna walk all over you.
    // ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga ting tinga
    // do (sha sha, sha sha)-ARE YA READY BOOTS?-do (sha sha, sha sha) do (sha sha, sha sha)
    // do BA BA BA BA-START WALKIN'!
    // DOO BAH DOO BUH BUH BUH BAH DOO BUH BUH BUH BAH DOO BUH BUH BUH BAH
    // DOO BAH DOO BUH BUH BUH BAH DOO BUH BUH BUH BAH DOO BUH BUH BUH BAH
    // DOO BAH DOO BUH BUH BUH BAH DOO BUH BUH BUH BAH DOO BUH BUH BUH BAH

    // Ok, last one:
    // (honk honk!)
    // doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa
    // honky ba boo boo, la honky dee daa! honky ba boo boo, la honky dee daa!
    // Gotta make a move to a town that's right for me!
    // honky ba boo boo, la honky dee daa!
    // Town to keep me movin', keep me groovin' with some enERgY!
    // honky ba boo boo, la honky dee daa!
    // Well, Iahhhh talk about it, talk about it, talk about it, talk about it!
    // yonky da doo doo, ha yonky bee baa!
    // Talk about, talk about, talk about mooOOOOviNG.
    // honky ba boo boo, la honky dee daa!
    // Gotta move on! (honky ba boo boo, la honky dee daa!)
    // Gotta move on! (honky ba boo boo, la honky dee daa!)
    // Gotta move on! (honky ba boo boo, la honky dee daa!)
    // booka booka, looka looka, yooka yooka, spooka spooka
    // rub-rub-rub-ber-ber b-b-b-b-band, band!
    // rub-rub-rub-ber-ber b-b-b-b-band, band!
    // rub-rub-rub-ber-ber b-b-b-b-band, band!
    // rub-rub-rub-ber-ber b-b-doo baa-b-b-band, band!
    // booka booka, looka looka, yooka yooka, spooka spooka (rub-rub-rub-ber-ber b-b-b-b-band, band!)
    // booka booka, looka looka, yooka yooka, spooka spooka (rub-rub-rub-ber-ber b-b-b-b-band, band!)
    // booka booka, looka looka, yooka yooka, spooka spooka (rub-rub-rub-ber-ber b-b-b-b-band, band!)
    // booka booka, looka looka, yooka yooka, spooka spooka (rub-rub-rub-ber-ber b-b-b-b-band, band!)
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa?
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa?
    // (that violin part that I cannot do)
    // doo doo doo doo d-doo
    // dah doo dah doo dah dooo dah dooo DAAAAAHH
    // doo doo doo doo d-doo
    // dah doo dah doo dah dooo dah dooo DUHHHHHHAAAAA
    // doo doo doo doo d-doo
    // dah doo dah doo dah dooo dah DAAAAHAAHH
    // doo doo doo doo d-doo
    // dah doo dah doo dah dooo dahHAAAAAHH
    // (HHHHHHHHHHHHHHHHHH) (yaaaa do daaaa) (HHHHHHHHHHHHHH-fades out) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa?
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa?
    // (that violin part that I cannot do)
    // doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa doo baa
    // honky ba boo boo, la honky dee daa! honky ba boo boo, la honky dee daa!
    // Gotta make a move to a town that's right for me!
    // honky ba boo boo, la honky dee daa!
    // Town to keep me movin', keep me groovin' with some enERgY!
    // honky ba boo boo, la honky dee daa!
    // Well, Iahhhh talk about it, talk about it, talk about it, talk about it!
    // yonky da doo doo, ha yonky bee baa!
    // Talk about, talk about, talk about mooOOOOviNG.
    // honky ba boo boo, la honky dee daa!
    // Gotta move on! (honky ba boo boo, la honky dee daa!)
    // Gotta move on! (honky ba boo boo, la honky dee daa!)
    // Gotta move on! (honky ba boo boo, la honky dee daa!)
    // booka booka, looka looka, yooka yooka, spooka spooka
    // rub-rub-rub-ber-ber b-b-b-b-band, band!
    // rub-rub-rub-ber-ber b-b-b-b-band, band!
    // rub-rub-rub-ber-ber b-b-b-b-band, band!
    // rub-rub-rub-ber-ber b-b-doo baa-b-b-band, band!
    // booka booka, looka looka, yooka yooka, spooka spooka (rub-rub-rub-ber-ber b-b-b-b-band, band!)
    // booka booka, looka looka, yooka yooka, spooka spooka (rub-rub-rub-ber-ber b-b-b-b-band, band!)
    // booka booka, looka looka, yooka yooka, spooka spooka (rub-rub-rub-ber-ber b-b-b-b-band, band!)
    // booka booka, looka looka, yooka yooka, spooka spooka (rub-rub-rub-ber-ber b-b-b-b-band, band!)
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa?
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa?
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa?
    // (that violin part that I cannot do)
    // doo doo doo doo d-doo
    // dah doo dah doo dah dooo dah dooo DAAAAAHH
    // doo doo doo doo d-doo
    // dah doo dah doo dah dooo dah dooo DUHHHHHHAAAAA
    // doo doo doo doo d-doo
    // dah doo dah doo dah dooo dah DAAAAHAAHH
    // doo doo doo doo d-doo
    // dah doo dah doo dah dooo dahHAAAAAHH
    //     // (HHHHHHHHHHHHHHHHHH) (yaaaa do daaaa) (HHHHHHHHHHHHHH-fades out) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Oh, won't you take me toHOOOOhoOOO (yaaaa do daaaa) funkytoooooowhhhhnaaa?
    // (yaaaa do daaaa) Oh, won't you take me to (yaaaa do daaaa) FUNK-E-TOWN-YOOAHHHHH?
    // (that violin part that I cannot do)
    // (yaaaa do daaaa) Won't you take me dooooooowhhhhnaaa? (yaaaa do daaaa) To funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Won't you take me dooooooowhhhhnaaa? (yaaaa do daaaa) To funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Won't you take me dooooooowhhhhnaaa? (yaaaa do daaaa) To funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) Won't you take me dooooooowhhhhnaaa? (yaaaa do daaaa) To funkytoooooowhhhhnaaa!
    // (yaaaa do daaaa) WGHONT YGOU TAGE ME TWO (yaaaa do daaaa) funkytoooooowhhhhnaaa?
    // (yaaaa do daaaa) WGHONT YGOU TAGE ME TWO (yaaaa do daaaa) FUNK-E-TOWN?
    // (yaaaa do daaaa) WGHONT YGOU TAGE ME TWO (yaaaa do daaaa) funkytoooooowhhhhn?
    // (yaaaa do daaaa) WGHONT YGOU TAGE ME TWO (yaaaa do daaaa) FUHH-HUUH-UNNNNKY-TOWN?
    // booka booka, looka looka, yooka yooka, spooka spooka
    // booka booka, looka looka, yooka yooka, spooka spooka
    // Take me! Won't you take me?
    // Take me! Won't you ta-AAAAHHHHH-ke me?
    // Take me! Won't you take me? (WGHONT YGOU TAGE ME TWO?)
    // Take me! Won't you ta-AAAAHHHHH-ke me? (WGHONT YGOU TAGE ME TWO?)
    // [initiate 5 year old kid who is spoilt mode] I wanna go, (WGHONT YGOU TAGE ME TWO?), to funkytown!
    // I wanna go, (WGHONT YGOU TAGE ME TWO?), to funkytown!
    // I wanna go, (WGHONT YGOU TAGE ME TWO?), to funkytown!
    // I wanna go, (WGHONT YGOU TAGE ME TWO?), to funkytown!
    // booka booka, looka looka, yooka yooka, spooka spooka
    // booka booka, looka looka, yooka yooka, spooka spooka
    // booka booka, looka looka, yooka yooka, spooka spooka
    // booka booka, looka looka, yooka yooka, spooka spooka
    // booka booka, looka looka, yooka yooka, spooka spooka
    // booka booka, looka looka, yooka yooka, spooka spooka
    // booka booka, looka looka, yooka yooka, spooka spooka
    // booka booka, looka looka, yooka yooka-(fades out)

    // woah I did the whole song then. damn, that took so long!

    private void sendMessageToPlayer(Level level, Player player, String text) {
        if(!level.isClientSide) { player.sendSystemMessage(Component.nullToEmpty(text)); }
    }

    private void stopAllChasingMobs(Level level, Player player) {
        List<Mob> mobs = level.getEntitiesOfClass(Mob.class, new AABB(player.getBlockX() + ifYouChangeYourMind, player.getBlockY() + onTheFirstDayInLine, player.getBlockZ() + ifYouChangeYourMind, player.getBlockX() - ifYouChangeYourMind, player.getBlockY() - onTheFirstDayInLine, player.getBlockZ() - ifYouChangeYourMind), mob -> mob.getTarget() == player);
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
            components.add(Component.literal("When going visible, you won't be able to mine blocks, place blocks\n or attack mobs for the amount of time you were invisible.").withStyle(ChatFormatting.DARK_RED));
        }
        super.appendHoverText(stack, level, components, isAdvanced);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player && player.isInvisible() && ((level.getGameTime() - timeKeepsOnSlippinSlippinSlippin) / 20) >= future) {
            sendMessageToPlayer(level, player, String.format("Uh oh! You was invisibile for longer than %d seconds, so you became visible again!", future));
            player.setInvisible(false);
            player.getCooldowns().addCooldown(this, future * 20);
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
