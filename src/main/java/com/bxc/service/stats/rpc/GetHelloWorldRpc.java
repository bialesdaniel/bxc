package com.bxc.service.stats.rpc;

import com.bxc.service.stats.rpc.gethelloworld.GetHelloWorldRequest;
import com.bxc.service.stats.rpc.gethelloworld.GetHelloWorldResponse;

public final class GetHelloWorldRpc {
  static public GetHelloWorldResponse execute(GetHelloWorldRequest request) {
    return GetHelloWorldResponse.newBuilder()
    .setMessage("Hello world!").build();
  }
}
