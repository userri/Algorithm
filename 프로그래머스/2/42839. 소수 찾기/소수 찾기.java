import java.util.*;
/*
구현전략
소수판별을 위한 함수는 별도로
종이조각으로 만든 수를 set으로 저장해(017=17)
*/
class Solution {
    int[] newNumbers, arr;
    int N;
    boolean[] visited;
    Set<Integer> set;
    StringBuilder sb = new StringBuilder();
    public int solution(String numbers) {
        this.N = numbers.length();
        newNumbers = new int[N];
        char[] temp = numbers.toCharArray();
        for(int i = 0; i < N; i++) {
            newNumbers[i] = Integer.parseInt(temp[i]+"");
        }
        this.visited = new boolean[N];
        
        set = new HashSet<>();
        
        for(int i = 1; i <= N; i++) {
            this.arr = new int[i];
            backtracking(0, i);
        }
        
        return set.size();
    }
    void backtracking(int depth, int length) {
        if(depth == length) {
            sb.setLength(0);
            for(int a: arr) {
                sb.append(a);
            }
            int result = Integer.parseInt(sb.toString());
            if(isPrime(result)) {
                set.add(result);
            }
            return;
        }
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = newNumbers[i];
                backtracking(depth + 1, length);
                visited[i] = false;
            }
        }
    }
    boolean isPrime(int n) {
        // 1이하면 리턴
        if(n <=1) return false;
        boolean check = true;
        for(int i = 2; i < n/2+1; i++) {
            if(n % i == 0) {
                check = false;
                break;
            }
        }
        return check;
    }
}