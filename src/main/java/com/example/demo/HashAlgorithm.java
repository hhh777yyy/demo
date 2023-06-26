package com.example.demo;

import java.util.*;

/**
 * @author hpy
 * @describtion
 * @since 2023-05-07
 */
public class HashAlgorithm {

    /**
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     * 四数之和
     */
    public int resolve(int[] A, int[] B, int[] C, int[] D) {// A、B、C、D length相同

        return 0;
    }

    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public static int[] resolveTwoSum(int[] nums, int target) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i =0; i<nums.length; i++) {
            int num = nums[i];
            Integer count = Objects.isNull(countMap.get(num)) ? 1 : countMap.get(num) + 1;
            countMap.put(num, count);
            indexMap.put(num, i);
        }

        for (int i =0; i<nums.length; i++) {
            int num = nums[i];
            Integer needNum = target-num;
            Map<Integer, Integer> newNumsMap = new HashMap<>();
            newNumsMap.putAll(indexMap);
            Integer count = countMap.get(num);

            if (count == 1) {// 例子{4, 5, 11, 15}, target=8，防止用4元素二次
                newNumsMap.remove(num);
            }
            if (Objects.nonNull(newNumsMap.get(needNum))) {
                return new int[]{i, indexMap.get(needNum)};
            }
        }


        return null;
    }

    /**
     * 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意： 答案中不可以包含重复的三元组。
     *
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
     */
    public static List<List<Integer>> resolveThreeSum(int[] nums) {
        // 先排序
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        int prev = nums[0];
        for (int i = 0; i<nums.length -1; i++) {
            int num = nums[i];
            if (num > 0) {
                continue;
            }
            if (i != 0 && num == prev) {
                continue;
            }
            int targetNum = 0 - num;
            int leftIndex = i+1;
            int rightIndex = nums.length-1;
            while (leftIndex < rightIndex) {
                int sum = nums[leftIndex] + nums[rightIndex];
                if (sum == targetNum) {
                    resultList.add(Arrays.asList(num, nums[leftIndex], nums[rightIndex]));
                    int temp = leftIndex;
                    leftIndex++;
                    while (nums[temp] == nums[leftIndex] && leftIndex < rightIndex) {
                        leftIndex++;
                    }
                }
                if (sum < targetNum) {
                    leftIndex++;
                } else {
                    rightIndex--;
                }

            }
            prev = nums[i];
        }
        return resultList;
    }

    public static List<List<Integer>>  resolve(int[] nums) {
        /**
         * 定义一个map，key为两数之和，value为该两数之和一共有几种方案（需要做去重）
         * key为a, value为b、c键值对
         *
         */
        Map<Integer, Map<Integer, Integer>> resultMap = new HashMap<>();
//        for (int i=0; i< nums.length-1; i++) {
//            for (int j=i+1; j< nums.length; j++) {
//                int twoSum = nums[i] + nums[j];
//                if (!twoSumMap.containsKey(twoSum)) {
//                    twoSumMap.put(twoSum, new HashMap<>());
//                }
//                Map<Integer, Integer> valueMap = twoSumMap.get(twoSum);
//                valueMap.put(nums[i], nums[j]);
//            }
//        }
//        // 去重
//        for (Integer key : twoSumMap.keySet()) {
//            Map<Integer, Integer> valueMap = twoSumMap.get(key);
//            for (Integer num1 : valueMap.keySet()) {
//                Integer num2 = valueMap.get(num1);
//                if (valueMap.containsKey(num2)) {
//                    valueMap.remove(num1);
//                }
//            }
//        }
        for (int i=0; i< nums.length-1; i++) {
            int num1 = nums[i];
            if (resultMap.containsKey(num1)) {
                continue;
            }
            // 两数之和
            Map<Integer, Integer> valueMap = new HashMap<>();
            for (int j=i+1; j< nums.length; j++) {
                // 两种情况：1、找到，2：没找到
                int num2 = nums[j];
                int num3 = 0 - (num1 + num2);
                // 需要对0做特殊处理
                if (valueMap.containsKey(num3)) {
                    valueMap.put(num2, num3);
                } else {
                    valueMap.put(num2, null);
                }
            }
            resultMap.put(num1, valueMap);

        }

        List<List<Integer>> result = new ArrayList<>();
        // 删除valueMap中值为null的键值对
        for (Integer num1 : resultMap.keySet()) {

            Map<Integer, Integer> valueMap = resultMap.get(num1);

            Iterator<Map.Entry<Integer, Integer>> it = valueMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> next = it.next();
                Integer num2 = next.getKey();
                Integer num3 = next.getValue();
                if (num3 == null) {
                    it.remove();
                }
            }
        }
        // 去重
        for (Integer num1 : resultMap.keySet()) {

            Map<Integer, Integer> valueMap = resultMap.get(num1);

            Iterator<Map.Entry<Integer, Integer>> it = valueMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> next = it.next();
                Integer num2 = next.getKey();
                Integer num3 = next.getValue();
                if (valueMap.containsKey(num3)) {
                    it.remove();
                }
            }
        }
        //
        Iterator<Map.Entry<Integer, Map<Integer, Integer>>> it = resultMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Map<Integer, Integer>> next = it.next();
            Integer num1 = next.getKey();
            Map<Integer, Integer> valueMap = next.getValue();
            for (Integer num2 : valueMap.keySet()) {// valueMap中有num1， key有valueMap中其中一个
                Integer num3 = valueMap.get(num2);
                if (num3 == num1 || num2 == num1) {
                    continue;
                }
                if ((resultMap.containsKey(num2) || resultMap.containsKey(num3)) && (num3 == num1 || num2 == num1)) {
                    it.remove();
                }
            }

        }

        for (Integer num1 : resultMap.keySet()) {
            Map<Integer, Integer> valueMap = resultMap.get(num1);
            for (Integer num2 : valueMap.keySet()) {
                Integer num3 = valueMap.get(num2);
                if (num3 != null) {
                    result.add(Arrays.asList(num1, num2, num3));
                }
            }
        }
        return result;
    }

    /**
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     *
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer>  abMap = new HashMap<>();
        Map<Integer, Integer> cdMap = new HashMap<>();
        for (int i=0; i<nums1.length; i++) {
            for (int j=0; j<nums2.length; j++) {
                int abSum = nums1[i] + nums2[j];
                int count = Objects.isNull(abMap.get(abSum)) ? 1 : abMap.get(abSum) + 1;
                abMap.put(abSum, count);
            }
        }
        for (int i=0; i<nums3.length; i++) {
            for (int j=0; j<nums4.length; j++) {
                int cdSum = nums3[i] + nums4[j];
                int count = Objects.isNull(cdMap.get(cdSum)) ? 1 : cdMap.get(cdSum) + 1;
                cdMap.put(cdSum, count);
            }
        }
        Integer count = 0;
        for (Integer abSum : abMap.keySet()) {
            int targetNum = 0 - abSum;
            Integer cdCount = cdMap.get(targetNum);
            if (Objects.nonNull(cdCount)) {
                Integer abCount = abMap.get(abSum);
                count = count + abCount * cdCount;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //A = [ 1, 2]
        //B = [-2,-1]
        //C = [-1, 2]
        //D = [ 0, 2]
//        List<List<Integer>> resolve = resolveThreeSum(new int[]{-1, 0, 1, 2, -1, -4});//-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6
//        List<List<Integer>> resolve = resolveThreeSum(new int[]{0,0,0,0});
        int i = fourSumCount(new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0});
        int[] twoSum = resolveTwoSum(new int[]{3,3}, 6);
        System.out.println();
    }
}
