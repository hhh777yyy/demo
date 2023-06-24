package com.example.demo;

import java.util.Arrays;

/**
 * @author hpy
 * @describtion
 * @since 2023-03-19
 */
public class ArrayAlgorithm {

    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (nums[0] != target) {
                return -1;
            } else {
                return 0;
            }
        }

        int leftIndex;// 5->2   0 1 2 3 4     4->1.5 0 1 2 3
        leftIndex = nums.length/2;

        int innerValue = nums[leftIndex];
        if (innerValue == target) {
            return leftIndex;
        }
        if (innerValue < target) {
            int[] rightNums = Arrays.stream(nums).skip(leftIndex+1).toArray();
            int search = search(rightNums, target);
            if (search == -1) {
                return -1;
            } else {
                return leftIndex + 1 + search;
            }
        } else  {// if (innerValue > target)
            int[] leftNums = Arrays.stream(nums).limit(leftIndex).toArray();
            return search(leftNums, target);
        }

    }

    public static int search2(int[] nums, int target) {// 5\7 中间是6 5、9中间是7  5、8中间是7  5\6\7\8
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {// 左闭右闭区间
            int mid = (left + right)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int[] delete(int[] nums, int target) {
        for (int i = 0;i<nums.length; i++) {
            if (nums[i] == target) {
                for (int j = i; j < nums.length -1; j++) {
                    nums[j] = nums[j+1];
                }
                i = i-1;// 重置遍历位点，防止少遍历
            }
        }
        return nums;
    }

    /**
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     *
     * 示例 1： 输入：nums = [-4,-1,0,3,10] 输出：[0,1,9,16,100] 解释：平方后，数组变为 [16,1,0,9,100]，排序后，数组变为 [0,1,9,16,100]
     * 示例 2： 输入：nums = [-7,-3,2,3,11] 输出：[4,9,9,49,121]
     *
     */
    public static int[] search3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] resultNums = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        while (left <= nums.length/2 || right > nums.length/2) {
            if (left <0 || right<0 || right-left<0) {
                break;
            }
            int numX = nums[left] * nums[left];
            int numY = nums[right] * nums[right];
            if (numX <= numY) {
                resultNums[right-left] = numY;
                right--;
            } else {
                resultNums[right-left] = numX;// left=0 right=5 right-left=5 left=0 right=4 right-left=4
                left++;
            }

        }
        for (int i=0; i < resultNums.length; i++) {// 双指针
            System.out.println(resultNums[i]);
        }
        return resultNums;
        //int right = nums.length - 1;
        //        int left = 0;
        //        int[] result = new int[nums.length];
        //        int index = result.length - 1;
        //        while (left <= right) {
        //            if (nums[left] * nums[left] > nums[right] * nums[right]) {
        //                // 正数的相对位置是不变的， 需要调整的是负数平方后的相对位置
        //                result[index--] = nums[left] * nums[left];
        //                ++left;
        //            } else {
        //                result[index--] = nums[right] * nums[right];
        //                --right;
        //            }
        //        }
        //        return result;
    }

    public static int[] search3Import(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;
        int[] resNums = new int[nums.length];
        while (left <= right) {
            int numX = nums[left] * nums[left];
            int numY = nums[right] * nums[right];
            if (numX <= numY) {
                resNums[index] = numY;
                right--;
            } else {
                resNums[index] = numX;
                left++;
            }
            index--;
        }
        for (int i=0; i < resNums.length; i++) {// 双指针
            System.out.println(resNums[i]);
        }
        return resNums;
    }

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
     *
     * 示例：
     * 输入：s = 7, nums = [2,3,1,2,4,3] 输出：2 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    public static Integer search4(int[] nums, int targetNum) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int targetLeft = 0, targetRight = 0;
        int result = nums.length;
        for (int left=0; left<nums.length; left++) {
            int sum = nums[left];
            if (sum >= targetNum) {
                result=1;
            }
            for (int right=left + 1; right<nums.length; right++) {
                sum = sum + nums[right];
                if (sum >= targetNum) {
                    int temp = right-left+1;
                    if (temp < result) {// 只记录长度最小值
                        result = temp;
                        targetLeft = left;
                        targetRight = right;
                    }
                    break;
                }
            }
        }
        System.out.println();
        return result;
    }

    /**
     * todo:https://programmercarl.com/0209.%E9%95%BF%E5%BA%A6%E6%9C%80%E5%B0%8F%E7%9A%84%E5%AD%90%E6%95%B0%E7%BB%84.html
     */
    public static Integer search4Import(int[] nums, int targetNum) {// left 遍历+1， right也一直往后走就行
        int result = nums.length;

        int left = 0;
        int sum = nums[left];
        if (sum >= targetNum) {
            return 1;
        }
        for (int right=1; right<nums.length; right++) {// left
            sum = sum + nums[right];
            if (sum >= targetNum) {
                int temp = right-left+1;
                if (temp < result) {// 只记录长度最小值
                    result = temp;
                }
                // left + 1;sum-nums[left]
                int tempLeft = left;
                left++;
                while ((sum = sum - nums[tempLeft])>= targetNum) {
                    temp = right-left+1;
                    if (temp < result) {// 只记录长度最小值
                        result = temp;
                    }
                    tempLeft = left;
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // nums = [-1,0,3,5,9,12], target = 2
//        int search = search2(new int[]{-1, 0, 3, 5, 9}, 0);
//        int[] delete = delete(new int[]{-1, 0, 0, 5, 9}, 0);

//        System.out.println(search3Import(new int[]{-4,-1,0,3,10}));
        System.out.println(search4Import(new int[]{2,3,1,2,4,3}, 7));
    }
}
