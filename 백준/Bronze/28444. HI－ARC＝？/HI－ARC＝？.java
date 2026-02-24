import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int h = Integer.parseInt(input[0]);
        int i = Integer.parseInt(input[1]);
        int a = Integer.parseInt(input[2]);
        int r = Integer.parseInt(input[3]);
        int c = Integer.parseInt(input[4]);
        System.out.println(h*i-a*r*c);
    }
}