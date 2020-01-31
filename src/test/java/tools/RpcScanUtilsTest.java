package tools;

import java.util.Arrays;

public class RpcScanUtilsTest {

    public static void main(String[] args) {
        RpcScanUtils.getBeans("example", rpc.RpcFace.class)
                    .stream()
                    .forEach(clazz -> {
                        Arrays.stream(clazz.getDeclaredMethods()).forEach(System.out::println);
                    });
    }
}
