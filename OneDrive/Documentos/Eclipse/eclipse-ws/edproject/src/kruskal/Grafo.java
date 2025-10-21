package kruskal;
import java.util.*;

public class Grafo{
	//cria um mapa chamado adjacencia que irá armazenar os vertices e arestas
	private Map<Integer, Map<Integer,Integer>> adjacencia; //troquei set por Map para poder adicionar dois inteiros no meu paraemntro(destio, peso)
	
	//construtor que inicializa o mapa
	public Grafo() {
		// TODO Auto-generated constructor stub
		adjacencia = new HashMap<>();
	}
	
	public void adicionarVertices(int no) {
		//no é o identificador do vertice
		//adiciona o vertice à lista adjacente se o espaço estiver vazio
		adjacencia.putIfAbsent(no, new HashMap<>());		
	}
	
	//origem: vertice que a aresta inicia; destino: onde termina
	public void adicionarAresta(int origem, int destino, int peso) {
		//onde V1 atua como origem e V2 como destino
		adicionarVertices(origem);//inicializa o vertice
		adicionarVertices(destino);
		adjacencia.get(origem).put(destino, peso);//V1->V2
		adjacencia.get(destino).put(origem, peso);// V2->V1	
	}
	/*
	public Map<Integer, Integer> getVizinhos(int vertice) {
        return adjacencia.getOrDefault(vertice, new HashMap<>());
    }*/

    public Set<Integer> getVertices() {
        return adjacencia.keySet();
    }
	
	
	//extração das arestas do map 
	public Set<Aresta> extractAllEdges(){
		// Usamos um HashSet para garantir que cada aresta única seja coletada
	    Set<Aresta> allEdges = new HashSet<>();
		
	    // Itera sobre todos os vértices de origem (V1)
	    for(int origem : adjacencia.keySet()) {
	    	 // Pega o mapa de vizinhos/pesos para V1
	    	Map<Integer, Integer> vizinhos = adjacencia.get(origem);
	    	
	    	//Itera sobre cada aresta de saída: V1->V2 com 'peso'
	    	for(Map.Entry<Integer,Integer> entry : vizinhos.entrySet()) {
	    		int destino = entry.getKey();
	    		int peso = entry.getValue();
	    		
	    		//Cria objeto Aresta e o adiciona ao conjunto
	    		Aresta aresta = new Aresta(origem, destino, peso);
	    		allEdges.add(aresta);
	    	}
	    }
	    return allEdges;
	}

}

