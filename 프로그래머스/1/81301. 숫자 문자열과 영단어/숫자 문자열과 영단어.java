import java.util.*;
class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'z') {
                sb.append(0);
                i += 3;
            } 
            else if (c == 'o') {
                sb.append(1);
                i += 2;
            }
            else if (c == 't') {
                char next = s.charAt(i+1);
                if(next == 'w') {
                    sb.append(2);
                    i += 2;
                } else {
                    sb.append(3);
                    i += 4;
                }
            }
            else if(c == 'f') {
                char next = s.charAt(i+1);
                if(next == 'o') {
                    sb.append(4);
                    i += 3;
                } else {
                    sb.append(5);
                    i += 3;
                }
            }
            else if(c == 's') {
                char next = s.charAt(i+1);
                if(next == 'i') {
                    sb.append(6);
                    i += 2;
                } else {
                    sb.append(7);
                    i += 4;
                }
            }
            else if(c == 'e') {
                sb.append(8);
                i += 4;
            }
            else if(c == 'n') {
                sb.append(9);
                i += 3;
            } else sb.append(c);
        }
        return Integer.parseInt(sb.toString());
    }
}