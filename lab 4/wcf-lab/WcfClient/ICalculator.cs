using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Security;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace WcfClient
{
    [ServiceContract(ProtectionLevel = System.Net.Security.ProtectionLevel.None)]
    public interface ICalculator
    {
        [OperationContract]
        double Add(double val1, double val2);

        [OperationContract]
        double Multiply(double val1, double val2);

        [OperationContract]
        double Divide(double val1, double val2);

        [OperationContract]
        double Substract(double val1, double val2);

        [OperationContract]
        double Mod(double val1, double val2);

        [OperationContract]
        double HMultiply(double val1, double val2);

        [OperationContract]
        int iAdd(int val1, int val2);

        [OperationContract]
        int iSub(int val1, int val2);

        [OperationContract]
        int iMul(int val1, int val2);

        [OperationContract]
        int iDiv(int val1, int val2);

        [OperationContract]
        int iMod(int val1, int val2);

        [OperationContract]
        int CalculateAmountOfPrimesInRange(int start, int end);
    }
}
