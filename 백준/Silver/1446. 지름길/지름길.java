import java.util.*;
import java.io.*;
class Main {
    static int minDist = Integer.MAX_VALUE;
    static List<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        
        for(int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            if (end > D) continue;
            list.add(new int[]{start, end, dist});
        }
         // 끝나는 지점 작은 애들 1순위, 시작점 작은 애들 2순위, 거리 작은 애들 3순위
        list.sort((o1, o2) -> {
            if(o1[0] != o2[0]) return o1[0] - o2[0];
            if(o1[1] != o2[1]) return o1[1] - o2[1];
            return o1[2] - o2[2];
        });
        
        dfs(D, 0, 0, 0);
        System.out.println(minDist);
        
    }
    static void dfs(int goal, int nowSite, int idx, int sum) {
        if(nowSite == goal) {
            minDist = Math.min(minDist, sum);
            return;
        }
        for(int i = idx; i < list.size();i++) {
            // 현재 시작점보다 시작점이 전에 있으면 건너뜀
            if(list.get(i)[0] < nowSite) continue;
            int temp = sum;
            // 현재 지점과 시작점이 같지 않으면 차이나는 만큼 수동으로 더해주기.
            sum += list.get(i)[0]-nowSite;
            sum += list.get(i)[2]; // 지름길에서의 길이만큼 업데이트
            dfs(goal, list.get(i)[1], i+1, sum);

            sum = temp; // dfs 하기 전으로 업데이트
        }
        // 마지막에 수동으로 도착지점 가기
        if(nowSite < goal) {
            dfs(goal, goal, list.size(), sum+(goal-nowSite));
        }
    }
}