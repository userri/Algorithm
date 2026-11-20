import java.util.*;
import java.io.*;
public class Solution {

    static int N;
    static int[][] nodes;
    static boolean[] visited;
    static long answer;
    static double E;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            nodes = new int[N][2];
            visited = new boolean[N];
            answer = 0;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) nodes[i][0] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) nodes[i][1] = Integer.parseInt(st.nextToken());

            E = Double.parseDouble(br.readLine());

            PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

            int cnt = 0;
            pq.offer(new long[]{0, 0});

            while(!pq.isEmpty()) {
                long[] cur = pq.poll();
                int idx = (int)cur[1];
                if(visited[idx]) continue;
                visited[idx] = true;
                cnt++;
                answer += cur[0];
                if(cnt >= N) break;

                for(int i = 0; i < N; i++) {
                    if(visited[i]) continue;
                    long dx = nodes[i][0] - nodes[idx][0];
                    long dy = nodes[i][1] - nodes[idx][1];
                    long L = dx*dx + dy*dy;
                    pq.offer(new long[]{L, i});
                }
            }
            
            
            sb.append("#").append(tc).append(" ").append(Math.round(answer * E));
            sb.append("\n");
        }
        System.out.println(sb);
    }
}