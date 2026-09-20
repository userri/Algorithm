import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        int cnt = 0;
        for(int i = 0; i < A.length(); i++) {
            if(A.charAt(i) == ')') continue;
            for(int j = i + 1; j < A.length(); j++) {
                if(A.charAt(j) == ')') cnt++;
            }
        }
        System.out.println(cnt);
    }
}