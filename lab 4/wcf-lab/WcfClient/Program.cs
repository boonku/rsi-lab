using System;
using System.Collections.Generic;
using System.ComponentModel;
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

/*            Console.WriteLine("...calling Add (for endpoint 1)");
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
            Task<int> count = CallCalculateAmountOfPrimesInRange(myClient2, 100000000, 100000000+1000000);
            Thread.Sleep(100);

            result = count.Result;
            Console.WriteLine("2...CallCalculateAmountOfPrimesInRange Result = " + result);

            Console.WriteLine("...press <ENTER> to STOP client...");
            Console.WriteLine();
            Console.ReadLine();*/

            int option;
            int val1, val2;
            PrintMenu();
            int.TryParse(Console.ReadLine(), out option);
            while (option != 8)
            {
                val1 = getValue();
                val2 = getValue();

                PerformAction(option, val1, val2, myClient2);

                PrintMenu();
                int.TryParse(Console.ReadLine(), out option);
            }

            ((IClientChannel)myClient).Close();
            Console.WriteLine("...Client closed - FINISHED");
        }

        private static void PrintMenu()
        {
            Console.WriteLine("1. iAdd");
            Console.WriteLine("2. iSub");
            Console.WriteLine("3. iMul");
            Console.WriteLine("4. iDiv");
            Console.WriteLine("5. iMod");
            Console.WriteLine("6. Count primes in range");
            Console.WriteLine("7. Biggest prime in range");
            Console.WriteLine("8. Exit");
            Console.Write("> ");
        }

        private static int getValue()
        {

            Console.WriteLine("Input value:");
            Console.Write("> ");
            int val;
            while (!int.TryParse(Console.ReadLine(), out val))
            {
                Console.WriteLine("Input valid value");
                Console.Write("> ");
            }
            return val;
        }

        private static void PerformAction(int option, int val1, int val2, CalculatorClient client)
        {
            int result;
            try
            {
                switch (option)
                {
                    case 1:
                        Console.WriteLine("...calling iAdd");
                        result = client.iAdd(val1, val2);
                        break;
                    case 2:
                        Console.WriteLine("...calling iSub");
                        result = client.iSub(val1, val2);
                        break;
                    case 3:
                        Console.WriteLine("...calling iMul");
                        result = client.iMul(val1, val2);
                        break;
                    case 4:
                        Console.WriteLine("...calling iDiv");
                        result = client.iDiv(val1, val2);
                        break;
                    case 5:
                        Console.WriteLine("...calling iMod");
                        result = client.iMod(val1, val2);
                        break;
                    case 6:
                        Console.WriteLine("...calling CountPrimesInRange");
                        result = CallCalculateAmountOfPrimesInRange(client, val1, val2).Result;
                        break;
                    case 7:
                        Console.WriteLine("...calling BiggestPrimeInRange");
                        result = CallBiggestPrimeInRange(client, val1, val2).Result;
                        break;
                    default:
                        return;
                }
                Console.WriteLine("Result = " + result);
                Console.WriteLine();
            } catch (FaultException e)
            {
                Console.WriteLine("Overflow exception");
                Console.WriteLine();
            }
            
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
            Console.WriteLine("...called CalculateAmountOfPrimesInRangeAsync");
            int reply = await client.CalculateAmountOfPrimesInRangeAsync(start, end);
            Console.WriteLine("...finished CalculateAmountOfPrimesInRangeAsync");

            return reply;
        }

        private static async Task<int> CallBiggestPrimeInRange(CalculatorClient client, int start, int end)
        {
            Console.WriteLine("...called BiggestPrimeInRangeAsync");
            int reply = await client.BiggestPrimeInRangeAsync(start, end);
            Console.WriteLine("...finished BiggestPrimeInRangeAsync");

            return reply;
        }
    }


}
