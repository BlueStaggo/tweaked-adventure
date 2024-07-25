package io.bluestaggo.tweakedadventure.mixin.block;

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
	@Shadow @Final private boolean hasDrops;

	private PaneBlockMixin(int id, Material material) {
		super(id, material);
	}

	@Redirect(
		method = "getDropItem",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/block/PaneBlock;hasDrops:Z"
		)
	)
	private boolean modifyGlassPaneDrops(PaneBlock instance) {
		return this.hasDrops || TweakedAdventureConfig.getInstance().dropPanes();
	}
}
