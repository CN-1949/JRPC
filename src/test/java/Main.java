import example.Hello;
import rpc.RpcProxy;

public class Main {

    public static void main(String[] args) {
        long now;
        long old;

        Hello hello = RpcProxy.synchronize(Hello.class);
        for (int i = 0; i < 1000_0000; i++) {
            now = System.nanoTime();
            hello.say("Rpc");
            old = System.nanoTime() - now;
            System.out.println("synchronize : " + old);
        }
    }
}