import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        String N = br.readLine();
        
        int sum = 0;
        int j = 0;
        for(int i = N.length()-1; i >=0; i--) {
            int iNum = N.charAt(i) - '0';
            sum += iNum * Math.pow(A, j++);
        }
        StringBuilder sb = new StringBuilder();
        while(sum > 0) {
            sb.insert(0, sum%B);
            sum/=B;
        }
        if(sb.length() == 0) System.out.println(0);
        System.out.println(sb);
        
    }
}