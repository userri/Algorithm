import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        arr = new int[M]; // M개의 숫자를 담아서 출력할 배열
        visit = new boolean[N]; // N개중 M개의 visit을 저장할 배열
        dfs(N,M,0);
        System.out.println(sb);
    }
    static void dfs(int N, int M, int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[depth] = i + 1;
                dfs(N, M, depth + 1);
                visit[i] = false;
            }
        }
    }
}
