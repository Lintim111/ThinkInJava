package StringLength;

import java.util.Scanner;

public class StringLength {

    private static int countLastStringLength(String originStr) {
        if (originStr.length() == 0) {
            return 0;
        }
        int pos = originStr.lastIndexOf(' ');
        return originStr.length() - (pos + 1);
    }

    protected static void test1() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(countLastStringLength(line));
    }
    /*------ ------*/

    private static void splitAndfull_8(String originStr) {
        int limit = 8;
        int length = originStr.length();
        if (length <= 0) {
            System.out.println(originStr);
            return;
        }
        for (int i = 0; i < (length / limit + (length % limit > 0 ? 1 : 0)); i++) {
            if ((i + 1) * limit > length) {
                String full = "";
                for (int j = 0; j < (limit - length % limit); j++) {
                    full += "0";
                }
                System.out.println(originStr.substring(i * limit) + full);
            } else {
                System.out.println(originStr.substring(i * limit, (i + 1) * limit));
            }
        }

    }

    protected static void test2() {
        Scanner scanner = new Scanner(System.in);
        int lineCount = 2;
        for (int i = 0; i < lineCount; i++) {
            String line = scanner.nextLine();
            splitAndfull_8(line);
        }
    }

    public static void main(String[] args) {
        //test1();
        test2();
    }


}
