import java.util.*;
import java.io.*;
/*
수열이 
*/

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0;i < N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N][2];
        // 0열에는 최대값, 1열에는 길이 저장
        dp[0] = new int[]{arr[0], 1};
        for(int i = 1; i < N; i++) {
            int length = 0;
            for(int j = 0 ; j < i; j++) {
                if(length > dp[j][1]) continue;
                if(dp[j][0] < arr[i]) {
                    length = dp[j][1];
                }
            }
            dp[i][0] = arr[i];
            dp[i][1] = length+1;
        }
        int max = 0;
        for(int[] line: dp) {
            // System.out.println(Arrays.toString(line));
            max = Math.max(max, line[1]);
        }
        System.out.println(max);
    }
}