package io.bluestaggo.tweakedadventure.mixin.worldgen;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import io.bluestaggo.tweakedadventure.worldgen.AddHillsLayer;
import net.minecraft.world.biome.layer.AddIslandLayer;
import net.minecraft.world.biome.layer.Layer;
import net.minecraft.world.biome.layer.ZoomLayer;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Layer.class)
public class LayerMixin {
	@Redirect(
		method = "init",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/biome/layer/ZoomLayer;zoom(JLnet/minecraft/world/biome/layer/Layer;I)Lnet/minecraft/world/biome/layer/Layer;",
			ordinal = 3
		)
	)
	private static Layer addHills(long seed, Layer layer, int magnification) {
		layer = ZoomLayer.zoom(seed, layer, magnification);
		if (!TweakedAdventureConfig.getInstance().hillBiomes()) {
			return layer;
		}
		return new AddHillsLayer(1000L, layer);
	}
}
