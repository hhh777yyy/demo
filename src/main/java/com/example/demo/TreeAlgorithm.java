package com.example.demo;

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
        TreeNode root = createTree(5, 4, 6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);
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
        TreeNode rootNode = createTree(new Integer[]{2,null,3,null,4,null,5,null,6});
        boolean symmetric = isSymmetric(rootNode);
        int i = minDepth(rootNode);
        System.out.println();
    }
}
