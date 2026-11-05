package com.example.knifepack.client;

import com.example.knifepack.Knife;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

/** Menu for choosing which knife is equipped. Selection is client-side only. */
public class KnifeSelectScreen extends Screen {
    private static final int BUTTON_WIDTH = 160;
    private static final int BUTTON_HEIGHT = 20;
    private static final int SPACING = 24;

    public KnifeSelectScreen() {
        super(Text.literal("Choose Knife"));
    }

    @Override
    protected void init() {
        int x = this.width / 2 - BUTTON_WIDTH / 2;
        int startY = this.height / 2 - (Knife.values().length * SPACING) / 2;

        for (int i = 0; i < Knife.values().length; i++) {
            Knife knife = Knife.values()[i];
            int y = startY + i * SPACING;
            this.addDrawableChild(ButtonWidget.builder(
                    knifeLabel(knife),
                    button -> {
                        KnifePackClient.equip(knife);
                        this.close();
                    })
                .dimensions(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
        }
    }

    private Text knifeLabel(Knife knife) {
        boolean selected = KnifePackClient.equipped() == knife;
        String name = knife.displayName();
        return Text.literal(selected ? "> " + name + " <" : name);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title,
                this.width / 2, this.height / 2 - (Knife.values().length * SPACING) / 2 - 16, 0xFFFFFF);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
