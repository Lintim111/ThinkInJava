package test.hw.q1.test2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String firstLine = in.nextLine();
        String pattern = ".*(([0-9]+,[0-9]+))+.*";
        //正则匹配提取(x,y)坐标再做比较
        if (!firstLine.matches(pattern)) {
            System.out.println("(0,0)");
        } else {
            String[] strs = firstLine.split(pattern);
            int maxPairX = 0, maxPairY = 0;
            for (int i = 0; i < strs.length; i++) {
                //抽取x,y计算最大距离
                int x = 0, y = 0;

                if (x * x + y * y > maxPairX * maxPairX + maxPairY + maxPairY) {
                    maxPairX = x;
                    maxPairY = y;
                }
            }
            System.out.println("(" + maxPairX + "," + maxPairY + ")");
        }
    }
}
