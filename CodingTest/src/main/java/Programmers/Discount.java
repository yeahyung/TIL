package Programmers;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/131127
 */
public class Discount {

    @Test
    void discountTest() {
        assertEquals(
                solution(new String[]{"banana", "apple", "rice", "pork", "pot"},
                        new int[]{3, 2, 2, 2, 1},
                        new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"}),
                3);
        assertEquals(
                solution(new String[]{"apple"},
                        new int[]{10},
                        new String[]{"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"}),
                0);
    }

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        HashMap<String, Integer> salesMap = initializeSalesMap(discount);
        for (int day = 1; day <= discount.length - want.length; day ++) {
            // check if requirements are accepted
            if (checkRequirements(want, number, salesMap)) {
                answer++;
            }

            // else, refresh sales map
            refreshSalesMap(discount, salesMap, day - 1);
        }

        return answer;
    }

    private boolean checkRequirements(String[] want, int[] number, HashMap<String, Integer> salesMap) {
        for (int i=0; i<want.length; i++) {
            if (!salesMap.containsKey(want[i]) || salesMap.get(want[i]) < number[i]) {
                return false;
            }
        }
        return true;
    }

    private HashMap<String, Integer> initializeSalesMap(String[] discount) {
        HashMap<String, Integer> salesMap = new HashMap<>();

        for (int i=0; i<10; i++) {
            updateSalesMap(discount[i], salesMap);
        }
        return salesMap;
    }

    private void refreshSalesMap(String[] discount, HashMap<String, Integer> salesMap, int index) {
        salesMap.put(discount[index], salesMap.get(discount[index]) - 1);
        if (discount.length > index + 10) {
            updateSalesMap(discount[index + 10], salesMap);
        }
    }

    private void updateSalesMap(String want, HashMap<String, Integer> salesMap) {
        salesMap.put(want, salesMap.getOrDefault(want, 0) + 1);
    }
}
