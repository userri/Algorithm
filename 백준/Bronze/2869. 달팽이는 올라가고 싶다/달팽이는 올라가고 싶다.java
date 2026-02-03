import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());


        // 일단 1일차에 올라갔음
        int result = A;
        int step = A - B;
        if ((V - result) % step == 0) {
            System.out.println(1+ (V - result) / step);
        } else {
            System.out.println(1+ (V - result) / step +1 );
        }

    }

}