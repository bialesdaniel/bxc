package com.bxc.service.stats;

import static org.junit.Assert.assertEquals;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.bxc.service.stats.rpc.gethelloworld.GetHelloWorldRequest;
import com.bxc.service.stats.rpc.gethelloworld.GetHelloWorldResponse;
import com.bxc.service.stats.rpc.DataService;
import com.bxc.service.stats.rpc.DataServiceGrpc;

@RunWith(JUnit4.class)
public class StatsServerTest {
  @Rule
  public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

  @Test
  public void statsServer_getHelloWorld_sendsResponse() throws Exception {
    // Generate a unique in-process server name.
    String serverName = InProcessServerBuilder.generateName();

    // Create a server, add service, start, and register for automatic graceful shutdown.
    grpcCleanup.register(InProcessServerBuilder
        .forName(serverName).directExecutor().addService(new StatsService()).build().start());

    StatsServiceGrpc.StatsServiceBlockingStub blockingStub = StatsServiceGrpc.newBlockingStub(
        // Create a client channel and register for automatic graceful shutdown.
        grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));


    GetHelloWorldResponse response =
        blockingStub.getHelloWorldRpc(GetHelloWorldRequest.getDefaultInstance());

    assertEquals("Hello world!", response.getMessage());
  }
}