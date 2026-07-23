
/*
재료에 대한 점수와 가게에서 제공하는 재료에 대한 칼로리가 주어짐
정해진 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거를 조합해주자
햄버거 선호도: 조합된 재료들의 맛에 대한 점수의 합으로 결정됨
같은재료 여러번 사용 못함
N: 1~20 ->  수가 작음. 비트마스킹 힌트
L: 1~10000
두번째 제출: 쓸데없는 visited 없앰
*/
import java.util.*;
import java.io.*;
public class Solution {
    static int[][] foods;
    static int N, L, maxScore;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            maxScore = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            // foods[i][0] = 0번째는 맛에 대한 점수, 1번째는 칼로리
            foods = new int[N][2];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                // 선호도
                int t = Integer.parseInt(st.nextToken());
                // 칼로리
                int k = Integer.parseInt(st.nextToken());
                foods[i][0] = t;
                foods[i][1] = k;
            }
            // 하나도 방문안함 상태
            comb(0, 0, 0);
            // 1 << 0 0번째는 방문한 상태
            comb(0, foods[0][0], foods[0][1]);

            System.out.println("#" + test_case + " " + maxScore);
        }
    }
    // cur: 지금 어디까지 체크했는지, visited: 방문상태
    static void comb(int cur, int scoreSum, int calSum) {
        // 모든 수를 체크했다면 최대선호도 업데이트
        // 어차피 칼로리 넘치는애들은 여기까지 도달못함
        if(cur == N-1) {
            maxScore = Math.max(maxScore, scoreSum);
            return;
        }
        
        // 조합이니까 순서없으니 무조건 내 바로 다음만 검사
        int next = cur + 1;
        // 바로 다음꺼 안넣을때는 칼로리 검사 안해, 방문상태, 점수합, 칼로리합 다 그대로 넘겨
        comb(next, scoreSum, calSum);
        
        // 바로 다음꺼 넣을때는 칼로리 검사 후 초과하면 통과 안시켜
        if(calSum + foods[next][1] > L) return; 
        comb(next, scoreSum + foods[next][0], calSum + foods[next][1]);
    }
}