package com.bxc.data;

import static org.junit.Assert.assertEquals;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.bxc.data.rpc.gethelloworld.GetHelloWorldRequest;
import com.bxc.data.rpc.gethelloworld.GetHelloWorldResponse;
import com.bxc.data.rpc.DataService;
import com.bxc.data.rpc.DataServiceGrpc;

@RunWith(JUnit4.class)
public class DataServerTest {
  @Rule
  public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

  @Test
  public void greeterImpl_replyMessage() throws Exception {
    // Generate a unique in-process server name.
    String serverName = InProcessServerBuilder.generateName();

    // Create a server, add service, start, and register for automatic graceful shutdown.
    grpcCleanup.register(InProcessServerBuilder
        .forName(serverName).directExecutor().addService(new DataService()).build().start());

    DataServiceGrpc.DataServiceBlockingStub blockingStub = DataServiceGrpc.newBlockingStub(
        // Create a client channel and register for automatic graceful shutdown.
        grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));


    GetHelloWorldResponse response =
        blockingStub.getHelloWorldRpc(GetHelloWorldRequest.getDefaultInstance());

    assertEquals("Hello world!", response.getMessage());
  }
}