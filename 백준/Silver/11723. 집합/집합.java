

import java.lang.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            String oper = "";
            int num = 0;
            oper = input[0];
            if (input.length == 2)
                num = Integer.parseInt(input[1]);

            // 21자리의 1들
            if (oper.equals("all")) result = (1 << 21) - 1;
            else if (oper.equals("empty")) result = 0;
            else {
                if (oper.equals("add")) result |= (1 << num);
                else if (oper.equals("remove")) result &= ~(1 << num);
                else if (oper.equals("check")) {
                    sb.append((result & (1 << num)) != 0 ? 1 : 0).append("\n");
                } else if (oper.equals("toggle")) result ^= (1 << num);
            }
        }
        System.out.println(sb);
    }

}