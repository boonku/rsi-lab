using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.Threading;

namespace WcfService
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single, ConcurrencyMode = ConcurrencyMode.Multiple)]
    public class MyCalculator : ICalculator
    {
        public double Add(double val1, double val2) 
        {
            double result =  val1 + val2;
            PrintCall("Add", val1, val2, result);
            return result;
        }

        public double Multiply(double val1, double val2) 
        {
            double result = val1 * val2;
            PrintCall("Multiply", val1, val2, result);
            return result;
        }

        public double Divide(double val1, double val2)
        {
            double result = val1 / val2;
            PrintCall("Divide", val1, val2, result);
            return result;
        }

        public double Substract(double val1, double val2) 
        {
            double result = val1 - val2;
            PrintCall("Substract", val1, val2, result);
            return result;
        }

        public double Mod(double val1, double val2)
        {
            double result = val1 % val2;
            PrintCall("Mod", val1, val2, result);
            return result;
        }

        public double HMultiply(double val1, double val2)
        {
            double result = val1 * val2;
            Thread.Sleep(5000);
            PrintCall("Multiply", val1, val2, result);
            return result;
        }

        private void PrintCall(string method, double val1, double val2, double result)
        {
            Console.WriteLine("Called: " + method + "(" + val1.ToString() + ", " + val2.ToString() + ")");
            Console.WriteLine("Result: " + result.ToString());
        }
    }
}
