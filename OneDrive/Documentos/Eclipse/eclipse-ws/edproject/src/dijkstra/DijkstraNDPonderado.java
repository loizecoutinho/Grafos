/*
 * Menor caminho de grafos não direcionados ponderados
 */
package dijkstra;
import java.util.*;
import estrutura.GrafoND;

public class DijkstraNDPonderado {
	private GrafoND grafo;
	public DijkstraNDPonderado(GrafoND grafo) {
		this.grafo = grafo;
	}
	public void dijkstra(int origem) {
		Map<Integer, Integer> distancia = new HashMap<>();//guarda a menor distancia da origem até os vertices
		Map<Integer, Integer> anterior = new HashMap<>();//guarrda o vertice anterior no caminho mais curto
		Set<Integer> visitados = new HashSet<>();
		// Inicializa as distâncias
		for (int vertice : grafo.getVertices()) {
			distancia.put(vertice, Integer.MAX_VALUE);
			anterior.put(vertice, null);
		}
		distancia.put(origem, 0);
		// Fila de prioridade ordenada pela menor distância
		PriorityQueue<Integer> fila = new PriorityQueue<>(Comparator.comparingInt(distancia::get));
		fila.add(origem);
		while (!fila.isEmpty()) {
			int atual = fila.poll();
			if (!visitados.add(atual)) continue; // já visitado
			for (Map.Entry<Integer, Integer> vizinho : grafo.getVizinhos(atual).entrySet()) {
				int v = vizinho.getKey();
				int peso = vizinho.getValue();
				int novaDistancia = distancia.get(atual) + peso;
				if (novaDistancia < distancia.get(v)) {
					distancia.put(v, novaDistancia);
					anterior.put(v, atual);
					fila.add(v);
				}
			}
		}
		// Exibe o resultado
		for (int v : distancia.keySet()) {
			System.out.println("Distância de " + origem + " até " + v + " = " + distancia.get(v));
			System.out.print("Caminho: ");
			imprimirCaminho(v, anterior);
			System.out.println();
		}	
	}
	private void imprimirCaminho(int destino, Map<Integer, Integer> anterior) {
		if (anterior.get(destino) != null) {
			imprimirCaminho(anterior.get(destino), anterior);
		}	
		System.out.print(destino + " ");
	}

}
