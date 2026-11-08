package com.day.day_2026_11;

import com.fx.practice.utils.ListNode;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 * @author Eureka <liangfengyuan1024@gmail.com>
 * @since 2026/11/3 23:51
 */
public class D03 {

    @Test
    public void test01() {
        System.out.println(new D03().lengthOfLongestSubstring("abcabcd"));
        System.out.println(new D03().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new D03().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new D03().lengthOfLongestSubstring("a"));
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int maxLen = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                left = Math.max(left, map.get(ch) + 1);
            }
            map.put(ch, i);
            maxLen = Math.max(maxLen, i - left + 1);
        }
        return maxLen;
    }


    @Test
    public void test02() {
        System.out.println(new D03().longestPalindrome("babad"));
        System.out.println(new D03().longestPalindrome("cbbd"));
        System.out.println(new D03().longestPalindrome("pwwkew"));
        System.out.println(new D03().longestPalindrome("a"));
    }

    public String longestPalindrome(String s) {
        String maxStr = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            {
                String str = fetchLoop(s, chars, i, i);
                if (str.length() > maxStr.length()) {
                    maxStr = str;
                }
            }
            {
                String str = fetchLoop(s, chars, i, i + 1);
                if (str.length() > maxStr.length()) {
                    maxStr = str;
                }
            }
        }
        return maxStr;
    }

    private String fetchLoop(String s, char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    @Test
    public void test03() {
        System.out.println(new D03().maxVowels("abciiidef", 3));
        System.out.println(new D03().maxVowels("aeiou", 2));
        System.out.println(new D03().maxVowels("leetcode", 3));
        System.out.println(new D03().maxVowels("rhythms", 4));
        System.out.println(new D03().maxVowels("tryhard", 4));
        System.out.println(new D03().maxVowels("weallloveyou", 7));
        System.out.println(new D03().maxVowels("a", 1));
    }


    public int maxVowels(String s, int k) {
        int max = 0;
        int cnt = 0;
        char[] chars = s.toCharArray();
        Set<Character> set = Set.of('a', 'e', 'i', 'o', 'u');
        for (int i = 0; i < k; i++) {
            if (set.contains(chars[i])) {
                cnt++;
                max++;
            }
        }
        for (int i = 0; i < chars.length - k; i++) {
            // 将index = i 移除
            if (set.contains(chars[i])) {
                cnt--;
            }
            // 将 i + k加入
            if (set.contains(chars[i + k])) {
                cnt++;
            }
            if (cnt > max) {
                max = cnt;
            }
        }
        return max;
    }


    @Test
    public void test04() {
        System.out.println(reverseBetween(ListNode.build(new int[]{1, 2, 3, 4, 5}), 1, 1).toStr());
        System.out.println(reverseBetween(ListNode.build(new int[]{1, 2, 3, 4, 5}), 2, 4).toStr());
        System.out.println(reverseBetween(ListNode.build(new int[]{3, 5}), 2, 2).toStr());
        System.out.println(reverseBetween(ListNode.build(new int[]{3, 5}), 1, 2).toStr());
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode leftNode = dummyNode, rightNode = head;
        for (int i = 0; i < left - 1; i++) {
            leftNode = leftNode.next;
        }
        for (int i = 0; i < right - 1; i++) {
            rightNode = rightNode.next;
        }
        ListNode rightNodeNext = rightNode.next;
        rightNode.next = null;
        ListNode leftNodeNext = leftNode.next;
        leftNode.next = reverseList(leftNode.next);
        leftNodeNext.next = rightNodeNext;
        return dummyNode.next;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode post = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return post;
    }


    @Test
    public void test05() {
        System.out.println(reverseKGroup(ListNode.build(new int[]{1, 2, 3, 4, 5}), 2).toStr());
        System.out.println(reverseKGroup(ListNode.build(new int[]{1, 2, 3, 4, 5}), 3).toStr());
        System.out.println(reverseKGroup(ListNode.build(new int[]{1, 2}), 2).toStr());
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int listLength = 0;
        ListNode cur = head;
        while (cur != null) {
            listLength++;
            cur = cur.next;
        }
        ListNode dummyNode = new ListNode(-1, head);
        for (int i = 0; i < listLength && listLength - i >= k; i += k) {
            var p0 = dummyNode;
            var p1 = dummyNode.next;
            for (int j = 0; j < i; j++) {
                p0 = p0.next;
            }
            for (int j = 0; j < i + k - 1; j++) {
                p1 = p1.next;
            }
            var p1Next = p1.next;
            var p0Next = p0.next;
            p1.next = null;
            p0.next = reverseList(p0.next);
            p0Next.next = p1Next;
        }
        return dummyNode.next;
    }


    @Test
    public void test06() {
        System.out.println(mergeTwoLists(
                ListNode.build(new int[]{1, 2, 4}),
                ListNode.build(new int[]{1, 3, 4}))
        );

        System.out.println(mergeTwoLists(
                ListNode.build(new int[]{5}),
                ListNode.build(new int[]{1, 2, 4}))
        );
    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        var dummyNode = new ListNode();
        var result = dummyNode;
        while (!(list1 == null || list2 == null)) {
            if (list1.val < list2.val) {
                dummyNode.next = list1;
                list1 = list1.next;
            } else {
                dummyNode.next = list2;
                list2 = list2.next;
            }
            dummyNode = dummyNode.next;
        }
        if (list1 != null) {
            dummyNode.next = list1;
        }
        if (list2 != null) {
            dummyNode.next = list2;
        }
        return result.next;
    }

    @Test
    public void test07() {
        System.out.println(mergeKLists(
                new ListNode[]{
                        ListNode.build(new int[]{1, 4, 5}),
                        ListNode.build(new int[]{1, 3, 4}),
                        ListNode.build(new int[]{2, 6}),
                }
        ));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start < end) {
            int mid = (start + end) / 2;
            ListNode left = mergeHelper(lists, start, mid);
            ListNode right = mergeHelper(lists, mid + 1, end);
            return mergeTwoLists(left, right);
        }
        return null;
    }

    @Test
    public void test08() {
        System.out.println(removeNthFromEnd(ListNode.build(new int[]{1, 2, 3, 4, 5}), 2));
        System.out.println(removeNthFromEnd(ListNode.build(new int[]{1}), 1));
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        var dummyNode = new ListNode(-1, head);
        ListNode slow = dummyNode, fast = dummyNode;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummyNode.next;
    }

    @Test
    public void test09() {
        System.out.println(subarraySum(new int[]{1, 2, 3, 4, 5}, 2));
        System.out.println(subarraySum(new int[]{1, 2, 3}, 3));
        //
        System.out.println(subarraySum(new int[]{1, 2, 1, 2, 1}, 3));
        System.out.println(subarraySum(new int[]{-1, -1, 1}, 0));
    }

    public int subarraySum(int[] nums, int k) {
        int[] s = new int[nums.length + 1];
        s[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            s[i + 1] = s[i] + nums[i];
        }
        int result = 0;
        // 公式 arr[left] ... arr[right] = arr[0] ... arr[right] - arr[0] + arr[left-1]  = s[right+1] - s[left]
        Map<Integer, Integer> map = new HashMap<>(nums.length, 1);
        for (int sj : s) {
            result += map.getOrDefault(sj - k, 0);
            map.merge(sj, 1, Integer::sum); // map[sj]++
        }
        return result;
    }
}
