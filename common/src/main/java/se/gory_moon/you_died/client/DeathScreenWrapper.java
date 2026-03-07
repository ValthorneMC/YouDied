package se.gory_moon.you_died.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.BooleanSupplier;

public class DeathScreenWrapper extends DeathScreen {

    private final DeathScreen deathScreen;
    private float alpha;

    protected BooleanSupplier condition = () -> true;

    public DeathScreenWrapper(DeathScreen deathScreen) {
        super(null, deathScreen.hardcore, Minecraft.getInstance().player);
        this.deathScreen = deathScreen;
    }

    @Override
    protected void init() {
        //noinspection ConstantConditions
        deathScreen.init(width, height);
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        int alphaColor = Mth.ceil(this.alpha * 255.0F) << 24;

        guiGraphics.fillGradient(0, 0, this.width, this.height, 0x60500000, 0xa0803030);
        guiGraphics.pose().pushMatrix();
        guiGraphics.pose().scale(2.0F, 2.0F);
        guiGraphics.drawCenteredString(this.font, deathScreen.getTitle(), this.width / 2 / 2, 30, 0xffffff | alphaColor);
        guiGraphics.pose().popMatrix();
        if (deathScreen.causeOfDeath != null) {
            guiGraphics.drawCenteredString(this.font, deathScreen.causeOfDeath, this.width / 2, 85, 0xffffff | alphaColor);
        }

        guiGraphics.drawCenteredString(this.font, deathScreen.deathScore, this.width / 2, 100, 0xffffff | alphaColor);


        // Sets the alpha on all widgets
        for (GuiEventListener guieventlistener : deathScreen.children()) {
            if (guieventlistener instanceof AbstractWidget) {
                ((AbstractWidget) guieventlistener).setAlpha(alpha);
            }
        }

        // Renders all renderables without calling super
        for(Renderable renderable : deathScreen.renderables) {
            renderable.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean fromKeyboard) {
        if (condition.getAsBoolean())
            return deathScreen.mouseClicked(event, fromKeyboard);
        return false;
    }

    @Override
    public void tick() {
        if (condition.getAsBoolean())
            deathScreen.tick();
    }

    @Override
    public void removed() {
        if (condition.getAsBoolean())
            deathScreen.removed();
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        if (condition.getAsBoolean())
            return deathScreen.mouseReleased(event);
        return super.mouseReleased(event);
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent event, double pDragX, double pDragY) {
        if (condition.getAsBoolean())
            return deathScreen.mouseDragged(event, pDragX, pDragY);
        return super.mouseDragged(event, pDragX, pDragY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
        if (condition.getAsBoolean())
            return deathScreen.mouseScrolled(pMouseX, pMouseY, pScrollX, pScrollY);
        return super.mouseScrolled(pMouseX, pMouseY, pScrollX, pScrollY);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        if (condition.getAsBoolean())
            return deathScreen.keyPressed(event);
        return super.keyPressed(event);
    }

    @Override
    public void afterMouseMove() {
        if (condition.getAsBoolean())
            deathScreen.afterMouseMove();
    }

    @Override
    public void afterMouseAction() {
        if (condition.getAsBoolean())
            deathScreen.afterMouseAction();
    }

    @Override
    public void afterKeyboardAction() {
        if (condition.getAsBoolean())
            deathScreen.afterKeyboardAction();
    }

    @Override
    public boolean keyReleased(KeyEvent event) {
        if (condition.getAsBoolean())
            return deathScreen.keyReleased(event);
        return super.keyReleased(event);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        if (condition.getAsBoolean())
            deathScreen.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public @NotNull Optional<GuiEventListener> getChildAt(double pMouseX, double pMouseY) {
        if (condition.getAsBoolean())
            return deathScreen.getChildAt(pMouseX, pMouseY);
        return super.getChildAt(pMouseX, pMouseY);
    }
}
