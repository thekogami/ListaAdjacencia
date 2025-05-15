# Validação da Lista de Adjacência

## 🧩 Problema

Dada uma lista de adjacência representando um grafo, escreva uma função que retorne se esse grafo é um **grafo não direcionado válido**, onde:

1. Cada nó está entre `0` e `V-1`, onde `V` é o número total de vértices.
2. Não há **auto-loops**, ou seja, uma aresta que conecta um nó a si mesmo.
3. Não há **arestas paralelas**, ou seja, mais de uma aresta conectando os mesmos dois nós.
4. A relação de vizinhança deve ser **bidirecional**: se `no1` aparece em `grafo[no2]`, então `no2` também deve aparecer em `grafo[no1]`.

---

## 🧠 Solução

O código em Java implementa a função `isGrafoNaoDirecionadoValido` que recebe um grafo como um `Map<Integer, List<Integer>>` e valida os critérios acima.

### ✅ O que o código verifica:

* Grafo vazio é considerado válido.
* **Auto-loops**: se um nó aparece na sua própria lista de adjacência.
* **Arestas paralelas**: se há elementos duplicados nas listas de adjacência.
* **Bidirecionalidade**: se toda conexão é mútua entre os nós.
* **Validação do intervalo dos nós**: todos os nós e seus vizinhos devem estar entre `0` e `V-1`.

---

## 💻 Código Java Completo

```java
package br.univille;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Exemplo de uso da função
        Map<Integer, List<Integer>> grafo = new HashMap<>();
        grafo.put(0, Arrays.asList(1, 2));
        grafo.put(1, Arrays.asList(0, 2));
        grafo.put(2, Arrays.asList(0, 1));

        System.out.println(isGrafoNaoDirecionadoValido(grafo)); // Deve retornar true
    }

    public static boolean isGrafoNaoDirecionadoValido(Map<Integer, List<Integer>> grafo) {
        if (grafo.isEmpty()) return true; // Grafo vazio é válido

        int V = grafo.size();

        for (Map.Entry<Integer, List<Integer>> entry : grafo.entrySet()) {
            int no1 = entry.getKey();
            List<Integer> adjacentes = entry.getValue();

            // Verifica se no1 está no intervalo válido
            if (no1 < 0 || no1 >= V) {
                return false;
            }

            // Verifica se há auto-loops
            if (adjacentes.contains(no1)) {
                return false;
            }

            // Verifica se não há arestas paralelas
            Set<Integer> setAdjacentes = new HashSet<>(adjacentes);
            if (setAdjacentes.size() != adjacentes.size()) {
                return false;
            }

            for (int no2 : adjacentes) {
                // Verifica se no2 está no intervalo válido
                if (no2 < 0 || no2 >= V) {
                    return false;
                }

                // Verifica se a relação é bidirecional
                if (!grafo.containsKey(no2) || !grafo.get(no2).contains(no1)) {
                    return false;
                }
            }
        }

        return true;
    }
}
```

---

## 🧪 Exemplo de Execução

**Entrada:**

```java
grafo.put(0, Arrays.asList(1, 2));
grafo.put(1, Arrays.asList(0, 2));
grafo.put(2, Arrays.asList(0, 1));
```

**Saída esperada:**

```
true
```

---

## 📌 Observações

* O grafo vazio (`grafo.isEmpty()`) é considerado válido.
* O número total de vértices `V` é obtido pela quantidade de chaves no mapa.
* Todos os nós (chaves) e seus vizinhos devem estar no intervalo `[0, V-1]`.
* O grafo deve conter todas as chaves correspondentes a seus vizinhos para garantir a bidirecionalidade.

---
