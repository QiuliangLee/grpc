package com.example.demo;

import com.lql.HelloProto;
import com.lql.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author liqiuliang
 * @create 2023-09-05 1:12
 */
// 重复 多次
@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        String name = request.getName();
        System.out.println("name is " + name);

        responseObserver.onNext(HelloProto.HelloResponse.newBuilder().setResult("this is result").build());
        responseObserver.onCompleted();
    }
}
