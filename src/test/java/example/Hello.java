package example;

import rpc.RpcFace;

@RpcFace(HelloImp.class)
public interface Hello {
    String say(String name);
}
