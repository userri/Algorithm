
/*
귀가방향이 비슷한 어피치와 택시합승 이용하여 택시요금 얼마나 아낄수 있을지 계산
만약 합승안하는 게 더 싸면 합승안해도 됨
근데 A입장에서는 탑승 안한 게 더 싼데 B때문에 전체방향이 싸지는 방향으로 가야되는 경우도 생기
그냥 다익스트라...는 아니란 말이지? 다익스트라 복습 하고싶어짐
흠...
일단 완탐으로 코드 짜보기?
근데...
A랑 B랑 어디까지 겹치는지 어케계산? 그걸 매번 택시요금 합쳐서 A랑 B랑 혼자가는 구간이 각각 어디인지 계산해야하는거아님? 댕머리아픔

음... i부터 j까지 가는 최단거리를 dist에 저장해
음... 합승하는 한명만 

*/
import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        
        int[][] dist = new int[n+1][n+1];
        for(int i = 0; i < n+1; i++) {
            Arrays.fill(dist[i], 199*10_0000);
        }
        for(int i = 0; i < n+1; i++) dist[i][i] = 0;
        for(int[] fare: fares) {
            dist[fare[0]][fare[1]] = fare[2];
            dist[fare[1]][fare[0]] = fare[2];
        }
        
        // 경유지가 가장 바깥 -> 중요
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    // 경유지를 지나는 게 더 빠르다면 업데이트
                    // System.out.print(dist[i][j] + "-> ");
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    // System.out.println(i + " " + j + " " + k + " " + dist[i][j]);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return answer;
    }
}