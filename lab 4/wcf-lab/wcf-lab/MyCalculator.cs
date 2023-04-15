using System;
using System.Collections;
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

        public int iAdd(int val1, int val2)
        {
            int result = val1 + val2;
            PrintCall("iAdd", val1, val2, result);
            return result;
        }

        public int iSub(int val1, int val2)
        {
            int result = val1 - val2;
            PrintCall("iSub", val1, val2, result);
            return result;
        }

        public int iMul(int val1, int val2)
        {
            int result = val1 * val2;
            PrintCall("iMul", val1, val2, result);
            return result;
        }

        public int iDiv(int val1, int val2)
        {
            int result = val1 / val2;
            PrintCall("iDiv", val1, val2, result);
            return result;
        }

        public int iMod(int val1, int val2)
        {
            int result = val1 % val2;
            PrintCall("iMod", val1, val2, result);
            return result;
        }

        public int CalculateAmountOfPrimesInRange(int start, int end)
        {
            bool[] primes = new bool[end + 1];

            // Initialize all values in the array to true
            for (int i = 2; i <= end; i++)
            {
                primes[i] = true;
            }

            // Set multiples of primes to false
            for (int p = 2; p * p <= end; p++)
            {
                if (primes[p])
                {
                    for (int i = p * p; i <= end; i += p)
                    {
                        primes[i] = false;
                    }
                }
            }

            // Count the number of primes in the given range
            int count = 0;
            for (int i = start; i <= end; i++)
            {
                if (primes[i])
                {
                    count++;
                }
            }

            PrintCall("CalculateAmountOfPrimesInRange", start, end, count);
            return count;
        }

        private void PrintCall(string method, double val1, double val2, double result)
        {
            Console.WriteLine("Called: " + method + "(" + val1.ToString() + ", " + val2.ToString() + ")");
            Console.WriteLine("Result: " + result.ToString());
        }
    }
}
