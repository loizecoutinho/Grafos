/*
 * Lista de adjacência de grafos direcionados
 */
package grafos;
import java.util.*;

public class Digrafo {
	//cria um mapa chamado adjacencia que irá armazenar os vertices e arestas
	private Map<Integer, Map<Integer,Integer>> adjacencia; //troquei set por Map para poder adicionar dois inteiros no meu paraemntro(destio, peso)
	
	//construtor que inicializa o mapa
	public Digrafo() {
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
	}
	
	public void imprimir() {
		for (int vertice : adjacencia.keySet()) {
			System.out.print("Vértice " + vertice + ": ");
			for (Map.Entry<Integer, Integer> vizinho : adjacencia.get(vertice).entrySet()) {
				System.out.print(vizinho.getKey() + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Digrafo grafo = new Digrafo();//inicializaçaõ do meu mapa vazio;
	      	grafo.adicionarAresta(1, 2,0);//1->2
	      	grafo.adicionarAresta(1, 3,0);//1->3 
	      	grafo.adicionarAresta(2, 4,0);//2->4 
	      	grafo.imprimir();
	      	//a quantidade de vizinhos mostra o grau do vertice
		}

}

