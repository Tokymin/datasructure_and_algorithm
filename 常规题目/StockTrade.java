package 常规题目;
//买卖股票的最好时机(三)
// 假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
//         1. 你最多可以对该股票有两笔交易操作，一笔交易代表着一次买入与一次卖出，但是再次购买前必须卖出之前的股票
//         2. 如果不能获取收益，请返回0
//         3. 假设买入卖出均无手续费
//
//         数据范围：，股票的价格满足
//         要求: 空间复杂度 ，时间复杂度
//         进阶：空间复杂度 ，时间复杂度

public class StockTrade {
    class Solution {
        public int maxProfit (int[] prices) {
            // 买卖两次，需要使用到三元数组
            if(prices.length == 0) {
                return 0;
            }
            // 0表示不持有，1表示持有
            // dp[天数][交易的次数][持有的状态]
            int[][][] dp = new int[prices.length][3][2];
            // 初始化
            for(int i = 0;i <= 2;i++) {
                // 不持有的时候 为0
                dp[0][i][0] = 0;
                // 持有的时候，为第一天的值
                dp[0][i][1] = -prices[0];
            }
            for(int i = 1;i < prices.length;i++) {
                for(int k = 0;k <= 2;k++) {
                    // 交易0次
                    if(k == 0) {
                        // 不可能的值，赋值为MIN_VALUE
                        dp[i][k][1] = Integer.MIN_VALUE;
                        dp[i][k][0] = 0;
                        continue;
                    }
                    dp[i][k][0] = Math.max(dp[i-1][k][0],dp[i-1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i-1][k][1],dp[i-1][k-1][0] - prices[i]);
                }
            }
            return dp[prices.length - 1][2][0];
        }
    }


}
