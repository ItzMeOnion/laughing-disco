package com.example.knifepack.client;

import com.example.knifepack.Knife;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class KnifePackClient implements ClientModInitializer {
    private static Knife equipped = Knife.BUTTERFLY;

    /** Opens the knife menu. Bindable under Controls > Key Binds. */
    private static KeyBinding menuKey;

    /**
     * Spins the held knife while held down. Bindable under Controls > Key Binds.
     * Deliberately not using wasPressed(): we want the live held state.
     */
    private static KeyBinding spinKey;

    private static final KeyBinding.Category CATEGORY =
            KeyBinding.Category.create(Identifier.of("knifepack", "knives"));

    @Override
    public void onInitializeClient() {
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.knifepack.menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, CATEGORY));

        spinKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.knifepack.spin", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) {
                return;
            }
            // Open the menu on press, but only from the game screen (no other GUI open).
            while (menuKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new KnifeSelectScreen());
                }
            }
            // Hold-to-spin: the knife only spins while the spin key is physically held
            // and no GUI is open.
            boolean held = spinKey.isPressed() && client.currentScreen == null;
            SpinState.tick(held);
        });
    }

    public static Knife equipped() {
        return equipped;
    }

    public static void equip(Knife knife) {
        equipped = knife;
    }
}
