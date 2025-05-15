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

- **Auto-loops**: se um nó aparece na sua própria lista de adjacência.
- **Arestas paralelas**: se há elementos duplicados nas listas de adjacência.
- **Bidirecionalidade**: se toda conexão é mútua entre os nós.
- (Opcional) A verificação se os nós estão entre `0` e `V-1` pode ser adicionada facilmente, se desejado.

---

## 💻 Exemplo de Código

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
        for (Map.Entry<Integer, List<Integer>> entry : grafo.entrySet()) {
            int no1 = entry.getKey();
            List<Integer> adjacentes = entry.getValue();

            // Verifica se há auto-loops
            if (adjacentes.contains(no1)) {
                return false;
            }

            // Verifica se não há arestas paralelas e se a relação é bidirecional
            Set<Integer> setAdjacentes = new HashSet<>(adjacentes);
            if (setAdjacentes.size() != adjacentes.size()) {
                return false; // Arestas paralelas detectadas
            }

            for (int no2 : adjacentes) {
                if (!grafo.containsKey(no2) || !grafo.get(no2).contains(no1)) {
                    return false; // Relação não é bidirecional
                }
            }
        }

        return true;
    }
}
````

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

O código assume que os nós estão entre `0` e `V-1`, onde `V` é o número total de vértices. Para validar explicitamente essa condição, você pode adicionar este trecho no início da função:

```java
int V = grafo.size();
for (int no : grafo.keySet()) {
    if (no < 0 || no >= V) return false;
}
```

---
