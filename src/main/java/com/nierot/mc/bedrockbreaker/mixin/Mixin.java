package com.nierot.mc.bedrockbreaker.mixin;

import com.nierot.mc.bedrockbreaker.BedrockBreaker;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@org.spongepowered.asm.mixin.Mixin(AbstractBlock.class)
public class Mixin {

    @Inject(method = "onUse", at = @At("HEAD"))
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
     BlockHitResult hit, CallbackInfoReturnable<ActionResult> returnable) {
        if (!world.isClient()) {
            // check if the block is bedrock and clicked with ImStuff.BEDROCK_BREAKER
            if (state.getBlock() == Blocks.BEDROCK && player.getStackInHand(hand).getItem() == BedrockBreaker.BEDROCK_BREAKER) {
                world.breakBlock(pos, true, player, 1);
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.BEDROCK, 1)));
                player.getStackInHand(hand).decrement(1);
                world.getPlayers().forEach(action -> action.sendMessage(new LiteralText(player.getName().getString() + " just destroyed bedrock!"), false));
            }
        }
    }
    
}
