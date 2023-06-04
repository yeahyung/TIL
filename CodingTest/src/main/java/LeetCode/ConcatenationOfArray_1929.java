package LeetCode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConcatenationOfArray_1929 {

	@Test
	public void concatenationOfArrayTest() {
		Assertions.assertArrayEquals(getConcatenation(new int[]{1,2,1}), new int[]{1,2,1,1,2,1});
		Assertions.assertArrayEquals(getConcatenation(new int[]{1,3,2,1}), new int[]{1,3,2,1,1,3,2,1});
	}

	public int[] getConcatenation(int[] nums) {
		int numsLength = nums.length;
		int[] ans = new int[numsLength * 2];
		for (int i = 0; i < numsLength; i++) {
			ans[i] = nums[i];
			ans[i + numsLength] = nums[i];
		}

		return ans;
	}

}
