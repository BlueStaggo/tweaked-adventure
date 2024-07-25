package io.bluestaggo.tweakedadventure.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.living.player.InputPlayerEntity;
import net.minecraft.client.gui.GameGui;
import net.minecraft.client.gui.GuiElement;
import net.minecraft.client.render.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.bluestaggo.tweakedadventure.TweakedAdventureConfig.ExperienceBarType;

@Mixin(GameGui.class)
public class GameGuiMixin extends GuiElement {
	@Shadow private Minecraft minecraft;

	@Redirect(
		method = "render",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/entity/living/player/InputPlayerEntity;getNextLevelExperience()I"
		)
	)
	private int hideExperienceBar(InputPlayerEntity player) {
		if (TweakedAdventureConfig.getInstance().experienceBarType() == TweakedAdventureConfig.ExperienceBarType.NONE) {
			return 0;
		}
		return player.getNextLevelExperience();
	}

	@Inject(
		method = "render",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/interaction/ClientPlayerInteractionManager;hasXpBar()Z",
			shift = At.Shift.BEFORE
		)
	)
	private void drawExperienceBarText(float tickDelta, boolean screenOpen, int mouseX, int mouseY, CallbackInfo ci,
									   @Local(ordinal = 2) int windowWidth, @Local(ordinal = 3) int windowHeight) {
		ExperienceBarType barType = TweakedAdventureConfig.getInstance().experienceBarType();
		if (this.minecraft.interactionManager.hasXpBar() && this.minecraft.player.xpLevel > 0
				&& barType != ExperienceBarType.NONE && barType != ExperienceBarType.BAR_ONLY) {
			TextRenderer textRenderer = this.minecraft.textRenderer;
			boolean skillPoints = barType == ExperienceBarType.BAR_AND_SKILL_POINTS;
			boolean skillHop = skillPoints && this.minecraft.player.time % 25 >= 20;
			int color = skillHop ? 16777215 : 8453920;
			String label = skillPoints
				? "Skill Points: " + this.minecraft.player.xpLevel * 3
				: String.valueOf(this.minecraft.player.xpLevel);
			int x = (windowWidth - textRenderer.getWidth(label)) / 2;
			int y = windowHeight - 35;
			if (skillPoints) y += 4;
			if (skillHop) y--;

			if (skillPoints) {
				for (int xx = -1; xx <= 1; xx++) {
					for (int yy = -1; yy <= 1; yy++) {
						textRenderer.draw(label, x + xx, y + yy, 0);
					}
				}
			} else {
				textRenderer.draw(label, x + 1, y, 0);
				textRenderer.draw(label, x - 1, y, 0);
				textRenderer.draw(label, x, y + 1, 0);
				textRenderer.draw(label, x, y - 1, 0);
			}
			textRenderer.draw(label, x, y, color);
		}
	}

	@ModifyConstant(
		method = "render",
		constant = @Constant(
			intValue = 39,
			ordinal = 0
		)
	)
	private int modifyExperienceBarShift(int constant) {
		if (TweakedAdventureConfig.getInstance().experienceBarType() == ExperienceBarType.NONE) {
			constant -= 7;
		}
		return constant;
	}
}
