
import java.util.*;
// N과 M 참고
class Solution {
    static StringBuilder sb = new StringBuilder();
    static char[] arr;
    static boolean[] visited;
    static char[] splitNums;
    static Set<Integer> set = new HashSet<>();
    public int solution(String numbers) {

        splitNums = numbers.toCharArray();

        int N = numbers.length();
        visited = new boolean[numbers.length()];
        // 길이 1부터 길이 numbers.length() 까지 백트래킹으로 경우의 수 구하기
        for(int i = 1;i < numbers.length()+1;i++) {
            arr = new char[i]; // 길이가 매번 다르므로 배열길이도 다르게 초기화
            dfs(N, i, 0);
        }
        int answer = 0;
        for(int s: set) {
            if (s <= 1) continue; // 1은 소수가 아님
            if (isPrime(s)) answer++;
        }
        return answer;
    }
    static boolean isPrime(int n){
        for(int i = 2; i <= n/2;i++) {
            if(n%i == 0) return false;
        }
        return true;
    }
    static void dfs(int N, int M, int depth) {
        if(depth == M) {
            for(char val: arr) {
                sb.append(val);
            }
            set.add(Integer.parseInt(sb.toString()));
            sb.setLength(0);
            return;
        }
        for(int i = 0; i < N;i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = splitNums[i]; // numbers에서 문자 꺼내서 길이 M인 배열에 저장
                dfs(N, M, depth+1);
                visited[i] = false;
            }
        }
        return;
    }
}