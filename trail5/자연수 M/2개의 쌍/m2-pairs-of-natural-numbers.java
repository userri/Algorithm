
/*
합이 가장 크되 작아야함
가장 큰거랑 가장 작은거 분배?


*/
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            map.put(y, x);
            set.add(y);
        }
        int[] arr = new int[set.size()];
        
        int idx = 0;
        for(int s: set) {
            arr[idx++] = s;
        }


        int answer = 0;
        int left = 0, right = arr.length - 1;
        while(left <= right) {
            int lNum = arr[left];
            int rNum = arr[right];
            
            if(map.get(lNum) == 0) {
                left++;
                continue;
            }
            if(map.get(rNum) == 0) {
                right--;
                continue;
            }
            int min = Math.min(map.get(lNum), map.get(rNum));
            answer = Math.max(answer, lNum + rNum);
            if(lNum == rNum) {
                break;
            } else {
                map.put(lNum, map.get(lNum) - min);
                map.put(rNum, map.get(rNum) - min);
            }
        }
        System.out.println(answer);
    }
}