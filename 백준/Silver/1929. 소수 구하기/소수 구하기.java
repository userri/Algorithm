
import java.lang.*;
import java.io.*;

class Main {
    public static boolean[] prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);

        prime = new boolean[N + 1];
        get_prime();

        for (int i = M; i <= N; i++) {
            // false(소수)면 출력
            if (!prime[i]) System.out.println(i);
        }


    }

    // 에라토스테네스의 체 알고리즘
    private static void get_prime() {
        // true 소수아님, false 소수
        prime[0] = prime[1] = true;
        for (int i = 2; i <= Math.sqrt(prime.length); i++) {
            // 소수아니면 건너뛰기
            if (prime[i]) continue;
            // 소수의 배수는 소수아님 처리
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }

        }
    }

}