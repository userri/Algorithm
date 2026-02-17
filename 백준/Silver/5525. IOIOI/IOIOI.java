import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split("");
        String[] target = new String[2*N+1];
        boolean flag = true;

        for (int i = 0; i <2*N+1; i++) {
            if (flag) {
                target[i] = "I";
            } else target[i] = "O";
            flag = !flag;
        }

        int cnt = 0;
        boolean pass = true;
        
        for (int i = 0; i <= M-(2*N+1); i++) {
            pass = true;
            if (s[i].equals("I")) {
                for (int j = 0; j < 2*N+1; j++) {
                    if (!s[i+j].equals(target[j])) {
                        pass = false;
                        break;
                    }
                }
                if (pass) {
                    cnt++;
                }
            }
        }
        
        System.out.println(cnt);
    }
}