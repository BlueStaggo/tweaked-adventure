package io.bluestaggo.tweakedadventure.mixin.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.world.gen.feature.decorator.FeatureDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FeatureDecorator.class)
public abstract class FeatureDecoratorMixin {
	@Redirect(
		method = "<init>",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/block/Block;YELLOW_FLOWER:Lnet/minecraft/block/PlantBlock;",
			ordinal = 1
		)
	)
	private PlantBlock addRoses() {
		return Block.RED_FLOWER;
	}
}
