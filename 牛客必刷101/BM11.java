package 牛客必刷101;
//两个链表生成相加链表
// 注意这里是像 做四位数五位数加法一样，还要考虑右对齐和进位的问题

//题目主要信息:
//    给定两个链表，每个链表中节点值都是0-9，每个链表就可以表示一个数字
//    将两个链表表示的数字相加，结果也存在链表中

//既然链表每个节点表示数字的每一-位,那相加的时候自然可以按照加法法则，从后往前依次相加。但是，链表是没有办
//法逆序访问的，这是我们要面对第一只拦路虎。解决它也很简单，既然从后往前不行，那从前往后总是可行的吧，将两
//个链表反转一下，即可得到个十百....各个数字从前往后的排列，相加结果也是个位在前，怎么办?再次反转，结果
//不就正常了。
//●step1: 任意-一个链表为空，返回另一个链表就行了，因为链表为空相当于0，0加任何数为0，包括另一个加数为
//0的情况。
//●step2:相继反转两个待相加的链表，反转过程可以参考反转链表。
//●step3:设置返回链表的链表头，设置进位carry=0.
//●step4:从头开始遍历两个链表，直到两个链表节点都为空且carry也不为1.每次取出不为空的链表节点值，为空
//就设置为0，将两个数字与carry相加，然后查看是否进位，将进位后的结果(对10取模)加入新的链表节点，连
//接在返回链表后面，并继续往后遍历。
//●step5:返回前将结果链表再反转回来。

public class BM11 {
    public class Solution {
        public ListNode ReverseList(ListNode pHead) { //反转链表
            if(pHead == null)
                return null;
            ListNode cur = pHead;
            ListNode pre = null;//增加表头节点
            while(cur != null){
                ListNode temp = cur.next; //断开链表，要记录后续一个
                cur.next = pre; //当前的next指向前一个
                pre = cur; //前一个更新为当前
                cur = temp; //当前更新为刚刚记录的后一个
            }
            return pre;
        }
        public ListNode addInList (ListNode head1, ListNode head2) {
            if(head1 == null) //任意一个链表为空，返回另一个
                return head2;
            if(head2 == null)
                return head1;
            head1 = ReverseList(head1); //反转两个链表
            head2 = ReverseList(head2);
            ListNode res = new ListNode(-1); //添加表头
            ListNode head = res;
            int carry = 0; //进位符号
            while(head1 != null || head2 != null || carry != 0){ //只要某个链表还有或者进位还有
                int val1 = head1 == null ? 0 : head1.data; //链表不为空则取其值
                int val2 = head2 == null ? 0 : head2.data;
                int temp = val1 + val2 + carry; //相加
                carry = temp / 10; //获取进位
                temp %= 10;
                head.next = new ListNode(temp); //添加元素
                head = head.next;
                if(head1 != null) //移动下一个
                    head1 = head1.next;
                if(head2 != null)
                    head2 = head2.next;
            }
            return ReverseList(res.next); //结果反转回来
        }
    }
}
