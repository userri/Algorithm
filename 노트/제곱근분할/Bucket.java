// 구간 더하기 + 구간 최댓값 을 제곱근 분할로.
// SegTreeLazy 와 "하는 일"이 같다. 재귀가 없을 뿐이다.
//
//   구간을 sqrt(n) 짜리 덩어리로 자른다.
//   통째로 들어간 덩어리 -> 덩어리에 표시 하나 (lazy)
//   걸친 자투리        -> 칸을 손으로 훑는다
class Bucket {

	private final int n;
	private final int bs;      // 덩어리 크기 = sqrt(n) + 1
	private final int[] a;     // 칸 값. 덩어리 lazy 는 아직 안 더해진 상태로 둔다
	private final int[] lazy;  // 덩어리별로 "이 덩어리 전체에 더해야 하는 값"
	private final int[] mx;    // 덩어리별 최댓값 (lazy 는 빠져 있는 상태)

	Bucket(int n) {
		this.n = n;
		this.bs = (int) Math.sqrt(n) + 1;
		this.a = new int[n];
		int nb = (n + bs - 1) / bs;
		this.lazy = new int[nb];
		this.mx = new int[nb];
		// a 는 전부 0 에서 시작하니 mx 도 0 이 맞다
	}

	// ── 여기부터 구현 ─────────────────────────────────────────

	// [l..r] 에 val 을 더한다
	void add(int l, int r, int val) {
		// TODO
	}

	// [l..r] 의 최댓값
	int max(int l, int r) {
		// TODO
		return 0;
	}

	// idx 한 칸을 val 로 덮어쓴다 (더하기가 아니라 대입)
	void set(int idx, int val) {
		// TODO
	}

	// ── 힌트 ────────────────────────────────────────────────
	// 정하고 시작할 것 하나: a[i] 에 lazy 가 들어있게 할 것인가, 빠져있게 할 것인가.
	//   -> 여기서는 "빠져있다"로 정했다. 그래서 실제 값은 항상 a[i] + lazy[i/bs] 다.
	//      이 약속을 어디 한 군데서라도 어기면 조용히 틀린다.
	//
	// 자투리를 손으로 고칠 때는 그 덩어리의 mx 를 다시 계산해야 한다 (rebuild).
}
