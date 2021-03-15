package invoke.overwriteandoverride.override;

public class OverrideObject {
    public int a;
    public long b;
    public char c;
    public double d;
    public void init() {
        a = 1;
        b = 1L;
        c = 'a';
        d = 1d;
    }

    public OverrideObject() {
        init();
    }

    public OverrideObject(int a) {
        init();
        this.a = a;
    }
}
