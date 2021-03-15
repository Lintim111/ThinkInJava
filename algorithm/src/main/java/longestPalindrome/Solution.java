package longestPalindrome;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args){
        ArrayList<String> testStrs = new ArrayList<>();
        testStrs.add("abc");
        testStrs.add("babad");
        testStrs.add("cbbd");
        testStrs.add("aa");
        testStrs.add("ac");
        for(int i=0;i<testStrs.size();i++){
            String testStr = testStrs.get(i);
            System.out.println("origin: "+testStr+", match: "+longestPalindrome(testStr));
        }
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int firstMatchPos = 0;
        int count = 1;
        for (int startPos = 0; startPos < s.length(); startPos++) {
            int lastMatchCharPos = s.length() - 1;
            if (lastMatchCharPos - startPos + 1 <= count) {
                // no more larger
                break;
            }
            for (lastMatchCharPos = s.length() - 1; lastMatchCharPos > startPos; lastMatchCharPos--) {
                if (s.charAt(startPos) != (s.charAt(lastMatchCharPos))) {
                    continue;
                }
                if (lastMatchCharPos - startPos + 1 > count) {
                    boolean isReallyMatch = true;
                    for (int k = 1; k < (lastMatchCharPos - startPos) / 2 + 1; k++) {
                        if (s.charAt(startPos + k) != (s.charAt(lastMatchCharPos - k))) {
                            isReallyMatch = false;
                            break;
                        }
                    }
                    if (isReallyMatch) {
                        firstMatchPos = startPos;
                        count = lastMatchCharPos - startPos + 1;
                    }
                } else {
                    // break and set startPos++ to match next
                    break;
                }
            }
        }
        // subStr
        return s.substring(firstMatchPos, firstMatchPos + count);
    }
}
