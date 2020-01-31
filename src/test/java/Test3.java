import example.Hello;
import example.HelloImp;

public class Test3 {

    public static void main(String[] args) {
        long now;
        long old;

        Hello hello3 = new HelloImp();
        for (int i = 0; i < 1000_0000; i++) {
            now = System.nanoTime();
            hello3.say("Rpc");
            old = System.nanoTime() - now;
            System.out.println("HelloImp : " + old);
        }
    }
}