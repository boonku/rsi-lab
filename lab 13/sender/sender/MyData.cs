using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;

namespace WcfClient
{

    public class MyData
    {
        public static void Info()
        {
            Console.WriteLine("Filip Wiśniewski, 260406");
            Console.WriteLine("Szymon Bąk, 260431");
            Console.WriteLine(DateTime.Now.ToString("dd MMMM HH:mm:ss"));
            Console.WriteLine(Environment.OSVersion.VersionString);
            Console.WriteLine(Environment.UserName);
            Console.WriteLine(Environment.Version.ToString());
            var host = Dns.GetHostEntry(Dns.GetHostName());
            foreach (var ip in host.AddressList)
            {
                if (ip.AddressFamily == AddressFamily.InterNetwork)
                {
                    Console.WriteLine(ip.ToString());
                }
            }
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            Console.WriteLine("Witaj w świecie C# \uD83D\uDE00\n");
        }
    }
}
