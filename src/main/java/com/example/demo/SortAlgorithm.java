package com.example.demo;

import java.util.Arrays;

/**
 * 排序算法：冒泡、快排、归并、堆排序、
 */
public class SortAlgorithm {

    /**
     * 冒泡：因为每次将相邻的元素比较，大小于预期不符则交换这两个元素
     */
    public static int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        Integer size = arr.length - 1;
        for (int i = 0; i<= size;) {
            int pre = i;
            for (int j = i + 1; j<=size; j++) {
                if (arr[pre] > arr[j]) {
                    swap(arr, pre, j);
                }
                pre = j;

            }
            size--;
        }
        return arr;
    }

    /**
     * 选择排序：内层循环取得最小（最大）值的下标，相邻两个数比较，取最小的下标保存。
     * 外层循环是排序（依次找出最小，其次小等值）排除内层循环之后的剩余元素再次执行内层循环。
     * 选择排序相比于冒泡排序法，关键点是减少交换次数。
     */
    public static int[] selectSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i=0; i <= arr.length - 1; i++) {
            int index = i;
            int min = arr[i];
            for (int j = i + 1; j <= arr.length -1 ; j++) {
                if (arr[j] < min) {
                    index = j;
                    min = arr[j];
                }
            }
            if (index != i) {
                swap(arr, i, index);
            }
        }
        return arr;
    }

    /**
     * 插入排序：首先分成两个序列（一个有序，一个无序），再将无序序列插入到有序序列中的适当位置是的被插入序列仍然有序，例如下：（左侧有序，右侧无序）
     */
    public static int[] insertSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0; i < arr.length - 1; i++) {
            int dealIndex= i+1;
            for (int j = i; j >= 0; j--) {
                if (arr[dealIndex] >= arr[j]) {
                    break;
                }
                swap(arr, dealIndex, j);
                dealIndex = j;
            }
        }
        return arr;
    }

    /**
     * 递归排序
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, left, k);
    }

    /**
     * 希尔排序
     * @param arr
     */
    public static void shellSort(int[] arr) {

    }

    /**
     * 堆排序一个深度为h的二叉树，除了第h层以外的（1~n-1）层的节点数达到最大，即除第h层以外是一个满二叉树（深刻为k，节点数为2
     *  ）
     */

    /**
     * 快速排序
     */
    public static void quickSort(int[] arr) {
//        while ()
        quickSort(arr, 0, arr.length-1);
        System.out.println();
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

//    public static void quickSort(int[] arr, Integer left, Integer right) {
//        if (right == 0 || left == arr.length) {
//            return;
//        }
//        int index = partition(arr, left, right);
//        if (left < index - 1) {
//            quickSort(arr, left, index - 1);
//        }
//        if (right > index) {
//            quickSort(arr, index+1, right);
//        }
//
//    }

//    public static int partition(int[] arr, Integer left, Integer right) {
//        int index = left;
//        while (left <= right) {
//            while (left < right && arr[right] >= arr[index]) {
//                right--;
//            }
//            if (right <= left) {
//                return index;
//            }
//            index = right;
//            swap(arr, left, right);
//
//            while (left < right && arr[left] <= arr[index]) {
//                left++;
//            }
//            if (right <= left) {
//                return index;
//            }
//            index = left;
//            swap(arr, left, right);
//        }
//        return index;
//    }


    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {

        int[] arr = {1, 7, 4, 6, 9, 3, 2};
        mergeSort(arr);
        quickSort(arr);
    }
}
