package 牛客必刷101.链表;
//判断一个链表是否为回文结构

//题目的主要信息:
//●给定一个链表的头节点，判读该链表是否为回文结构
//●回文结构即正序遍历与逆序遍历结果都是一 样的，类似123321
//●空链表默认为回文结构
//方法-:数组复制反转法(前置知识)

import 牛客必刷101.链表.ListNode;

import java.util.Stack;

//具体做法:
//即然回文结构正序遍历和逆序遍历结果都是一样的，我们是不是可以尝试将正序遍历的结果与逆序遍历的结果一一比
//较，如果都是对应的，那很巧了!它就是回文结构!
//这道题看起来解决得如此之快，但是别高兴太早，链表可没有办法逆序遍历啊。链表由前一个节点的指针指向后一个节
//点，指针是单向的，只能从前面到后面，我们不能任意访问，也不能从后往前。但是，另一个容器数组，可以任意访
//问，我们把链表中的元素值取出来放入数组中，然后判断数组是不是回文结构，这不是一样的吗?
//●step1: 遍历-次链表,将元素取出放入辅助数组中。
//●step2:准备另一个辅助数组，录入第一个数组的全部元素，再将其反转。
//●step 3:依次遍历原数组与反转后的数组，若是元素都相等则是回文结构，只要遇到一个不同的就不是回文结构。
public class BM13 {


    //第二种方法
    //方法二:数组复制双指针(前置知识)
    //具体做法:
    //既然方法一我们已经将链表的值放入了数组中，数组是可以按照下标直接访问的，那千啥还要傻乎乎地用另一个数组来
    //表示反转后的数组呢?我们直接从后往前遍历与从前往后遍历一同比较，不就少了很多额外的时间了吗?
    //●step1: 遍历一次链表，将元素取出放入辅助数组中。
    //●step2: 使用下标访问，两个下标代表两个指针，两个指针分别从数组首尾开始遍历，左指针指向开头，从左到
    //右，右指针指向数组末尾，从右到左，依次比较元素是否相同。
    //●step3: 如果有不一-样，则不是回文结构。否则遍历到两个指针相遇就好了，因为左指针到了右半部分都是右指针
    //走过的路，比较的值也是与之前相同的。


    // 第三种方法
    //方法三:长度法找中点(推荐使用)
    //具体做法:
    //在数组中，我们可以借助双指针，一个从前往遍历前-一半数组，另一个从后往前遍历后-半数组，依次比较值。链表中
    //如果我们要用这样的思想，左指针从前往后很容易，直接的链表的遍历就可以了。但是右指针是真的没有办法从尾巴往
    //前走，要是链表后半段的指针是逆序的就好了。
    //怎么样能让链表后半段的指针反过来，将后半段链表整体反转不就行了吗?如果我们将后半段链表整体反转,那么相当
    //于后半段就是从末尾指向中间，就可以实现后半段的逆序遍历一一按照指针直接走就可以了。
    //●step1: 遍历链表，统计链表的长度。
    //●step2: 将长度除2，遍历这么多位置，找到链表的中点。
    //●step3: 从中点位置开始，对链表后半段进行反转。
    //●step4:与方法二类似，双指针左指针指向链表开头，右指针指向链表尾，此时链表前半段是正常的，我们也可以
    //正常遍历，但是链表后半段所有指针都被我们反转逆序，因此我们可以从尾节点往前遍历。
    //●step 5:依次比较对应位置的元素值是否相等。
    public class Solution3 {
        ListNode reverse(ListNode head) { //反转链表指针
            ListNode prev = null; //前序节点
            while (head != null) {
                ListNode next = head.next; //断开后序
                head.next = prev; //指向前序
                prev = head;
                head = next;
            }
            return prev;
        }

        public boolean isPail(ListNode head) {
            ListNode p = head;
            int n = 0;
            while (p != null) { //找到链表长度
                n++;
                p = p.next;
            }
            n = n / 2; //中点
            p = head;
            while (n > 0) { //遍历到中点位置
                p = p.next;
                n--;
            }
            p = reverse(p);  //中点处反转
            ListNode q = head;
            while (p != null) {
                if (p.data != q.data) //比较判断节点值是否相等
                    return false;
                p = p.next;
                q = q.next;
            }
            return true;
        }
    }


    //方法四:双指针找中点(推荐使用)
    //具体做法:
    //上述方法四找中点，我们遍历整个链表找到长度，又遍历长度一半找中点位置。过程过于繁琐，我们想想能不能优化一
    //下，一次性找到中点。
    //我们首先来看看中点的特征，一个链表的中点，距离链表开头是一半的长度，距离链表结尾也是- -半的长度，那如果从
    //链表首遍历到链表中点位置，另-一个每次遍历两个节点的指针是不是就到了链表尾，那这时候我们的快慢双指针就登场
    //了:
    //●step1: 慢指针每次走一个节点，快指针每次走两个节点，快指针到达链表尾的时候，慢指针刚好到了链表中点。
    //●step 2:从中点的位置，开始往后将后半段链表反转。
    //●step3: 按照方法四的思路，左右双指针，左指针从链表头往后遍历，右指针从链表尾往反转后的前遍历，依次比
    //较遇到的值。
    public class Solution4 {
        ListNode reverse(ListNode head) { //反转链表指针
            ListNode prev = null; //前序节点
            while (head != null) {
                ListNode next = head.next; //断开后序
                head.next = prev; //指向前序
                prev = head;
                head = next;
            }
            return prev;
        }

        public boolean isPail(ListNode head) {
            if (head == null) //空链表直接为回文
                return true;
            ListNode slow = head; //准备快慢双指针
            ListNode fast = head;
            while (fast != null && fast.next != null) { //双指针找中点
                slow = slow.next;
                fast = fast.next.next;
            }
            slow = reverse(slow);  //中点处反转
            fast = head;
            while (slow != null) {
                if (slow.data != fast.data) //比较判断节点值是否相等
                    return false;
                fast = fast.next;
                slow = slow.next;
            }
            return true;
        }
    }


    //方法五:栈逆序(扩展思路)
    //具体做法:
    //同样的，逆序访问我们不一-定需要借助可以随机访问的数组，或者反转链表，我们还可以借助先进先出的栈:根据链表
    //顺序入栈的元素，越在前面的就越在栈底，越在后面的就越在栈顶，因此后续从栈中弹出的元素，依次就是链表的逆
    //序。
    //●step1: 遍历链表，将链表元素依次加入栈中。
    //●step 2:依次从栈中弹出元素值，和链表的顺序遍历比较，如果都是一- -比较相同的值，那正好就是回文，否则就
    //不是。
    public class Solution5 {

        public boolean isPail(ListNode head) {
            ListNode p = head;
            Stack<Integer> s = new Stack();
            while (p != null) { //辅助栈记录元素
                s.push(p.data);
                p = p.next;
            }
            p = head;
            while (!s.isEmpty()) { //正序遍历链表，从栈中弹出的内容是逆序的
                if (p.data != s.pop()) //比较是否相同
                    return false;
                p = p.next;
            }
            return true;
        }
    }
}
