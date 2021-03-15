package invoke.overwriteandoverride.override;

import java.lang.reflect.Field;

public abstract class BaseOverride implements IBaseOverride {

    private OverrideObject overrideObject=new OverrideObject();

    @Override
    public int compare(Object o) throws Exception {
        try {
            for (Field field : overrideObject.getClass().getFields()) {
                Class c = field.getType();
                if (c.isInstance(o)) {
                    switch (c.getName()) {
                        case "int":
                            return Integer.compare(overrideObject.a, (Integer) o);
                        case "char":
                            return Character.compare(overrideObject.c, (Character) o);
                        default:
                            break;
                    }
                }
                throw new Exception("Not match");
            }
        }catch (Exception e){
            throw e;
        }

        return -2;
    }
}
