package 递归_回溯;

public class LatinNumber2 {
    /* 拉丁数字放置问题 */

    public static int[][] Latin=new int[4][4];
    static int count = 0;
    static boolean canPlace(int row, int colum, int value){
        for(int i=0; i<row; i++)
            if(Latin[i][colum] == value)
                return false;
        for(int j=0; j<colum; j++)
            if(Latin[row][j] == value)
                return false;
        return true;

    }
    static void backTrace(int level)
    {
        if(level == 17)
        {//得到一组可行解
            ++count;
            System.out.print("可行解"+count+":  <");
            for(int i=1; i<4; i++)
            {
                for(int j=0; j<4; j++)
                {
                    System.out.print(Latin[i][j]+", ");
                }
            }
            System.out.println(">");

        }
        else
        {
            int r = (level-1)/4; // 取行row
            int c = (level-1)%4; // 取列colum

            for(int j=1; j<=4; j++)
            {
                if(canPlace(r, c, j))
                {
                    Latin[r][c] = j;
                    backTrace(level+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<4; i++)
        {
            Latin[0][i] = i+1;
        }

        backTrace(5); //求解第5层开始的可行解，即第1,2,3,4已经确定。

        System.out.println("可行解总数："+count);
    }




}
