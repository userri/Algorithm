import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        int num = 666;
        while (cnt < N) {
            String str = num + "";
            if (str.contains("666")) {
                cnt++;
            }
            if (cnt == N) {
                break;
            }
            num++;
        }
        System.out.println(num);

    }

}