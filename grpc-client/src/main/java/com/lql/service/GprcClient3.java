package com.lql.service;

import com.lql.HelloProto;
import com.lql.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
 * @author liqiuliang
 * @create 2023-09-03 19:10
 */
public class GprcClient3 {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        try {
            HelloServiceGrpc.HelloServiceBlockingStub helloService = HelloServiceGrpc.newBlockingStub(managedChannel);

            HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
            builder.setName("sunshuai");
            HelloProto.HelloRequest helloRequest = builder.build();
            Iterator<HelloProto.HelloResponse> helloResponseIterator = helloService.c2ss(helloRequest);
            while (helloResponseIterator.hasNext()) {
                HelloProto.HelloResponse helloResponse = helloResponseIterator.next();
                System.out.println("helloResponse.getResult() = " + helloResponse.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            managedChannel.shutdown();
        }
    }
}