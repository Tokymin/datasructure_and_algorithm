package 递归_回溯;
import java.util.*;
//给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
//        例如，给出n=3，解集为：
//        "((()))", "(()())", "(())()", "()()()", "()(())"
//数据范围1<n<8
public class NC26 {
    // 我的尝试递归解法
    public ArrayList<String> generateParenthesis(int n) {
        // write code here
        List<String> res = new ArrayList<String>();
        if (n <= 1) {
            res.add("()");
            return (ArrayList<String>) res;
        }

        res = generateParenthesis(n - 1);//先递归
        for (int i = 0; i <= res.size(); i++) {
            String strings = res.get(i);
//            for (int j=0;j<strings.length();j++){
            StringBuffer b = new StringBuffer(strings);
            res.add(b.toString());
//            }
        }
        return (ArrayList<String>) res;
    }

    //别人的递归做法
    public ArrayList<String> generateParenthesisothers(int n) {
        ArrayList<String> arr = new ArrayList<>();
        if (n == 0) return null;
        //合法括号 不以)开头，不以(结尾
        //即()开头结尾
        //消去几个（）后，仍应该保持这个性质,比如())不行
        //一个合法序列插入另一个合法序列任意位置仍然合法
        //问题转化成，从一个括号开始，用其他括号慢慢插
        String init = "()";
        if (n == 1) {
            arr.add(init);
            return arr;
        }
        HashSet<String> set = new HashSet<>();
        ArrayList<String> lastRes = generateParenthesis(n - 1);
        for (String item : lastRes) {
            //可插入空位个数枚举,2(n-1)+1=2n-1
            for (int i = 0; i < 2 * n - 1; i++) {
                String prefix; // 这种插入方式值得借鉴
                if (i == 0) prefix = "";
                else prefix = item.substring(0, i);

                String end;
                if (i == 2 * n - 2) end = "";
                else end = item.substring(i);
                set.add(prefix + "()" + end);
            }
        }
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            arr.add(it.next());
        }
        return arr;
    }

}



