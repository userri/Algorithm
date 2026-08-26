
import java.util.*;

class SegTree {
	static final int INF = Integer.MAX_VALUE;

	private int n;
	private int[] tree;

	SegTree(int n) {
		this.n = n;
		tree = new int[4 * n];
		Arrays.fill(tree, INF);
	}

	void update(int idx, int val) {
		update(1, 0, n - 1, idx, val);
	}

	// idx는 원본
	private void update(int node, int nl, int nr, int idx, int val) {
		// tree배열을 node로 참조할 때 빼고는 다 원본배열 변수임(nl, nr, idx, val)
		if (nr == nl) {
			tree[node] = val;
			return;
		}
		int mid = (nl + nr) / 2;
		if (idx <= mid)
			update(node * 2, nl, mid, idx, val);
		else
			update(node * 2 + 1, mid + 1, nr, idx, val);

		// 자식이 있을 때만 다시 계산
		tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);

	}

	int query(int l, int r) {
		// 범위검사!
		if(r < l) return INF;
		return query(1, 0, n - 1, l, r);
	}

	private int query(int node, int nl, int nr, int l, int r) {
		// 안 겹침
		if (r < nl || nr < l)
			return INF;
		// 노드가 질의 안에 통째로 들어옴
		if(l <= nl && nr <= r) return tree[node];
		// 일부만 겹침 - 양쪽 다 검사하고 작은 쪽 리턴
		int mid = (nl + nr) / 2;
		int left = query(node * 2, nl, mid, l, r);
		int right = query(node * 2 + 1, mid + 1, nr, l, r);
		return Math.min(left, right);

	}
}