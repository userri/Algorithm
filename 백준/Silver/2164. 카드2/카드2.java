import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        while (q.size() > 1) {
            q.poll();
            q.offer(q.poll()); // 맨앞 버림과 동시에 뒤에 넣음
        }
        System.out.println(q.poll()); // 마지막으로 남은 원소 출력

    }

}