

import java.util.*;
class Solution {
    String[][] tickets;
    List<String> answers = new ArrayList<>();
    String[] arr;
    boolean[] visited;
    StringBuilder sb = new StringBuilder();
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        arr = new String[tickets.length+1];
        visited = new boolean[tickets.length];
        arr[0] = "ICN";
        dfs(tickets.length,tickets.length+1,1, "ICN");
        Collections.sort(answers);
//        for(String s: answers) {
//            System.out.println(s);
//        }
        return answers.get(0).split(" ");
    }
    void dfs(int N, int M, int depth, String start) {
        if(depth == M) {
//            System.out.println("도달하긴 함");
            for(String s: arr) {
                sb.append(s).append(" ");
            }
//            System.out.println(sb);
            answers.add(sb.toString());
            sb.setLength(0);
            return;
        }
        for(int i = 0; i < N;i++) {
            if(!visited[i] && tickets[i][0].equals(start)) {
//                System.out.println(tickets[i][1] + " 방문");
                visited[i] = true;
                arr[depth] = tickets[i][1];
                dfs(N,M,depth+1, tickets[i][1]);
                visited[i] = false;
            }
        }
    }
}