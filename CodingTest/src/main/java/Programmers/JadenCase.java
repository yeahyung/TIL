package Programmers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12951
 */
public class JadenCase {

    @Test
    void JadenCaseTest() {
        assertEquals(solution("3people unFollowed me"), "3people Unfollowed Me");
        assertEquals(solution("for the last week"), "For The Last Week");
        assertEquals(solution("for the last  week"), "For The Last  Week");
        assertEquals(solution(" for the last  week"), " For The Last  Week");
        assertEquals(solution("  for the last  week"), "  For The Last  Week");
        assertEquals(solution("  3for the last  week"), "  3for The Last  Week");
        assertEquals(solution("  3for 3the last  week"), "  3for 3the Last  Week");
        assertEquals(solution("  3for 3the last  week "), "  3for 3the Last  Week ");
        assertEquals(solution("  3for 3the last  week  "), "  3for 3the Last  Week  ");
    }

    public String solution(String s) {
        StringBuilder answer = new StringBuilder(s.length());

        char[] chars = s.toLowerCase().toCharArray();
        boolean isFirstLetter = true;
        for (char character : chars) {
            if (isFirstLetter) {
                character = Character.toUpperCase(character);
            }
            answer.append(character);
            isFirstLetter  = Character.isWhitespace(character) ? true : false;
        }
        return answer.toString();
    }
}
