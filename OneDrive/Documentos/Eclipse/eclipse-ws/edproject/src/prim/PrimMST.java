package prim;
import java.util.*;
import com.estrutura.GrafoND;

public class PrimMST {
    
    // Constante para representar "infinito"
    private static final int INFINITY = Integer.MAX_VALUE;

    /**
     * @param G O grafo não-direcionado e ponderado.
     * @param origem O vértice de onde o algoritmo deve começar (a "raiz").
     * @return Um mapa que representa a MST, onde a Chave é o vértice e o Valor é o seu "pai" na árvore.
     */
    public Map<Integer, Integer> arvoreGeradoraMinima(GrafoND G, int origem) {
        // Map<Vértice, Pai>: Armazena a MST final
        Map<Integer, Integer> pai = new HashMap<>(); 
        
        // Map<Vértice, Preco>: Armazena o CUSTO MÍNIMO para conectar um vértice 'v',fora do mst,
        //a QUALQUER vértice que já esteja DENTRO da MST
        Map<Integer, Integer> preco = new HashMap<>();
        
        // Set<Vértice>: Rastreia quais vértices já foram incluídos na MST
        Set<Integer> inMST = new HashSet<>();

        // pega todos os vértices do grafo
        Set<Integer> vertices = G.getVertices();

        // Prepara as estruturas para todos os vértices
        for (int v : vertices) {
            // O custo inicial para alcançar qualquer vértice é "infinito"
            preco.put(v, INFINITY);
            // O pai inicial é indefinido
            pai.put(v, -1); 
        }

        // O custo para alcançar a 'origem'é 0
        preco.put(origem, 0); 
        
        //a raiz apontar para si mesma serve para identifica-la
        pai.put(origem, origem);

        // O loop deve executar V vezes
        int V = vertices.size();
        for (int count = 0; count < V; count++) {
            
            // encontra o vértice 'u' com o menor 'preco' que ainda não ta na mst
            int u = encontrarMinimoPreco(preco, inMST, vertices);//guloso

            // Se 'u' for -1  ou seu preço for infinito significa que não há mais vértices alcançáveis
            // Se inMST.size() < V, o grafo é desconexo
            if (u == -1 || preco.get(u) == INFINITY) {
                break; // Interrompe o loop
            }

            // Adiciona o vértice 'u' à MST
            inMST.add(u);

            
            // pega o mapa de vizinhos de 'u'
            Map<Integer, Integer> vizinhos = G.getVizinhos(u);
            
             //verifica os vizinho(v) de u e checa se 'u' oferece um caminho mais barato para eles           
            if (vizinhos != null) {
                // Itera sobre cada vizinho 'v' e o peso da aresta
                for (Map.Entry<Integer, Integer> aresta : vizinhos.entrySet()) {
                    int v = aresta.getKey();
                    int peso = aresta.getValue(); // Peso da aresta (u, v)

                    // O vizinho 'v' ainda não pode estar na MST
                    // O 'peso' da aresta (u, v) deve ser menor que o 'preco' atual que temos armazenado para 'v'
                    if (!inMST.contains(v) && peso < preco.get(v)) {
                        
                        //O novo custo mais barato para 'v' é 'peso'
                        preco.put(v, peso); 
                        
                        // 'u' é agora o melhor pai para 'v'
                        pai.put(v, u); 
                    }
                }
            }
        }
        
        // Retorna o mapa 'pai'
        return pai; 
    }

    /**
     * Encontra o vértice com o menor 'preco' que ainda não está na MST
     */
    private int encontrarMinimoPreco(Map<Integer, Integer> preco, Set<Integer> inMST, Set<Integer> todosVertices) {
        int min = INFINITY; // Começa com o mínimo como "infinito"
        int minVertex = -1; // Vértice de custo mínimo (não encontrado)

        // Itera sobre os vértices do grafo
        for (int v : todosVertices) {
            
            // Se o vértice 'v' não está na MST e seu preço é o menor até agora
            if (!inMST.contains(v) && preco.get(v) < min) {
                min = preco.get(v);//o minimo é atualizado
                minVertex = v;
            }
        }
        
        // Retorna o vértice que deve ser adicionado à MST
        // Retorna -1 se não encontrar ninguém 
        return minVertex; 
    }

    /**
     * Imprime as arestas da MST e calcula o custo total
     * @param G O grafo não-direcionado original (usado para buscar os pesos).
     * @param mstPais O mapa de pais (resultado da MST).
     * @param origem O vértice raiz escolhido.
     */
    public void imprimirMSTECustoTotal(GrafoND G, Map<Integer, Integer> mstPais, int origem) {
        
        System.out.println("\n--- Árvore Geradora Mínima (MST) - Algoritmo de Prim (Raiz: " + origem + ") ---");
        double custoTotal = 0;

        // Itera sobre todos os vértices para encontrar suas arestas na MST
        for (int vertice : G.getVertices()) {
            // pega o pai do vértice atual
            int pai = mstPais.getOrDefault(vertice, -1); 
            
            // Se o vértice é a raiz
            if (vertice == origem) {
                System.out.println("Vértice " + vertice + " (Raiz)");
            } 
            // Se o vértice tem um pai (não é a raiz e foi alcançado)
            else if (pai != -1) {
                
                // O mapa 'pai' só diz a conexão, não o peso
                // Buscamos o peso no grafo original
                Map<Integer, Integer> vizinhosDoPai = G.getVizinhos(pai);
                
                // pega o peso da aresta específica
                Integer pesoAresta = vizinhosDoPai.get(vertice); 
                
                if (pesoAresta != null) {
                    // Imprime a aresta e seu peso
                    System.out.println("Aresta: " + pai + " - " + vertice + " | Peso: " + pesoAresta);
                    // Adiciona o peso ao custo total
                    custoTotal += pesoAresta;
                }
            } else {
                // Se 'pai' é -1 e não é a raiz, o vértice é inalcançável
                System.out.println("Vértice " + vertice + " não alcançado (Grafo Desconexo)");
            }
        }
        
        System.out.println("\nCusto Total da MST: " + custoTotal); 
    }
}
