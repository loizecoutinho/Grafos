package com.dijkstra;
import java.util.*;
import com.estrutura.GrafoND;

public class DijkstraND {
    
    // Armazena a instância do grafo
    private GrafoND grafo;

    public DijkstraND(GrafoND grafo) {
        this.grafo = grafo;
    }

    /**
     * @param origem O vértice (nó) inicial
     */
    public void dijkstra(int origem) {
        
        // Mapa para guardar a menor distancia da origem  até cada vértice
        Map<Integer, Integer> distancia = new HashMap<>();
        
        // Mapa que armazena o vértice anterior
        Map<Integer, Integer> anterior = new HashMap<>();
        
        // Conjunto de vértices já visitados 
        Set<Integer> visitados = new HashSet<>();

        // Itera sobre todos os vértices do grafo
        for (int vertice : grafo.getVertices()) {
            // distância inicial de todos como "infinito"
            distancia.put(vertice, Integer.MAX_VALUE);
            // Define o anterior de todos como nulo
            anterior.put(vertice, null);
        }
        
        // A distância da origem até ela mesma é sempre 0
        distancia.put(origem, 0);

        // a fila ordena os vértices pela menor 'distancia' conhecida.
        // Vértices a 1 salto de distância serão processados antes dos vértices a 2 saltos
        PriorityQueue<Integer> fila = new PriorityQueue<>(Comparator.comparingInt(distancia::get));
        
        // Adiciona a origem na fila
        fila.add(origem);

        // Continua enquanto houver vértices na fila
        while (!fila.isEmpty()) {
            // Remove o vértice com a menor distância da fila.
            int atual = fila.poll();

            // Otimização: Se já visitamos o nó, pulamos para o próximo.
            if (!visitados.add(atual)) continue;
            
            //corrige problema de valor infinito
            if (distancia.get(atual) == Integer.MAX_VALUE) {
                continue;
            }
            
            // Itera sobre todos os vizinhos do vértice 'atual'
            for (Map.Entry<Integer, Integer> vizinho : grafo.getVizinhos(atual).entrySet()) {
                int v = vizinho.getKey();
                
                // O peso de qualquer aresta é considerado 1
                int peso = 1;
                
                // Calcula a nova distância: distância até 'atual' + 1 
                int novaDistancia = distancia.get(atual) + peso;

                // checa se tem um caminho com menos arestas
                if (novaDistancia < distancia.get(v)) {
                    distancia.put(v, novaDistancia); // atualiza a distância para 'v'
                    anterior.put(v, atual);  // 'atual' é definido o antecessor de 'v'
                    //  o vizinho 'v' é adicionado na fila para ser processado
                    fila.add(v);
                }
            }
        }

        System.out.println("\n--- Resultados de DijkstraND (BFS - Peso 1) (Origem: " + origem + ") ---");
        for (int v : distancia.keySet()) {
        	System.out.println("Distância de " + origem + " até " + v + " = " + distancia.get(v));
            // Imprime a distância (número de arestas).tância de " + origem + " até " + v + " = " + distancia.get(v));
            System.out.print("Caminho: ");
            
            // Chama o método  recursivo para imprimir o caminho
            imprimirCaminho(v, anterior);
            
            System.out.println();
        }   
    }

    /**
     * Método RECURSIVO para imprimir o caminho do 'destino' voltando até a origem, usando o mapa 'anterior'.
     */
    private void imprimirCaminho(int destino, Map<Integer, Integer> anterior) {
        if (anterior.get(destino) != null) {
            
            imprimirCaminho(anterior.get(destino), anterior);
        }
        
        // Imprime o nó atual (ocorre após a recursão "voltar")
        System.out.print(destino + " ");
    }
}
