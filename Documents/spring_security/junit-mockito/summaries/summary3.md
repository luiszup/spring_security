# Seção 3 - Conceitos básicos

## O que é um teste?
Um teste é o alinhamento da expectativa com a realidade. Ou seja, é a comparação do resultado esperado com o resultado obtido.
Por exemplo, se quando compramos uma caneta, esperamos que ela escreva, então, para testar se a caneta está funcionando, tentamos escrever com ela. Se ela escrever, então o teste passou, caso contrário, o teste falhou.

## Princípio FIRST
Os testes unitários devem seguir o princípio FIRST:
- **F**ast: os testes devem ser rápidos tanto de executar quanto de se produzir.
- **I**solated: os testes devem ser isolados, ou seja, não devem depender uns dos outros.
- **R**epeatable: os testes devem ser repetíveis, ou seja, devem sempre retornar o mesmo resultado.
- **S**elf-validating: os testes devem ser auto validáveis, ou seja, devem retornar um resultado claro.
- **T**imely: os testes devem ser escritos no momento certo, ou seja, antes do código.
- **T**horough: os testes devem ser completos, ou seja, devem cobrir todos os casos, por exemplo teste tanto o caminho feliz quanto o infeliz.

## JUnit 5
Para usar o JUnit 5 em um projeto Maven, primeiramente pesquisa-se a versão mais recente do JUnit 5 no [Maven Repository](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api) e, em seguida, adiciona-se a seguinte dependência ao arquivo `pom.xml`:
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>${junit.version}</version>
    <scope>test</scope>
</dependency>
```

## Anotações JUnit 5
O JUnit 5 possui várias anotações que nos ajudam a criar testes. As principais anotações são:
- `@Test`: indica que o método é um teste.
- `@BeforeEach`: indica que o método deve ser executado antes de cada teste.
- `@AfterEach`: indica que o método deve ser executado depois de cada teste.
- `@BeforeAll`: indica que o método deve ser executado antes de todos os testes.
- `@AfterAll`: indica que o método deve ser executado depois de todos os testes.
- `@DisplayName`: permite dar um nome personalizado ao teste.
- `@Tag`: permite marcar os testes com tags.
- `@ExtendWith`: permite estender o JUnit 5 com outras bibliotecas.
- `@ParameterizedTest`: indica que o método é um teste parametrizado.
- `@ValueSource`: fornece valores para um teste parametrizado.
- `@CsvSource`: fornece valores para um teste parametrizado a partir de um CSV.
- `@TempDir`: permite criar um diretório temporário para os testes.
- `@TempFile`: permite criar um arquivo temporário para os testes.

## Assertions
Os asserts são usados para verificar se o resultado obtido é o esperado. O JUnit 5 fornece vários métodos de asserts, como:
- `assertEquals`: verifica se dois valores são iguais.
- `assertNotEquals`: verifica se dois valores são diferentes.
- `assertTrue`: verifica se um valor é verdadeiro.
- `assertFalse`: verifica se um valor é falso.
- `assertNull`: verifica se um valor é nulo.
- `assertNotNull`: verifica se um valor não é nulo.
- `assertArrayEquals`: verifica se dois arrays são iguais.
- `assertThrows`: verifica se uma exceção é lançada.
- `assertAll`: verifica várias condições ao mesmo tempo.
- `assertNotSame`: verifica se dois objetos não são o mesmo objeto.
- `assertSame`: verifica se dois objetos são o mesmo objeto.
- `fail`: faz o teste falhar.

**Exemplos de uso:**
- [AssertionsTest.java](src/test/java/br/edu/estudos/AssertionsTest.java)
- [CalculadoraTest.java](src/test/java/br/edu/estudos/calculadora/CalculadoraTest.java)

## Classe de equivalência
Uma classe de equivalência é um conjunto de entradas que geram o mesmo resultado. Ou seja, a ideia por traz da classe de equivalência é a seleção de alguns valores de entrada que são representantes de todo um conjunto.
Isso é útil, pois não precisamos testar todas as entradas possíveis, apenas uma de cada classe de equivalência.

Um exemplo de particionamento de classes de equivalência é um método que recebe uma idade e retorna a fase da vida (criança, adolescente, adulto, idoso). 

Nesse caso, podemos testar uma idade de cada classe de equivalência, por exemplo, uma idade negativa, uma idade entre 0 e 12, uma idade entre 13 e 18, uma idade entre 19 e 59, e uma idade maior que seja entre 60 e 120.
Assim, esse valores cobrem todas as classes de equivalência e garantem que o método está funcionando corretamente.

[Artigo de classe de equivalência](https://artoftesting.com/equivalence-class-partitioning)
## Valores limites
A ideia por traz da técnica de valores limites é testar os valores limites de uma classe de equivalência, ou seja, testar os valores que estão no limite entre uma classe de equivalência e outra.

## Erro vs Falha
- **Erro**: é um problema que impede o teste de ser executado, por exemplo, um erro de compilação, algo que não foi tratado.
- **Falha**: é um problema que impede o teste de passar, por exemplo, um assert que falhou, ou seja, um comportamento inesperado.

## Tratamento de exceções
O `assertThrows` retorna a exceção lançada, permitindo que façamos verificações adicionais. Por exemplo, podemos verificar a mensagem da exceção, ou verificar se a exceção foi lançada em um método específico.

```java
    @Test
    @DisplayName("Deve retornar uma exceção ao tentar dividir por zero")
    void divisaoPorZero(){
        Calculadora calculadora = new Calculadora();

        ArithmeticException ex = Assertions.assertThrows(ArithmeticException.class, ()->{calculadora.divisao(10, 0);});

        Assertions.assertEquals("/ by zero", ex.getMessage());
    }
```

[Documentação Oficial do Junit5](https://junit.org/junit5/docs/5.0.1/api/overview-summary.html)