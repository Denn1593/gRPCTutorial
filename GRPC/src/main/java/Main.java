import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        if(args.length > 0 && args[0].equals("server"))
        {
            createServer();
        }
        else
        {
            createClient();
        }
    }

    public static void createServer()
    {
        try
        {
            Server server = ServerBuilder.forPort(50051).addService(new HelloHandler()).build().start();
            System.out.println("server started");
            server.awaitTermination();

        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void createClient()
    {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:50051").usePlaintext().build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder().setName("bob").setNumber(69).build();

        System.out.println("saying hello to server");

        HelloResponse response = stub.greet(request);

        System.out.println("Server said: " + response.getResponse());
    }
}
