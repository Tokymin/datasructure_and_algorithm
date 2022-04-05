package 字符串;
//给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，
//即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。

//示例 1：
//输入：s = "eleetminicoworoep"
//输出：13
//解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。

//示例 2：
//输入：s = "leetcodeisgreat"
//输出：5
//解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。

//示例 3：
//输入：s = "bcbcbc"
//输出：6
//解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。

//提示：
//1 <= s.length <= 5 x 10^5
//s 只包含小写英文字母。
//Related Topics 字符串

import java.util.HashMap;
import java.util.Map;

//要求最长子字符串，也就是一个最长子区间，那和我们上一题类似。在上一题中，我们的 count 变量是记录 0 比 1 多出来的次数，当区间 0 和 1 次数相等时，count 变量还是同一个值。类比到此题，我们希望有一个变量能够记录元音字母 a, e, i, o, u 出现的次数，并且希望其在元音字母恰好出现了偶数次的时候，这个变量的值能够和上一次相等。
//题目只是要求偶数次，偶数，即是 x % 2 == 0，那我们便可以想到利用二进制来存储：当出现一次(奇数次)时，我们记录为 1，出现两次(偶数次)时，我们记录为 0， 于是可以利用异或 ^ 来计算结果。由于要记录5个元音字母，那我们可以用5个二进制位来进行记录，所以这里的 count 范围为 00000-11111。当区间中元音字母都出现偶数次时，count 还是同一个结果。(代码中的 status 即为分析中的 count)
public class YuanYinOuShuMaxStr {
    public static class Solution {
        public int findTheLongestSubstring(String s) {
            //status 用来记录元音字母出现的奇偶性组合
            int maxlen = 0;
            int status = 0;
            //记录status第一次出现的位置
            Map hashmap = new HashMap();
            hashmap.put(0, -1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'a')
                    status ^= 1 << 0;
                else if (s.charAt(i) == 'e')
                    status ^= 1 << 1;
                else if (s.charAt(i) == 'i')
                    status ^= 1 << 2;
                else if (s.charAt(i) == 'o')
                    status ^= 1 << 3;
                else if (s.charAt(i) == 'u')
                    status ^= 1 << 4;
                if (hashmap.get(status) != null)
                    maxlen = Math.max(maxlen, i - (int) hashmap.get(status));
                else
                    hashmap.put(status, i);
            }
            return maxlen;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int res = s.findTheLongestSubstring("bcbcbc");
        System.out.println(res);
    }
}
