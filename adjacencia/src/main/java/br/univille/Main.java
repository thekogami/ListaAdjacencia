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
