package dijkstra;
import java.util.*;

public class GrafoND {
	//cria um mapa chamado adjacencia que irá armazenar os vertices e arestas
	private Map<Integer, Map<Integer,Integer>> adjacencia; //troquei set por Map para poder adicionar dois inteiros no meu paraemntro(destio, peso)
	
	//construtor que inicializa o mapa
	public GrafoND() {
		// TODO Auto-generated constructor stub
		adjacencia = new HashMap<>();
	}
	
	public void adicionarVertices(int no) {
		//no é o identificador do vertice
		//adiciona o vertice à lista adjacente se o espaço estiver vazio
		adjacencia.putIfAbsent(no, new HashMap<>());		
	}
	
	//origem: vertice que a aresta inicia; destino: onde termina
	public void adicionarAresta(int V1, int V2, int peso) {
		//onde V1 atua como origem e V2 como destino
		adicionarVertices(V1);//inicializa o vertice
		adicionarVertices(V2);
		adjacencia.get(V1).put(V2, peso);//V1->V2
		adjacencia.get(V2).put(V1, peso);// V2->V1	
	}
	
	public Map<Integer, Integer> getVizinhos(int vertice) {
        return adjacencia.getOrDefault(vertice, new HashMap<>());
    }

    public Set<Integer> getVertices() {
        return adjacencia.keySet();
    }

}
