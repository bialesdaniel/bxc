package com.bxc.data.rpc;

import io.grpc.stub.StreamObserver;

import com.bxc.data.DataServiceGrpc;
import com.bxc.data.gethelloworld.GetHelloWorldRequest;
import com.bxc.data.gethelloworld.GetHelloWorldResponse;

public final class DataService
    extends DataServiceGrpc.DataServiceImplBase {

  @Override
  public void getHelloWorldRpc(GetHelloWorldRequest request,
      StreamObserver<GetHelloWorldResponse> responseObserver) {
        GetHelloWorldResponse response = GetHelloWorldRpc.execute(request);
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}