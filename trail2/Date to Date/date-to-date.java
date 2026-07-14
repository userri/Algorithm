import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int m1 = Integer.parseInt(input[0]);
        int d1 = Integer.parseInt(input[1]);
        int m2 = Integer.parseInt(input[2]);
        int d2 = Integer.parseInt(input[3]);

        Set<Integer> day31 = new HashSet<>();
        day31.add(1);
        day31.add(3);
        day31.add(5);
        day31.add(7);
        day31.add(8);
        day31.add(10);
        day31.add(12);
        int sum = 0;

        if(m1 == m2) {
            System.out.println(d2-d1 + 1);
            return;
        }
        if(day31.contains(m1)) {
            sum = (31 - d1 + 1);
        } else if(m1 == 2) sum = 28 - d1 + 1;
        else sum = 30 - d1 + 1;

        for(int m = m1+1; m <= m2-1;  m++) {
            if(day31.contains(m)) sum += 31;
            else if (m == 2) sum += 28;
            else sum += 30;
        }
        sum += d2;
        System.out.println(sum);
    }
}