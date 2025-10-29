package estrutura;
import java.util.*;

/**
 * Representação unificada de um Grafo DIRECIONADO (Digrafo) e ponderado.
 */
public class Digrafo {
	//cria um mapa chamado adjacencia que irá armazenar os vertices e arestas
    private Map<Integer, Map<Integer, Integer>> adjacencia;//troquei set por Map para poder adicionar dois inteiros no meu paraemntro(destio, peso)

  //construtor que inicializa o mapa
    public Digrafo() {
        adjacencia = new HashMap<>();
    }

    public void adicionarVertices(int no) {
		//no é o identificador do vertice
		//adiciona o vertice à lista adjacente se o espaço estiver vazio
        adjacencia.putIfAbsent(no, new HashMap<>());
    }

    // Adiciona aresta unidirecional
    public void adicionarAresta(int v1, int v2, int peso) {
        adicionarVertices(v1);
        adicionarVertices(v2);
        adjacencia.get(v1).put(v2, peso); // Apenas V1 -> V2
    }

    public Map<Integer, Integer> getVizinhos(int vertice) {
        return adjacencia.getOrDefault(vertice, new HashMap<>());
    }

    public Set<Integer> getVertices() {
        return adjacencia.keySet();
    }
    
    // Método para imprimir (de grafos.Digrafo)
    public void imprimir() {
        for (int vertice : adjacencia.keySet()) {
            System.out.print("Vértice " + vertice + " -> ");
            for (Map.Entry<Integer, Integer> vizinho : adjacencia.get(vertice).entrySet()) {
                System.out.print(vizinho.getKey() + " (Peso: " + vizinho.getValue() + ") ");
            }
            System.out.println();
        }
    }
}