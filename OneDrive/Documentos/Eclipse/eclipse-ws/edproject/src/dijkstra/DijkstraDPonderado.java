/**
 * Implementa o Algoritmo de Dijkstra para encontrar o menor caminho
 * em grafos direcionados e ponderados(positivos).
 */
package dijkstra;
import java.util.*;
import com.estrutura.Digrafo;

public class DijkstraDPonderado {
    
    // Variável para armazenar o Digrafo
    private Digrafo grafo;

    public DijkstraDPonderado(Digrafo grafo) {
        this.grafo = grafo;
    }

    /**
     * @param origem O vértice inicial
     */
    public void dijkstra(int origem) {
        
        // Mapa para armazenar a menor distância da 'origem' até cada vértice.
        Map<Integer, Integer> distancia = new HashMap<>();        // Chave: Vértice, Valor: Distância
        
        // Mapa para armazenar o "antecessor" de cada vértice no menor caminho.
        Map<Integer, Integer> anterior = new HashMap<>();        // Chave: Vértice, Valor: Vértice anterior no caminho
        
        // Conjunto que armazena os vértices que já foram visitados
        Set<Integer> visitados = new HashSet<>(); //evita reprocessar nós que já tem o menor caminho definido

        // Itera sobre todos os vértices do grafo.
        for (int vertice : grafo.getVertices()) {
            // Define a distância inicial de todos os vértices como "infinito"
            distancia.put(vertice, Integer.MAX_VALUE);
          
            // Define o anterior de todos os vértices como nulo (caminho desconhecido)
            anterior.put(vertice, null);
        }
        
        distancia.put(origem, 0);  //define a distância da origem até ela mesma

        /**
         * Fila de Prioridade
         */
        // ordena os vértices com base em suas distâncias ATUAIS
        PriorityQueue<Integer> fila = new PriorityQueue<>(Comparator.comparingInt(distancia::get));// "compara as distâncias dos vertices no mapa 
        
        // Adiciona o vértice de origem à fila. Ele será o primeiro a ser processado
        fila.add(origem);

        // O loop continua enquanto tiver vértices na PriorityQueue
        while (!fila.isEmpty()) {
            // Remove o vértice com a MENOR distância da fila
            int atual = fila.poll();//o primeiro a ser retirado é o nó de origem

            //verifica se o vértice atual já foi visitado, se sim, o seu menor caminho já foi achado, se não, 'continue'
            if (!visitados.add(atual)) continue;
            if (distancia.get(atual) == Integer.MAX_VALUE) {
                continue;
            }
   
            // Itera sobre todos os vizinhos do vértice 'atual'.
            // Chave (v) = ID do vizinho; Valor (peso) = Peso da aresta (atual -> v)
            for (Map.Entry<Integer, Integer> vizinho : grafo.getVizinhos(atual).entrySet()) {
                int v = vizinho.getKey();
                int peso = vizinho.getValue();
                
                // Calcula a nova distância potencial para o vizinho 'v':
                int novaDistancia = distancia.get(atual) + peso; // origem -> atual(distancia) + atual -> v(peso)

                // Compara a nova distância com a distância já conhecida
                if (novaDistancia < distancia.get(v)) {
                    //Atualiza a distância no mapa
                    distancia.put(v, novaDistancia);
                    
                    //Define 'atual' como o antecessor de 'v'
                    anterior.put(v, atual);
                    
                    //Adiciona/atualiza a nova distancia de 'v' na fila de prioridade
                    fila.add(v);//a função visitados impede duplicatas
                }
            }
        }

        System.out.println("\n--- Resultados de Dijkstra (Origem: " + origem + ") ---");
        // Itera sobre todos os vértices para imprimir seus resultados.
        for (int v : distancia.keySet()) {
            System.out.println("Distância de " + origem + " até " + v + " = " + distancia.get(v));
            System.out.print("Caminho: ");
            
            //imprime o caminho recursivamente
            imprimirCaminho(v, anterior);
            System.out.println();//pula linha
        }
    }

    /**
     * Método recursivo que imprime o caminho do 'destino' voltando até a origem
     * @param destino O vértice final do caminho.
     * @param anterior O mapa que armazena os antecessores.
     */
    private void imprimirCaminho(int destino, Map<Integer, Integer> anterior) {
        /**
         * A recursão para quando chega a um vertice que não tem anterior
         */
        if (anterior.get(destino) != null) {
            //a impressão ocorre "da origem para o destino".
            imprimirCaminho(anterior.get(destino), anterior);
        }
        
        // Imprime o vértice. A origem será impressa primeiro
        System.out.print(destino + " ");
    }
}
