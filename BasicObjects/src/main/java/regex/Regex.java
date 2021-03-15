package regex;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String args[]){
        String s = String.format(
                "conversion kind %s failed, status %s", "pspdfkit",
                "protected_file");

        Pattern pattern = Pattern.compile( "failed, status (.+)");
        Matcher matcher = pattern.matcher(s);
        if(matcher.find()){
            System.out.println(matcher.group(1));
        }
        Set<String> set = new HashSet<String>();
        String ignore_failed_queue_status_string = "too_large,extension_not_correct,not_support,empty,protected_file";

        for(String keyword : ignore_failed_queue_status_string.split(",")) {
            keyword = keyword.trim();
            if (keyword != null && keyword != "") {
                set.add(keyword);
            }
        }
        System.out.println(set);
    }
}
