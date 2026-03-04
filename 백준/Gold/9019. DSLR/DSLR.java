import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            sb.append(bfs(A,B)).append("\n");
        }
        System.out.println(sb);
    }
    static String bfs(int a, int b) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);
        Map<Integer, String> map = new HashMap<>();
        map.put(a,"");
        // 이미 map에 있는 숫자로는 더이상 가지 않아
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == b) {
                return map.get(cur);
            }
            // D
            int d = (cur*2)%10000;
            // map에 없어야지만 집어넣음
            if (!map.keySet().contains(d)) {
                // System.out.println(d + " D");
                map.put(d, map.get(cur)+"D");
                queue.offer(d);
            }
            // S
            int s = cur-1<0?9999:cur-1;
            if (!map.keySet().contains(s)) {
                // System.out.println(s + " S");
                map.put(s, map.get(cur)+"S");
                queue.offer(s);
            }
            // L
            // 천의자리수
            int first = (int)(cur/1000);
            int l = (cur*10)%10000+first;
            if (!map.keySet().contains(l)) {
                // System.out.println(l + " L");
                map.put(l, map.get(cur)+"L");
                queue.offer(l);
            }
            // R
            int last = cur%10;
            int r = cur/10 + last*1000;
            if (!map.keySet().contains(r)) {
                // System.out.println(r + " R");
                map.put(r, map.get(cur)+"R");
                queue.offer(r);
            }
        }
        return "";
    }
}
