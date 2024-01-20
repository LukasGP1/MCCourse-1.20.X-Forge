package de.lulkas_.mccourse.event;

import de.lulkas_.mccourse.MCCourseMod;
import de.lulkas_.mccourse.command.ReturnHomeCommand;
import de.lulkas_.mccourse.command.SetHomeCommand;
import de.lulkas_.mccourse.item.ModItems;
import de.lulkas_.mccourse.item.custom.HammerItem;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MCCourseMod.MOD_ID)
public class ModEvents {

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for (BlockPos pos : HammerItem.getBlocksToBeDestroyed(hammer.range, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                // Have to add them to a Set, otherwise the same code right here will get called for each block!
                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        event.getEntity().getPersistentData().putIntArray("mccourse.homepos", event.getOriginal().getPersistentData().getIntArray("mccourse.homepos"));
    }

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if(event.getEntity() instanceof Sheep) {
            if(event.getSource().getDirectEntity() instanceof Player player) {
                if(player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ModItems.KOHLRABI.get()) {
                    player.sendSystemMessage(Component.literal("You are weird!"));
                }
                if(player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.DIAMOND) {
                    player.sendSystemMessage(Component.literal("Gift it to the poor sheep!"));
                }
            }
        }
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.KOHLRABI_SEEDS.get(), 6);
            int villagerLevel = 1;

            trades.get(villagerLevel).add(
                    (pTrader, pRandom) -> new MerchantOffer(
                            new ItemStack(Items.EMERALD),
                            stack,
                            10,
                            2,
                            0.02f
                    )
            );
        }
    }

    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> trades = event.getGenericTrades();

        trades.add(
                (pTrader, pRandom) -> new MerchantOffer(
                        new ItemStack(Items.EMERALD, 2),
                        new ItemStack(ModItems.METAL_DETECTOR.get(), 1),
                        1,
                        2,
                        0.02f
                )
        );
    }
}
