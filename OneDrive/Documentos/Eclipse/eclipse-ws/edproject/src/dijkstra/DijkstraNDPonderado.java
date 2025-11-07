package com.dijkstra;
import java.util.*;
import com.estrutura.GrafoND;

public class DijkstraNDPonderado {
    
    private GrafoND grafo;

    public DijkstraNDPonderado(GrafoND grafo) {
        this.grafo = grafo;
    }

    /**
     * @param origem O vértice (nó) inicial.
     */
    public void dijkstra(int origem) {
        
        // Mapa para armazenar a menor distância ponderada da 'origem' até cada vértice.
        Map<Integer, Integer> distancia = new HashMap<>();
        
        // Mapa para reconstruir o caminho
        Map<Integer, Integer> anterior = new HashMap<>();
        
        // Conjunto de vértices já visitados
        Set<Integer> visitados = new HashSet<>();

        // Itera sobre todos os vértices do grafo.
        for (int vertice : grafo.getVertices()) {
            // Define a distância inicial de todos como "infinito"
            distancia.put(vertice, Integer.MAX_VALUE);
            // Define o anterior de todos como nulo
            anterior.put(vertice, null);
        }
        
        // A distância da origem até ela mesma
        distancia.put(origem, 0);
        
        // a fila ordena os vértices pela menor distancia conhecida.
        PriorityQueue<Integer> fila = new PriorityQueue<>(Comparator.comparingInt(distancia::get));
        
        // Adiciona a origem na fila
        fila.add(origem);

        // Continua enquanto houver vértices na fila
        while (!fila.isEmpty()) {
            
            // Remove o vértice com a menor distância da fila.
            int atual = fila.poll();

            // Otimização: Se já finalizamos este nó ('atual'), pulamos para o próximo.
            if (!visitados.add(atual)) continue;

            if (distancia.get(atual) == Integer.MAX_VALUE) {
                continue;
            }
            
            // Itera sobre todos os vizinhos do vértice 'atual'.
            for (Map.Entry<Integer, Integer> vizinho : grafo.getVizinhos(atual).entrySet()) {
                int v = vizinho.getKey();
                
                // peso  da aresta (atual, v) do grafo
                int peso = vizinho.getValue();
                
                // Calcula a nova distância potencial para o vizinho 'v'
                int novaDistancia = distancia.get(atual) + peso;// Custo(origem -> atual) + Custo(atual -> v)

                // Se for achado um caminho com custo total menor
                if (novaDistancia < distancia.get(v)) {
                    //o custo (distância) para 'v' é atualizado
                    distancia.put(v, novaDistancia);
                    //'atual' é definido como o antecessor de 'v' no caminho mais curto
                    anterior.put(v, atual);
                    //adicionamos o vizinho 'v' na fila para ser processado
                    fila.add(v);
                }
            }
        }
        
        // Após o loop, imprime os resultados para todos os vértices
        System.out.println("\n--- Resultados de DijkstraND PONDERADO (Origem: " + origem + ") ---");
        for (int v : distancia.keySet()) {
            // Imprime o custo total
            System.out.println("Distância de " + origem + " até " + v + " = " + distancia.get(v));
            System.out.print("Caminho: ");
            
            imprimirCaminho(v, anterior);
            
            System.out.println();
        }   
    }

    /**
     * Método RECURSIVO para imprimir o caminho do 'destino' voltando até a origem, usando o mapa 'anterior'.
     */
    private void imprimirCaminho(int destino, Map<Integer, Integer> anterior) {
        if (anterior.get(destino) != null) {
            //Chama para o antecessor primeiro.
            imprimirCaminho(anterior.get(destino), anterior);
        }
        
        // Imprime o nó atual (ocorre após a recursão "voltar")
        System.out.print(destino + " ");
    }
}
