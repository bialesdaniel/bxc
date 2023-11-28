package com.bxc.data.rpc;

import com.bxc.data.gethelloworld.GetHelloWorldRequest;
import com.bxc.data.gethelloworld.GetHelloWorldResponse;

public final class GetHelloWorldRpc {
  static public GetHelloWorldResponse execute(GetHelloWorldRequest request) {
    return GetHelloWorldResponse.newBuilder()
    .setMessage("Hello world!").build();
  }
}
