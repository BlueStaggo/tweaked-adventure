package io.bluestaggo.tweakedadventure.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.locale.LanguageManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Properties;

@Mixin(LanguageManager.class)
public abstract class LanguageManagerMixin {
	@Inject(
		method = "setLanguage",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/locale/LanguageManager;loadTranslations(Ljava/util/Properties;Ljava/lang/String;)V",
			ordinal = 0
		)
	)
	private void addCustomLanguageEntries(String language, CallbackInfo ci, @Local Properties translations) {
		translations.put("generator.default_1_1", "Release 1.1");
	}
}
