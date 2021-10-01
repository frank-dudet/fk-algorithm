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
public class Solution_51 {

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> permute = new Solution_51().solveNQueens(n);
        System.out.printf(JSON.toJSONString(permute));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }

        backTrack(board, 0, result);

        return result;
    }

    /**
     * 回溯
     * @param board
     * @param result
     */
    private void backTrack(char[][] board, int row, List<List<String>> result) {
        //结束条件
        if (row == board.length) {
            buildResult(board, result);
            return;
        }

        for (int i = 0; i < board.length; i++) {
            //过滤数据
            if (!isValidPos(board, row, i)) {
                continue;
            }
            //做选择
            board[row][i] = 'Q';
            //回溯
            backTrack(board, row + 1, result);
            //撤销选择
            board[row][i] = '.';
        }
    }

    /**
     * 该位置是否可放置
     *
     *
     * @param board
     * @param row
     * @param column
     * @return
     */
    private boolean isValidPos(char[][] board, int row, int column) {
        int length = board.length;
        //判断列
        for (int i = 0; i < length; i++) {
            if (board[i][column] == 'Q') {
                return false;
            }
        }
        //判断斜线(右上方)
        for (int i = row - 1, j = column + 1; i >= 0 && j < length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        //判断反斜线(左上方)
        for (int i = row - 1, j = column - 1; i >= 0 && j >=0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
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
