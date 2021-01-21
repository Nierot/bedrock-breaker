package com.nierot.mc.bedrockbreaker;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;


public class BedrockBreaker implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "bedrockbreaker";
    public static final String MOD_NAME = "Bedrock Breaker";

    public static Item BEDROCK_BREAKER;

    @Override
    public void onInitialize() {
        LOGGER.log(Level.INFO, "who would want this?");
        BEDROCK_BREAKER = Registry.register(Registry.ITEM, "bedrock_breaker", new BreakerItem());
    }
}

class BreakerItem extends Item {

    public BreakerItem() {
        super(new Item.Settings().group(ItemGroup.MISC));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("Ever wanted to prank someone by replacing their bed with bedrock? Now you can!"));
    }
}
