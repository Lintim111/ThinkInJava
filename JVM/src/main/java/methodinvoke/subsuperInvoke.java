package methodinvoke;

import methodinvoke.sub.subclass;

public class subsuperInvoke {
    public static void main(String[] args){
        subclass sub1 = new subclass();
        // unable to invoke protected method out package
        // sub1.defaultInvoke();
        // sub1.protectedInvoke();
        sub1.publicInvoke();
    }
}
