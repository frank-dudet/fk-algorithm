package org.frank;

import java.util.Arrays;

/**
 * 动态规划-凑零钱问题
 */
public class Solution_322 {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.println(new Solution_322().coinChange(coins, amount));
    }
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        int[] dpTable = new int[amount + 1];
        Arrays.fill(dpTable, amount + 1);
        dpTable[0] = 0;
        for(int i=1; i<=amount;i++) {
            calcDpTable(i, dpTable, coins);
        }

        return dpTable[amount] > amount ? -1 : dpTable[amount];
    }

    public void calcDpTable(int amount, int[] dpTable, int[] coins) {
        for(int j=0;j< coins.length; j++) {
            if (amount - coins[j] >= 0) {
                dpTable[amount] = Math.min(dpTable[amount - coins[j]] + 1, dpTable[amount]);
            }
        }
    }
}
