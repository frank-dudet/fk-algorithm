package org.frank;

import java.util.Arrays;

public class Solution_322 {
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
