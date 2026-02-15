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
        String[] monster = new String[N+1];
        Map<String, Integer> strMap = new HashMap<>(2*N);
        
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            monster[i] = name;
            strMap.put(name, i);
        }
        while (M-- > 0) {
            String question = br.readLine();
            if (Character.isDigit(question.charAt(0))) {
                int num = Integer.parseInt(question);
                sb.append(monster[num]).append("\n");
            } else {
                sb.append(strMap.get(question)).append("\n");
            }
        }
        System.out.println(sb);
    }
}