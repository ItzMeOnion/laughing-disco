package com.example.knifepack.client;

/**
 * Tracks the spin of the held knife. The spin only advances while the spin key
 * is held; when it is released the knife eases back to its resting angle.
 */
public final class SpinState {
    /** Degrees per tick while spinning. */
    private static final float SPIN_SPEED = 30.0f;
    /** Degrees per tick the spin decays back to rest after release. */
    private static final float DECAY_SPEED = 20.0f;

    private static float angle = 0.0f;
    private static boolean spinning = false;

    private SpinState() {
    }

    /** Called once per client tick with whether the spin key is currently held. */
    public static void tick(boolean keyHeld) {
        spinning = keyHeld;
        if (keyHeld) {
            angle = (angle + SPIN_SPEED) % 360.0f;
        } else if (angle != 0.0f) {
            // Ease back to the nearest full rotation so the knife settles upright.
            float toRest = 360.0f - angle;
            float step = Math.min(DECAY_SPEED, Math.min(angle, toRest));
            angle = angle <= toRest ? angle - step : angle + step;
            angle = ((angle % 360.0f) + 360.0f) % 360.0f;
            if (angle < 0.01f || angle > 359.99f) {
                angle = 0.0f;
            }
        }
    }

    /** Current spin angle in degrees, for the renderer to apply. */
    public static float angle() {
        return angle;
    }

    public static boolean isSpinning() {
        return spinning;
    }
}
