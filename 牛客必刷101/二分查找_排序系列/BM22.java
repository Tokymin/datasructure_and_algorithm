package 牛客必刷101.二分查找_排序系列;
// 比较版本号
//题目的主要信息:
//●给出2个版本号version1和version2， 比较它们的大小,例如 1.02.11 和1.02.010
//●版本号是由修订号组成，修订号与修订号之间由一个"."连接，需要比较的是每一节
//●修订号可能有前导0,按从左到右的顺序依次比较它们的修订号，比较修订号时，只需比较忽略任何前导零后的整数值
//●如果版本号没有指定某个下标处的修订号，则该修订号视为0
//●版本号中每一节可能超过int的表达范围

//方法1: 遍历直接截取(推荐使用)
//具体做法:
//既然是比较两个字符串每个点之间的数字是否相同，就直接遍历字符串比较，但是数字前导零不便于我们比较，因为我，
//们不知道后面会出现多少前导零，因此应该将点之间的部分转化为数字再比较才方便。

//●step1: 利用两个指针表示字符串的下标，分别遍历两个字符串。
//●step2: 每次截取点之前的数字字符组成数字，即在遇到一个点之前，直接取数字，加在前面数字乘10的后面。
//(因为int会溢出，这里采用long记录数字) .
//●step 3:然后比较两个数字大小，根据大小关系返回1或者-1，如果全部比较完都无法比较出大小关系，则返回0.

public class BM22 {
    public class Solution1 {
        public int compare (String version1, String version2) {
            int n1 = version1.length();
            int n2 = version2.length();
            int i = 0, j = 0;
            while(i < n1 || j < n2){//直到某个字符串结束
                long num1 = 0;
                while(i < n1 && version1.charAt(i) != '.'){ //从下一个点前截取数字  // 字符串取值是用charAt函数
                    num1 = num1 * 10 + (version1.charAt(i) - '0');// 取到的数字每次会*10 以表示进位
                    i++;
                }
                i++; //跳过点
                long num2 = 0;
                while(j < n2 && version2.charAt(j) != '.'){ //从下一个点前截取数字
                    num2 = num2 * 10 + (version2.charAt(j) - '0');
                    j++;
                }
                j++; //跳过点
                if(num1 > num2) //比较数字大小
                    return 1;
                if(num1 < num2)
                    return -1;
            }
            return 0; //版本号相同
        }
    }

    //方法二:分割截取(思路扩展)

    //具体做法:
    //既然方法一都是每次以点为界限，将字符转换为数字，那我们是不是可以尝试提前就把它们分割好呢?分割也不难，可
    //以借助Java的split函数直接按照点为间隔划分开。C++没有这么方便的split函数了，但是我们还有流输入
    //istringstream,只需要用一个字符型变量承接点，其他部分就是逐渐输入数组中。
    //●step1: 使用split函数或者字符串流输入，按照点将两个原始字符串分割，使每个修订号的数字单独呈现在数组
    //中。
    //●step2:遍历数组，每次各自取出一个数字比较，较短的版本号没有可取的数字了，就直接取0。
    //●step3:遍历取出的数字字符串，将其转换成数字，然后比较数字大小。根据大小关系返回1或者-1，如果全部比
    //较完都无法比较出大小关系，则返回0.
    public class Solution2 {
        public int compare (String version1, String version2) {
            String[] nums1 = version1.split("\\."); //按照.划分
            String[] nums2 = version2.split("\\.");
            for(int i = 0; i < nums1.length || i < nums2.length; i++){
                String str1 = i < nums1.length ? nums1[i] : "0"; //较短的版本号后续都取0
                String str2 = i < nums2.length ? nums2[i] : "0";
                long num1 = 0;
                for(int j = 0; j < str1.length(); j++) //字符串转数字
                    num1 = num1 * 10 + (str1.charAt(j) - '0');
                long num2 = 0;
                for(int j = 0; j < str2.length(); j++)
                    num2 = num2 * 10 + (str2.charAt(j) - '0');
                if(num1 > num2) //比较数字大小
                    return 1;
                if(num1 < num2)
                    return -1;
            }
            return 0;
        }
    }
}
