package 牛客必刷101.链表;
//单链表的排序:给定一个无序链表，要将其排序为升序链表

//前面我们做合并两个有序链表不是使用归并思想吗?说明在链表中归并排序也不是不可能使用，合并阶段可以参照前面
//这道题，两个链表逐渐取最小的元素就可以了，但是划分阶段呢?
//常规数组的归并排序，是将数组从中间个元素开始划分，然后将划分后的子数组作为-一个要排序的数组，再将排好序的
//两个子数组合并成一个完整的有序数组，因此采用的是递归。而链表中我们也可以用同样的方式，只需要找到中间个元
//素的前一个节点，将其断开，就可以将链表分成两个子链表，然后继续划分，直到最小，然后往上依次合并。

//●step1: 首先判断链表为空或者只有一个元素，直接就是有序的。
//●step2:准备三个指针，快指针right每次走两步，慢指针mid每次走一 步，前序指针left每次跟在mid前一个位
//置。三个指针遍历链表，当快指针到达链表尾部的时候，慢指针mid刚好走了链表的一半，正好是中间位置。
//●step3:从left位置将链表断开，刚好分成两个子问题开始递归。

//。终止条件:当子链表划分到为空或者只剩一个节点时，不再继续划分，往上合并。
//。返回值:每次返回两个排好序且合并好的子链表。
//。本级任务:找到这个链表的中间节点，从前面断开，分为左右两个子链表，进入子问题排序。

import 牛客必刷101.链表.ListNode;

import java.util.ArrayList;
import java.util.Collections;

//●step 4:将子问题得到的链表合并，参考合并两个有序链表。
public class BM12 {
    public class Solution1 {
        ListNode merge(ListNode pHead1, ListNode pHead2) { //合并两段有序链表
            if(pHead1 == null) //一个已经为空了，直接返回另一个
                return pHead2;
            if(pHead2 == null)
                return pHead1;
            ListNode head = new ListNode(0); //加一个表头
            ListNode cur = head;
            while(pHead1 != null && pHead2 != null){ //两个链表都要不为空
                if(pHead1.data <= pHead2.data){ //取较小值的结点
                    cur.next = pHead1;
                    pHead1 = pHead1.next; //只移动取值的指针
                }else{
                    cur.next = pHead2;
                    pHead2 = pHead2.next; //只移动取值的指针
                }
                cur = cur.next; //指针后移
            }
            if(pHead1 != null) //哪个链表还有剩，直接连在后面
                cur.next = pHead1;
            else
                cur.next = pHead2;
            return head.next; //返回值去掉表头
        }



        public ListNode sortInList (ListNode head) {
            if(head == null || head.next == null) //链表为空或者只有一个元素，直接就是有序的
                return head;
            ListNode left = head;
            ListNode mid = head.next;
            ListNode right = head.next.next;
            //右边的指针到达末尾时，中间的指针指向该段链表的中间
            while(right != null && right.next != null){
                left = left.next;
                mid = mid.next;// 走1步
                right = right.next.next;// 走两步
            }
            left.next = null; //左边指针指向左段的左右一个节点，从这里断开
            //分成两段排序，合并排好序的两段
            return merge(sortInList(head), sortInList(mid));
        }
    }




//链表最难受的就是不能按照下标访问，只能逐个遍历，那像排序中常规的快速排序、堆排序都不能用了，只能用依次遍
//历的冒泡排序、选择排序这些。但是这些O(n2 )复杂度的排序方法太费时间了，我们可以将其转化成数组后再排序。
//●step1: 遍历链表，将节点值加入数组。
//●step2:Java或者C++内置的排序函数对数组进行排序。
//●step3:依次遍历数组和链表,按照位置将链表中的节点值修改为排序后的数组值。
    public class Solution2 {
        public ListNode sortInList (ListNode head) {
            ArrayList<Integer> nums = new ArrayList();
            ListNode p = head;
            while(p != null){ //遍历链表，将节点值加入数组
                nums.add(p.data);
                p = p.next;
            }
            p = head;
            Collections.sort(nums); //对数组元素排序
            for(int i = 0; i < nums.size(); i++){ //遍历数组
                p.data = nums.get(i); //将数组元素依次加入链表
                p = p.next;
            }
            return head;
        }
    }
}
