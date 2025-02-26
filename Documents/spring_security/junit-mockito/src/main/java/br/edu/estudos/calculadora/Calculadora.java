package br.edu.estudos.calculadora;

public class Calculadora {
    public double soma(double numero1, double numero2) {
        return numero1 + numero2;
    }

    public double divisao(double numero1, double numero2){

        if (numero2 == 0){
            throw new ArithmeticException("/ by zero");
        }
        return numero1/numero2;
    }

}
