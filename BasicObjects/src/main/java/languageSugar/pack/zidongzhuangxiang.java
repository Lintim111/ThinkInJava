package languageSugar.pack;

public class zidongzhuangxiang {
    public static void main(String[] args) {
        Integer a = 1; // Integer a = Integer.valueOf(1); 内存分配
        Integer b = 2;
        Integer c = 3;
        Integer d = 3; // Integer.valueOf(3)复用c的cache(类成员, static加载类时开辟数组空间)
        Integer e = 128;
        Integer f = 128;
        Long g = 3L;
        System.out.println(c == d); // true Integer.intVaule(c)==Integer.intVaule(d): error, 内存地址比较，相同内存
        System.out.println(e == f); // true ? why false 临界值128 10000000 8位byte? Integer.valueOf()当值-128 to 127时内存空间缓存复用, 超过后开辟新内存, 2块内存地址所以false

        System.out.println(c == (a + b)); // true Integer.intVaule(c)==(Integer.intVaule(a)+Integer.intVaule(b))

        System.out.println(c.equals(a + b)); // false, error: c.equals(Integer.valueOf(a.intValue() + b.intValue())) 值小于128,Integer内存空间相同
        System.out.println(g == (a+b)); //true
        System.out.println(g.equals(a + b)); //false
    }
}
