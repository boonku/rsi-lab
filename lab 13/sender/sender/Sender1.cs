using System;
using System.Text;
using RabbitMQ.Client;
using System.Threading;
using Newtonsoft.Json;
using System.Xml.Linq;
using WcfClient;

public class Sender1
{
    private const int durationInSeconds = 5;
    private const string queue = "hello";
    private static Random random = new();


    public static void Main()
    {
        MyData.Info();
        var factory = new ConnectionFactory { HostName = "localhost" };
        // var factory = new ConnectionFactory { HostName = "IP", Port = 5672, UserName = "client", Password = "client", VirtualHost = "/"};
        using var connection = factory.CreateConnection();
        using var channel = connection.CreateModel();

        channel.QueueDeclare(queue: queue,
                     durable: false,
                     exclusive: false,
                     autoDelete: false,
                     arguments: null);

        DateTime endTime = DateTime.Now.AddSeconds(durationInSeconds);
        int counter = 1;
        while (DateTime.Now < endTime)
        {
            string message = JsonConvert.SerializeObject(new { message = "Szymon", time = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss"), counter = counter++ });
            var body = Encoding.UTF8.GetBytes(message);

            channel.BasicPublish(exchange: "",
                                 routingKey: queue,
                                 basicProperties: null,
                                 body: body);

            Console.WriteLine($"[x] Sent {message}");

            int sleep = random.Next(500, 1000);
            Thread.Sleep(sleep);
        }

        string endMarker = "end";
        var endMarkerBody = Encoding.UTF8.GetBytes(endMarker);
        channel.BasicPublish(exchange: string.Empty,
                             routingKey: queue,
                             basicProperties: null,
                             body: endMarkerBody);

        Console.WriteLine($"[x] Sent end marker '{endMarker}'");

        Console.WriteLine(" Press [enter] to exit.");
        Console.ReadLine();
    }
}

