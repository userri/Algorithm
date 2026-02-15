import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] inputs  = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        Map<Integer, String> intMap = new HashMap<>();
        Map<String, Integer> strMap = new HashMap<>();
        
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            intMap.put(i, name);
            strMap.put(name, i);
            
        }
        while (M-- > 0) {
            String question = br.readLine();
            try {
                int num = Integer.parseInt(question);
                sb.append(intMap.get(num)).append("\n");
            } catch (Exception e) {
                sb.append(strMap.get(question)).append("\n");
            }
        }
        System.out.println(sb);
    }
}