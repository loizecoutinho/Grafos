package estrutura;
import java.util.*;

/**
 * Representação unificada de um Grafo NÃO-DIRECIONADO e ponderado.
 */
public class GrafoND {
    private Map<Integer, Map<Integer, Integer>> adjacencia;

    public GrafoND() {
        adjacencia = new HashMap<>();
    }

    public void adicionarVertices(int no) {
        adjacencia.putIfAbsent(no, new HashMap<>());
    }

    // Adiciona aresta bidirecional
    public void adicionarAresta(int v1, int v2, int peso) {
        adicionarVertices(v1);
        adicionarVertices(v2);
        adjacencia.get(v1).put(v2, peso);
        adjacencia.get(v2).put(v1, peso);
    }
    
    // Remove aresta bidirecional (necessário para ApagaReverso)
    public void removerAresta(int v1, int v2) {
        if (adjacencia.containsKey(v1)) {
            adjacencia.get(v1).remove(v2);
        }
        if (adjacencia.containsKey(v2)) {
            adjacencia.get(v2).remove(v1);
        }
    }

    public Map<Integer, Integer> getVizinhos(int vertice) {
        return adjacencia.getOrDefault(vertice, new HashMap<>());
    }

    public Set<Integer> getVertices() {
        return adjacencia.keySet();
    }
    
    /**
     * Extrai todas as arestas únicas do grafo.
     * Necessário para Kruskal, ApagaReverso e Ordenacao.
     */
    public Set<Aresta> extractAllEdges() {
        Set<Aresta> arestaSet = new HashSet<>();
        Set<Integer> visitados = new HashSet<>();

        for (int u : adjacencia.keySet()) {
            for (Map.Entry<Integer, Integer> vizinho : adjacencia.get(u).entrySet()) {
                int v = vizinho.getKey();
                int peso = vizinho.getValue();
                
                // Adiciona a aresta (u, v) e (v, u) apenas uma vez
                if (!visitados.contains(v)) {
                    arestaSet.add(new Aresta(u, v, peso));
                }
            }
            visitados.add(u); // Marca u como visitado
        }
        return arestaSet;
    }
    
    // Método para imprimir (de grafos.GrafoNDirecionado)
    public void imprimir() {
        for (int vertice : adjacencia.keySet()) {
            System.out.print("Vértice " + vertice + ": ");
            for (Map.Entry<Integer, Integer> vizinho : adjacencia.get(vertice).entrySet()) {
                System.out.print(vizinho.getKey() + " (Peso: " + vizinho.getValue() + ") ");
            }
            System.out.println();
        }
    }
}