package io.bluestaggo.tweakedadventure;

import java.util.Arrays;
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
}
