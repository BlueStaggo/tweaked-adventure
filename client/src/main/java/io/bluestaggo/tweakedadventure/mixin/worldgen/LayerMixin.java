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
			value = "NEW",
			target = "(JLnet/minecraft/world/biome/layer/Layer;)Lnet/minecraft/world/biome/layer/AddIslandLayer;",
			ordinal = 3
		)
	)
	private static AddIslandLayer shrinkLand1(long seed, Layer parent) {
		if (TweakedAdventureConfig.getInstance().releaseLandScale()) {
			seed = 4L;
		}
		return new AddIslandLayer(seed, parent);
	}

	@Redirect(
		method = "init",
		at = @At(
			value = "NEW",
			target = "(JLnet/minecraft/world/biome/layer/Layer;)Lnet/minecraft/world/biome/layer/ZoomLayer;",
			ordinal = 3
		)
	)
	private static ZoomLayer shrinkLand2(long seed, Layer parent) {
		if (TweakedAdventureConfig.getInstance().releaseLandScale()) {
			return new ZoomLayer(seed, parent) {
				@Override
				public int[] nextValues(int x, int z, int width, int length) {
					return this.parent.nextValues(x, z, width, length);
				}
			};
		}
		return new ZoomLayer(seed, parent);
	}

	@Redirect(
		method = "init",
		at = @At(
			value = "NEW",
			target = "(JLnet/minecraft/world/biome/layer/Layer;)Lnet/minecraft/world/biome/layer/AddIslandLayer;",
			ordinal = 4
		)
	)
	private static AddIslandLayer shrinkLand3(long seed, Layer parent) {
		if (TweakedAdventureConfig.getInstance().releaseLandScale()) {
			return new AddIslandLayer(seed, parent) {
				@Override
				public int[] nextValues(int x, int z, int width, int length) {
					return this.parent.nextValues(x, z, width, length);
				}
			};
		}
		return new AddIslandLayer(seed, parent);
	}

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
