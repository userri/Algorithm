import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.BufferedReader;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> students = new ArrayList<>();
        for (int i = 0;i<28;i++) {
            students.add(Integer.parseInt(bf.readLine()));
        }
        Collections.sort(students);
        int cnt = 0;
        for (int i  = 1;i<31;i++) {
            if (!students.contains(i)) {
                System.out.println(i);
                cnt++;
            }
            if (cnt==2) break;
        }
    }
}