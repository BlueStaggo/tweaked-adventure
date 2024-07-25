package io.bluestaggo.tweakedadventure.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Inject(method = "main", remap = false, at = @At("HEAD"))
	private static void exampleMod$onInit(CallbackInfo ci) {
		System.out.println("This line is printed by an example mod mixin!");
	}
}
