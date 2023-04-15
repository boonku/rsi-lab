using System;
using System.Collections.Generic;
using System.Linq;
using System.Resources;
using System.ServiceModel;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using WcfClient.ServiceReference1;

namespace WcfClient
{
    internal class Program
    {
        static void Main(string[] args)
        {
            MyData.Info();
            Console.WriteLine("... The client is started");

            Uri baseAddress;

            BasicHttpBinding myBinding = new BasicHttpBinding();
            baseAddress = new Uri("http://localhost:10001/ServiceBaseName/endpoint1");

            EndpointAddress eAddress = new EndpointAddress(baseAddress);

            ChannelFactory<ICalculator> myCF = new ChannelFactory<ICalculator>(myBinding, eAddress);

            ICalculator myClient = myCF.CreateChannel();
            CalculatorClient myClient2 = new CalculatorClient("WSHttpBinding_ICalculator");


            Console.WriteLine("...calling Add (for endpoint 1)");
            double result = myClient.Add(-3.7, 9.5);
            Console.WriteLine("Result = " + result);

            Console.WriteLine("...calling Multiply (for endpoint 2)");
            result = myClient2.Multiply(-3.7, 9.5);
            Console.WriteLine("Result = " + result);

            Console.WriteLine("2...calling HMultiply Async");
            Task<double> asyncResult = CallHMultiply(myClient2, 1.1, -3.3);
            Thread.Sleep(100);

            Console.WriteLine("...calling Add (for endpoint 2)");
            result = myClient2.Add(-3.7, 9.5);
            Console.WriteLine("Result = " + result);

            result = asyncResult.Result;
            Console.WriteLine("2...HMultiplyAsync Result = " + result);

            Console.WriteLine("2...calling CallCalculateAmountOfPrimesInRange");
            Task<int> count = CallCalculateAmountOfPrimesInRange(myClient2, 1, 100);
            Thread.Sleep(100);

            result = count.Result;
            Console.WriteLine("2...CallCalculateAmountOfPrimesInRange Result = " + result);

            Console.WriteLine("...press <ENTER> to STOP client...");
            Console.WriteLine();
            Console.ReadLine();

            ((IClientChannel)myClient).Close();
            Console.WriteLine("...Client closed - FINISHED");
        }

        private static async Task<double> CallHMultiply(CalculatorClient client, double val1, double val2)
        {
            Console.WriteLine("2...... called callHMultiplyAsync");
            double reply = await client.HMultiplyAsync(val1, val2);
            Console.WriteLine("2...... finished callHMultiplyAsync");

            return reply;
        }

        private static async Task<int> CallCalculateAmountOfPrimesInRange(CalculatorClient client, int start, int end)
        {
            Console.WriteLine("2...... called CalculateAmountOfPrimesInRangeAsync");
            int reply = await client.CalculateAmountOfPrimesInRangeAsync(start, end);
            Console.WriteLine("2...... finished CalculateAmountOfPrimesInRangeAsync");

            return reply;
        }
    }


}
