package org.frank;

import java.util.HashMap;
import java.util.Map;

public class Solution_322_1 {

    public int coinChange(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        Map<Integer,Integer> dpTable = new HashMap<>();
        dpTable.put(0, 0);
        for(int i=1; i<=amount;i++) {
            calcDpTable(i, dpTable, coins);
        }

        if(dpTable.containsKey(amount)) {
            return dpTable.get(amount);
        }

        return -1;
    }

    public void calcDpTable(int amount, Map<Integer, Integer> dpTable, int[] coins) {
        for(int j=0;j< coins.length; j++) {
            int coin = coins[j];
            int index = amount - coin;
            if(index < 0) {
                continue;
            }
            if (!dpTable.containsKey(index)) {
                continue;
            }
            if (dpTable.containsKey(amount)) {
                dpTable.put(amount, Math.min(dpTable.get(index) + 1, dpTable.get(amount)));
                continue;
            }
            dpTable.put(amount, dpTable.get(index) + 1);
        }



    }
}
