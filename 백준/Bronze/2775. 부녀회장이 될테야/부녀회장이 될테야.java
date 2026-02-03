import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] peoples;
        peoples = new int[0][0];
        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            peoples = new int[k + 1][n + 1];
            for (int j = 1; j <= n; j++) {
//                0층의 i호에 i명 살도록 추가
                peoples[0][j] = j;
            }
//            k층 될때까지 계속 채우기
            for (int j = 1; j <= k; j++) {
                peoples[j][0] = peoples[j - 1][0];
                for (int jj = 1; jj <= n; jj++) {
//                    i-1 호수에 아랫층의 i 호수 사람만큼 더하기
                    peoples[j][jj] = peoples[j][jj - 1] + peoples[j - 1][jj];
                }
            }
            System.out.println(peoples[k][n]);
        }


    }
}








