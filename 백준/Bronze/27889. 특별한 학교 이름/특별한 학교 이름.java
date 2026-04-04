import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Map<String, String> map = new HashMap<>();
        map.put("NLCS","North London Collegiate School");
        map.put("BHA","Branksome Hall Asia");
        map.put("KIS","Korea International School");
        map.put("SJA","St. Johnsbury Academy");
        System.out.println(map.get(input));
    }
}