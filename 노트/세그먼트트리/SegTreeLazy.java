// 구간 더하기 + 구간 최댓값.
// SegTree(점 갱신 + 구간 최솟값) 와 다른 점은 두 가지뿐이다.
//   1) 구간 전체에 값을 더한다 -> 잎까지 다 내려가면 O(n). 그래서 "빚"을 적어두고 멈춘다.
//   2) 최솟값이 아니라 최댓값이다.
class SegTreeLazy {

	private final int n;
	private final int[] tree;
	private final int[] lazy; // lazy[node] = "내 자식들 아래 전부에 아직 안 넘긴 더할 값"

	SegTreeLazy(int n) {
		this.n = n;
		this.tree = new int[4 * n];
		this.lazy = new int[4 * n];
		// 초기값은 전부 0 이다 (배열도 0 으로 시작하니 그대로 맞다)
	}

	// ── 여기부터 구현 ──────────────────────────────────────────────

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

	// ── 힌트로 남겨둔 뼈대 (원하면 지우고 네 방식대로 짜라) ──────────

	// private void add(int node, int nl, int nr, int l, int r, int val) { }
	// private int  max(int node, int nl, int nr, int l, int r)          { }
	// private void set(int node, int nl, int nr, int idx, int val)      { }

	// 규칙 1. tree[node] 는 "lazy[node] 가 이미 반영된 값" 이다.
	//         lazy[node] 는 자식에게 아직 안 넘긴 빚이지, 내 값에 안 들어간 빚이 아니다.
	// 규칙 2. 자식으로 내려가기 직전에는 반드시 빚을 넘긴다.
	//         private void push(int node) { ... }
}
