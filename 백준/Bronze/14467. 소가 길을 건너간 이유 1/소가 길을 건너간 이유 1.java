import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> map = new HashMap<>();
        int changeCount = 0;

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!map.containsKey(a)) {
                map.put(a, b);
            } else {
                int before = map.get(a);
                if (before != b) {
                    map.put(a, b);
                    changeCount++;
                }
            }
        }
        System.out.println(changeCount);

    }
}