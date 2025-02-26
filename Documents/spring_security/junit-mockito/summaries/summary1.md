# Seção 1 - Introdução

## Testes unitários
Testes unitários são testes que verificam o comportamento de uma unidade de código. Uma unidade de código pode ser um método, uma classe, ou até mesmo um pacote. 
O objetivo dos testes unitários é garantir que cada unidade de código funcione corretamente.

## Por que testar?
Testar é importante para garantir que o software está funcionando corretamente.
Além disso, testar ajuda a identificar problemas no software, antes que ele seja entregue ao cliente.
Quanto antes um problema é identificado, mais barato é corrigi-lo.

## JUnit 5 
JUnit 5 é um framework de testes unitários para a linguagem Java. Ele é a evolução do JUnit 4 e traz várias melhorias, como suporte a lambdas, suporte a testes parametrizados. 
- O Junit 4 ainda é amplamente utilizado no mercado de trabalho, especialmente em projetos legados;
- Junit 5 == Junit Jupiter;

## Pirâmide de testes
A pirâmide de testes é uma representação visual da quantidade de testes que devemos ter em um projeto. Ela é composta por três tipos de testes: testes unitários, testes de integração e testes end-to-end.

![Pirâmide de testes](https://devporai.com.br/wp-content/uploads/2020/02/Pir%C3%A2mide-testes.png)

Vale resaltar que na base da pirâmide estar os teste unitários que possuem a caracteristica de serem mais rápido de produzir e possuem o menor custo.
Esses testes são muito importantes para garantir que as unidades de código funcionem corretamente (regras de negócios implementadas corretamente). Assim, quanto antes fazermos os testes unitários, mais cedo encontraremos os bugs.