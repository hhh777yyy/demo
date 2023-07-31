package com.example.demo;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class TreeAlgorithm {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        Boolean fillLeft = false;

        Boolean fillRight = false;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static TreeNode createTree(int val, Integer leftVal, Integer rightVal) {
        TreeNode root = new TreeNode(val);
        if (Objects.nonNull(leftVal)) {
            root.left = new TreeNode(leftVal);
        }
        if (Objects.nonNull(rightVal)) {
            root.right = new TreeNode(rightVal);
        }
        return root;
    }


    /**
     * 前序遍历,中间节点->左->右 一棵树的根永远在左子树前面，左子树又永远在右子树前面
     * 左节点下有节点,就一直往下遍历,直到遍历到的左节点下面没有节点,再遍历右边
     */
    private static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        preorderTraversal(root, resultList);
        return resultList;
    }

    private static void preorderTraversal(TreeNode root, List<Integer> resultList) {
        if (Objects.isNull(root)) {
            return;
        }
        resultList.add(root.val);
        if (root.left != null) {
            preorderTraversal(root.left, resultList);
        }
        if (root.right != null) {
            preorderTraversal(root.right, resultList);
        }
    }

    /**
     * 中序遍历,左->中间节点->右?????? 一棵树的左子树永远在根前面，根永远在右子树前面
     */
    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        inorderTraversal(root, resultList);
        return resultList;
    }

    private static void inorderTraversal(TreeNode root, List<Integer> resultList) {
        if (root.left != null) {
            inorderTraversal(root.left, resultList);
        }
        resultList.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, resultList);
        }
    }

    /**
     * 后序便利,左->右->中间节点 一棵树的左子树永远在右子树前面，右子树永远在根前面
     */
    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        postorderTraversal(root, resultList);
        return resultList;
    }

    private static void postorderTraversal(TreeNode root, List<Integer> resultList) {
        if (root.left != null) {
            postorderTraversal(root.left, resultList);
        }

        if (root.right != null) {
            postorderTraversal(root.right, resultList);
        }
        resultList.add(root.val);
    }

    /**
     * 层序遍历
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> resList = new ArrayList<>();

        List<TreeNode> rootList = new ArrayList<>();

        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        resList.add(Arrays.asList(root.val));
        rootList.add(root);
        while (rootList != null && rootList.size() > 0) {
            List<Integer> levelValList = new ArrayList<>();
            List<TreeNode> nextRootList = new ArrayList<>();
            for (int i = 0; i < rootList.size(); i++) {
                TreeNode rootNode = rootList.get(i);
                if (rootNode.left != null) {
                    levelValList.add(rootNode.left.val);
                    nextRootList.add(rootNode.left);
                }
                if (rootNode.right != null) {
                    levelValList.add(rootNode.right.val);
                    nextRootList.add(rootNode.right);
                }
            }
            if (levelValList.size() > 0) {
                resList.add(levelValList);
            }
            rootList = nextRootList;
        }
        return resList;
    }

    /**
     * 翻转二叉树
     */
    private static TreeNode invertTree(TreeNode root) {
        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的
     * <p>
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }


        return isEqual(root.left, root.right);
    }

    /**
     * 对比左右子树是否相同
     */
    private static Boolean isEqual(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null && right != null) {
            return false;
        }
        if (left != null && right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        Boolean equal = isEqual(left.left, right.right);
        if (!equal) {
            return false;
        }
        equal = isEqual(left.right, right.left);
        return equal;
    }

    /**
     * 二叉树最大深度
     */

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return maxDepth(root, 1);
    }

    private static int maxDepth(TreeNode root, int currentDepth) {
        if (root == null) {
            return currentDepth - 1;
        }
        int leftMaxDepth = maxDepth(root.left, currentDepth + 1);
        int rightMaxDepth = maxDepth(root.right, currentDepth + 1);

        return Math.max(leftMaxDepth, rightMaxDepth);

    }

    /**
     * 高度、深度
     * 高度：从最下面的叶子节点到该节点的
     * 深度：从根节点到该节点的
     * https://blog.csdn.net/qq_45302622/article/details/103973286?ydreferer=aHR0cHM6Ly93d3cuYmluZy5jb20v
     */

    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        return minDepth(root.right, 2);
    }

    private static int minDepth(TreeNode root, int currentDepth) {
        if (root.left == null && root.right == null) {
            return currentDepth - 1;
        }
        int leftMaxDepth = minDepth(root.left, currentDepth + 1);
        if (root.right == null) {
            return leftMaxDepth;
        }
        int rightMaxDepth = minDepth(root.right, currentDepth + 1);

        return Math.min(leftMaxDepth, rightMaxDepth);

    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);
        return leftCount + rightCount + 1;
    }

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：一个二叉树【每个节点】 的左右两个子树的高度差的绝对值不超过1。
     *
     * 中左右     左右中
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftBalanced = isBalanced(root.left);
        boolean rightBalanced = isBalanced(root.right);
        if (leftBalanced==false || rightBalanced==false) {
            return false;
        }
        // 左子树高度
        int leftHigh = high(root.left);
        // 右子树高度
        int rightHigh = high(root.right);
        int highGap = leftHigh - rightHigh >= 0 ? leftHigh - rightHigh : rightHigh - leftHigh;
        if (highGap > 1) {
            return false;
        }

        return true;

        // return true/false
        // if 任意false，即false
    }

    public static int high(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHigh = high(root.left);
        int rightHigh = high(root.right);
        return Math.max(leftHigh, rightHigh) + 1;
    }

    /**
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> pathElementList = new ArrayList<>();
        List<String> resList = new ArrayList<>();
        binaryTreePaths(root, pathElementList, resList);
        return resList;
    }

    public static void binaryTreePaths(TreeNode root, List<String> pathElementList, List<String> resList) {
        if (root == null) {

            return;
        }
        // 放进pathElement
        pathElementList.add(String.valueOf(root.val));

        binaryTreePaths(root.left, pathElementList, resList);
        if (root.left == null && root.right == null) {
            resList.add(String.join("->", pathElementList));
            pathElementList.remove(pathElementList.size() - 1);
            return;
        }


        binaryTreePaths(root.right, pathElementList, resList);
        pathElementList.remove(pathElementList.size() - 1);

    }

    /**
     * 计算给定二叉树的所有左叶子之和。
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        List<Integer> leftList = new ArrayList<>();
        sumOfLeftLeaves(root, leftList);
        int sum = 0;
        for (Integer value : leftList) {
            sum = sum + value;
        }
        return sum;
    }


    public static void sumOfLeftLeaves(TreeNode root, List<Integer> leftList) {
        // root非空才递归
        if (root.left == null && root.right == null) {
            leftList.add(root.val);
        }
        if (root.left != null) {
            sumOfLeftLeaves(root.left, leftList);
        }
//        if (root.left == null && root.right == null) {
//            leftList.add(root.val);
//        }
        if (root.right != null) {
            sumOfLeftLeaves(root.right, leftList);
        }
    }

    /**
     * 层序遍历
     */
    public static List<List<Integer>> get(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> resultList = new ArrayList<>();
        ArrayDeque<TreeNode> currentQueue = new ArrayDeque<>();
        ArrayDeque<TreeNode> nextQueue = new ArrayDeque<>();

        currentQueue.offer(root);
        List<Integer> levelList = new ArrayList<>();
        while (!currentQueue.isEmpty()) {
            TreeNode pollNode = currentQueue.poll();
            levelList.add(pollNode.val);
            if (pollNode.left != null) {
                nextQueue.offer(pollNode.left);
            }
            if (pollNode.right != null) {
                nextQueue.offer(pollNode.right);
            }
            if (currentQueue.isEmpty()) {
                resultList.add(levelList);
                levelList = new ArrayList<>();
                currentQueue = nextQueue;
                nextQueue = new ArrayDeque<>();
            }
        }
        return resultList;
    }

    /**
     * 16.给定一个二叉树，在树的最后一行找到最左边的值。
     */
//    public static int findBottomLeftValue(TreeNode root) {
//
//    }

    /**
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例: 给定如下二叉树，以及目标和 sum = 22，
     */


    /**
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     *
     * 注意: 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3] 返回如下的二叉树：
     */

    /**
     * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
     *
     * 二叉树的根是数组中的最大元素。
     * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
     * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
     * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
     */

    /**
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     *
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     */




    private static TreeNode createTree(Integer[] nums) {
        TreeNode root = null;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        for (Integer num : nums) {
            TreeNode treeNode = null;
            TreeNode first = queue.peekFirst();
            // 放进队列,设置左右子树,左右子树都有了出队列
            if (num != null) {
                treeNode = new TreeNode(num);
                if (queue.isEmpty()) {
                    root = treeNode;
                }
                queue.addLast(treeNode);
            }
            if (first == null) {
                continue;
            }
            if (first.fillLeft && first.fillRight) {
                queue.removeFirst();
                first = queue.peekFirst();
                if (first == null) {
                    return root;
                }
            }

            if (!first.fillLeft) {
                first.left = treeNode;
                first.fillLeft = true;
                continue;
            }
            if (!first.fillRight) {
                first.right = treeNode;
                first.fillRight = true;
                continue;
            }
        }
        return root;
    }


    public static void main(String[] args) {
//        TreeNode root = createTree(5, 4, 6);
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(2);
//        root.right.left = new TreeNode(7);
//        root.right.right = new TreeNode(8);
//        List<Integer> integers = preorderTraversal(root);
//        List<Integer> integers1 = inorderTraversal(root);
//        List<Integer> integers2 = postorderTraversal(root);
//        List<List<Integer>> lists = levelOrder(root);
//        invertTree(root);

//        TreeNode root = createTree(1, 2, 2);
//        root.left.right = new TreeNode(3);
//        root.right.right = new TreeNode(3);

//        TreeNode rootNode = createTree(new Integer[]{5,4,1,null,1,null,4,2,null,2,null});
//        []
        TreeNode rootNode = createTree(new Integer[]{3,9,20,null,null,15,7});
//        boolean symmetric = isSymmetric(rootNode);
//        int i = minDepth(rootNode);
//        boolean balanced = isBalanced(rootNode);
//        List<String> strings = binaryTreePaths(rootNode);
//        int i = sumOfLeftLeaves(rootNode);
        List<List<Integer>> lists = get(rootNode);
        System.out.println();
    }
}
