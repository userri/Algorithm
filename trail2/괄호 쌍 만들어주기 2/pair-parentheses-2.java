import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        
        int cnt = 0;
        for(int i = 0; i < A.length()-1; i++) {
            if(!(A.charAt(i) == '(' && A.charAt(i+1) == '(')) continue;
            for(int j = i + 2; j < A.length()-1; j++) {
                if(A.charAt(j) == ')' && A.charAt(j+1) == ')') cnt++;
            }
        }
        System.out.println(cnt);
    }
}