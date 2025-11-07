package menu;
import java.util.*;
import com.estrutura.GrafoND;
import com.estrutura.Digrafo;
import com.estrutura.Aresta;
import com.dijkstra.DijkstraDirecionado;
import com.dijkstra.DijkstraDPonderado;
import com.dijkstra.DijkstraND;
import com.dijkstra.DijkstraNDPonderado;
import com.kruskal.Kruskal;
import com.prim.PrimMST;
import com.apagareverso.ApagaReverso;
import com.ordenacao.OrdenacaoPeso;

public class Menu {
	
    private static void exibirMenu() {
        System.out.println("\n\n---Menu Principal: Algoritmos em Grafos ---");
        System.out.println("\n---Lista de adjacência ---");
        System.out.println("1. Grafo Não-Direcionado");
        System.out.println("2. Grafo Direcionado");
        System.out.println("--- Menor Caminho por Dijkstra ---");
        System.out.println("3. Direcionado Não Ponderado");
        System.out.println("4. Direcionado Ponderado");
        System.out.println("5. Não-Direcionado Não Ponderado");
        System.out.println("6. Não-Direcionado Ponderado");
        System.out.println("--- Árvore Geradora Mínima (MST) ---");
        System.out.println("7. Kruskal");
        System.out.println("8. Prim");
        System.out.println("9. Apaga-Reverso");
        System.out.println("--- Operações ---");
        System.out.println("10. Ordenação de Arestas por Peso");
        System.out.println("---");
        System.out.println("0. Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            exibirMenu();
            int opcao; //=-1;

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\n[ERRO] Por favor, digite um número válido.");
                scanner.next(); 
                continue;
            }

            System.out.println("\n---======================================---");

            switch (opcao) {
                case 1:
                    System.out.println("Executando: Lista de Adjacência (Não-Direcionado)\n");
                    // Agora usa o Grafo do 'estrutura'
                    GrafoND grafo = new GrafoND(); 
                    grafo.adicionarAresta(0, 1, 2);
                    grafo.adicionarAresta(0, 2, 3);
                    grafo.adicionarAresta(0, 3, 5);
                    grafo.adicionarAresta(1, 2, 2);
                    grafo.adicionarAresta(2, 3, 5);
                    grafo.imprimir();
                    break;
                case 2:
                    System.out.println("Executando: Lista de Adjacência (Direcionado)\n");
                    // Agora usa o Digrafo do 'estrutura'
                    Digrafo digrafo = new Digrafo(); 
                    digrafo.adicionarAresta(0, 1, 2);//2
                    digrafo.adicionarAresta(0, 2, 3);
                    digrafo.adicionarAresta(0, 3, 5);//3
                    digrafo.adicionarAresta(1, 2, 2);//3
                    digrafo.adicionarAresta(2, 3, 5);//3
                    digrafo.imprimir();
                    break;
                case 3:
                    System.out.println("Executando: Menor Caminho (Dijkstra - Direcionado)\n");
                    Digrafo dDijkstra = new Digrafo();
                    dDijkstra.adicionarAresta(0, 1, 1);
                    dDijkstra.adicionarAresta(0, 3, 1);
                    dDijkstra.adicionarAresta(3, 2, 1);
                    dDijkstra.adicionarAresta(1, 2, 1);
                    dDijkstra.adicionarAresta(2, 1, 1);
                    DijkstraDirecionado buscaDir = new DijkstraDirecionado(dDijkstra);
                    buscaDir.dijkstra(0);
                    break;
                case 4:
                    System.out.println("Executando: Menor Caminho (Dijkstra - Direcionado Ponderado)\n");
                    Digrafo dPDijkstra = new Digrafo();
                    dPDijkstra.adicionarAresta(0, 1, 2);
                    dPDijkstra.adicionarAresta(0, 3, 3);
                    dPDijkstra.adicionarAresta(3, 2, 1);
                    dPDijkstra.adicionarAresta(1, 2, 3);
                    dPDijkstra.adicionarAresta(2, 1, 3); 
                    DijkstraDPonderado buscaDirP = new DijkstraDPonderado(dPDijkstra);
                    buscaDirP.dijkstra(0);
                    break;
                case 5:
                    System.out.println("Executando: Menor Caminho (Dijkstra - Não-Direcionado)\n");
                    GrafoND gDijkstra = new GrafoND();
                    gDijkstra.adicionarAresta(0, 1, 1);
                    gDijkstra.adicionarAresta(0, 3, 1);
                    gDijkstra.adicionarAresta(1, 2, 1);
                    gDijkstra.adicionarAresta(2, 3, 1);
                    gDijkstra.adicionarAresta(0, 2, 1);
                    DijkstraND buscaND = new DijkstraND(gDijkstra);
                    buscaND.dijkstra(0);
                    break;
                case 6:
                    System.out.println("Executando: Menor Caminho (Dijkstra - Não-Direcionado Ponderado)\n");
                    GrafoND gPDijkstra = new GrafoND();
                    gPDijkstra.adicionarAresta(0, 1, 2);
                    gPDijkstra.adicionarAresta(0, 3, 5);
                    gPDijkstra.adicionarAresta(1, 2, 2);
                    gPDijkstra.adicionarAresta(2, 3, 5);
                    gPDijkstra.adicionarAresta(0, 2, 3);
                    DijkstraNDPonderado buscaNDP = new DijkstraNDPonderado(gPDijkstra);
                    buscaNDP.dijkstra(0);
                    break;
                case 7:
                    System.out.println("Executando: Árvore Geradora Mínima (Kruskal)\n");
                    GrafoND gKruskal = new GrafoND();
                    gKruskal.adicionarAresta(0, 1, 2);
                    gKruskal.adicionarAresta(0, 3, 5);
                    gKruskal.adicionarAresta(1, 2, 2);
                    gKruskal.adicionarAresta(2, 3, 5);
                    gKruskal.adicionarAresta(0, 2, 3);

                    Kruskal arvoreKruskal = new Kruskal(gKruskal);
                    arvoreKruskal.kruskalMST();
            	  	
                    arvoreKruskal.imprimirMSTECustoTotal();
                    break;
                case 8:
                    System.out.println("Executando: Árvore Geradora Mínima (Prim)\n");
                    GrafoND gPrim = new GrafoND();
                    gPrim.adicionarAresta(0, 1, 2);
                    gPrim.adicionarAresta(0, 3, 5);
                    gPrim.adicionarAresta(1, 2, 2);
                    gPrim.adicionarAresta(2, 3, 5);
                    gPrim.adicionarAresta(0, 2, 3);

                    PrimMST primAlg = new PrimMST();
                    int origemPrim = 0;
                    // Chama a função principal que calcula a MST e retorna o mapa de pais
                    Map<Integer, Integer> mstPrim = primAlg.arvoreGeradoraMinima(gPrim, origemPrim);
                    // APRESENTAÇÃO DO RESULTADO
                    primAlg.imprimirMSTECustoTotal(gPrim, mstPrim, origemPrim);
                    break;
                case 9:
                    System.out.println("Executando: Árvore Geradora Mínima (Apaga-Reverso)\n");
                    GrafoND gApaga = new GrafoND();
                    // Arestas de alto custo serão testadas primeiro       
                    gApaga.adicionarAresta(0, 1, 2);
                    gApaga.adicionarAresta(0, 3, 5);
                    gApaga.adicionarAresta(1, 2, 2);
                    gApaga.adicionarAresta(2, 3, 5);
                    gApaga.adicionarAresta(0, 2, 3);

                    ApagaReverso ar = new ApagaReverso(gApaga);
                    Set<Aresta> mstApaga = ar.apagaReversoMST();
                    ar.imprimirMSTECustoTotal(mstApaga);
                    break;
                case 10:
                    System.out.println("Executando: Ordenação de Arestas por Peso\n");
                    GrafoND gOrdena = new GrafoND();
                    gOrdena.adicionarAresta(0, 1, 2);
                    gOrdena.adicionarAresta(0, 3, 5);
                    gOrdena.adicionarAresta(1, 2, 2);
                    gOrdena.adicionarAresta(2, 3, 5);
                    gOrdena.adicionarAresta(0, 2, 3);
                    OrdenacaoPeso op = new OrdenacaoPeso();
                    //ORDENAÇÃO CRESCENTE
                    List<Aresta> crescente = op.ordenarArestasCrescente(gOrdena);
                    //IMPRESSÃO CRESCENTE
                    op.imprimirOrdenacaoCrescente(crescente);
                    //ORDENAÇÃO DECRESCENTE
                    List<Aresta> decrescente = op.ordenarArestasDecrescente(gOrdena);
                    //IMPRESSÃO DECRESCENTE
                    op.imprimirOrdenacaoDecrescente(decrescente);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    executando = false;
                    break;
                default:
                    System.out.println("[AVISO] Opção inválida. Tente novamente.");
            }
            
            if (executando) {
                 System.out.println("\n---======================================---");
                 System.out.print("Pressione Enter para voltar ao menu...");
                 scanner.nextLine();
                 scanner.nextLine(); // Aguarda o Enter
            }
        }
        scanner.close();
    }


}
