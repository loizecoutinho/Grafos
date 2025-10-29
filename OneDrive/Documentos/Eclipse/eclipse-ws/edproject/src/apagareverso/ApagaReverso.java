package apagareverso;
import java.util.*;
import estrutura.GrafoND;
import estrutura.Aresta;


/**
 * Implementa o Algoritmo Apaga-Reverso (Reverse-Delete) para encontrar a MST.
 * O algoritmo começa com o grafo completo e remove arestas de maior custo
 * que não desconectam o grafo [7, 8].
 */
public class ApagaReverso {

    private GrafoND grafoOriginal;

    public ApagaReverso(GrafoND grafo) {
        this.grafoOriginal = grafo;
    }

    /**
     * Extrai e ordena todas as arestas de forma decrescente (do maior peso para o menor).
     */
    private List<Aresta> ordenarArestasDecrescente(GrafoND G) {
        Set<Aresta> arestaSet = G.extractAllEdges(); 
        List<Aresta> arestaOrdenada = new ArrayList<>(arestaSet);
        
        // Collections.sort() usa Aresta.compareTo() que ordena em ordem CRESCENTE [1].
        Collections.sort(arestaOrdenada); 
        
        // Inverte a lista para obter a ordem DECRESCENTE (Apaga-Reverso)
        Collections.reverse(arestaOrdenada);
        
        return arestaOrdenada;
    }
    
    /**
     * Verifica se o grafo G está conexo usando Busca em Largura (BFS).
     * O(V + E) onde V é o número de vértices e E é o número de arestas restantes.
     */
    private boolean isConexo(GrafoND G) {
        Set<Integer> vertices = G.getVertices(); 
        if (vertices.isEmpty()) return true;

        // Se o grafo tem vértices, escolhemos um para começar a travessia.
        int startNode = vertices.iterator().next();
        
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>(); // Queue é usada para BFS [9, 10]

        queue.add(startNode);
        visited.add(startNode);
        
        // Executa BFS
        while (!queue.isEmpty()) {
            int u = queue.poll();
            
            // Usamos o método getVizinhos()
            Map<Integer, Integer> vizinhos = G.getVizinhos(u);
            
            for (int v : vizinhos.keySet()) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        
        // Se a busca alcançou todos os vértices, o grafo é conexo.
        return visited.size() == vertices.size();
    }

    /**
     * Calcula a Árvore Geradora Mínima (MST) usando o Algoritmo Apaga-Reverso.
     * Começa com o grafo completo e remove arestas de maior peso que não desconectam [8].
     * 
     * @return Um conjunto de arestas que formam a MST.
     */
    public Set<Aresta> apagaReversoMST() {
        
        // 1. Ordem decrescente das arestas
        List<Aresta> arestasDecrescentes = ordenarArestasDecrescente(this.grafoOriginal);
        
        int V = grafoOriginal.getVertices().size();
        
        // A MST deve ter V-1 arestas (para grafos conexos).
        int arestasMinimas = V - 1;
        
        // 2. Itera e remove arestas
        for (Aresta aresta : arestasDecrescentes) {
            
            int u = aresta.getOrigem();
            int v = aresta.getDestino();
            int peso = aresta.getPeso();
            
            // Só tentamos remover se o grafo ainda tiver mais que N-1 arestas.
            if (grafoOriginal.extractAllEdges().size() > arestasMinimas) {
                
                // A. Tenta remover a aresta
                grafoOriginal.removerAresta(u, v); 
                
                // B. Verifica a Conectividade
                if (isConexo(grafoOriginal)) {
                    // Se continua conexo, a aresta de maior custo era redundante. 
                    // Ela permanece removida.
                } else {
                    // A aresta é CRÍTICA, a remoção desconectou o grafo. Deve ser RESTAURADA.
                    grafoOriginal.adicionarAresta(u, v, peso); 
                }
            } else {
                // Já atingimos a MST.
                break;
            }
        }
        
        // 3. Retorna as arestas restantes (a MST)
        return grafoOriginal.extractAllEdges();
    }

    /**
     * Imprime as arestas da MST resultante e calcula o custo total.
     */
    public void imprimirMSTECustoTotal(Set<Aresta> mst) {
        
        double custoTotal = 0;
        
        System.out.println("\n--- Arestas da MST (Apaga-Reverso) ---");
        
        // Itera sobre o conjunto de arestas da MST
        for (Aresta aresta : mst) {
            // Usa os métodos getOrigem, getDestino e getPeso da classe Aresta
            System.out.println("Aresta: " + aresta.getOrigem() + " - " + aresta.getDestino() + " | Peso: " + aresta.getPeso());
            custoTotal += aresta.getPeso(); // Soma o peso da aresta ao custo total
        }
        
        System.out.println("\nNúmero final de arestas: " + mst.size());
        System.out.println("Custo Total da MST: " + custoTotal); 
    }
    
}