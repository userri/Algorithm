import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int m1 = Integer.parseInt(st.nextToken());
        int d1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int d2 = Integer.parseInt(st.nextToken());
        String A = br.readLine();

        int[] days = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        Map<String, Integer> dayOfWeek = new HashMap<>();
        dayOfWeek.put("Mon", 0);
        dayOfWeek.put("Tue", 1);
        dayOfWeek.put("Wed", 2);
        dayOfWeek.put("Thu", 3);
        dayOfWeek.put("Fri", 4);
        dayOfWeek.put("Sat", 5);
        dayOfWeek.put("Sun", 6);
        
        int targetDow = dayOfWeek.get(A);

        int totalDays1 = getDays(m1, d1, days);
        int totalDays2 = getDays(m2, d2, days);
        int diff = totalDays2 - totalDays1; 

        int count = diff / 7;
        if (diff % 7 >= targetDow) {
            count++;
        }

        System.out.println(count);
    }

    private static int getDays(int m, int d, int[] days) {
        int total = d;
        for (int i = 1; i < m; i++) {
            total += days[i];
        }
        return total;
    }
}