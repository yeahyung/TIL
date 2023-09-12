package Programmers;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12909
 */
public class Correct_Parentheses {

    @Test
    void test() {
        assertEquals(solution("()()"), true);
        assertEquals(solution("(())()"), true);
        assertEquals(solution(")()("), false);
        assertEquals(solution("(()("), false);
    }

    public boolean solution(String s) {
        Stack<Boolean> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(true);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
