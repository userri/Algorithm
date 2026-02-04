import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);

        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        while (!q.isEmpty()) {
            for (int i = 0; i < K - 1; i++) {
                q.offer(q.poll());
            }

            result.add(q.poll());

        }
        sb.append("<");
        for (int i = 0; i < result.size()-1; i++) {
            sb.append(result.get(i)).append(", ");
        }
        sb.append(result.get(result.size() - 1));
        sb.append(">");
        System.out.println(sb);
    }
}