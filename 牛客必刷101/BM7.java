package 牛客必刷101;
//链表中环的入口节点: 给定一个链表，首先判断其是否有环，然后找到环的入口


//那我们现在假定已经是一个有环的链表了，那么这个链表中怎么找到环的入口呢?，在慢指针进入链表环之前快指针已
//经进入了环，且在里面循环，这才能在慢指针进入环之后，快指针追到了慢指针，不妨假设快指针在环中走了n圈，慢
//指针在环中走了m圈，它们才相遇，而进入环之前的距离为x，环入口到相遇点的距离为y,相遇点到环入口的距离为2
//。快指针一共走了x +n(y+ z)+ y步,慢指针一共走了x + m(y+ z) + y,这个时候快指针走的倍数是慢指针
//的两倍，则x +n(y+z)+y= 2(x + m(y+z)+ y),这时候x +y= (n- 2m)(y + z),因为环的大小
//是y+z,说明从链表头经过环入口到达相遇地方经过的距离等于整数倍环的大小:那我们从头开始遍历到相遇位置,
//和从相遇位置开始在环中遍历，会使用相同的步数，而双方最后都会经过入口到相遇位置这y个节点，那说明这y个节
//点它们就是重叠遍历的，那它们从入口位置就相遇了，这我们不就找到了吗?

//●step1: 使用判断链表中是否有环中的方法判断链表是否有环，并找到相遇的节点。
//●step 2:慢指针继续在相遇节点，快指针回到链表头，两个指针同步逐个元素逐个元素开始遍历链表。
//●step 3:再次相遇的地方就是环的入口。

public class BM7 {
    public class Solution {
        public ListNode hasCycle(ListNode head) {
            if(head == null) //先判断链表为空的情况
                return null;
            ListNode fast = head; //快慢双指针
            ListNode slow = head;
            while(fast != null && fast.next != null){ //如果没环快指针会先到链表尾
                fast = fast.next.next; //快指针移动两步
                slow = slow.next; //慢指针移动一步
                if(fast == slow) //相遇则有环，返回相遇的位置
                    return slow;
            }
            return null; //到末尾说明没有环，返回null
        }


        public ListNode EntryNodeOfLoop(ListNode pHead) {
            ListNode slow = hasCycle(pHead);
            if(slow == null) //没有环
                return null;
            ListNode fast = pHead; //快指针回到表头
            while(fast != slow){ //再次相遇即是环入口
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }

    }
}
