import java.util.*;
import java.io.*;
class Main {
    static int answer = Integer.MAX_VALUE;
    static int max = 987654321;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        dp = new int[100_001];
        Arrays.fill(dp, max);
        bfs(N, K);
        System.out.println(dp[K]);
    }
    static void bfs(int N, int K) {
        // depth 작은 순서대로 정렬
        PriorityQueue<Point> q = new PriorityQueue<>((o1, o2) -> o1.depth - o2.depth);
        q.offer(new Point(N, 0));
        dp[N] = 0;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            if(cur.pos == K) return;
            if( cur.pos * 2 < 100_001 && dp[cur.pos * 2] > cur.depth) {
                q.offer(new Point(cur.pos * 2, cur.depth));
                dp[cur.pos * 2] = cur.depth;
            }
            if( cur.pos + 1 < 100_001 && dp[cur.pos + 1] > cur.depth + 1) {
                q.offer(new Point(cur.pos + 1, cur.depth + 1));
                dp[cur.pos + 1] = cur.depth + 1;
            }
            if( cur.pos - 1 >= 0 && dp[cur.pos - 1] > cur.depth + 1) {
                q.offer(new Point(cur.pos - 1 , cur.depth + 1));
                dp[cur.pos - 1] = cur.depth + 1;
            }
        }
    }
}
class Point {
    int pos, depth;
    public Point (int pos, int depth) {
        this.pos = pos;
        this.depth = depth;
    }
}