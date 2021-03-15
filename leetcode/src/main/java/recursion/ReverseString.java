package recursion;

import java.util.ArrayList;
import java.util.List;

public class ReverseString implements ITestRunner {

    private List<char[]> TestCases;

    @Override
    public void initTestCases() {
        TestCases = new ArrayList<>();
        TestCases.add(new char[]{'h', 'e', 'l', 'l', 'o'});
        TestCases.add(new char[]{'h', 'e', 'n', 'n', 'l', 'o'});
        TestCases.add(new char[]{});

        TestCases.add(new char[]{'h'});
        TestCases.add(new char[]{'h', 'a'});
        TestCases.add(new char[]{'a', 'b', 'c'});
        TestCases.add(new char[]{'H', 'a', 'n', 'n', 'a', 'h'});
    }

    @Override
    public void run() {
        initTestCases();
        for (char[] caseOne : TestCases
        ) {
            System.out.println(caseOne);
            reverseString(caseOne);
            System.out.println(caseOne);
            reverseString2(caseOne);
            System.out.println(caseOne);
            reverseString3(caseOne);
            System.out.println(caseOne);

        }
    }

    private void reverseString(char[] s) {
        if (s.length < 2)
            return;
        char temp;
        for (int i = 0; i < s.length / 2; i++) {
            temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }

    }

    private void reverseString2(char[] s) {
        subReverse(0, s.length - 1, s);
    }

    private void subReverse(int start, int end, char[] s) {
        if (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            subReverse(++start, --end, s);
        }
    }

    private void reverseString3(char[] s) {
        char tmp = 'a';
        helper(0, tmp, s);
    }

    private void helper(int index, char tmp, char[] str) {
        if (str == null || index >= str.length) {
            return;
        }
        helper(index + 1, tmp, str);
        tmp = str[index];
        str[str.length - 1 - index] = str[index];
        str[str.length - 1 - index] = tmp;
    }
}
