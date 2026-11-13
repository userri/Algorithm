
/*
시작포인트를 저장해두고
각 시작포인트까지의 거리를 비교하면 되는거아님?

각 시작포인트까지의 거리를 i번째까지 구해
마지막 숫자는 (길이 - 마지막 쪼개기)

조각들을 map으로 pieces로 관리해. key: 시작점, val: 길이

작은 숫자, 큰 숫자를 구해서... 뭐 어쩔건데...
작은 숫자로부터 조각을 빼서 다시 쪼개서 넣어
나중에 treeset의 last key를 출력하면 될듯

*/

import java.util.Scanner;
import java.util.*;
public class Main {
    static int N, M;
    static int[] rm;
    static TreeSet<Integer> set;
    static TreeMap<Integer, Integer> pieces; // key: 조각길이, val: 개수
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        rm = new int[M];
        set = new TreeSet<>();
        pieces = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < M; i++) {
            rm[i] = sc.nextInt();
        }

        // 첫번째꺼는 직접 넣기
        set.add(rm[0]);
        pieces.put(rm[0], pieces.getOrDefault(rm[0], 0) + 1);
        pieces.put(N - rm[0], pieces.getOrDefault(N - rm[0], 0) + 1);
        System.out.println(pieces.firstKey());

        for(int i = 1; i < M; i++) {
            int left = set.lower(rm[i]) == null ? -1 : set.lower(rm[i]);
            int right = set.higher(rm[i]) == null ? -1 : set.higher(rm[i]);
            
            // 왼이 null
            if(left == -1) {
                pieces.put(right, pieces.getOrDefault(right, 0) - 1);
                if(pieces.get(right) == 0) pieces.remove(right);
                pieces.put(rm[i], pieces.getOrDefault(rm[i], 0) + 1);
                pieces.put(right - rm[i] - 1, pieces.getOrDefault(right - rm[i] - 1, 0) + 1);
            } else if(right == -1) {
            // 오른이 null
                pieces.put(N - left, pieces.getOrDefault(N - left, 0) - 1);
                if(pieces.get(N - left) == 0) pieces.remove(N - left);
                pieces.put(rm[i] - left - 1, pieces.getOrDefault(rm[i] - left - 1, 0) + 1);
                pieces.put(N - rm[i], pieces.getOrDefault(N - rm[i] , 0) + 1);
            } else {
            // 왼오 둘다 null 아님
                pieces.put(right - left - 1, pieces.getOrDefault(right - left - 1, 0) - 1);
                if(pieces.get(right - left - 1) == 0) pieces.remove(right - left - 1);
                pieces.put(rm[i] - left - 1, pieces.getOrDefault(rm[i] - left - 1, 0) + 1);
                pieces.put(right - rm[i] - 1, pieces.getOrDefault(right - rm[i] - 1 , 0) + 1);
            }
            set.add(rm[i]);

            System.out.println(pieces.firstKey());
        }
    }
}
// 엣지: 추가했는데 0일때