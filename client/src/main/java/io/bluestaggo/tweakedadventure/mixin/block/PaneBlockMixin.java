package io.bluestaggo.tweakedadventure.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PaneBlock.class)
public abstract class PaneBlockMixin extends Block {
	private PaneBlockMixin(int id, Material material) {
		super(id, material);
	}

	@ModifyExpressionValue(
		method = "getDropItem",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/block/PaneBlock;hasDrops:Z"
		)
	)
	private boolean modifyGlassPaneDrops(boolean original) {
		return original || TweakedAdventureConfig.getInstance().dropPanes();
	}
}
