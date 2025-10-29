package ordenacao;
import java.util.*;
import estrutura.GrafoND;
import estrutura.Aresta;

public class OrdenacaoPeso {

    /**
     * Ordena todas as arestas do grafo por peso, em ordem CRESCENTE (menor peso primeiro).
     * Utiliza a ordenação natural definida em Aresta.compareTo().
     * @param grafo O grafo a ser analisado.
     * @return Uma lista de Arestas ordenada do menor peso para o maior.
     */
    public List<Aresta> ordenarArestasCrescente(GrafoND grafo) {
        
        // 1. Extrai todas as arestas
        Set<Aresta> arestaSet = grafo.extractAllEdges();
        List<Aresta> arestaOrdenada = new ArrayList<>(arestaSet);
        
        // 2. Ordena usando Aresta.compareTo (ordem crescente) [1]
        Collections.sort(arestaOrdenada); 

        return arestaOrdenada;
    }

    /**
     * Ordena todas as arestas do grafo por peso, em ordem DECRESCENTE (maior peso primeiro).
     * 
     * @param grafo O grafo a ser analisado.
     * @return Uma lista de Arestas ordenada do maior peso para o menor.
     */
    public List<Aresta> ordenarArestasDecrescente(GrafoND grafo) {
        
        // 1. Extraímos e ordenamos em ordem crescente primeiro
        List<Aresta> arestaOrdenada = ordenarArestasCrescente(grafo);
        
        // 2. Invertemos a lista para obter a ordem decrescente
        Collections.reverse(arestaOrdenada);

        /* Alternativa: Usar um Comparator.reverseOrder() na chamada Collections.sort:
           Collections.sort(arestaOrdenada, Collections.reverseOrder());
           Isso atinge o mesmo resultado, aproveitando o Comparable existente.
        */

        return arestaOrdenada;
    }
    
    /**
     * Imprime uma lista de arestas na ordem CRESCENTE.
     * 
     * @param arestas A lista de arestas já ordenada.
     */
    public void imprimirOrdenacaoCrescente(List<Aresta> arestas) {
        System.out.println("--- Arestas ordenadas por peso (CRESCENTE) ---");
        for (Aresta aresta : arestas) {
            // Utilizamos os getters da classe Aresta para exibir as informações de forma clara
            System.out.println("Aresta: " + aresta.getOrigem() + " - " + aresta.getDestino() + " | Peso: " + aresta.getPeso());
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * Imprime uma lista de arestas na ordem DECRESCENTE.
     * 
     * @param arestas A lista de arestas já ordenada.
     */
    public void imprimirOrdenacaoDecrescente(List<Aresta> arestas) {
        System.out.println("--- Arestas ordenadas por peso (DECRESCENTE) ---");
        for (Aresta aresta : arestas) {
            // Utilizamos os getters da classe Aresta para exibir as informações de forma clara
            System.out.println("Aresta: " + aresta.getOrigem() + " - " + aresta.getDestino() + " | Peso: " + aresta.getPeso());
        }
        System.out.println("------------------------------------------------");
    }
    
    // Métodos de Ordenação (Omitidos aqui para brevidade, mas devem existir)
    // public List<Aresta> ordenarArestasCrescente(Grafo grafo) { ... }
    // public List<Aresta> ordenarArestasDecrescente(Grafo grafo) { ... }
}