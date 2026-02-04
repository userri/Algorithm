import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, List<String>> members = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int age = Integer.parseInt(input[0]);
            String name = input[1];
            if (members.containsKey(age)) {
                members.get(age).add(name);
            } else {
                ArrayList<String> nameList = new ArrayList<>();
                nameList.add(name);
                members.put(age, nameList);
            }
        }
        for (int key:members.keySet()) {
            for (String name : members.get(key)) {
                System.out.println(key + " " + name);
            }
        }
    }
}