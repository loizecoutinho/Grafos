package kruskal;


public class Aresta implements Comparable<Aresta> {

	private int origem;
	private int destino;
	private int peso;
	
	public Aresta(int origem, int destino, int peso) {
		// TODO Auto-generated constructor stub
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
	
	@Override
	public int compareTo(Aresta ordem) {
		return Integer.compare(this.peso, ordem.peso);
	}
	
}
