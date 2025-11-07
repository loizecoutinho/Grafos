package estrutura;
import java.util.*;
// 'implements Comparable<Aresta>' define um método (compareTo)que permite que listas de Arestas sejam ordenadas
public class Aresta implements Comparable<Aresta> {
    
    private final int origem;
    private final int destino;
    private final int peso;

    public Aresta(int origem, int destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public int getOrigem() {
        return origem;
    }

    public int getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    /**
     * Define a ordem natural: arestas de menor peso vêm primeiro.
     * É usado por 'Collections.sort()'.
     */
    @Override
    public int compareTo(Aresta outraAresta) {
        // Compara o peso desta Aresta com o peso da 'outraAresta'.
        // ordenação crescente
        return Integer.compare(this.peso, outraAresta.peso);
    }
    
    /**
     * Compara arestas não-direcionadas
     */
    @Override
    public boolean equals(Object o) {
        // Verifica se os objetos são exatamente a mesma instância na memória
        if (this == o) return true;
        
        //Verifica se 'o' é nulo ou se não é da classe Aresta
        if (o == null || getClass() != o.getClass()) return false;
        
        //Converte o Object 'o' para o tipo Aresta
        Aresta aresta = (Aresta) o;
        
        // Verifica se (A.origem == B.origem and A.destino == B.destino)
        boolean mesmaAresta = (this.origem == aresta.origem && this.destino == aresta.destino) ||
                              // Verifica se (A.origem == B.destino and A.destino == B.origem)
                             (this.origem == aresta.destino && this.destino == aresta.origem);
        
        //Retorna verdadeiro se os vértices e o peso forem iguais
        return mesmaAresta && this.peso == aresta.peso;
    }

    /**
     * O hash de (u, v) deve ser igual ao de (v, u).
     * Se a.equals(b) é true, então a.hashCode() deve ser igual a b.hashCode()
     */
    @Override 
    public int hashCode() {
        // 'Objects.hash(origem, destino, peso)' != 'Objects.hash(origem + destino, peso)'
        //o hash de (1, 2, 10) seria DIFERENTE de (2, 1, 10), etnão a soma garante que (u,v) tenha o mesmo hash que (v,u)
        return Objects.hash(origem + destino, peso);
    }

    /**
     * Retorna uma representação em String da Aresta.
     * Usado para imprimir o objeto (ex: System.out.println(minhaAresta)).
     */
    @Override 
    public String toString() {
        return "(" + origem + " - " + destino + ", Peso: " + peso + ")";
    }
}
