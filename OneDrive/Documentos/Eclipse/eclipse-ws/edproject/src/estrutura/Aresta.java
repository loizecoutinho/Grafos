package estrutura;
import java.util.*;

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
     */
    @Override
    public int compareTo(Aresta ordem) {
        return Integer.compare(this.peso, ordem.peso);
    }
    
    /**
     * Compara arestas não-direcionadas. (u, v) é igual a (v, u).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta aresta = (Aresta) o;
        
        // Verifica (u, v) ou (v, u)
        boolean mesmaAresta = (this.origem == aresta.origem && this.destino == aresta.destino) ||
                             (this.origem == aresta.destino && this.destino == aresta.origem);
        
        return mesmaAresta && this.peso == aresta.peso;
    }

    /**
     * Gera hashCode para arestas não-direcionadas.
     * O hash de (u, v) deve ser igual ao de (v, u).
     */
    @Override
    public int hashCode() {
        // Usa (origem + destino) para garantir que (u, v) e (v, u) tenham o mesmo hash
        return Objects.hash(origem + destino, peso);
    }

    @Override
    public String toString() {
        return "(" + origem + " - " + destino + ", Peso: " + peso + ")";
    }
}