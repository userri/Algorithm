import java.util.*;

import java.lang.*;

import java.io.*;

// The main method must be in a class named "Main".

class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double n = Integer.parseInt(br.readLine())/11*10;

        System.out.println((int)n);

    }

}