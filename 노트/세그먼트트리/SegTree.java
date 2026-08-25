class SegTree {
    static final int INF = Integer.MAX_VALUE;

    private final int n;
    private final int[] tree;

    SegTree(int n) {
        this.n = n;
        this.tree = new int[4 * n];
        java.util.Arrays.fill(tree, INF);
    }

    void update(int idx, int val) {
        update(1, 0, n - 1, idx, val);
    }

    private void update(int node, int nl, int nr, int idx, int val) {
        if (nl == nr) {
            tree[node] = val;
            return;
        }
        int mid = (nl + nr) / 2;
        if (idx <= mid) update(2 * node, nl, mid, idx, val);
        else            update(2 * node + 1, mid + 1, nr, idx, val);

        // 자식이 있을 때만 다시 계산한다. 잎에서 하면 방금 넣은 값이 INF 로 덮인다.
        tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
    }

    int query(int l, int r) {
        if (l > r) return INF;
        return query(1, 0, n - 1, l, r);
    }

    private int query(int node, int nl, int nr, int l, int r) {
        // 1) 안 겹침
        if (nr < l || nl > r) return INF;
        // 2) [nl..nr] 이 [l..r] 안에 통째로 들어감
        if (l <= nl && nr <= r) return tree[node];
        // 3) 일부만 겹침 - 양쪽 다 내려가서 작은 쪽을 올려보낸다
        int mid = (nl + nr) / 2;
        int left  = query(2 * node,     nl,      mid, l, r);
        int right = query(2 * node + 1, mid + 1, nr,  l, r);
        return Math.min(left, right);
    }

    // 자식 번호: 왼쪽 2*node, 오른쪽 2*node+1
    // 구간 쪼개기: mid = (nl + nr) / 2  ->  왼쪽 [nl..mid], 오른쪽 [mid+1..nr]
}
