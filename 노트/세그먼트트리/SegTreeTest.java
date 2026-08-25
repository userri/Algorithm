import java.util.Random;

// 브루트포스와 대조한다. 틀리면 그 순간의 입력을 찍고 멈춘다.
public class SegTreeTest {

    public static void main(String[] args) {
        Random rnd = new Random(20260825);

        for (int round = 1; round <= 200; round++) {
            int n = 1 + rnd.nextInt(30);

            int[] naive = new int[n];
            java.util.Arrays.fill(naive, SegTree.INF);
            SegTree seg = new SegTree(n);

            for (int op = 1; op <= 300; op++) {
                if (rnd.nextBoolean()) {
                    int idx = rnd.nextInt(n);
                    int val = rnd.nextInt(100);
                    naive[idx] = val;
                    seg.update(idx, val);
                } else {
                    int a = rnd.nextInt(n), b = rnd.nextInt(n);
                    int l = Math.min(a, b), r = Math.max(a, b);

                    int expected = SegTree.INF;
                    for (int i = l; i <= r; i++) expected = Math.min(expected, naive[i]);
                    int got = seg.query(l, r);

                    if (expected != got) {
                        System.out.println("FAIL  round=" + round + " op=" + op);
                        System.out.println("  n=" + n + "  query(" + l + ", " + r + ")");
                        System.out.println("  expected=" + show(expected) + "  got=" + show(got));
                        System.out.println("  array=" + dump(naive));
                        return;
                    }
                }
            }
        }
        System.out.println("PASS  200 rounds x 300 ops");
    }

    private static String show(int v) {
        return v == SegTree.INF ? "INF" : String.valueOf(v);
    }

    private static String dump(int[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(show(a[i]));
        }
        return sb.append("]").toString();
    }
}
