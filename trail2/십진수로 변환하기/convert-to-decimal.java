import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int result = 0;
        String s = br.readLine();
        int j = 0;
        for(int i = s.length()-1; i >= 0; i--) {
            int num = s.charAt(i) - '0';
            result += num == 0 ? 0 : Math.pow(2, j);
            j++;
        }
        System.out.println(result);
    }
}