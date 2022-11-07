import source.domain.Graph;
import source.domain.Node;

public class App {
    public static void main(String[] args) {
        Graph graph = new Graph(true);

        graph.addNode(new Node("A"));
        graph.addNode(new Node("B"));
        graph.addNode(new Node("C"));

        graph.addEdge("A", "B", 10);
        graph.addEdge("A", "A", 20);

        int[][] matrix = graph.getMatrixAdj();
        for(int i=0;i< graph.getOrder();i++){
            for(int j=0;j< graph.getOrder();j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("The weight of edge AB is: " + graph.getWeightEdge("AB"));
        System.out.println("The weight of edge AA is: " + graph.getWeightEdge("AA"));
        System.out.println("The degree of node A is: " + graph.getNodeDegree("A"));
        System.out.println("The degree of node B is: " + graph.getNodeDegree("B"));
        System.out.println("The degree of node C is: " + graph.getNodeDegree("C"));
    }
}
