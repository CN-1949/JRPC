import example.Hello;
import rpc.RpcProxy;

public class Test2 {

    public static void main(String[] args) {
        long now;
        long old;

        Hello hello2 = RpcProxy.asynchronous(Hello.class);

        for (int i = 0; i < 1000_0000; i++) {
            now = System.nanoTime();
            hello2.say("Rpc");
            old = System.nanoTime() - now;
            System.out.println("asynchronous : " + old);
        }
    }
}