package com.apagareverso;
import java.util.*;
import com.estrutura.GrafoND; 
import com.estrutura.Aresta; 


/*
 * O algoritmo do Apaga-Reverso começa com o grafo completo e remove arestas de maior custo
 * que não desconectam o grafo.
 */
public class ApagaReverso {

    // esse é o grafo que iremos retirar as arestas para encontrar a MST.
    final GrafoND grafoOriginal; //variavel final para armazenar o grafo original

    public ApagaReverso(GrafoND grafo) {
        this.grafoOriginal = grafo;
    }

    /*
     * Extrai e ordena todas as arestas do maior peso para o menor
     */
    private List<Aresta> ordenarArestasDecrescente(GrafoND G) {
        
        //pega todas as arestas únicas do grafo
        Set<Aresta> arestaSet = G.extractAllEdges(); 
        
        //Converte o Set de arestas em uma ArrayList(p/ordenação)
        List<Aresta> arestaOrdenada = new ArrayList<>(arestaSet);
        
        //Ordena a lista
        Collections.sort(arestaOrdenada); 
        
        //Inverte a lista(decrescente) para processar as arestas de maior peso primeiro
        Collections.reverse(arestaOrdenada);
        
        //Retorna a lista de arestas
        return arestaOrdenada;
    }
    
    /*
     * Verifica se o grafo G está conexo usando Busca em Largura
     */
    private boolean isConexo(GrafoND G) {
        
        // pega todos os vértices do grafo
        Set<Integer> vertices = G.getVertices(); 
        
        // Se o grafo não tem vértices, consideramos ele conexo
        if (vertices.isEmpty()) return true; //Verdade Vácua

        // Se o grafo tem vértices, inicia a BFS  a partir d eum vertice aleatorio
        int noInicial = vertices.iterator().next();//pega o primeiro elemento de um Set
        
        // armazena os vértices que já foram visitados
        Set<Integer> visitado = new HashSet<>();
        
        // Cria uma Fila para a BFS
        Queue<Integer> fila = new LinkedList<>(); // LinkedList - listas conectadas

        // Adiciona o nó inicial na fila e marca como visitado.
        fila.add(noInicial);
        visitado.add(noInicial);
        
        // Continua enquanto a fila não estiver vazia.
        while (!fila.isEmpty()) {
            
            // Remove o próximo vértice da fila
            int u = fila.poll();
            
            // pega todos os vizinhos de 'u'
            Map<Integer, Integer> vizinhos = G.getVizinhos(u);
           
            for (int v : vizinhos.keySet()) {//keySet() retorna todos os IDs dos vizinhos
                // verifica se 'v' não foi visitado
                if (!visitado.contains(v)) {
                    visitado.add(v);// marca 'v' como visitado.
                    fila.add(v);//adiciona 'v' na fila para visitar seus vizinhos depois
                }
            }
        }
        
        // Compara o número de vértices visitados com o número total de vértices no grafo.
        return visitado.size() == vertices.size(); //iguais = conexo; diferente = desconexo
    }

    /*
     * * @return Um conjunto de arestas que formam a MST.
     */
    public Set<Aresta> apagaReversoMST() {
        
        //pega a lista de todas as arestas
        List<Aresta> arestasDecrescentes = ordenarArestasDecrescente(this.grafoOriginal);
        
        // Obtém o número total de vértices
        int V = grafoOriginal.getVertices().size();
        
        // Uma MST (em grafo conexo) tem no minimo V-1 arestas.
        int arestasMinimas = V - 1;
        
        //percorre todas as arestas
        for (Aresta aresta : arestasDecrescentes) {
            // pega os vértices e o peso da aresta atual
            int u = aresta.getOrigem();
            int v = aresta.getDestino();
            int peso = aresta.getPeso();
            
            //remove se tiver mais arestas que o mínimo necessário (V-1)
            if (grafoOriginal.extractAllEdges().size() > arestasMinimas) {
                //Tenta remover a aresta (u, v) do grafo
                grafoOriginal.removerAresta(u, v); //remoção temporária
                
                //Verifica se o grafo continua conexo
                if (isConexo(grafoOriginal)) {
                    // O loop continua
                } else {
                    //aresta é crítica
                    grafoOriginal.adicionarAresta(u, v, peso);//restaura para manter a conectividade
                }
            } else {
                //o grafo já atingiu o número mínimo de arestas (V-1),
                break;
            }
        }
        // Retorna o conjunto de arestas restantes
        return grafoOriginal.extractAllEdges();
    }

    /*
     * Imprime as arestas da MST resultante e calcula o custo total
     */
    public void imprimirMSTECustoTotal(Set<Aresta> mst) {
        
        //acumulador para custo total
        double custoTotal = 0;
        
        System.out.println("\n--- Arestas da MST (Apaga-Reverso) ---");
        
        // Itera sobre as arestas da mst
        for (Aresta aresta : mst) {
            
            // Imprime origem, destino, peso
            System.out.println("Aresta: " + aresta.getOrigem() + " - " + aresta.getDestino() + " | Peso: " + aresta.getPeso());
            
            // Adiciona o peso ao custo total.
            custoTotal += aresta.getPeso();
        }
        
        // Imprime o número final de arestas na MST.
        System.out.println("\nNúmero final de arestas: " + mst.size());
        // Imprime o custo total 
        System.out.println("Custo Total da MST: " + custoTotal); 
    }
}
