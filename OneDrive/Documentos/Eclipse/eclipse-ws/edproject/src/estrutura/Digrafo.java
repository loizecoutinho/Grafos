package estrutura;
import java.util.*;

public class Digrafo {
    
    // O Mapa externo armazena os vértices de origem, o Mapa interno armazena os vizinhos desse vértice
    // No Mapa interno, a chave é o vértice de DESTINO, O valor é o PESO da aresta
    private Map<Integer, Map<Integer, Integer>> adjacencia; // uma Lista de Adjacência implementada com Mapas

    public Digrafo() {
        // Inicialização do mapa de adjacência principal como um novo HashMap vazio
        adjacencia = new HashMap<>();
    }

    /**
     * Adiciona um novo vértice (nó) ao grafo
     * @param no id do vertice
     */
    public void adicionarVertices(int no) {
        //verifica se a chave 'no' já existe no mapa 'adjacencia'
        // Se não existir,'no' é adicionado como chave e criado um novo HashMap vazio(armazenara os vizinhos)
        adjacencia.putIfAbsent(no, new HashMap<>());
    }

    /**
     * Adiciona uma aresta DIRECIONADA (unidirecional) e ponderada ao grafo.
     * @param v1 O vértice de ORIGEM.
     * @param v2 O vértice de DESTINO.
     * @param peso O peso da aresta (v1 -> v2).
     */
    public void adicionarAresta(int v1, int v2, int peso) {
        //v1-2 é adicionado (com um mapa de vizinhos vazio)
        adicionarVertices(v1);
        adicionarVertices(v2);
        
        //adjacencia.get(v1): pega o mapa de vizinhos do vértice v1
        // .put(v2, peso): Adiciona v2 a esse mapa de vizinhos, associando-o ao 'peso'
        // V1 -> V2  -aresta DIRECIONADA:
        adjacencia.get(v1).put(v2, peso);
    }

    /**
     * Retorna o mapa de vizinhos e seus pesos de um vértice específico
     * @param vertice O vértice do qual queremos os vizinhos.
     * @return Um Mapa<Integer, Integer> onde a chave é o vizinho e o valor é o peso
     */
    public Map<Integer, Integer> getVizinhos(int vertice) {
        // tenta buscar a chave 'vertice' no mapa 'adjacencia'
        // Se encontrar, retorna o mapa de vizinhos
        // Se NÃO, ele retorna o valor padrão(um mapa vazio)
        return adjacencia.getOrDefault(vertice, new HashMap<>());
    }

    /**
     * Retorna um conjunto com todos os vértices presentes no grafo
     * @return Um Set<Integer> contendo os IDs de todos os vértices
     */
    public Set<Integer> getVertices() {
        //'keySet()' retorna um Set de todos os vértices do grafo
        return adjacencia.keySet();
    }
    
    /**
     * Imprime a lista de adjacência do dígrafo no console
     */
    public void imprimir() {
        // Itera sobre cada vértice no mapa de adjacência
        for (int vertice : adjacencia.keySet()) {
            
            // Imprime o vértice de origem
            System.out.print("Vértice " + vertice + " -> ");
            
            // pega o mapa interno de vizinhos para o 'vertice' atual
            // '.entrySet()' retorna um conjunto de pares (Chave, Valor) - (Vizinho, Peso)
            for (Map.Entry<Integer, Integer> vizinho : adjacencia.get(vertice).entrySet()) { 
                // vizinho.getKey() = ID do vértice do vizinho
                // vizinho.getValue() = peso da aresta
                System.out.print(vizinho.getKey() + " (Peso: " + vizinho.getValue() + ") ");
            }

            System.out.println();
        }
    }
}
