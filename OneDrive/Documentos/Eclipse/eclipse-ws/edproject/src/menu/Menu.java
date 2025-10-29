package menu;
import java.util.*;
import estrutura.GrafoND;
import estrutura.Digrafo;
import estrutura.Aresta;

// Importa as classes principais dos algoritmos
import dijkstra.DijkstraDirecionado;
import dijkstra.DijkstraDPonderado;
import dijkstra.DijkstraND;
import dijkstra.DijkstraNDPonderado;
import kruskal.Kruskal;
import prim.PrimMST;
import apagareverso.ApagaReverso;
import ordenacao.OrdenacaoPeso;

/**
 * Classe principal refatorada.
 * Agora utiliza as classes centrais do pacote 'core'.
 */
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
                    grafo.adicionarAresta(1, 2, 0);
                    grafo.adicionarAresta(1, 3, 0);
                    grafo.adicionarAresta(2, 4, 0);
                    grafo.imprimir();
                    break;
                case 2:
                    System.out.println("Executando: Lista de Adjacência (Direcionado)\n");
                    // Agora usa o Digrafo do 'core'
                    Digrafo digrafo = new Digrafo(); 
                    digrafo.adicionarAresta(1, 2, 0);
                    digrafo.adicionarAresta(1, 3, 0);
                    digrafo.adicionarAresta(2, 4, 0);
                    digrafo.imprimir();
                    break;
                case 3:
                    System.out.println("Executando: Menor Caminho (Dijkstra - Direcionado)\n");
                    Digrafo dDijkstra = new Digrafo();
                    dDijkstra.adicionarAresta(0, 1, 1);
                    dDijkstra.adicionarAresta(0, 2, 1);
                    dDijkstra.adicionarAresta(0, 3, 1);
                    dDijkstra.adicionarAresta(1, 2, 1);
                    dDijkstra.adicionarAresta(1, 4, 1);
                    dDijkstra.adicionarAresta(2, 3, 1);
                    dDijkstra.adicionarAresta(2, 4, 1);
                    dDijkstra.adicionarAresta(2, 5, 1);
                    dDijkstra.adicionarAresta(3, 4, 1);
                    dDijkstra.adicionarAresta(3, 5, 1);
                    dDijkstra.adicionarAresta(4, 5, 1);
                    DijkstraDirecionado buscaDir = new DijkstraDirecionado(dDijkstra);
                    buscaDir.dijkstra(0);
                    break;
                case 4:
                    System.out.println("Executando: Menor Caminho (Dijkstra - Direcionado Ponderado)\n");
                    Digrafo dPDijkstra = new Digrafo();
                    dPDijkstra.adicionarAresta(0, 1, 6);
                    dPDijkstra.adicionarAresta(0, 2, 1);
                    dPDijkstra.adicionarAresta(0, 3, 5);
                    dPDijkstra.adicionarAresta(1, 2, 2);
                    dPDijkstra.adicionarAresta(1, 4, 5);
                    dPDijkstra.adicionarAresta(2, 3, 2);
                    dPDijkstra.adicionarAresta(2, 4, 6);
                    dPDijkstra.adicionarAresta(2, 5, 4);
                    dPDijkstra.adicionarAresta(3, 4, 3);
                    dPDijkstra.adicionarAresta(3, 5, 4);
                    dPDijkstra.adicionarAresta(4, 5, 5); 
                    DijkstraDPonderado buscaDirP = new DijkstraDPonderado(dPDijkstra);
                    buscaDirP.dijkstra(0);
                    break;
                case 5:
                    System.out.println("Executando: Menor Caminho (Dijkstra - Não-Direcionado)\n");
                    GrafoND gDijkstra = new GrafoND();
                    gDijkstra.adicionarAresta(0, 1, 1);
                    gDijkstra.adicionarAresta(0, 2, 1);
                    gDijkstra.adicionarAresta(0, 3, 1);
                    gDijkstra.adicionarAresta(1, 2, 1);
                    gDijkstra.adicionarAresta(1, 4, 1);
                    gDijkstra.adicionarAresta(2, 3, 1);
                    gDijkstra.adicionarAresta(2, 4, 1);
                    gDijkstra.adicionarAresta(2, 5, 1);
                    gDijkstra.adicionarAresta(3, 4, 1);
                    gDijkstra.adicionarAresta(3, 5, 1);
                    gDijkstra.adicionarAresta(4, 5, 1);
                    DijkstraND buscaND = new DijkstraND(gDijkstra);
                    buscaND.dijkstra(0);
                    break;
                case 6:
                    System.out.println("Executando: Menor Caminho (Dijkstra - Não-Direcionado Ponderado)\n");
                    GrafoND gPDijkstra = new GrafoND();
                    gPDijkstra.adicionarAresta(0, 1, 6);
                    gPDijkstra.adicionarAresta(0, 2, 1);
                    gPDijkstra.adicionarAresta(0, 3, 5);
                    gPDijkstra.adicionarAresta(1, 2, 2);
                    gPDijkstra.adicionarAresta(1, 4, 5);
                    gPDijkstra.adicionarAresta(2, 3, 2);
                    gPDijkstra.adicionarAresta(2, 4, 6);
                    gPDijkstra.adicionarAresta(2, 5, 4);
                    gPDijkstra.adicionarAresta(3, 4, 3);
                    gPDijkstra.adicionarAresta(3, 5, 4);
                    gPDijkstra.adicionarAresta(4, 5, 5); 
                    DijkstraNDPonderado buscaNDP = new DijkstraNDPonderado(gPDijkstra);
                    buscaNDP.dijkstra(0);
                    break;
                case 7:
                    System.out.println("Executando: Árvore Geradora Mínima (Kruskal)\n");
                    GrafoND gKruskal = new GrafoND();
                    gKruskal.adicionarAresta(0, 1, 6);
                    gKruskal.adicionarAresta(0, 2, 1);
                    gKruskal.adicionarAresta(0, 3, 5);
                    gKruskal.adicionarAresta(1, 2, 2);
                    gKruskal.adicionarAresta(1, 4, 5);
                    gKruskal.adicionarAresta(2, 3, 2);
                    gKruskal.adicionarAresta(2, 4, 6);
                    gKruskal.adicionarAresta(2, 5, 4);
                    gKruskal.adicionarAresta(3, 4, 3);
                    gKruskal.adicionarAresta(3, 5, 4);
                    gKruskal.adicionarAresta(4, 5, 5); 

                    Kruskal arvoreKruskal = new Kruskal(gKruskal);
                    arvoreKruskal.kruskalMST();
            	  	
                    arvoreKruskal.imprimirMSTECustoTotal();
                    break;
                case 8:
                    System.out.println("Executando: Árvore Geradora Mínima (Prim)\n");
                    GrafoND gPrim = new GrafoND();
                    gPrim.adicionarAresta(0, 1, 6);
                    gPrim.adicionarAresta(0, 2, 1);
                    gPrim.adicionarAresta(0, 3, 5);
                    gPrim.adicionarAresta(1, 2, 2);
                    gPrim.adicionarAresta(1, 4, 5);
                    gPrim.adicionarAresta(2, 3, 2);
                    gPrim.adicionarAresta(2, 4, 6);
                    gPrim.adicionarAresta(2, 5, 4);
                    gPrim.adicionarAresta(3, 4, 3);
                    gPrim.adicionarAresta(3, 5, 4);
                    gPrim.adicionarAresta(4, 5, 5); 

                    PrimMST primAlg = new PrimMST();
                    int origemPrim = 0;
                    // Chama a função principal que calcula a MST e retorna o mapa de pais
                    Map<Integer, Integer> mstPrim = primAlg.arvoreGeradoraMinima(gPrim, origemPrim);
                    // APRESENTAÇÃO DO RESULTADO (Chamada do método refatorado)
                    primAlg.imprimirMSTECustoTotal(gPrim, mstPrim, origemPrim);
                    break;
                case 9:
                    System.out.println("Executando: Árvore Geradora Mínima (Apaga-Reverso)\n");
                    GrafoND gApaga = new GrafoND();
                    // Arestas de alto custo serão testadas primeiro       
                    gApaga.adicionarAresta(0, 1, 10);
                    gApaga.adicionarAresta(0, 2, 1);
                    gApaga.adicionarAresta(0, 3, 5);
                    gApaga.adicionarAresta(1, 2, 2);
                    gApaga.adicionarAresta(1, 4, 8);
                    gApaga.adicionarAresta(2, 3, 4);
                    gApaga.adicionarAresta(2, 4, 6);
                    gApaga.adicionarAresta(2, 5, 4);
                    gApaga.adicionarAresta(3, 4, 3);
                    gApaga.adicionarAresta(3, 5, 4);
                    gApaga.adicionarAresta(4, 5, 15); 

                    ApagaReverso ar = new ApagaReverso(gApaga);
                    Set<Aresta> mstApaga = ar.apagaReversoMST();
                    ar.imprimirMSTECustoTotal(mstApaga);
                    break;
                case 10:
                    System.out.println("Executando: Ordenação de Arestas por Peso\n");
                    GrafoND gOrdena = new GrafoND();
                    gOrdena.adicionarAresta(0, 2, 1);
                    gOrdena.adicionarAresta(0, 3, 5);
                    gOrdena.adicionarAresta(1, 2, 2);
                    gOrdena.adicionarAresta(1, 4, 8);
                    gOrdena.adicionarAresta(2, 3, 4);
                    gOrdena.adicionarAresta(2, 4, 6);
                    gOrdena.adicionarAresta(2, 5, 4);
                    gOrdena.adicionarAresta(3, 4, 3);
                    gOrdena.adicionarAresta(3, 5, 4);
                    gOrdena.adicionarAresta(4, 5, 15); 

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
                 scanner.nextLine(); // Consome o \n anterior
                 scanner.nextLine(); // Aguarda o Enter
            }
        }
        scanner.close();
    }


}