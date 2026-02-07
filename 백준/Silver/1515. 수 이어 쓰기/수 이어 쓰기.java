
/*
대략 숫자는 1050 근처까지
 */
import java.lang.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        int len = input.length;
        int i = 1;
        int pointer = 0;
        while (pointer < len) {
            String[] iStr = (i + "").split("");
            for (String s : iStr) {
                if (pointer >= len) {
                    break;
                }
                if (input[pointer].equals(s)) {
                    pointer++;
                }
            }
            if (pointer >= len) {
                System.out.println(i);
                break;
            }
            i++;
        }

    }

}