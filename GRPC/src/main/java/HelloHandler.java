import io.grpc.stub.StreamObserver;

public class HelloHandler extends HelloServiceGrpc.HelloServiceImplBase
{
    @Override
    public void greet(HelloRequest request, StreamObserver<HelloResponse> responseObserver)
    {
        System.out.println(request.getName() + " just sent " + request.getNumber());
        HelloResponse response = HelloResponse.newBuilder().setResponse("you what m8?").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
