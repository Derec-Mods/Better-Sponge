package io.github.derexxd.betterSponge;

public final class SpongeSettings {

    private static final int VANILLA_MAX_DEPTH = 6;
    private static final int VANILLA_MAX_BLOCKS = 64;
    public static final int RADIUS_MULTIPLIER = 3;
    public static final int MAX_DEPTH = VANILLA_MAX_DEPTH * RADIUS_MULTIPLIER;
    public static final int MAX_BLOCKS =
            VANILLA_MAX_BLOCKS * RADIUS_MULTIPLIER * RADIUS_MULTIPLIER * RADIUS_MULTIPLIER;

    private SpongeSettings() {
    }
}
