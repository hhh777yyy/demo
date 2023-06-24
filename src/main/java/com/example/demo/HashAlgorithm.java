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
     */
    public int[][] resolveTwoSum(int[] nums, int target) {
        return null;
    }

    /**
     * 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意： 答案中不可以包含重复的三元组。
     */
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

    public static void main(String[] args) {
        List<List<Integer>> resolve = resolve(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println();
    }
}
