import source.domain.Converter;
import source.domain.Graph;
import source.domain.Node;

import java.util.Scanner;
import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner option = new Scanner(System.in);
        int intChoice;
        boolean booleanChoice;
        float floatChoice;
        String stringChoice;
        Graph graph = new Graph(true);

        System.out.println("That program is to create or import" +
                "\n created by: \nIgor Rosa F. Pinto \nFernando Souza Pimenta");

        System.out.println("You like to create(1) or import(2)");
        intChoice = option.nextInt();
        if (intChoice == 1){
            System.out.println("That graph is a digraph ?\n true or false");
            booleanChoice = option.nextBoolean();
            graph = new Graph(booleanChoice);

            System.out.println("\n\n********************************\n\n");
            System.out.println("Now you have created a graph, you will enter with the nodes.");
            boolean nodesAdition = true;
            while(nodesAdition){
                System.out.println("Enter the label of the node.");
                stringChoice = option.next();
                graph.addNode(new Node(stringChoice));
                System.out.println("Enter if you want to create another node.\n true or false");
                booleanChoice = option.nextBoolean();
                nodesAdition = booleanChoice;
                System.out.println("\n");
            }
            System.out.println("\n***************************************\n\n");

            System.out.println("Now you have created the nodes, you will enter with the Edges.");
            boolean edgeAdition = true;
            while(edgeAdition){
                System.out.println("Enter the label of the source node.");
                String source_node = option.next();
                System.out.println("Enter the label of the destination node");
                String destination_node = option.next();
                System.out.println("Enter with the weight of the edge");
                floatChoice = option.nextFloat();
                graph.addEdge(source_node,destination_node, floatChoice);
                System.out.println("Enter if you want to create another edge.\n true or false");
                booleanChoice = option.nextBoolean();
                edgeAdition = booleanChoice;
                System.out.println("\n");
            }
        } else if (intChoice == 2) {
            System.out.println("Enter the path of the graph to be imported");
            stringChoice = option.next();
            graph = Converter.importGraph(stringChoice);
        } else {
            System.out.println("We dont have that option yet.");
            System.exit(1);
        }

        System.out.println("\n*******************************************\n\n");
        boolean gettingInformation = true;
        while(gettingInformation) {
            System.out.println("What do you want to visualize about the graph ?");
            System.out.println("Type:\n 1 to visualize graph order." +
                    "\n 2 to visualize the degree of the graph " +
                    "\n 3 to visualize the matrix of adjacency\n\n");
            intChoice = option.nextInt();
            if (intChoice == 1){
                System.out.println("The order of the graph is: "+graph.getOrder());
                System.out.println("You want to know another thing or not." +
                        "\n true to yes\n false to no");
                booleanChoice = option.nextBoolean();
                gettingInformation = booleanChoice;
                System.out.println("\n");
            } else if (intChoice == 2) {
                System.out.println("What node you want to know the degree?");
                System.out.println("Enter the label of the node!");
                stringChoice = option.next();
                System.out.println("The degree is: "+graph.getNodeDegree(stringChoice)+"\n");
                System.out.println("\n");
            } else if (intChoice == 3) {
                int[][] matrix = graph.getMatrixAdj();
                for(int i=0;i< graph.getOrder();i++) {
                    for (int j = 0; j < graph.getOrder(); j++) {
                        System.out.print(matrix[i][j] + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("We dont have that option yet.");
                System.exit(1);
            }
        }
    }
}