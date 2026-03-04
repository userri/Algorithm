import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int D(int x) {return x*2%10000;};
    static int S(int x) {return x-1<0?9999:x-1;};
    static int L(int x) {return (x*10)%10000 + x/1000;};
    static int R(int x) {return (x%10)*1000+ x/10;};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(bfs(a,b)).append("\n");
        }
        System.out.println(sb);
    }
    static String bfs(int start, int target) {
        if (start == target) return "";
        boolean[] visited = new boolean[10000];
        int[] parent = new int[10000];
        char[] how = new char[10000];
        Deque<Integer> q = new ArrayDeque<>();
        
        q.offer(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            int[] nxt = {D(cur), S(cur), L(cur), R(cur)};
            char[] op = {'D', 'S', 'L', 'R'};
            for (int i = 0;i < 4 ;i ++ ) {
                if (!visited[nxt[i]]) {
                    visited[nxt[i]] = true;
                    parent[nxt[i]] = cur;
                    // nxt[i] 까지 도달하기 위한 마지막 연산
                    how[nxt[i]] = op[i];

                    // 숫자를 넣었을 때만 검사
                    if (nxt[i] == target) {
                        StringBuilder sb = new StringBuilder();
                        int n = nxt[i];
                        while (n != start) {
                            sb.append(how[n]);
                            n = parent[n];
                        }
                        return sb.reverse().toString();
                    }
                    // visit 안했던 숫자만 큐에 넣어야 함!
                    q.offer(nxt[i]);
                }
            }
        }
        return "";
    }
}