package org.frank;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回溯算法-N皇后
 * <p>
 *     for 选择 in 选择列表:
 *     # 做选择
 *     将该选择从选择列表移除
 *     路径.add(选择)
 *     backtrack(路径, 选择列表)
 *     # 撤销选择
 *     路径.remove(选择)
 *     将该选择再加入选择列表
 * </p>
 */
public class Solution_51_2 {

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> permute = new Solution_51_2().solveNQueens(n);
        System.out.printf(JSON.toJSONString(permute));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        //已经放置Q的列
        boolean[] usedColumn = new boolean[n];
        //已经放置Q斜线
        boolean[] usedSlash = new boolean[2 * n - 1];
        //已经放置Q反斜线
        boolean[] usedBackSlash = new boolean[2 * n - 1];

        backTrack(board, 0, usedColumn, usedSlash, usedBackSlash, result);

        return result;
    }

    /**
     * 回溯
     * @param board
     * @param usedColumn
     * @param usedSlash
     * @param usedBackSlash
     * @param result
     */
    private void backTrack(char[][] board, int row, boolean[] usedColumn, boolean[] usedSlash,
                           boolean[] usedBackSlash, List<List<String>> result) {
        //结束条件
        if (row == board.length) {
            buildResult(board, result);
            return;
        }

        for (int i = 0; i < board.length; i++) {
            //过滤数据
            if (usedColumn[i] || usedSlash[i + row] || usedBackSlash[i - row + board.length-1]) {
                continue;
            }
            //做选择
            board[row][i] = 'Q';
            usedColumn[i] = true;
            usedSlash[i + row] = true;
            usedBackSlash[i - row + board.length-1] = true;
            //回溯
            backTrack(board, row + 1, usedColumn, usedSlash, usedBackSlash, result);
            //撤销选择
            board[row][i] = '.';
            usedColumn[i] = false;
            usedSlash[i + row] = false;
            usedBackSlash[i - row + board.length-1] = false;
        }
    }


    /**
     * 组装返回结果
     *
     * @param board
     * @param result
     */
    private void buildResult(char[][] board, List<List<String>> result) {
        List<String> boardStr = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            boardStr.add(new String(board[i]));
        }
        result.add(boardStr);
    }
    
}
