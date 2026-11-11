
/* 
같은 격자를 두번 방문해도 되는 미로
탈출한 경로를 문자열로 나타냈을 때, 문자열 사전순 가장 빠른 경로로 탈출
같은 곳을 두번 방문할 수 있다면...
k가 2500까지라서 4^2500은 너무 많다
일단 최단거리를 찾은 다음에
위아래, 왼오 와 같이 목적지에 변화를 주지 않는 쌍을 경로에 넣어
근데... 그 경로가 성립하는지를 어케알고 삽입하지?
bfs를 따로 돌려야 하나?

*/

import java.util.*;
class Solution {
    int R, C, k, N, M;
    // 알파벳 순서 하좌우상(d, l, r, u)
    int[] dr = {1,0,0,-1};
    int[] dc = {0,-1,1,0};
    char[] dir = new char[]{'d', 'l', 'r', 'u'};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int dist = Math.abs(x-r) + Math.abs(y-c);
        if(dist > k || dist % 2 != k % 2) return "impossible";
        
        this.N = n; this.M = m; this.k = k;
        this.R = r-1; this.C = c-1;
        
        return dfs(x-1, y-1, 0, new StringBuilder());
    }
    
    // 그리디로 하좌우상 순서로 경로를 찾아가
    // 성공하면? 문자열을 리턴
    // 실패하면? 빈 문자열을 리턴
    String dfs(int r, int c, int move, StringBuilder sb) {
        if(move == k) return (r == R) && (c == C) ? sb.toString() : "";
        
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i], nmove = move + 1;
            if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            // 가지치기
            if(Math.abs(nr - R) + Math.abs(nc - C) > k - nmove) continue;
            
            sb.append(dir[i]);
            String s = dfs(nr, nc, nmove, sb);
            if(!s.isEmpty()) return s;
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return "";
    }
}