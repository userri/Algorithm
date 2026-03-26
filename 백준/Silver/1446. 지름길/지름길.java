import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Shortcut> shortcuts = new ArrayList<>();

        for(int i = 0;i < N;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            // 목적지 초과하거나 그냥 가는게 지름길보다 빠르면 건너뛰기
            if(e > D || e-s < d) continue;
            shortcuts.add(new Shortcut(s, e, d));
        }
        // 최단거리 저장 배열
        int[] dist = new int[D+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        int curPos = 0;
        int shortcutIdx = 0;

        shortcuts.sort(Comparator.comparingInt(o -> o.start));
        while(curPos < D) {
            if(dist[curPos + 1] > dist[curPos] + 1) {
                dist[curPos+1] = dist[curPos] + 1;
            }
            for(Shortcut shortcut : shortcuts) {
                if(shortcut.start == curPos) {
                    if(dist[shortcut.end] > dist[curPos] + shortcut.dist) {
                        dist[shortcut.end] = dist[curPos] + shortcut.dist;
                    }
                }
            }
            curPos++;
        }
        System.out.println(dist[D]);
    }
}
class Shortcut {
    int start, end, dist;
    public Shortcut(int start, int end, int dist) {
        this.start = start;
        this.end = end;
        this.dist = dist;
    }
}