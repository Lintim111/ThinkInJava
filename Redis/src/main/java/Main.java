import RedisUtil.RedisClient;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Jedis redis = RedisClient.getClient("conf.yml");
        redis.set("hello","word");
        String value = redis.get("hello");
        System.out.println(value);
        System.out.println(redis.setnx("nx:key","nx:value"));
        System.out.println(redis.setnx("nx:key","nx:value"));
        lock(redis);
    }

    private static void lock(Jedis jedis) throws Exception {
        String script = "local rs=redis.call('setnx',KEYS[1],ARGV[1]);if(rs<1) then return 'F';end;redis.call('expire',KEYS[1],tonumber(ARGV[2]));return 'S';";
        List<String> keys=new ArrayList<>();
        keys.add("eval:1");
        List<String> args=new ArrayList<>();
        args.add("eval:args:1");
        args.add("10000");
        String sha1 = Encoder(script,"SHA-1");

        System.out.println(sha1+" is exist? "+jedis.scriptExists(String2Sha1(script)));
        if(!jedis.scriptExists(String2Sha1(script))){
            System.out.println(jedis.scriptLoad(script));//可以直接load来获取sha1
        }
        Object result = jedis.evalsha(sha1, keys, args);
        System.out.println(result);
    }

    private static String String2Sha1(String src) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] text = src.getBytes("utf-8");
        md.update(text);
        byte[] sha1 = md.digest(text);
        StringBuffer bf = new StringBuffer();
        for(int i=0;i<sha1.length;i++){
            int val=((int) sha1[i]) & 0xff;
            if(val<16){
                bf.append("0");
            }
            bf.append(Integer.toHexString(val));
        }
        return bf.toString().toLowerCase();
    }

    private static String Encoder(String data,String method) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance(method);

        md5.update(data.getBytes());
        byte[] md5Bytes = md5.digest();
        String res = "";
        for (int i = 0; i < md5Bytes.length; i++) {
            int temp = md5Bytes[i] & 0xFF;
            if (temp <= 0XF) { // 转化成十六进制不够两位，前面加零
                res += "0";
            }
            res += Integer.toHexString(temp);
        }
        return res;
    }
}
