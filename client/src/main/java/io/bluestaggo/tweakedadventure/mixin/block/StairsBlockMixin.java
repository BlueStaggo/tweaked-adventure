package io.bluestaggo.tweakedadventure.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(StairsBlock.class)
public abstract class StairsBlockMixin extends Block {
	private StairsBlockMixin(int id, Material material) {
		super(id, material);
	}

	@Inject(
		method = "getDropItem",
		at = @At("HEAD"),
		cancellable = true
	)
	private void getDropStairs(int metadata, Random random, int fortuneLevel, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(this.id);
	}

	@Inject(
		method = "dropItems",
		at = @At("HEAD"),
		cancellable = true
	)
	private void dropStairs(World world, int x, int y, int z, int metadata, float luck, int fortuneLevel, CallbackInfo ci) {
		ci.cancel();
		if (world.isMultiplayer || world.random.nextFloat() > luck) return;
		this.dropItems(world, x, y, z, new ItemStack(this.id, 1, 0));
	}
}
