ARG PROTO_FILE_BASE_PATH=proto/build/generated/source/proto/main
ARG JAVA_OUTPUT_DIR=${PROTO_FILE_BASE_PATH}/java
ARG JAVA_GRPC_OUTPUT_DIR=${PROTO_FILE_BASE_PATH}/grpc

FROM gradle:8.5.0-jdk21-alpine as base
RUN apk update \
  && apk upgrade \
  && apk add protoc=3.21.12-r2 gcompat=1.1.0-r1
WORKDIR /proto
COPY . .
RUN ls
RUN gradle build --no-daemon
RUN ls build/generated/source/proto/main

###############################################################################

FROM scratch as java-build
ARG JAVA_OUTPUT_DIR
ARG JAVA_GRPC_OUTPUT_DIR
COPY --from=base $JAVA_OUTPUT_DIR /proto/java
COPY --from=base $JAVA_GRPC_OUTPUT_DIR /proto/grpc