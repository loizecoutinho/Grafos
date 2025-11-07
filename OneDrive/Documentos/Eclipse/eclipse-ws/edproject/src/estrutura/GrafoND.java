package estrutura;
import java.util.*;

public class GrafoND {
    
    // Mapa externo (Chave): Vértice de Origem
    // Mapa interno (Valor): Mapa de Vizinhos (Chave: Vértice Destino, Valor: Peso)
    private Map<Integer, Map<Integer, Integer>> adjacencia;

    public GrafoND() {
        adjacencia = new HashMap<>();
    }

    /**
     * Adiciona um nó ao grafo, se ele ainda não existir
     * @param no O ID do vértice.
     */
    public void adicionarVertices(int no) {
        // Se o vértice 'no' não existir no mapa, cria uma entrada para ele com um novo HashMap (vazio) para seus vizinhos
        adjacencia.putIfAbsent(no, new HashMap<>());
    }

    /**
     * Adiciona uma aresta BIDIRECIONAL
     * @param v1 Vértice 1.
     * @param v2 Vértice 2.
     * @param peso Peso da aresta.
     */
    public void adicionarAresta(int v1, int v2, int peso) {
        // Garante que ambos os vértices existem no mapa.
        adicionarVertices(v1);
        adicionarVertices(v2);
        
        // Adiciona a aresta nos dois sentidos
        adjacencia.get(v1).put(v2, peso);
        adjacencia.get(v2).put(v1, peso);
    }
    
    /**
     * Remove uma aresta BIDIRECIONAL.
     * Necessário para o algoritmo Apaga-Reverso.
     * @param v1 Vértice 1.
     * @param v2 Vértice 2.
     */
    public void removerAresta(int v1, int v2) {
        // Verifica se o vértice v1 existe no grafo
        if (adjacencia.containsKey(v1)) {
            // Remove a aresta v1 -> v2 do mapa de vizinhos de v1
            adjacencia.get(v1).remove(v2);
        }
        // Verifica se o vértice v2 existe no grafo
        if (adjacencia.containsKey(v2)) {
            // Remove a aresta v2 -> v1 do mapa de vizinhos de v2
            adjacencia.get(v2).remove(v1);
        }
    }

    /**
     * Retorna o mapa de vizinhos e pesos de um vértice
     * @param vertice O vértice a consultar
     * @return O mapa de vizinhos (ou um mapa vazio se o vértice não existir)
     */
    public Map<Integer, Integer> getVizinhos(int vertice) {
        // Retorna o mapa de vizinhos de 'vertice'
        return adjacencia.getOrDefault(vertice, new HashMap<>());
    }

    /**
     * Retorna um conjunto (Set) com todos os vértices do grafo
     * @return Um Set<Integer> com os IDs de todos os vértices.
     */
    public Set<Integer> getVertices() {
        // Retorna todas as chaves do mapa
        return adjacencia.keySet();
    }
    
    /**
     * Extrai todas as arestas ÚNICAS do grafo (sem duplicatas)
     * Kruskal e ApagaReverso
     * @return Um Set<Aresta> contendo cada aresta apenas uma vez.
     */
    public Set<Aresta> extractAllEdges() {
        // 'arestaSet' usa Aresta.equals() e Aresta.hashCode() para garantir que (u, v) e (v, u) sejam tratados como a mesma aresta
        Set<Aresta> arestaSet = new HashSet<>();
        
        // 'visitados' rastreia os vértices de ORIGEM que já iteramos
        // Isso evita que, ao iterar sobre 'u' e encontrar 'v',a gente adicione (u, v), e depois, ao iterar sobre 'v',
        // a gente adicione (v, u)
        Set<Integer> visitados = new HashSet<>();

        // Itera sobre cada vértice 'u' no grafo.
        for (int u : adjacencia.keySet()) {
            
            // Itera sobre cada vizinho 'v' do vértice 'u'
            for (Map.Entry<Integer, Integer> vizinho : adjacencia.get(u).entrySet()) {
                int v = vizinho.getKey();
                int peso = vizinho.getValue();
                // verifica se o vizinho 'v' ainda não foi processado como um vértice de origem
                if (!visitados.contains(v)) {
                    // é adicionada a aresta (u, v)
                    // Isso garante que a aresta só é adicionada uma vez
                    arestaSet.add(new Aresta(u, v, peso));
                }
            }
            // Marca o vértice 'u' como processado (visitado como origem)
            visitados.add(u);
        }
        // Retorna o conjunto de arestas
        return arestaSet;
    }
    
    /**
     * Imprime a lista de adjacência do grafo não-direcionado
     */
    public void imprimir() {
        // Itera sobre cada vértice no grafo
        for (int vertice : adjacencia.keySet()) {
            // Imprime o vértice atual
            System.out.print("Vértice " + vertice + ": ");
            
            // Itera sobre o mapa de vizinhos do vértice atual
            for (Map.Entry<Integer, Integer> vizinho : adjacencia.get(vertice).entrySet()) {
                // Imprime o vizinho (chave) e o peso (valor)
                System.out.print(vizinho.getKey() + " (Peso: " + vizinho.getValue() + ") ");
            }
            System.out.println();
        }
    }
}
