package br.edu.estudos.calculadora;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes da classe Calculadora")
class CalculadoraTest {

    @Test
    @DisplayName("Deve retornar o resultado a soma sem problemas")
    void soma() {

        Calculadora calculadora = new Calculadora();
        double result = calculadora.soma(2,3);

        Assertions.assertTrue(result == 5);
        Assertions.assertEquals(5, result);
    }

    @Test
    @DisplayName("Deve retorna um resultado sem casa decimais")
    void divisaoInteira(){
        Calculadora calculadora = new Calculadora();

        double result = calculadora.divisao(6, 2);

        Assertions.assertEquals(3, result);
    }

    @Test
    @DisplayName("Deve retornar um resultado negativo")
    void divisaoComNegativo(){
        Calculadora calculadora = new Calculadora();

        double result = calculadora.divisao(-6, 2);

        Assertions.assertEquals(-3, result);
    }

    @Test
    @DisplayName("Deve retornar um resultado positivo")
    void divisaoComNegativos(){
        Calculadora calculadora = new Calculadora();

        double result = calculadora.divisao(-6, -3);

        Assertions.assertEquals(2, result);
    }

    @Test
    @DisplayName("Deve retornar um resultado decimal")
    void divisaoComResultadoDecimal(){
        Calculadora calculadora = new Calculadora();

        double result = calculadora.divisao(10, 3); // = 3.333333333....

        Assertions.assertEquals(3.33, result, 0.01); // margem de erro
    }

    @Test
    @DisplayName("Deve retornar o resultado da divisao sem problemas")
    void divisaoComNumeradorIgualAZero(){
        Calculadora calculadora = new Calculadora();

        double result = calculadora.divisao(0, 2);

        Assertions.assertEquals(0, result);
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao tentar dividir por zero")
    void divisaoPorZero(){
        Calculadora calculadora = new Calculadora();

        ArithmeticException ex = Assertions.assertThrows(ArithmeticException.class, ()->{
           calculadora.divisao(10, 0);
        });

        Assertions.assertEquals("/ by zero", ex.getMessage());

    }
}