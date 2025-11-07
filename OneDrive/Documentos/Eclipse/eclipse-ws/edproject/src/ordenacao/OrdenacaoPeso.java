package ordenacao;
import java.util.*;
import com.estrutura.GrafoND;
import com.estrutura.Aresta;

/**
 * Classe utilitária focada em extrair e ordenar as arestas de um grafo por peso
 */
public class OrdenacaoPeso {

    /**
     * Ordena todas as arestas do grafo por peso, em ordem CRESCENTE (menor peso primeiro).
     * Utiliza a ordenação natural definida em Aresta.compareTo().
     * @param grafo O grafo a ser analisado.
     * @return Uma lista de Arestas ordenada do menor peso para o maior.
     */
    public List<Aresta> ordenarArestasCrescente(GrafoND grafo) {
        
        //Extrai todas as arestas únicas do grafo 
        Set<Aresta> arestaSet = grafo.extractAllEdges();
        
        //Converte o Set para uma List
        List<Aresta> arestaOrdenada = new ArrayList<>(arestaSet);
        
        //Ordena a lista
        // A ordenação padrão é CRESCENTE
        Collections.sort(arestaOrdenada); 

        // Retorna a lista ordenada
        return arestaOrdenada;
    }

    /**
     * Ordena todas as arestas do grafo por peso, em ordem DECRESCENTE 
     * @param grafo O grafo a ser analisado.
     * @return Uma lista de Arestas ordenada do maior peso para o menor.
     */
    public List<Aresta> ordenarArestasDecrescente(GrafoND grafo) {
        
        //Reutiliza o método anterior para obter a lista em ordem CRESCENTE
        List<Aresta> arestaOrdenada = ordenarArestasCrescente(grafo);
        
        // Inverte a lista
        Collections.reverse(arestaOrdenada);

        // Retorna a lista invertida
        return arestaOrdenada;
    }
    
    /**
     * Imprime uma lista de arestas na ordem CRESCENTE
     * @param arestas A lista de arestas já ordenada.
     */
    public void imprimirOrdenacaoCrescente(List<Aresta> arestas) {
        System.out.println("--- Arestas ordenadas por peso (CRESCENTE) ---");
        
        // Itera sobre a lista de arestas 
        for (Aresta aresta : arestas) {
            System.out.println("Aresta: " + aresta.getOrigem() + " - " + aresta.getDestino() + " | Peso: " + aresta.getPeso());
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * Imprime uma lista de arestas na ordem DECRESCENTE
     * @param arestas A lista de arestas já ordenada.
     */
    public void imprimirOrdenacaoDecrescente(List<Aresta> arestas) {
        System.out.println("--- Arestas ordenadas por peso (DECRESCENTE) ---");
        
        // Itera sobre a lista de arestas 
        for (Aresta aresta : arestas) {
            System.out.println("Aresta: " + aresta.getOrigem() + " - " + aresta.getDestino() + " | Peso: " + aresta.getPeso());
        }
        System.out.println("------------------------------------------------");
    }
  
}
