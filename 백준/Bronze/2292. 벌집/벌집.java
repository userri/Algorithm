import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int endOfShell = 1;
        int dist = 1;

        while (!(N<=endOfShell)) {
            endOfShell += dist * 6;
            dist++;
        }
        System.out.println(dist);
    }

}










