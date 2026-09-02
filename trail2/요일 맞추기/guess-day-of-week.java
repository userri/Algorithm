import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        String[] dayOfWeek ={"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        int m1 = Integer.parseInt(st.nextToken());
        int d1 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int d2 = Integer.parseInt(st.nextToken());
        
        boolean reversed = false;

        // 무조건 큰게 뒤에있도록
        if(m1 > m2 || (m1 == m2 && d1 > d2)) {
            int tempM = m1, tempD = d1;
            m1 = m2;
            d1 = d2;
            m2 = tempM;
            d2 = tempD;
            reversed = true;
        }


        int passedDays = 0;
        

        if(m1 == m2) {
            int dow = 0;
            if(reversed) {
                dow = ((1 - (d2 - d1)) % 7 + 7)%7;
            }
            else dow = (1 + d2 - d1) % 7;
            
            System.out.println(dayOfWeek[dow]);
            return;
        } else{
            // 만약 m1: 1, d1: 30이면 1 -> 시작날짜 포함안함
            passedDays = days[m1] - d1;
            for(int i = m1 + 1; i <= m2 - 1; i++) {
                passedDays += days[i];
            }
            passedDays += d2;
        }
        int dow = 0;
        if(reversed) {
            dow = ((1 - passedDays) % 7 + 7)%7;
        } else {
            dow = ((1 + passedDays) % 7 + 7)%7;
        }

        System.out.println(dayOfWeek[dow]);
    }
}