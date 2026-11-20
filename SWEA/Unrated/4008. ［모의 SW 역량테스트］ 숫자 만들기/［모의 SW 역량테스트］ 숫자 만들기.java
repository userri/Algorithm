import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static int[] nums;
    static int[] cals;
    static int max, min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            cals = new int[4];
            max = Integer.MIN_VALUE; min = Integer.MAX_VALUE;
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++) cals[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
            
            dfs(nums[0], 0, 0, 0, 0, 1);
            
            
            sb.append("#").append(tc).append(" ").append(max - min);
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int num, int o1, int o2, int o3, int o4, int cnt) {
        if(cnt >= N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }
        if(o1 < cals[0]) {
            dfs(num + nums[cnt], o1 + 1, o2, o3, o4, cnt+ 1);
        }
        if(o2 < cals[1]) {
            dfs(num - nums[cnt], o1, o2 + 1, o3, o4, cnt + 1);
        }
        if(o3 < cals[2]) {
            dfs(num * nums[cnt], o1, o2, o3 + 1, o4, cnt + 1);
        }
        if(o4 < cals[3]) {
            dfs(num / nums[cnt], o1, o2, o3, o4 + 1, cnt + 1);
        }
    }
}