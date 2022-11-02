import source.domain.Graph;
import source.domain.Node;

public class App {
    public static void main(String[] args) {
        Graph graph = new Graph(false);

        graph.addNode(new Node("A"));
        graph.addNode(new Node("B"));
        graph.addNode(new Node("C"));
        graph.addNode(new Node("D"));
        graph.addNode(new Node("E"));

        graph.addEdge("A", "B", 10);

        int[][] matrix = graph.getMatrixAdj();
        for(int i=0;i< graph.getOrder();i++){
            for(int j=0;j< graph.getOrder();j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(graph.getWeightEdge("AB"));
    }
}
