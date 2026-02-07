

/*
대략 숫자는 1050 근처까지
 */
import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        Queue<String> str = new LinkedList<>();
        for (String s : input) {
            str.add(s);
        }
        int i = 1;
        while (!str.isEmpty()) {
            String[] iStr = (i + "").split("");
            for (String s : iStr) {
                if (str.isEmpty()) {
                    break;
                }
                if (str.peek().equals(s)) {
                    str.poll();
                }
            }
            if (str.isEmpty()) {
                System.out.println(i);
                break;
            }
            i++;
        }

    }

}