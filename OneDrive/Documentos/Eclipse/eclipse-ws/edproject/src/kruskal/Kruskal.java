/*
 * Arvore geradora mínima - kruskal
 */
package kruskal;
import java.util.*;
import estrutura.GrafoND;
import estrutura.Aresta;


public class Kruskal {
	private GrafoND grafo;
    //Armazena o resultado do cálculo da MST
    private Set<Aresta> mstResult; 
	
	public Kruskal(GrafoND grafo) {
		this.grafo = grafo;
	}
	
	public List<Aresta> ordenacaoArestas(GrafoND grafo) {
		//extrai as arestas do grafo
		Set<Aresta> arestaSet = grafo.extractAllEdges();
		
		//converte o Set para List(ordenaveis)
		List<Aresta> arestaOrdenada = new ArrayList<>(arestaSet);
		
		//ordenação da lista
		//Collections.sort usa a função compareTo
		Collections.sort(arestaOrdenada);
		
		return arestaOrdenada;
	}

/////////////////////////////////////UNION-FIND///////////////////////////////////////

	/**
     * @return O conjunto de arestas (X) que formam a MST.
     */
    public Set<Aresta> kruskalMST() {
        // 1. Arestas ordenadas (Linha 1)
        List<Aresta> arestaOrdenada = ordenacaoArestas(this.grafo);
        
        // Assumindo que o número de vértices n é 
        // o tamanho do conjunto de vértices do seu grafo
        int n = grafo.getVertices().size(); 

        // 2. Inicializar X (a MST) (Linha 2)
        Set<Aresta> X = new HashSet<>();
        
        // 3. Inicializar a estrutura Union-Find (Linha 3)
        // Nota: Assumindo que os IDs dos vértices são contínuos de 0 a n-1
        // Se não forem, você precisará de um mapeamento de ID para Índice de array.
        UnionFind uf = new UnionFind(n); 

        // 4. Iterar sobre as arestas ordenadas (Linhas 4-10)
        for (Aresta uv : arestaOrdenada) {
            int u = uv.getOrigem(); // Vértice de origem
            int v = uv.getDestino(); // Vértice de destino

            // 5. Encontrar os diretores (Linhas 6 e 7)
            int diretorU = uf.encontra(u); // r := Find(u)
            int diretorV = uf.encontra(v); // s := Find(v)

            // 6. Verificar se formam ciclo (Linha 8)
            // A aresta é externa à floresta se r ≠ s [1, 3].
            if (diretorU != diretorV) { 
                
                // 7. Adicionar à MST (Linha 9)
                X.add(uv); 
                
                // 8. Unir as componentes (Linha 10)
                uf.uniao(diretorU, diretorV); 
                
                // Opcional: O Kruskal para quando |X| = n - 1 (para grafos conexos)
                if (X.size() == n - 1) {
                    break; 
                }
            }
        }

        this.mstResult = X; 
        return X;
    }
    //MÉTODO DE IMPRESSÃO
    public void imprimirMSTECustoTotal() {
        
        // Verifica se o cálculo já foi feito (evita NullPointerException)
        if (this.mstResult == null) {
            // Se a MST ainda não foi calculada, chamamos o método principal.
            // Isso garante que o estado `mstResult` seja preenchido.
            System.out.println("MST não calculada. Executando algoritmo de Kruskal...");
            kruskalMST(); 
        }
        
        double custoTotal = 0;
        
        System.out.println("\n--- Árvore Geradora Mínima (MST) - Algoritmo de Kruskal ---");
        
        // Acessa o atributo de instância
        for (Aresta aresta : this.mstResult) { 
            System.out.println("Aresta: " + aresta.getOrigem() + " - " + aresta.getDestino() + " | Peso: " + aresta.getPeso());
            custoTotal += aresta.getPeso(); 
        }
        
        System.out.println("\nNúmero total de arestas na MST: " + this.mstResult.size());
        System.out.println("Custo Total da MST: " + custoTotal);
    }
	
}

