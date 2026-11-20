import java.util.*;
import java.io.*;
public class Solution {
    static int N;
    static int[][] islands;
    static List<long[]> edges;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            double E = 0;
            islands = new int[N][2];
            edges = new ArrayList<>();
            parent = new int[N];
            for(int i = 0; i < N; i++) 
                parent[i] = i;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                islands[i][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                islands[i][1] = Integer.parseInt(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            for(int i = 0; i < N; i++) {
                for(int j = i + 1; j < N; j++) {
                    int xDist = islands[i][0] - islands[j][0];
                    int yDist = islands[i][1] - islands[j][1];
                    edges.add(new long[]{i, j, (xDist*1L)*xDist + (yDist*1L)*yDist});
                }
            }
            Collections.sort(edges, (a,b) -> Long.compare(a[2],b[2]));

            long answer = 0;
            for(int i = 0; i < edges.size(); i++) {
                if(find((int)edges.get(i)[0]) != find((int)edges.get(i)[1])) {
                    union((int)edges.get(i)[0], (int)edges.get(i)[1]);
                    answer += edges.get(i)[2];
                }
            }
            
            sb.append("#").append(tc).append(" ").append(Math.round(answer*E));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int find(int a) {
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if(aParent != bParent) {
            parent[aParent] = bParent;
        }
    }
}