package test.hw.q1.memory;

import java.util.Scanner;

public class Main {

    private static final int TOTALMEMEORYSIZE=100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int memoryStart=0;
        int memoryEnd=0;
        for(int i = 0; i < n; i++){
            String query = sc.nextLine();
            String[] queryStrs = query.split("=");
            String q=queryStrs[0];
            int size= Integer.parseInt(queryStrs[1]);
            // 来不及写了...
            if(q.equals("REQUEST")){
                //申请空间
                if(memoryStart>=TOTALMEMEORYSIZE||memoryStart+size>TOTALMEMEORYSIZE ) {
                    System.out.println("Err");
                    continue;
                }
                System.out.println(memoryStart);
                continue;
            }
            else if(q.equals("RELEASE")){
                //释放空间
                continue;
            }

        }
        System.out.println("");
    }
}
