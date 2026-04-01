import java.util.*;

import java.lang.*;

import java.io.*;

// The main method must be in a class named "Main".

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st= new StringTokenizer(br.readLine());

        int h1 = Integer.parseInt(st.nextToken());

        int y1 = Integer.parseInt(st.nextToken());

        st= new StringTokenizer(br.readLine());

        int h2 = Integer.parseInt(st.nextToken());

        int y2 = Integer.parseInt(st.nextToken());

        System.out.println((h1+h2<y1+y2)?"Hanyang Univ.":(h1+h2==y1+y2)?"Either":"Yongdap");

    }

}