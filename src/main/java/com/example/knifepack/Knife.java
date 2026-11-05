package com.example.knifepack;

/**
 * The knives the menu offers. Each knife is an original design: add your own
 * model and texture under assets/knifepack/ using the same id.
 */
public enum Knife {
    BUTTERFLY("butterfly", "Butterfly Knife"),
    KARAMBIT("karambit", "Karambit"),
    BAYONET("bayonet", "Bayonet"),
    TALON("talon", "Talon"),
    PUSH_DAGGER("push_dagger", "Push Dagger"),
    HUNTING("hunting", "Hunting Knife");

    private final String id;
    private final String displayName;

    Knife(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String id() {
        return id;
    }

    public String displayName() {
        return displayName;
    }
}
