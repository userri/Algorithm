import java.util.Random;

// 브루트포스와 대조한다. 틀리면 그 순간의 입력을 찍고 멈춘다.
public class SegTreeLazyTest {

	public static void main(String[] args) {
		Random rnd = new Random(20260826);

		for (int round = 1; round <= 300; round++) {
			int n = 1 + rnd.nextInt(30);

			int[] naive = new int[n]; // 전부 0 에서 시작
			SegTreeLazy seg = new SegTreeLazy(n);

			for (int op = 1; op <= 300; op++) {
				int kind = rnd.nextInt(3);
				int a = rnd.nextInt(n), b = rnd.nextInt(n);
				int l = Math.min(a, b), r = Math.max(a, b);

				if (kind == 0) { // 구간 더하기
					int val = rnd.nextInt(21) - 10; // 음수도 섞는다
					for (int i = l; i <= r; i++) naive[i] += val;
					seg.add(l, r, val);

				} else if (kind == 1) { // 점 대입
					int idx = rnd.nextInt(n);
					int val = rnd.nextInt(200) - 100;
					naive[idx] = val;
					seg.set(idx, val);

				} else { // 구간 최댓값
					int expected = Integer.MIN_VALUE;
					for (int i = l; i <= r; i++) expected = Math.max(expected, naive[i]);
					int got = seg.max(l, r);

					if (expected != got) {
						System.out.println("FAIL  round=" + round + " op=" + op);
						System.out.println("  n=" + n + "  max(" + l + ", " + r + ")");
						System.out.println("  expected=" + expected + "  got=" + got);
						System.out.println("  array=" + dump(naive));
						return;
					}
				}
			}
		}
		System.out.println("PASS  300 rounds x 300 ops");
	}

	private static String dump(int[] a) {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < a.length; i++) {
			if (i > 0) sb.append(", ");
			sb.append(a[i]);
		}
		return sb.append("]").toString();
	}
}
