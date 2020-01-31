package site;

import java.util.List;

/**
 * rpc 配置初始化
 */
public class RpcInstal {

    private int rpcServerPort = 8080;
    private List<RpcRegistrationCenter> rpcRegistrationCenters;

    public int getRpcServerPort() {
        return rpcServerPort;
    }

    public void setRpcServerPort(int rpcServerPort) {
        this.rpcServerPort = rpcServerPort;
    }

    public List<RpcRegistrationCenter> getRpcRegistrationCenters() {
        return rpcRegistrationCenters;
    }

    public void setRpcRegistrationCenters(List<RpcRegistrationCenter> rpcRegistrationCenters) {
        this.rpcRegistrationCenters = rpcRegistrationCenters;
    }
}