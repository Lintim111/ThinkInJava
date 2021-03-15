package RedisUtil;

import RedisUtil.Factory.RedisConnectConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import utils.YamlUtil;


public class RedisClient {
    private static Logger logger= LoggerFactory.getLogger(RedisClient.class);
    private static JedisPool jedisPool;
    private String yamlPath;
    private void setJedisPool(){
        RedisConnectConfig config = YamlUtil.LoadFromYaml(new RedisConnectConfig(),YamlUtil.YamlStream(yamlPath));
        jedisPool = new JedisPool( null== config.getJedisPoolConfig()?new JedisPoolConfig():config.getJedisPoolConfig(),config.getHost(),config.getPort(),config.getTimeout(),config.getPassword());
    }

    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    private void setYamlPath(String yamlPath){
        this.yamlPath=yamlPath;
        setJedisPool();
    }

    public static Jedis getClient(String yamlPath){
        RedisClient client = new RedisClient();
        client.setYamlPath(yamlPath);
        return client.getJedis();
    }

    public static void close() {
        jedisPool.destroy();
    }

}
