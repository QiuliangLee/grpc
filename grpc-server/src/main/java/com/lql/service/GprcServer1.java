package com.lql.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author liqiuliang
 * @create 2023-09-01 2:18
 */

public class GprcServer1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        //1. 绑定端口
        ServerBuilder serverBuilder = ServerBuilder.forPort(9000);
        //2. 发布服务
        serverBuilder.addService(new HelloServiceImpl());
        //serverBuilder.addService(new UserServiceImpl());
        //3. 创建服务对象
        Server server = serverBuilder.build();

        server.start();
        server.awaitTermination();;
    }
}