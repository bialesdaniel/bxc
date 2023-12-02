ARG PORT=9870
ARG PROTO_JAVA_VERSION=0.0.1-java-build

# Create a stage for the proto files.
FROM docker.io/bxcportal/proto/service/com/bxc/data:${PROTO_JAVA_VERSION} as proto

###############################################################################

FROM gradle:8.5.0-jdk21-alpine as base
# Copy build and source code into data service directory.
WORKDIR /service/data
COPY --chown=gradle:gradle . .

# Copy proto files into proto directory.
COPY --chown=gradle:gradle --from=proto . .
RUN ls 

# Reset working directory.
WORKDIR /

###############################################################################

# TEST
FROM base as test
WORKDIR /service/data
RUN gradle test

###############################################################################

# BUILD
FROM base as build
WORKDIR /service/data
# Run build.
RUN gradle build --no-daemon


###############################################################################

FROM eclipse-temurin:21-jre-jammy as prod
ARG PORT
# Set port env.
ENV PORT=${PORT}
# Expose port.
EXPOSE ${PORT}

# Copy jar.
COPY --from=build /service/data/build/libs/data.jar .
# Run service.
ENTRYPOINT ["java", "-jar", "data.jar"]

