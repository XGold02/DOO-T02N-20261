# Pedro Henrique Hideo Makiyama
## Conceito Escolhido 1 - Delegation
### Timestamp do Vídeo Que Menciona o Conceito
2m06s - https://www.youtube.com/watch?v=BsfXZjKLT9A&t=2m06s.
### O Que é?
A delegation (delegação) é um recurso da linguagem Kotlin que permite que uma classe ou propriedade transfira parte de suas responsabilidades para outro objeto. Em vez de implementar determinado comportamento diretamente, a classe pode delegar essa implementação a outro componente especializado. Assim, esse mecanismo é uma aplicação prática do princípio de composição em vez de herança, altamente recomendado na Programação Orientada a Objetos. Ao utilizar a delegação, é possível reutilizar funcionalidades existentes sem a necessidade de criar hierarquias de classes complexas.
### Para Que Serve?
A delegação é utilizada principalmente para:
- Reaproveitar implementações já existentes;
- Reduzir código repetitivo;
- Diminuir o acoplamento entre classes;
- Tornar o código mais modular e flexível;
- Facilitar a manutenção e os testes da aplicação.

Em vez de uma classe herdar comportamentos de outra, ela pode utilizar um objeto e delegar operações específicas a ele.
### Como Normalmente é Utilizado?
Na prática, a delegação em Kotlin é frequentemente utilizada para:
- Implementar a lazy initialization (inicialização preguiçosa) por meio de `lazy`;
- Monitorar alterações em propriedades por meio de `observable`;
- Validar ou impedir modificações em propriedades por meio de `vetoable`;
- Reutilizar implementações de interfaces por meio da palavra-chave `by`;
- Dar suporte a frameworks e bibliotecas modernas do ecossistema Kotlin, como Jetpack Compose e componentes do Android.
### Exemplo de Código
Neste exemplo, será apresentada uma delegação de interface.
```kotlin
// A interface Printer define o comportamento de impressão
interface Printer {
    fun print()
}

// A classe ConsolePrinter implementa esse comportamento
class ConsolePrinter : Printer {
    override fun print() {
        println("Imprimindo documento...")
    }
}

// A classe Report também implementa a interface Printer
// Utiliza ": Printer by printer", delegando a implementação dos métodos da interface para o objeto printer
class Report(
    private val printer: Printer
) : Printer by printer

// Quando report.print() for executado, a chamada é automaticamente encaminhada para ConsolePrinter
fun main() {
    val report = Report(ConsolePrinter())

    report.print()
}
```
---
## Conceito Escolhido 2 - Suspending Functions
### Timestamp do Vídeo Que Menciona o Conceito
3m21s - https://www.youtube.com/watch?v=BsfXZjKLT9A&t=3m21s.
### O Que é?
As suspending functions (funções suspensas) são funções especiais da linguagem Kotlin que podem interromper temporariamente sua execução e retomá-la posteriormente sem bloquear a thread em que estão sendo executadas. Elas são declaradas utilizando a palavra-chave `suspend` e constituem um dos principais recursos do sistema de coroutines do Kotlin. Esse mecanismo permite que operações demoradas sejam executadas de forma eficiente e com uma sintaxe simples, semelhante ao código sequencial tradicional.
### Para Que Serve?
As suspending functions são utilizadas principalmente para executar tarefas que envolvem espera, tais como:
- Requisições a serviços web e APIs;
- Operações de banco de dados;
- Leitura e escrita de arquivos;
- Comunicação em rede;
- Processamentos assíncronos em geral.

Sem coroutines, essas operações normalmente exigiriam o uso de múltiplas threads, callbacks ou estruturas mais complexas para evitar o bloqueio da aplicação. Com funções suspensas, é possível escrever código assíncrono de forma mais legível e organizada.
### Como Normalmente é Utilizado?
Na prática, as suspending functions são frequentemente utilizadas para:
- Realizar chamadas a APIs e serviços externos;
- Executar consultas e operações em bancos de dados;
- Manipular arquivos e recursos de entrada e saída (I/O);
- Implementar tarefas assíncronas em aplicações Android;
- Coordenar operações concorrentes e assíncronas através de coroutines.

Frameworks e bibliotecas modernas do ecossistema Kotlin, como o Jetpack Compose, Retrofit e Room, utilizam amplamente funções suspensas para simplificar o desenvolvimento de aplicações assíncronas.
### Exemplo de Código
Neste exemplo, será apresentado o conceito usando `delay()`.
```kotlin
import kotlinx.coroutines.*

// A função loadData() é declarada usando a palavra-chave suspend
suspend fun loadData(): String {
    // Nela, delay(2000) suspende a execução da coroutine por 2 segundos, período no qual a thread não fica bloqueada
    delay(2000)
    return "Dados carregados!"
}

// A função main() utiliza runBlocking para criar uma coroutine e permitir a chamada da função suspensa
fun main() = runBlocking {

    println("Iniciando operação...")

    val result = loadData()

    println(result)
}
```
---