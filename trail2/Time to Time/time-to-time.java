import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        int C = Integer.parseInt(input[2]);
        int D = Integer.parseInt(input[3]);
        int result = 0;
        if(B < D) {
            C--;
            D+=60;
        }
        result = 60*(C-A);
        result += D-B;
        System.out.println(result);

        
    }
}