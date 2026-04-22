import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, String> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            String course = input[0];
            int year = Integer.parseInt(input[1]);
            map.put(year, course);
        }
        System.out.println(map.get(2026));
    }
}