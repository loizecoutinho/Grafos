package dijkstra;
import java.util.*;
import com.estrutura.Digrafo;

public class DijkstraDirecionado {
    //grafo que será analisado
    private Digrafo grafo;

    public DijkstraDirecionado(Digrafo grafo) {
        this.grafo = grafo;
    }

    /**
     * @param origem O nó inicial.
     */
    public void dijkstra(int origem) {
        // Mapa para guardar a menor distancia da origem até cada vértice
        Map<Integer, Integer> distancia = new HashMap<>();
        
        // Mapa que armazena o vértice anterior no caminho
        Map<Integer, Integer> anterior = new HashMap<>();
        
        // Conjunto de vértices já visitados
        Set<Integer> visitados = new HashSet<>();
        // Itera sobre todos os vértices do grafo.
        for (int vertice : grafo.getVertices()) {
            // Define a distância inicial de todos como "infinito".
            distancia.put(vertice, Integer.MAX_VALUE);
            // Define o anterior de todos como nulo.
            anterior.put(vertice, null);
        }
        
        distancia.put(origem, 0); //define a distância da origem até ela mesma
        /**
         * Fila de Prioridade
         */
        // ordena os vértices com base em suas distâncias ATUAIS
        PriorityQueue<Integer> fila = new PriorityQueue<>(Comparator.comparingInt(distancia::get));
        
        // Adiciona a origem na fila
        fila.add(origem);

        // Enquanto houver vértices na fila para processar.
        while (!fila.isEmpty()) {
            
            // Remove o vértice com a MENOR distância da fila.
            int atual = fila.poll();

            // se já visitado, 'continue'
            if (!visitados.add(atual)) continue;
            if (distancia.get(atual) == Integer.MAX_VALUE) {
                continue;
            }
            // Itera sobre todos os vizinhos do vértice 'atual'.
            for (Map.Entry<Integer, Integer> vizinho : grafo.getVizinhos(atual).entrySet()) {
                int v = vizinho.getKey();

                // Em vez de usar o peso real da aresta (vizinho.getValue()),definimos o peso de qualquer aresta como 1.
                int peso = 1;
                
                // Calcula a nova distância: distância até 'atual' + 1 (salto).
                int novaDistancia = distancia.get(atual) + peso;

                // Se encontramos um caminho com menos arestas
                if (novaDistancia < distancia.get(v)) {
                    distancia.put(v, novaDistancia);//atualiza a distancia
                    anterior.put(v, atual);//define o vertice anterior
 
                    fila.add(v);//adiciona o vizinho na fila
                }
            }
        }

        // Imprime os resultados para todos os vértices
        System.out.println("\n--- Resultados de Dijkstra (modo BFS - Peso 1) (Origem: " + origem + ") ---");
        for (int v : distancia.keySet()) {
            // Imprime a distância 
            System.out.println("Distância de " + origem + " até " + v + " = " + distancia.get(v));
            System.out.print("Caminho: ");
            
            // Chama o método  recursivo para imprimir o caminho
            imprimirCaminho(v, anterior);
            
            System.out.println();
        }
    }

    /**
     * Método  RECURSIVO para imprimir o caminho do 'destino' voltando até a origem, usando o mapa 'anterior'.
     */
    private void imprimirCaminho(int destino, Map<Integer, Integer> anterior) {
        // Caso base da recursão: Se o nó não tem anterior (é a origem).
        if (anterior.get(destino) != null) {
            // Chama para o antecessor primeiro.
            imprimirCaminho(anterior.get(destino), anterior);
        }
        
        // Imprime o nó atual (ocorre após a recursão voltar)
        System.out.print(destino + " ");
    }
}
