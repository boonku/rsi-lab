﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace WcfClient.ServiceReference1 {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="ServiceReference1.ICalculator")]
    public interface ICalculator {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Add", ReplyAction="http://tempuri.org/ICalculator/AddResponse")]
        double Add(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Add", ReplyAction="http://tempuri.org/ICalculator/AddResponse")]
        System.Threading.Tasks.Task<double> AddAsync(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Multiply", ReplyAction="http://tempuri.org/ICalculator/MultiplyResponse")]
        double Multiply(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Multiply", ReplyAction="http://tempuri.org/ICalculator/MultiplyResponse")]
        System.Threading.Tasks.Task<double> MultiplyAsync(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Divide", ReplyAction="http://tempuri.org/ICalculator/DivideResponse")]
        double Divide(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Divide", ReplyAction="http://tempuri.org/ICalculator/DivideResponse")]
        System.Threading.Tasks.Task<double> DivideAsync(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Substract", ReplyAction="http://tempuri.org/ICalculator/SubstractResponse")]
        double Substract(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Substract", ReplyAction="http://tempuri.org/ICalculator/SubstractResponse")]
        System.Threading.Tasks.Task<double> SubstractAsync(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Mod", ReplyAction="http://tempuri.org/ICalculator/ModResponse")]
        double Mod(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/Mod", ReplyAction="http://tempuri.org/ICalculator/ModResponse")]
        System.Threading.Tasks.Task<double> ModAsync(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/HMultiply", ReplyAction="http://tempuri.org/ICalculator/HMultiplyResponse")]
        double HMultiply(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/HMultiply", ReplyAction="http://tempuri.org/ICalculator/HMultiplyResponse")]
        System.Threading.Tasks.Task<double> HMultiplyAsync(double val1, double val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iAdd", ReplyAction="http://tempuri.org/ICalculator/iAddResponse")]
        int iAdd(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iAdd", ReplyAction="http://tempuri.org/ICalculator/iAddResponse")]
        System.Threading.Tasks.Task<int> iAddAsync(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iSub", ReplyAction="http://tempuri.org/ICalculator/iSubResponse")]
        int iSub(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iSub", ReplyAction="http://tempuri.org/ICalculator/iSubResponse")]
        System.Threading.Tasks.Task<int> iSubAsync(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iMul", ReplyAction="http://tempuri.org/ICalculator/iMulResponse")]
        int iMul(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iMul", ReplyAction="http://tempuri.org/ICalculator/iMulResponse")]
        System.Threading.Tasks.Task<int> iMulAsync(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iDiv", ReplyAction="http://tempuri.org/ICalculator/iDivResponse")]
        int iDiv(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iDiv", ReplyAction="http://tempuri.org/ICalculator/iDivResponse")]
        System.Threading.Tasks.Task<int> iDivAsync(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iMod", ReplyAction="http://tempuri.org/ICalculator/iModResponse")]
        int iMod(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/iMod", ReplyAction="http://tempuri.org/ICalculator/iModResponse")]
        System.Threading.Tasks.Task<int> iModAsync(int val1, int val2);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/CalculateAmountOfPrimesInRange", ReplyAction="http://tempuri.org/ICalculator/CalculateAmountOfPrimesInRangeResponse")]
        int CalculateAmountOfPrimesInRange(int start, int end);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/ICalculator/CalculateAmountOfPrimesInRange", ReplyAction="http://tempuri.org/ICalculator/CalculateAmountOfPrimesInRangeResponse")]
        System.Threading.Tasks.Task<int> CalculateAmountOfPrimesInRangeAsync(int start, int end);
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ICalculatorChannel : WcfClient.ServiceReference1.ICalculator, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class CalculatorClient : System.ServiceModel.ClientBase<WcfClient.ServiceReference1.ICalculator>, WcfClient.ServiceReference1.ICalculator {
        
        public CalculatorClient() {
        }
        
        public CalculatorClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public CalculatorClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public CalculatorClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public CalculatorClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        public double Add(double val1, double val2) {
            return base.Channel.Add(val1, val2);
        }
        
        public System.Threading.Tasks.Task<double> AddAsync(double val1, double val2) {
            return base.Channel.AddAsync(val1, val2);
        }
        
        public double Multiply(double val1, double val2) {
            return base.Channel.Multiply(val1, val2);
        }
        
        public System.Threading.Tasks.Task<double> MultiplyAsync(double val1, double val2) {
            return base.Channel.MultiplyAsync(val1, val2);
        }
        
        public double Divide(double val1, double val2) {
            return base.Channel.Divide(val1, val2);
        }
        
        public System.Threading.Tasks.Task<double> DivideAsync(double val1, double val2) {
            return base.Channel.DivideAsync(val1, val2);
        }
        
        public double Substract(double val1, double val2) {
            return base.Channel.Substract(val1, val2);
        }
        
        public System.Threading.Tasks.Task<double> SubstractAsync(double val1, double val2) {
            return base.Channel.SubstractAsync(val1, val2);
        }
        
        public double Mod(double val1, double val2) {
            return base.Channel.Mod(val1, val2);
        }
        
        public System.Threading.Tasks.Task<double> ModAsync(double val1, double val2) {
            return base.Channel.ModAsync(val1, val2);
        }
        
        public double HMultiply(double val1, double val2) {
            return base.Channel.HMultiply(val1, val2);
        }
        
        public System.Threading.Tasks.Task<double> HMultiplyAsync(double val1, double val2) {
            return base.Channel.HMultiplyAsync(val1, val2);
        }
        
        public int iAdd(int val1, int val2) {
            return base.Channel.iAdd(val1, val2);
        }
        
        public System.Threading.Tasks.Task<int> iAddAsync(int val1, int val2) {
            return base.Channel.iAddAsync(val1, val2);
        }
        
        public int iSub(int val1, int val2) {
            return base.Channel.iSub(val1, val2);
        }
        
        public System.Threading.Tasks.Task<int> iSubAsync(int val1, int val2) {
            return base.Channel.iSubAsync(val1, val2);
        }
        
        public int iMul(int val1, int val2) {
            return base.Channel.iMul(val1, val2);
        }
        
        public System.Threading.Tasks.Task<int> iMulAsync(int val1, int val2) {
            return base.Channel.iMulAsync(val1, val2);
        }
        
        public int iDiv(int val1, int val2) {
            return base.Channel.iDiv(val1, val2);
        }
        
        public System.Threading.Tasks.Task<int> iDivAsync(int val1, int val2) {
            return base.Channel.iDivAsync(val1, val2);
        }
        
        public int iMod(int val1, int val2) {
            return base.Channel.iMod(val1, val2);
        }
        
        public System.Threading.Tasks.Task<int> iModAsync(int val1, int val2) {
            return base.Channel.iModAsync(val1, val2);
        }
        
        public int CalculateAmountOfPrimesInRange(int start, int end) {
            return base.Channel.CalculateAmountOfPrimesInRange(start, end);
        }
        
        public System.Threading.Tasks.Task<int> CalculateAmountOfPrimesInRangeAsync(int start, int end) {
            return base.Channel.CalculateAmountOfPrimesInRangeAsync(start, end);
        }
    }
}
