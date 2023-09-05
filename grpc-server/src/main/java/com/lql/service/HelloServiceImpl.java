package com.lql.service;

import com.lql.HelloProto;
import com.lql.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author liqiuliang
 * @create 2023-09-01 2:09
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    /* 1、简单RPC(一元RPC)
      1. 接受client提交的参数  request.getParameter()
      2. 业务处理 service+dao 调用对应的业务功能。
      3. 提供返回值
     */
    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        //1.接受client的请求参数
        String name = request.getName();
        //2.业务处理
        System.out.println("name parameter " + name);
        //3.封装响应
        //3.1 创建相应对象的构造者
        HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
        //3.2 填充数据
        builder.setResult("hello method invoke ok");
        //3.3 封装响应
        HelloProto.HelloResponse helloResponse = builder.build();

        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }

    /**
     * 2、服务端流式RPC
     * @param request
     * @param responseObserver
     */
    public void c2ss(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        //1 接受client的请求参数
        String name = request.getName();
        //2 做业务处理
        System.out.println("name = " + name);
        //3 根据业务处理的结果，提供响应
        for (int i = 0; i < 9; i++) {
            HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
            builder.setResult("处理的结果 " + i);
            HelloProto.HelloResponse helloResponse = builder.build();

            responseObserver.onNext(helloResponse);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        responseObserver.onCompleted();
    }

    /**
     * 3、客户端流式RPC
     * IOT(物联网 【传感器】) 向服务端 发送数据
     * @param responseObserver
     * @return
     */
    public StreamObserver<HelloProto.HelloRequest> cs2s(StreamObserver<HelloProto.HelloResponse> responseObserver) {
        return new StreamObserver<HelloProto.HelloRequest>() {
            @Override
            public void onNext(HelloProto.HelloRequest value) {
                System.out.println("接受到了client发送一条消息 " + value.getName());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("client的所有消息 都发送到了 服务端 ....");

                //提供响应：响应的目的：当接受了全部client提交的信息，并处理后，提供相应
                HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
                builder.setResult("this is result");
                HelloProto.HelloResponse helloResponse = builder.build();

                responseObserver.onNext(helloResponse);
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * 4、双向流式RPC
     * @param responseObserver
     * @return
     */
    public StreamObserver<HelloProto.HelloRequest> cs2ss(StreamObserver<HelloProto.HelloResponse> responseObserver) {
        return new StreamObserver<HelloProto.HelloRequest>() {
            @Override
            public void onNext(HelloProto.HelloRequest value) {
                System.out.println("接受到client 提交的消息 "+value.getName());
                responseObserver.onNext(HelloProto.HelloResponse.newBuilder().setResult("response "+value.getName()+" result ").build());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("接受到了所有的请求消息 ... ");
                responseObserver.onCompleted();
            }
        };
    }
}
