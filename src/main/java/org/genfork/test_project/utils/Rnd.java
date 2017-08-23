package org.genfork.test_project.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
public final class Rnd {
	private static final class RandomContainer {
		private final Random random;

		RandomContainer(final Random random) {
			this.random = random;
		}

		final int get(final int min, final int max) {
			return min + (int) (random.nextDouble() * ((max - min) + 1));
		}
	}

	private static final class ThreadLocalRandom extends Random {
		private static final class Seed {
			long seed;

			Seed(final long seed) {
				setSeed(seed);
			}

			final int next(final int bits) {
				return (int) ((seed = ((seed * MULTIPLIER) + ADDEND) & MASK) >>> (48 - bits));
			}

			final void setSeed(final long seed) {
				this.seed = (seed ^ MULTIPLIER) & MASK;
			}
		}

		private final ThreadLocal<Seed> seedLocal;

		ThreadLocalRandom() {
			seedLocal = new ThreadLocal<Seed>() {
				@Override
				public final Seed initialValue() {
					return new Seed(++SEED_UNIQUIFIER + System.nanoTime());
				}
			};
		}

		@Override
		public final int next(final int bits) {
			return seedLocal.get().next(bits);
		}

		@Override
		public final void setSeed(final long seed) {
			if (seedLocal != null) {
				seedLocal.get().setSeed(seed);
			}
		}
	}

	private static final long ADDEND = 0xBL;

	private static final long MASK = (1L << 48) - 1;

	private static final long MULTIPLIER = 0x5DEECE66DL;

	private static final RandomContainer rnd = newInstance();

	private static volatile long SEED_UNIQUIFIER = 8682522807148012L;

	private static int get(final int min, final int max) {
		return rnd.get(min, max);
	}

	public static List<Integer> generateRandomInts(final int min, final int max, int counter) {
		final List<Integer> list = new ArrayList<>();
		for (int i = 0; i < counter; i++) {
			list.add(get(min, max));
		}

		return list;
	}

	private static RandomContainer newInstance() {
		return new RandomContainer(new ThreadLocalRandom());
	}
}
