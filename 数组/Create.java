package 数组;
import java.util.Arrays;
import java.util.Scanner;

class initArr {
    public int[] oneDimention() {
        int[] res = null;
        //创建数组的第一种方法
        int[] arr = new int[6];// 总共6个元素，但是下标只到5这里
        int intValue = arr[5];
        //创建数组的第二种方法
        int[] x = {1, 2, 3, 4};
        //创建数组的第三种方法。
        int[] y = new int[]{1, 2, 3, 4, 5};
        return res;

    }

    public int[] inputoneDimention(int len) {
        int[] res = new int[len];
        int i = 0; //计数用的
        Scanner scanner = new Scanner(System.in);
        int data = scanner.nextInt();
        while (data != 999) {              //输入999结束
            res[i] = data;
            data = scanner.nextInt();
            i++;
        }
        return res;
    }


}

class operation {
    //判断数组下标是否越界
    public static boolean isLength(int m, int arr[]) { // m 为下标， arr是传入的数组
        boolean flag = false;
        int length = arr.length;
        if (m < length)
            flag = true;
        return flag;
    }
}


public class Create {
    public static void main(String[] args) {
        initArr ia = new initArr();
        int len=10;
        int[] res = ia.inputoneDimention(len); // 初始化一维数组的方法,这里传入一个长度len
        System.out.println(Arrays.toString(res));
    }
//————————————————
//    版权声明：本文为CSDN博主「@ ^ @」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/jdbfvhxx/article/details/95377739
}
