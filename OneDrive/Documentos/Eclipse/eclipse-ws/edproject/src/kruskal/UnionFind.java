/**
 *gerencia quais vértices já estão conectados
 */
package kruskal;

/**
 * otimizado com as heurísticas de
 * "União por Rank" (no método uniao) e
 * "Compressão de Caminho" (no método encontra).
 */
public class UnionFind {

    // Se pai[i] == i, então 'i' é o diretor do seu conjunto
    private int[] pai;
    
    // Array para armazenar o "rank" (tamanho) de cada conjunto
    // Usado na "União por Rank" para manter as árvores "baixas".
    private int[] tamConjunto; 
    
    // O número total de vértices que a estrutura gerencia
    private int numVertices;
    
    /**
     * Prepara a estrutura para 'n' vértices
     * @param n O número total de elementos (vértices), ex: grafo.getVertices().size()
     */
    public UnionFind(int n) {
        // Armazena o número de vértices
        this.numVertices = n;
        
        // memória para o array de pais (um para cada vértice)
        pai = new int[n];
        //  memória para o array de rank (um para cada vértice)
        tamConjunto = new int[n]; 
        // Inicialmente, cada vértice é seu próprio conjunto
        for (int i = 0; i < n; i++) {
            
            // Cada elemento 'i' começa sendo seu próprio diretor/pai
            pai[i] = i;
            
            // O rank inicial de um conjunto com um único elemento é 0
            tamConjunto[i] = 0;
        }
    }

    /**
     * Implementa o passo r := Find(u) (Encontrar)
     * Retorna o diretor do conjunto ao qual 'i' pertence
     * @param i O elemento (vértice) a ser encontrado.
     * @return O ID do representante (raiz) do conjunto.
     */
    public int encontra(int i) {
        
        //Se 'i' é seu próprio pai ,então 'i' é a raiz do conjunto
        if (pai[i] == i) {
            return i;
        }

        // Compressão de Caminho-heuristica
        // Se 'i' não é a raiz, chamamos recursivamente 'encontra' para seu pai
        // O resultado é ARMAZENADO DIRETAMENTE em 'pai[i]'.
        // Isso "achata" a árvore
        pai[i] = encontra(pai[i]);
        
        // Retorna a raiz que foi encontrada
        return pai[i];
    }

    /**
     * Implementa o passo Union(r, s) (União)
     * Une os dois conjuntos (componentes) representados por 'r' e 's'
     * @param r Um elemento (ou a raiz) do primeiro conjunto.
     * @param s Um elemento (ou a raiz) do segundo conjunto.
     */
    public void uniao(int r, int s) {
        
        // Encontra o diretor do conjunto de 'r'
        int raizR = encontra(r);
        
        // Encontra o diretor do conjunto de 's'
        int raizS = encontra(s);

        // Se as raízes são diferentes, r e s estao em conjuntos diferentes
        if (raizR != raizS) {
            
            // União por Rank (Union-by-Rank)
            // veriifca se o tamanho de R é menor que o de S
            if (tamConjunto[raizR] < tamConjunto[raizS]) {
                // S se torna o pai de R(rankS continua o mesmo)
                pai[raizR] = raizS;//anexa a árvore menor à árvore maior(r -> s)
            } 
            // Se o rank de R for maior que o de S
            else if (tamConjunto[raizR] > tamConjunto[raizS]) {
                // R se torna o pai de S(rankR continua o mesmo)
                pai[raizS] = raizR;//anexa a árvore menor à árvore maior(s ->r)
            } 
            // Se os ranks são IGUAIS
            else {
                // escolhe-se qualquer um para ser o pai
                pai[raizS] = raizR;//(r->s)
                
                // E o rank da nova raiz deve ser incrementado em 1
                tamConjunto[raizR]++;
            }
        }
    }
}
