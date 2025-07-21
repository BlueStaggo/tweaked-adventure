package io.bluestaggo.tweakedadventure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.function.IntFunction;

public final class TAUtil {
	private TAUtil() {
	}

	public static <T> T[] combineArray(T[] a, T[] b, IntFunction<T[]> generator) {
		T[] c = generator.apply(a.length + b.length);
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	public static boolean canBlockBeReplacedBySnow(int id, boolean allowAir) {
		Block block = Block.BY_ID[id];
		if (block == null) {
			return allowAir;
		}

		Material material = block.material;
		return !material.blocksMovement() && !material.isLiquid();
	}
}
