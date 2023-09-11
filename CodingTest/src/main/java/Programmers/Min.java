package Programmers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Min {

    @Test
    void minTest() {
        assertEquals(solution(new int[]{1, 4, 2}, new int[]{5, 4, 4}), 29);
        assertEquals(solution(new int[]{1, 2}, new int[]{3, 4}), 10);
    }

    public int solution(int []A, int []B)
    {
        int answer = 0;
        int length = A.length;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i=0; i<length; i++) {
            answer += A[i] * B[length -1 -i];
        }

        return answer;
    }
}
