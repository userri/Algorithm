import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        if(n == 0) {
            System.out.println(0);
            return;
        }
        while(n > 0) {
            sb.insert(0,n%2);
            n/=2;
        }
        System.out.println(sb);
    }
}