package com.example.knifepack;

import net.fabricmc.api.ModInitializer;

/**
 * Common initializer. The mod is client-only (see fabric.mod.json), so nothing
 * is registered on the server side.
 */
public class KnifePack implements ModInitializer {
    public static final String MOD_ID = "knifepack";

    @Override
    public void onInitialize() {
        // Intentionally empty: the knife menu and spin are client-side.
    }
}
