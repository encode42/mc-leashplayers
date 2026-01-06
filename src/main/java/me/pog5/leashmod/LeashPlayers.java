package me.pog5.leashmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRuleCategory;
import net.minecraft.world.rule.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LeashPlayers implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("leashmod");

    private static final GameRule<Boolean> ruleEnabled = GameRuleBuilder
        .forBoolean(true)
        .category(GameRuleCategory.PLAYER)
        .buildAndRegister(Identifier.of("leashmod", "leash_players_enabled"));

    private static final GameRule<Double> ruleDistanceMin = GameRuleBuilder
        .forDouble(4.0D)
        .category(GameRuleCategory.PLAYER)
        .buildAndRegister(Identifier.of("leashmod", "leash_players_distance_min"));

    private static final GameRule<Double> ruleDistanceMax = GameRuleBuilder
        .forDouble(10.0D)
        .category(GameRuleCategory.PLAYER)
        .buildAndRegister(Identifier.of("leashmod", "leash_players_distance_max"));

    private static final GameRule<Boolean> ruleAllowLeashedRemoveFenceKnot = GameRuleBuilder
        .forBoolean(false)
        .category(GameRuleCategory.PLAYER)
        .buildAndRegister(Identifier.of("leashmod", "leash_players_allow_leashed_remove_fence_knot"));

    public static LeashSettings getSettings(ServerWorld world) {
        return new LeashSettings() {
            private GameRules getGameRules() {
                return world.getGameRules();
            }

            @Override
            public boolean isEnabled() {
                return getGameRules().getValue(ruleEnabled);
            }

            @Override
            public double getDistanceMin() {
                return getGameRules().getValue(ruleDistanceMin);
            }

            @Override
            public double getDistanceMax() {
                return getGameRules().getValue(ruleDistanceMax);
            }

            @Override
            public boolean allowLeashedRemoveFenceKnot() {
                return getGameRules().getValue(ruleAllowLeashedRemoveFenceKnot);
            }
        };
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized LeashPlayers");
    }
}
