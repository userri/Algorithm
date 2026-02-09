import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        Map<String, String> passwords = new HashMap<>();
        for (int i = 0; i <N; i++) {
            String[] line = br.readLine().split(" ");
            passwords.put(line[0],line[1]);
        }
        for (int i = 0; i<M; i++) {
            String site = br.readLine();
            sb.append(passwords.get(site)).append("\n");
        }
        System.out.println(sb);
    }
}