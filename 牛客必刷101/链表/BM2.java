package 牛客必刷101.链表;
// 链表指定区间内反转
//将一个节点数为size链表m位置到n位置之间的区间反转
//链表其他部分不变，返回头节点


import 牛客必刷101.链表.ListNode;

public class BM2 {
    public class Solution1 {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode res = new ListNode(-1); //加个表头
            res.next = head;
            ListNode pre = res; //前序节点
            ListNode cur = head; //当前节点
            for (int i = 1; i < m; i++) { //找到m
                pre = cur;
                cur = cur.next;
            }
            for (int i = m; i < n; i++) { //从m反转到n
                ListNode temp = cur.next;
                cur.next = temp.next;
                temp.next = pre.next;
                pre.next = temp;
            }
            return res.next; //返回去掉表头
        }
    }

    public class Solution2 {// 递归的解法
        ListNode temp = null;

        public ListNode reverse(ListNode head, int n) {
            if (n == 1) { //只颠倒第一个节点，后续不管
                temp = head.next;
                return head;
            }
            ListNode node = reverse(head.next, n - 1); //进入子问题
            head.next.next = head; //反转
            head.next = temp; //拼接
            return node;
        }

        public ListNode reverseBetween(ListNode head, int m, int n) {
            if (m == 1) //从第一个节点开始
                return reverse(head, n);
            ListNode node = reverseBetween(head.next, m - 1, n - 1); //缩减子问题
            head.next = node; //拼接已翻转
            return head;
        }
    }
}
