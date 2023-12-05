package com.bxc.service.stats.rpc;

import io.grpc.stub.StreamObserver;

import com.bxc.service.stats.rpc.DataServiceGrpc;
import com.bxc.service.stats.rpc.gethelloworld.GetHelloWorldRequest;
import com.bxc.service.stats.rpc.gethelloworld.GetHelloWorldResponse;

public final class StatsService
    extends DataServiceGrpc.DataServiceImplBase {

  @Override
  public void getHelloWorldRpc(GetHelloWorldRequest request,
      StreamObserver<GetHelloWorldResponse> responseObserver) {
        GetHelloWorldResponse response = GetHelloWorldRpc.execute(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}