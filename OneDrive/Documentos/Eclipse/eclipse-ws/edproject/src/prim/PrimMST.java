package prim;
import java.util.*;
import estrutura.GrafoND;

public class PrimMST {
    // Constante para representar "infinito" (custo máximo)
    private static final int INFINITY = Integer.MAX_VALUE;

    /*
     *  G O grafo não-direcionado valorado.
     * origem O vértice de onde o algoritmo deve começar.
     *  Um mapa que representa a MST, onde a chave é o vértice e o valor é o seu pai/gancho na MST.
     */
    public Map<Integer, Integer> arvoreGeradoraMinima(GrafoND G, int origem) {
        
        // O algoritmo de Prim trabalha em grafos não-dirigidos com custos nas arestas [3, 7].
        // Usaremos as estruturas auxiliares (pai, preco, inMST) [14-16].

        // 1. Estruturas Auxiliares
        
        // Map<Vértice, Pai> : Armazena a MST final (pai[v] é o gancho que conecta v à árvore) [14, 15].
        Map<Integer, Integer> pai = new HashMap<>(); 
        
        // Map<Vértice, Preco> : Armazena o custo mínimo para conectar o vértice à árvore T [13, 15].
        Map<Integer, Integer> preco = new HashMap<>();
        
        // Set<Vértice> : Rastreia quais vértices já estão na MST (a subárvore T) [15, 17].
        Set<Integer> inMST = new HashSet<>();

        // Obtém todos os vértices do grafo
        Set<Integer> vertices = G.getVertices();

        // 2. Inicialização
        
        // Inicializa pai e preco para todos os vértices [15].
        for (int v : vertices) {
            preco.put(v, INFINITY); // Todos os preços iniciais são "infinito" [15].
            pai.put(v, -1);         // -1 indica "sem pai" ou indefinido [15, 18].
        }

        // O vértice de origem tem preço 0 para ser escolhido primeiro [15].
        preco.put(origem, 0); 
        pai.put(origem, origem); // A raiz aponta para si mesma [15, 19].

        // 3. Processo Iterativo (V iterações)
        int V = vertices.size();
        // Repete V-1 vezes para construir a MST com V-1 arestas, ou até que a franja esvazie [20].
        // O laço continua até que todos os vértices sejam incluídos ou o grafo seja desconexo.
        for (int count = 0; count < V; count++) {
            
            // 3.1. Encontrar o vértice u não incluído com o menor preço (busca gulosa) [10, 11, 13].
            int u = encontrarMinimoPreco(preco, inMST, vertices);

            // Se o menor preço for INFINITY, o grafo é desconexo, e a busca deve parar [6, 21, 22].
            if (preco.get(u) == INFINITY) {
                // Se a MST não incluir todos os vértices, o grafo original G não era conexo [6, 20].
                break; 
            }

            // Adiciona o vértice escolhido à MST [9, 15].
            inMST.add(u);

            // 3.2. Atualização de Preços e Ganchos
            
            // Percorre todos os vizinhos de u para atualizar a fronteira [14, 15].
            // A sua classe GrafoNDirecionado usa 'adjacencia.get(u)' para obter Map<vizinho, peso> [2].
            Map<Integer, Integer> vizinhos = G.getVizinhos(u);
            
            if (vizinhos != null) {
                for (Map.Entry<Integer, Integer> aresta : vizinhos.entrySet()) {
                    int v = aresta.getKey();
                    int peso = aresta.getValue(); // f(u, v) [16].
                    
                    // Se v ainda não está na MST (inMST) E o peso da aresta (u, v) é menor 
                    // que o preço atual de v [14, 15].
                    if (!inMST.contains(v) && peso < preco.get(v)) {
                        
                        // Atualiza o preço: o novo custo mais barato para v é o peso desta aresta [15].
                        preco.put(v, peso); 
                        
                        // Atualiza o gancho: u é agora o melhor pai para v [14, 15].
                        pai.put(v, u); 
                    }
                }
            }
        }
        
        // O mapa 'pai' contém a MST. Vértices com pai = -1 (exceto a raiz se pai[origem] = origem)
        // ou preco = INFINITY indicam que o grafo não era conexo [22].
        return pai; 
    }

    /**
     * Função auxiliar para encontrar o vértice com o menor custo que ainda não está na MST.
     * Esta é a parte O(V) da iteração (resultando em O(V^2) total) [8].
     */
    private int encontrarMinimoPreco(Map<Integer, Integer> preco, Set<Integer> inMST, Set<Integer> todosVertices) {
        int min = INFINITY;
        int minVertex = -1;

        // Percorre todos os vértices para encontrar o mínimo preço (O(V)) [15].
        for (int v : todosVertices) {
            // Verifica se o vértice não está na árvore E se tem um preço menor que o mínimo atual [15].
            if (!inMST.contains(v) && preco.get(v) < min) {
                min = preco.get(v);
                minVertex = v;
            }
        }
        
        // Retorna o vértice de preço mínimo. Se retornar -1, significa que todos os vértices 
        // alcançáveis já foram incluídos, ou o grafo é vazio/desconexo no início.
        return minVertex; 
    }

    /**
     * Imprime as arestas da MST e calcula o custo total, usando o grafo original para buscar os pesos das arestas da MST.
     * G O grafo não-direcionado original.
     * mstPais O mapa de pais (resultado da MST).
     * origem O vértice raiz escolhido.
     */
    public void imprimirMSTECustoTotal(GrafoND G, Map<Integer, Integer> mstPais, int origem) {
        
        System.out.println("--- Resultado da MST (Raiz: " + origem + ") ---");
        double custoTotal = 0;

        for (int vertice : G.getVertices()) {
            int pai = mstPais.getOrDefault(vertice, -1); // Usa getOrDefault para segurança
            
            if (vertice == origem) {
                // A raiz
                System.out.println("Vértice " + vertice + " (Raiz)");
            } 
            else if (pai != -1) {
                // Para vértices na MST: obtém o peso da aresta do pai para o vértice
                Map<Integer, Integer> vizinhosDoPai = G.getVizinhos(pai);
                
                // O método getVizinhos é crucial para obter o peso de forma encapsulada.
                Integer pesoAresta = vizinhosDoPai.get(vertice); 
                
                if (pesoAresta != null) {
                    System.out.println("Aresta: " + pai + " - " + vertice + " | Peso: " + pesoAresta);
                    custoTotal += pesoAresta;
                }
            } else {
                 // Este caso só acontece se o grafo for desconexo e o vértice não for a origem
                 System.out.println("Vértice " + vertice + " não alcançado (Grafo Desconexo)");
            }
        }
        
        System.out.println("\nCusto Total da MST: " + custoTotal); 
    }
    
}