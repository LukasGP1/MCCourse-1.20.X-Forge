package de.lulkas_.mccourse.item.custom;

import de.lulkas_.mccourse.item.ModItems;
import de.lulkas_.mccourse.particle.ModParticles;
import de.lulkas_.mccourse.sound.ModSounds;
import de.lulkas_.mccourse.util.InventoryUtil;
import de.lulkas_.mccourse.util.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState blockState = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isValuableBlock(blockState)) {
                    //outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
                    foundBlock = true;

                    if(InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET.get())) {
                        addDataToDataTablet(player, positionClicked.below(i), blockState.getBlock());
                    }

                    pContext.getLevel().playSeededSound(null, player.getX(), player.getY(), player.getZ(),
                            ModSounds.METAL_DETECTOR_FOUND_ORE.get(),
                            SoundSource.BLOCKS,
                            1f,
                            1f,
                            0
                    );

                    spawnFoundParticles(pContext, positionClicked, blockState);

                    break;
                }
            }

            if(!foundBlock) {
                //outputNoValuableFound(player);
                addDataToDataTablet(player);
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> {
            player.broadcastBreakEvent(player.getUsedItemHand());
        });

        return InteractionResult.SUCCESS;
    }

    private void spawnFoundParticles(UseOnContext pContext, BlockPos positionClicked, BlockState blockState) {
        for(int i = 0; i < 20; i++) {
            ServerLevel level = (ServerLevel) pContext.getLevel();

            level.sendParticles(ModParticles.ALEXANDRITE_PARTICLE.get(),
                    positionClicked.getX() + 0.5d,
                    positionClicked.getY() + 1,
                    positionClicked.getZ() + 0.5d,
                    1,
                    Math.cos(i * 18) * 0.15d,
                    0.15d,
                    Math.sin(i * 18) * 0.15d,
                    0.1
            );

            level.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Item.byBlock(blockState.getBlock()))),
                    positionClicked.getX() + 0.5d,
                    positionClicked.getY() + 1,
                    positionClicked.getZ() + 0.5d,
                    1,
                    Math.cos(i * 18) * 0.15d,
                    0.15d,
                    Math.sin(i * 18) * 0.15d,
                    0.1
            );
        }
    }

    private void addDataToDataTablet(Player player) {
        ItemStack dataTablet = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET.get()));

        CompoundTag data = new CompoundTag();
        data.putString("mccourse.detecting_result", "No valuable was found.");

        dataTablet.setTag(data);
    }

    private void addDataToDataTablet(Player player, BlockPos pos, Block block) {
        ItemStack dataTablet = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET.get()));

        CompoundTag data = new CompoundTag();
        data.putString("mccourse.detecting_result", "Valuable Found: " + I18n.get(block.getDescriptionId()) +
                " at: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());

        dataTablet.setTag(data);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.metal_detector.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.metal_detector.tooltip.default"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    // private void outputNoValuableFound(Player player) {
    //     player.sendSystemMessage(Component.literal("No valuable was found."));
    // }

    // private void outputValuableCoordinates(BlockPos pos, Player player, Block block) {
    //     player.sendSystemMessage(Component.literal("Valuable Found: " + I18n.get(block.getDescriptionId()) +
    //             " at: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ()));
    // }

    private boolean isValuableBlock(BlockState blockState) {
        return blockState.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
