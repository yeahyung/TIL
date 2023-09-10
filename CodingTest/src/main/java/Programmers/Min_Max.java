package Programmers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12939
 */
public class Min_Max {

    @Test
    void getMin_Max_Test() {
        Assertions.assertEquals(solution("1 2 3 4"), "1 4");
        Assertions.assertEquals(solution("-1 -2 -3 -4"), "-4 -1");
        Assertions.assertEquals(solution("-1 -1"), "-1 -1");
    }

    public String solution(String s) {
        String[] numsInString = s.split(" ");
        List<Integer> nums = Arrays.stream(numsInString).map(Integer::new).sorted().collect(Collectors.toList());

        return nums.get(0) + " " + nums.get(nums.size() - 1);
    }

}
