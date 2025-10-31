# ⚡ Algoritmos de Ordenação Paralelos em Java

## 🧠 Descrição

Este projeto implementa diversos **algoritmos de ordenação clássicos** com suporte a **paralelismo em Java**, utilizando o framework **Fork/Join (`java.util.concurrent`)**.  
O objetivo é comparar o desempenho entre as versões **sequenciais e paralelas**, permitindo configurar a **quantidade de threads** usadas em cada execução.

---

## 🚀 Algoritmos Implementados

| Algoritmo        | Classe Java                | Controle de Threads | Tipo de Execução |
|------------------|---------------------------|----------------------|------------------|
| 🧩 Merge Sort    | `MergeSortParalelo.java`  | ✅ Sim               | `RecursiveTask`  |
| 🔍 Selection Sort| `SelectionSortParalelo.java` | ✅ Sim             | `RecursiveTask`  |
| ⚡ Quick Sort    | `ParallelQuickSort.java`  | ✅ Sim               | `RecursiveAction`|
| 💨 Bubble Sort   | `BubbleSortParalelo.java` | ✅ Sim (já implementado) | Paralelo via ForkJoin |

Cada algoritmo segue o modelo **divide and conquer (dividir e conquistar)**, permitindo que as listas sejam divididas e processadas simultaneamente em diferentes threads.

---

## Criação do CSV
O CSV contendo todos os resultados é gerado em uma pasta dentro do diretório do arquivo, a pasta é chamada de 'Export' e o arquivo interno contém os valores no formato de CSV

## 🧵 Controle de Threads

Todos os algoritmos aceitam um parâmetro que define o número de threads que serão usadas.  
Se `numThreads <= 0`, o algoritmo utiliza automaticamente o número total de núcleos do processador:
#OBS: Na classe Main temos a aplicação completa rodando.

```java
// Exemplo com MergeSort paralelo
MergeSortParalelo merge = new MergeSortParalelo(lista, 8);
merge.mergeSortParalelo();

// Exemplo com QuickSort paralelo
ParallelQuickSort.parallelQuickSort(lista, 8);
