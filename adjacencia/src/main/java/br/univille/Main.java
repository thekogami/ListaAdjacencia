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