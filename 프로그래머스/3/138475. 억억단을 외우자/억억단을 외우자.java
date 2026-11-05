
import java.util.*;
class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        
        /*
        e부터 시작하여 1까지 최대 약수의 개수를 저장함
        [0]: 최대 약수의 개수
        [1]: 최대 약수의 위치
        */
        int[][] store = new int[e + 1][2];
        store[e][0] = primeCount(e);
        store[e][1] = e;
        for(int i = e-1; i >=1; i--) {
            int cnt = primeCount(i);
            
            // 최대 약수 갱신
            if(cnt >= store[i+1][0]) {
                store[i][0] = cnt;
                store[i][1] = i;
            } else {
                store[i][0] = store[i+1][0];
                store[i][1] = store[i+1][1];
            }
        }
        
        for(int i = 0; i < starts.length; i++) {
            int star = starts[i];
            answer[i] = store[star][1];
        }
        return answer;
    }
    
    // 소수의 개수
    private static int primeCount(int n) {
        Map<Integer, Integer> primes = new HashMap<>();
        
        for(int i = 2; i <= Math.sqrt(n); i++) {
            while(n % i == 0) {
                primes.put(i, primes.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
        
        if(n != 1) {
            primes.put(n, primes.getOrDefault(n, 0) + 1);
        }
        
        int count = 1;
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(primes.entrySet());
        for(Map.Entry<Integer, Integer> entry: list) {
            count *= (entry.getValue() + 1);
        }
        return count;
    }
}