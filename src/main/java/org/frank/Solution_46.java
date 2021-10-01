package org.frank;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法-全排列
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
public class Solution_46 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> permute = new Solution_46().permute(nums);
        System.out.printf(JSON.toJSONString(permute));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums, track,result);
        return result;
    }

    /**
     * 回溯
     *  @param nums
     * @param track
     * @param result
     */
    private void backTrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> result) {
        //结束条件
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //过滤数据
            if (track.contains(nums[i])) {
                continue;
            }
            //做选择
            track.add(nums[i]);
            //回溯
            backTrack(nums, track, result);
            //撤销选择
            track.removeLast();
        }
    }
}
