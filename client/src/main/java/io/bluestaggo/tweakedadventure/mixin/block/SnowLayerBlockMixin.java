package io.bluestaggo.tweakedadventure.mixin.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.SnowLayerBlock;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SnowLayerBlock.class)
public abstract class SnowLayerBlockMixin extends Block {
	protected SnowLayerBlockMixin(int id, Material material) {
		super(id, material);
	}

	@WrapOperation(
		method = "canSurvive",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/block/Block;isOpaqueCube()Z"
		)
	)
	private boolean canSurviveOnLeaves(Block instance, Operation<Boolean> original) {
		return original.call(instance) || instance == Block.LEAVES;
	}
}
