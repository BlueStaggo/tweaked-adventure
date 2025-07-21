package io.bluestaggo.tweakedadventure.mixin;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import io.bluestaggo.tweakedadventure.mixin.worldgen.WorldGeneratorTypeAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.world.gen.WorldGeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Inject(
		method = "init",
		at = @At("HEAD")
	)
	public void initTweakedAdventure(CallbackInfo ci) {
		TweakedAdventureConfig.getInstance();

		((WorldGeneratorTypeAccessor)WorldGeneratorType.DEFAULT_1_1).invokeSetVisible(true);
	}
}
