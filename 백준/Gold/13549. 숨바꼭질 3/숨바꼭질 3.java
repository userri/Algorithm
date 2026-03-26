import java.util.*;
import java.io.*;
class Main{
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);
        dp = new int[100001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        

        int answer = dijkstra(N, K);
        // for(int i = 1;i < 50;i++) {
        //     System.out.print(dp[i] + ", ");
        //     if(i%10 == 0) System.out.println();
        // }
        System.out.println(answer);        
    }
    static int dijkstra(int N, int K){
        // 시간 수 적은 순서대로 탐색
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        pq.offer(new Point(N, 0));
        dp[N] = 0; // 시작지점은 0
        while(!pq.isEmpty()) {

            Point cur = pq.poll();
            int cursite = cur.site;
            int curtime = cur.time;
            if(cursite == K) return curtime;

            // 이미 더 적은 시간으로 초기화된 적 있다면 건너뛰기
            if(dp[cursite] < curtime) continue;
            if(cursite - 1 >= 0 && dp[cursite-1] > curtime) {
                dp[cursite-1] = curtime + 1;
                pq.offer(new Point(cursite-1, dp[cursite-1]));
            }
            if(cursite + 1 <= 100000 && dp[cursite+1] > curtime) {
                dp[cursite+1] = curtime + 1;
                pq.offer(new Point(cursite+1, dp[cursite+1]));
            }
            if(cursite*2 != cursite && cursite*2 <= 100000 && dp[cursite*2] > curtime) {
                dp[cursite*2] = curtime; // 순간이동은 시간동일(0초만에 이동)
                pq.offer(new Point(cursite*2, dp[cursite*2]));
            }
        }
        return -1;
    }
}
class Point{
    int site, time;
    public Point(int site, int time) {
        this.site = site;
        this.time = time;
    }
    @Override
    public String toString() {
        return "[" + site+ ", " + time+ "]";
    }
}