package rpc;

import com.esotericsoftware.yamlbeans.YamlReader;
import io.netty.channel.nio.NioEventLoopGroup;
import network.RpcServer;
import site.RpcInstal;

import java.io.FileReader;
import java.net.URL;

/**
 * rpc 启动器
 */
public class RpcBoot {

    public static void run(Class<?> clazz) {
        try {
            ClassLoader classLoader = RpcBoot.class.getClassLoader();
            URL resource = classLoader.getResource("application.yml");
            if (resource == null) {
                throw new RpcException("Rpc configuration file is abnormal.");
            }

            String path = resource.getPath();
            YamlReader reader = new YamlReader(new FileReader(path));
            RpcInstal instal = reader.read(RpcInstal.class);
            if (instal == null) {
                throw new RpcException("Rpc configuration information is abnormal.");
            }

            RpcServer server =
                    new RpcServer(new NioEventLoopGroup(), new NioEventLoopGroup(), instal.getRpcServerPort());
            server.start();
            

        } catch (Exception e) {
            throw new RpcException(e);
        }
    }
}