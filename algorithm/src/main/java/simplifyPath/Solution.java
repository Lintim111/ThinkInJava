package simplifyPath;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        ArrayList<String> strs = new ArrayList<>();
        strs.add("/a/../../b/../c//.//");
        strs.add("/../");
        strs.add("/home//foo/");
        strs.add("/a/./b/../../c/");
        strs.add("/home/");
        strs.add("/home");

        for (int i = 0; i < strs.size(); i++) {
            String str = strs.get(i);
            System.out.println("origin: " + str + ", target: " + simplifyPath(str));
        }

    }

    public static String simplifyPath(String path) {
        for (int i = 1; i < path.length() - 1; i++) {
            if (path.charAt(i) != '.' && path.charAt(i) != '/')
                continue;
            String subStr = path.substring(i, i + 2);
            if (subStr.equals("./")) {
                path = path.substring(0, i) + path.substring(i + 2);
                i = i - 1;
            } else if (subStr.equals("..")) {
                int leftPost=i+3;
                if(leftPost>path.length()){
                    leftPost = i+2;
                }
                int last = i;
                for (int j = i - 2; j >= 0; j--) {
                    if (path.charAt(j) == '/') {
                        last = j;
                        break;
                    }
                }
                if (i - last <= 1) {
                    path = path.substring(0, i) + path.substring(leftPost);
                    i = i - 1;
                } else {
                    path = path.substring(0, last + 1) + path.substring(leftPost);
                    i = last;
                }
            } else if (subStr.equals("//")) {
                path = path.substring(0, i) + path.substring(i + 1);
                i = i - 1;
            }
        }
        int finalPos = path.length() - 1;
        while (finalPos > 1 && path.charAt(finalPos) == '/') {
            finalPos--;
        }
        path = path.substring(0, finalPos+1);
        return path;
    }

}
