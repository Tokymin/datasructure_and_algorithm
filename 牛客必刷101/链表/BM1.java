package 牛客必刷101.链表;

// 反转链表
class ListNode {
    protected int data;
    protected ListNode next;

    public ListNode(int data) {   //构造器来赋值
        this.data = data;
        this.next = null;
    }

    public ListNode() {
    }
}

public class BM1 {
    public class Solution1 {
        public ListNode ReverseList(ListNode head) {
            if (head == null) //处理空链表
                return null;
            ListNode cur = head;
            ListNode pre = null;
            while (cur != null) {
                ListNode temp = cur.next; //断开链表，要记录后续一个
                cur.next = pre; //当前的next指向前一个
                pre = cur; //前一个更新为当前
                cur = temp; //当前更新为刚刚记录的后一个
            }
            return pre;
        }
    }


    public class Solution2 {// 递归做法

        public ListNode ReverseList(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode newHead = ReverseList(head.next); //反转下一个
            head.next.next = head; //逆转
            head.next = null; //尾部设置空结点
            return newHead;
        }
    }
}
