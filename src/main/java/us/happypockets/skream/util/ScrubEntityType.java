package us.happypockets.skream.util;

import org.bukkit.entity.EntityType;

/* This is straight from skRayFall (current mobs added), credit to eyesniper!*/

public class ScrubEntityType {

    /**
     * Convert a string to a bukkit entity type.
     *
     * @param exprs The string to be converted.
     */
    public static EntityType getType(String exprs) {
        switch (exprs.replace("\"", "").toLowerCase().replace("_", " ").replaceFirst("the ", "")) {
            case "player":
            case "the player":
                return EntityType.PLAYER;
            case "pig":
                return EntityType.PIG;
            case "blaze":
                return EntityType.BLAZE;
            case "bat":
                return EntityType.BAT;
            case "chicken":
                return EntityType.CHICKEN;
            case "creeper":
                return EntityType.CREEPER;
            case "cow":
                return EntityType.COW;
            case "enderman":
                return EntityType.ENDERMAN;
            case "ender dragon":
                return EntityType.ENDER_DRAGON;
            case "ghast":
                return EntityType.GHAST;
            case "giant":
                return EntityType.GIANT;
            case "iron golem":
                return EntityType.IRON_GOLEM;
            case "magma cube":
                return EntityType.MAGMA_CUBE;
            case "mushroom cow":
                return EntityType.MUSHROOM_COW;
            case "ocelot":
                return EntityType.OCELOT;
            case "sheep":
                return EntityType.SHEEP;
            case "silverfish":
                return EntityType.SILVERFISH;
            case "squid":
                return EntityType.SQUID;
            case "snowman":
                return EntityType.SNOWMAN;
            case "wolf":
                return EntityType.WOLF;
            case "skeleton":
                return EntityType.SKELETON;
            case "slime":
                return EntityType.SLIME;
            case "spider":
                return EntityType.SPIDER;
            case "witch":
                return EntityType.WITCH;
            case "wither":
                return EntityType.WITHER;
            case "villager":
                return EntityType.VILLAGER;
            case "zombie":
                return EntityType.ZOMBIE;
            case "armor stand":
                return EntityType.ARMOR_STAND;
            case "guardian":
                return EntityType.GUARDIAN;
            case "horse":
                return EntityType.HORSE;
            case "donkey":
                return EntityType.DONKEY;
            case "bee":
                return EntityType.BEE;
            case "cat":
                return EntityType.CAT;
            case "cave spider":
                return EntityType.CAVE_SPIDER;
            case "cod":
                return EntityType.COD;
            case "dolphin":
                return EntityType.DOLPHIN;
            case "drowned":
                return EntityType.DROWNED;
            case "elder guardian":
                return EntityType.ELDER_GUARDIAN;
            case "endermite":
                return EntityType.ENDERMITE;
            case "evoker":
                return EntityType.EVOKER;
            case "hoglin":
                return EntityType.HOGLIN;
            case "husk":
                return EntityType.HUSK;
            case "llama":
                return EntityType.LLAMA;
            case "trader llama":
                return EntityType.TRADER_LLAMA;
            case "wandering trader":
                return EntityType.WANDERING_TRADER;
            case "mooshroom":
                return EntityType.MUSHROOM_COW;
            case "mule":
                return EntityType.MULE;
            case "panda":
                return EntityType.PANDA;
            case "parrot":
                return EntityType.PARROT;
            case "phantom":
                return EntityType.PHANTOM;
            case "piglin":
                return EntityType.PIGLIN;
            case "piglin brute":
                return EntityType.PIGLIN_BRUTE;
            case "zombified piglin":
                return EntityType.ZOMBIFIED_PIGLIN;
            case "zombie pigman":
                return EntityType.ZOMBIFIED_PIGLIN;
            case "zombie villager":
                return EntityType.ZOMBIE_VILLAGER;
            case "pillager":
                return EntityType.PILLAGER;
            case "polar bear":
                return EntityType.POLAR_BEAR;
            case "puffer fish":
                return EntityType.PUFFERFISH;
            case "rabbit":
                return EntityType.RABBIT;
            case "ravager":
                return EntityType.RAVAGER;
            case "salmon":
                return EntityType.SALMON;
            case "shulker":
                return EntityType.SHULKER;
            case "skeleton horse":
                return EntityType.SKELETON_HORSE;
            case "wither skeleton":
                return EntityType.WITHER_SKELETON;
            case "stray":
                return EntityType.STRAY;
            case "strider":
                return EntityType.STRIDER;
            case "tropical fish":
                return EntityType.TROPICAL_FISH;
            case "vex":
                return EntityType.VEX;
            case "turtle":
                return EntityType.TURTLE;
            case "vindicator":
                return EntityType.VINDICATOR;
            case "zoglin":
                return EntityType.ZOGLIN;
            default:
                return EntityType.PLAYER;
        }
    }
}
