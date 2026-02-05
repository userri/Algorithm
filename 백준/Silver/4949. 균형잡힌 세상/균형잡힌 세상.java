

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        Deque<String> stack;
        while (true) {
            input = br.readLine().split("");
            if (input[0].equals(".")) break;
            stack = new LinkedList<>();
            boolean flag = false;

            for (String s : input) {
                if (s.equals("(") || s.equals("[")) {
                    stack.add(s);
                } else if (s.equals(")")) {
                    if (stack.isEmpty()) {
                        flag = true;
                        break;
                    }
                    // 마지막거 꺼냈을 때 짝지어지지 않으면 탈출
                    if (stack.peekLast().equals("(")) {
                        stack.pollLast();
                    } else {
                        flag = true;
                        break;
                    }
                } else if (s.equals("]")) {
                    if (stack.isEmpty()) {
                        flag = true;
                        break;
                    }
                    if (stack.peekLast().equals("[")) {
                        stack.pollLast();
                    } else {
                        flag = true;
                        break;
                    }
                }
            }            
            // 검사 끝나고 스택에 남아있으면 안됨!!
            if (!stack.isEmpty()) {
                flag = true;
            }
            if (flag) {
                System.out.println("no");
            } else {
                System.out.println("yes");
            }
        }
    }
}