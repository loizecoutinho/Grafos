package kruskal;

public class UnionFind {
	// Array para armazenar o pai/diretor de cada vértice
    private int[] pai;
    // Array para armazenar o rank (ou rank/tamanho do conjunto) para otimizações
    private int[] tamConjunto; 
    private int numVertices;
	
    /* Implementa o passo Initialize().
    * Prepara a estrutura de diretores.
    */
   public UnionFind(int n) {
       this.numVertices = n;
       pai = new int[n];
       tamConjunto = new int[n];//O número total de vértices no grafo.

       // Initialize(): Consome O(n) unidades de tempo.
       // Inicialmente, cada vértice é o pai de seu próprio conjunto (componente)
       for (int i = 0; i < n; i++) {
           pai[i] = i;
           tamConjunto[i] = 0; // Usaremos tamConjunto para simplicidade.
       }
   }
   
   // Supondo que os vértices são numerados de 0 a n-1. 
   // Se seus vértices usam IDs arbitrárias (e.g., 10, 20, 50), você precisará de um mapa
   // para mapear o ID do vértice (Integer) para um índice de array (0, 1, 2, ...).

   /**
    * Implementa o passo r := Find(u).
    * Devolve o diretor da componente a que o vértice 'i' pertence [3].
    * 
    * Usa a heurística de Compressão de Caminho (Path Compression) para eficiência.
    * Se implementada com heurísticas path compression e union-by-rank, consome 
    * aproximadamente O(log* n) (tempo amortizado) por operação Find [6].
    */
   public int encontra(int i) {
       // Encontra a raiz do conjunto (o diretor)
       if (pai[i] == i) {
           return i;
       }

       // Compressão de Caminho: Faz o vértice i apontar diretamente para a raiz
       pai[i] = encontra(pai[i]);
       return pai[i];
   }

   /**
    * Implementa o passo Union(r, s) (Linha 10 do MST-Kruskal).
    * Recebe os diretores (r e s) de duas componentes de F e faz a fusão delas [3].
    * 
    * Usa a heurística Union-by-Rank para garantir que a árvore permaneça "achatada" 
    * (mantendo a eficiência). 
    * Se implementada com heurística union-by-rank, consome O(1) unidades de tempo [5].
    */
   public void uniao(int r, int s) {
       // Encontra as raízes (diretores) dos conjuntos de r e s (embora o Kruskal
       // chame Union com as raízes r e s já encontradas)
       int rootR = encontra(r);
       int rootS = encontra(s);

       // Se já estão na mesma componente, não faz nada (não deveria acontecer
       // se a verificação r != s no Kruskal for correta, mas é uma boa prática)
       if (rootR != rootS) {
           // Heurística Union-by-Rank: anexa a árvore de rank menor à árvore de rank maior.
           if (tamConjunto[rootR] < tamConjunto[rootS]) {
        	   pai[rootR] = rootS;
           } else if (tamConjunto[rootR] > tamConjunto[rootS]) {
        	   pai[rootS] = rootR;
           } else {
               // Ranks são iguais, escolha um para ser o novo diretor e aumente seu rank
        	   pai[rootS] = rootR;
        	   tamConjunto[rootR]++;
           }
       }
   }
}
