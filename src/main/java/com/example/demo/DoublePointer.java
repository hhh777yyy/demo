package com.example.demo;

/**
 * @author hpy
 * @describtion
 * @since 2023-05-07
 */
public class DoublePointer {

    public static int remove(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        int size = nums.length;
        while (right >= left) {
            if (nums[right] == val) {
                right--;
                size--;
                continue;
            }
            if (nums[left] != val) {
                left++;
                continue;
            }
            int tempLeft = nums[left];
            int tempRight = nums[right];
            nums[left] = tempRight;
            nums[right] = tempLeft;
            size--;
            left++;
            right--;
        }
        return size;
    }

    public static void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (right >= left) {
            char tempLeft = s[left];
            char tempRight = s[right];
            s[left] = tempRight;
            s[right] = tempLeft;
            left++;
            right--;
        }
        System.out.println();
    }

    /**
     *
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode reverseHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            ListNode preReverseNode = reverseHead;
            reverseHead = new ListNode(head.val);
            reverseHead.next = preReverseNode;

            head = next;
        }
        return reverseHead;
    }

    /**
     * 删除倒数第n个节点
     * 问题：不知道链表有几个节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n){
        ListNode xx = head.next;
        ListNode cur = head;
        ListNode slow = null;
        ListNode fast = null;
        int i =0;
        while (cur != null) {
            fast = cur;
            i++;
            if (slow != null) {
                slow = slow.next;
            }
            if (i==n+1) {
                slow = head;
            }
            cur = cur.next;

        }
        if (slow != null) {
            slow.next = slow.next.next;
            return head;
        }  else {
                return xx;
        }


    }

    public static ListNode createListNode(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode pre = head;

        for (int i=1; i<nums.length-1; i++) {
            ListNode cur = new ListNode(nums[i]);
            ListNode next = new ListNode(nums[i+1]);
            cur.next = next;
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return null;
    }

    public static void main(String[] args) {
//        int remove = remove(new int[]{3, 2, 2, 3}, 3);
//        reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});
        ListNode listNode = createListNode(new int[]{1,2});
//        ListNode node = reverseList(listNode);
        ListNode node1 = removeNthFromEnd(listNode, 2);
        System.out.println();
    }
}
