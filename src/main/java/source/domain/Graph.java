package source.domain;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodeList;
    private Integer numberNode;
    private boolean digraph;
    private List<Edge> edgeList;


    public Graph(boolean digraph){
        this.digraph = digraph;
        this.numberNode = 0;
        this.nodeList = new ArrayList<>();
        this.edgeList = new ArrayList<>();

    }

    public int getOrder(){
        return this.numberNode;
    }

    public void getMatrizAdj(){
        int[][] matrix = new int[this.numberNode][this.numberNode];
        if (!digraph){
            
        }
    }

    public void addEdge(Edge obj){
        this.edgeList.add(obj);
    }

    public void addNode(Node obj){
        this.nodeList.add(obj);
        this.numberNode += 1;
    }
}
