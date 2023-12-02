package com.bxc.data.rpc;

import com.bxc.data.rpc.gethelloworld.GetHelloWorldRequest;
import com.bxc.data.rpc.gethelloworld.GetHelloWorldResponse;

public final class GetHelloWorldRpc {
  static public GetHelloWorldResponse execute(GetHelloWorldRequest request) {
    return GetHelloWorldResponse.newBuilder()
    .setMessage("Hello world!").build();
  }
}
