package invoke.overwriteandoverride.override;

public class SimpleOverride extends BaseOverride implements IBaseOverride{
    private OverrideObject overrideObject;
    public SimpleOverride(){
        overrideObject=new OverrideObject(10);
    }
    @Override
    public int compare(Object o) throws Exception {
        int r= super.compare(o);
        if(r==-2)
            return -3;
        return r;
    }

    public static void main(String[] args){
        SimpleOverride simpleOverride=new SimpleOverride();
        int o=5;
        try {
            System.out.println(simpleOverride.compare(o));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
