package springbootclient.demo;

import com.lql.HelloProto;
import com.lql.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

/**
 * @author liqiuliang
 * @create 2023-09-05 23:30
 */
public class HelloController {
    @GrpcClient("grpc-server")
    private static HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    public static void main(String[] args) {
        HelloProto.HelloResponse hello = helloServiceBlockingStub.hello(HelloProto.HelloRequest.newBuilder().build());
        System.out.println(hello);
    }
}
