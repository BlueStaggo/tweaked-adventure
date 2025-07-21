package io.bluestaggo.tweakedadventure.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.world.biome.layer.AddDeepOceanLayer;
import net.minecraft.world.biome.layer.Layer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Layer.class)
public class LayerMixin {
	@WrapOperation(
		method = "init",
		at = @At(
			value = "NEW",
			target = "(JLnet/minecraft/world/biome/layer/Layer;)Lnet/minecraft/world/biome/layer/AddDeepOceanLayer;"
		)
	)
	private static AddDeepOceanLayer addHills(long seed, Layer parent, Operation<AddDeepOceanLayer> original) {
		if (TweakedAdventureConfig.getInstance().hillBiomes()) {
			return original.call(seed, parent);
		}
		return new AddDeepOceanLayer(seed, parent) {
			@Override
			public int[] nextValues(int x, int z, int width, int length) {
				return parent.nextValues(x, z, width, length);
			}
		};
	}
}
