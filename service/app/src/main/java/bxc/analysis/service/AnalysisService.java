package bxc.analysis.service;

import io.grpc.stub.StreamObserver;
import bxc.analysis.service.getservicestatus.GetServiceStatusRequest;
import bxc.analysis.service.getservicestatus.GetServiceStatusResponse;

public final class AnalysisService
    extends AnalysisServiceGrpc.AnalysisServiceImplBase {

  @Override
  public void getServiceStatusRpc(GetServiceStatusRequest request,
      StreamObserver<GetServiceStatusResponse> responseObserver) {
        GetServiceStatusResponse response = GetServiceStatusResponse.newBuilder()
        .setIsActive(true).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
