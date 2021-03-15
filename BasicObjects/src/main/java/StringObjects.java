public class StringObjects {
    public static void main(String[] args) {
        String[] emptys = {null, "", " ", "   "};
        for (String s : emptys) {
            echoEmpty(s);
        }
        StringBuffer sb = new StringBuffer();
        sb.append("a");
        sb.append("b");
        System.out.println(sb.toString());
    }

    private static void echoEmpty(String s) {
        if (s == null || s.isEmpty()) {
            System.out.println(String.format("Empty String: %s", s));
        }
    }
}
