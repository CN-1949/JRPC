package test;

import kryo.KryoUtil;
import message.RequestMessage;

public class KryoTest {

    public static void main(String[] args) {
        RequestMessage request = new RequestMessage();
        byte[] bytes = KryoUtil.writeToByteArray(request);

        RequestMessage o = KryoUtil.readFromByteArray(bytes);
    }
}