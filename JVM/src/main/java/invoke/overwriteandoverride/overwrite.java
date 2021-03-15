package invoke.overwriteandoverride;

public class overwrite {
    private int a;
    private long b;
    private char c;
    private double d;

    private void init() {
        a = 1;
        b = 1L;
        c = 'a';
        d = 1d;
    }

    public overwrite() {
        init();
    }

    public overwrite(int a) {
        init();
        this.a = a;
    }

    public overwrite(long b) {
        init();
        this.b = b;
    }

    public overwrite(char c) {
        init();
        this.c = c;
    }

    public overwrite(double d) {
        init();
        this.d = d;
    }

    public int compare(int code) {
        return a > code ? 1 : (a < code ? -1 : 0);
    }

    public int compare(long code) {
        return b > code * 1.2 ? 1 : (b < code * 1.2 ? -1 : 0);
    }

    public int compare(char code) {
        return c > code ? 1 : (c < code ? -1 : 0);
    }

    public int compare(double code) {
        return d > code * 0.5 ? 1 : (d < code * 0.5 ? -1 : 0);
    }

    public static void main(String[] args) {
        overwrite object = new overwrite(1);
        long code=1L;
        object.compare(code);
        int code1=1;
        object.compare(code1);
    }
}
