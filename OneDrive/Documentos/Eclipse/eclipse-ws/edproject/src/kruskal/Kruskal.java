/*
 * Implementa o algoritmo de Kruskal para encontrar a Árvore Geradora Mínima (MST) em um grafo não-direcionado e ponderado
 */
package kruskal;
import java.util.*;
import com.estrutura.GrafoND;
import com.estrutura.Aresta;


public class Kruskal {
   
    private GrafoND grafo;
    
    // Armazena as arestas da MST  depois que for calculado
    // Isso serve como um "cache" para o método de impressão
    private Set<Aresta> mstResult; 

    public Kruskal(GrafoND grafo) {
        this.grafo = grafo;
    }
    
    /**
     * Extrai todas as arestas do grafo e as ordena em ordem crescente de peso
     * @param grafo O grafo de onde extrair as arestas
     * @return Uma Lista de Arestas, ordenada do menor peso para o maior
     */
    public List<Aresta> ordenacaoArestas(GrafoND grafo) {
        //extrai todas as arestas únicas do grafo
        Set<Aresta> arestaSet = grafo.extractAllEdges();
        
        //converte o Set para uma List 
        List<Aresta> arestaOrdenada = new ArrayList<>(arestaSet);
        
        //ordenação da lista
        Collections.sort(arestaOrdenada);
        
        // Retorna a lista de arestas
        return arestaOrdenada;
    }

/////////////////////////////////////UNION-FIND///////////////////////////////////////
// 1. find(u): Diz a qual "componente" o vértice 'u' pertence
// 2. union(u, v): Une os componentes dos vértices 'u' e 'v' em um só
////////////////////////////////////////////////////////////////////////////////////

    /**
     * Executa o algoritmo de Kruskal para encontrar a MST
     * @return O conjunto de arestas que formam a MST
     */
    public Set<Aresta> kruskalMST() {
        // pega a lista de todas as arestas, ordenadas por peso crescente
        List<Aresta> arestaOrdenada = ordenacaoArestas(this.grafo);
        
        // pega o número total de vértices no grafo
        int n = grafo.getVertices().size(); 

        // Inicializa o conjunto mst como um conjunto vazio
        Set<Aresta> mst = new HashSet<>();

        //começa com 'n' componentes, onde cada vértice é seu próprio componente
        //IDs dos vértices: 0 a n-1
        UnionFind uf = new UnionFind(n); 

        // Itera sobre cada aresta, da mais barata para a mais cara
        for (Aresta uv : arestaOrdenada) {
            // Pega os dois vértices da aresta
            int u = uv.getOrigem(); 
            int v = uv.getDestino();

            // Encontra o diretor do conjunto que'u' pertence
            int diretorU = uf.encontra(u); // r := Find(u)
            // Encontra o diretor do conjunto que'v' pertence
            int diretorV = uf.encontra(v); // s := Find(v)

            // Se os representantes são DIFERENTES, significa que adicionar a aresta não formará um ciclo
            if (diretorU != diretorV) { 
                // Adiciona a aresta 'uv' à MST
                mst.add(uv); 
                // Une os dois componentes em um só
                // 'u', 'v' e todos os seus "vizinhos de componente" pertencem ao mesmo conjunto
                uf.uniao(diretorU, diretorV); 

                // Uma MST com 'n' vértices sempre terá 'n-1' arestas
                if (mst.size() == n - 1) {
                    break; // Interrompe o loop 'for'
                }
            }

        }

        // Armazena mst  na variável da classe para impressão
        this.mstResult = mst; 
        
        // Retorna o conjunto de arestas que compõem a MST
        return mst;
    }
    
    /**
     * Imprime as arestas da MST e o custo total
     */
    public void imprimirMSTECustoTotal() {
        
        // Verifica se a mst ja foi criada/calculada 
        if (this.mstResult == null) {
            System.out.println("MST não calculada. Executando algoritmo de Kruskal...");
            kruskalMST(); 
        }
        
        // Inicializa o acumulador de custo
        double custoTotal = 0;
        
        System.out.println("\n--- Árvore Geradora Mínima (MST) - Algoritmo de Kruskal ---");
        
        // Itera sobre as arestas que estão no 'mstResult'
        for (Aresta aresta : this.mstResult) { 
            // Imprime os detalhes da aresta
            System.out.println("Aresta: " + aresta.getOrigem() + " - " + aresta.getDestino() + " | Peso: " + aresta.getPeso());
            // Soma o peso da aresta ao custo total
            custoTotal += aresta.getPeso(); 
        }
        
        // Imprime o resumo final
        System.out.println("\nNúmero total de arestas na MST: " + this.mstResult.size());
        System.out.println("Custo Total da MST: " + custoTotal);
    }
}
