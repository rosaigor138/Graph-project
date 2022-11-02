package source.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private List<Node> nodeList;
    private int numberNode;
    private boolean digraph;
    private int[][] matrix;
    private Map<String, Integer> weightEdges;
    private Map<String, Integer> nodeIndex;

    public Graph(boolean digraph){
        this.digraph = digraph;
        this.nodeList = new ArrayList<>();
        this.numberNode = 0;
        this.weightEdges = new HashMap<>();
        this.nodeIndex = new HashMap<>();
    }

    public void createMatrix(){
        this.matrix = new int[this.numberNode][this.numberNode];
        for(int i=0; i<this.numberNode;i++){
            for(int j=0; j<this.numberNode;j++){
                this.matrix[i][j] = 0;
            }
       }
    }
    public int getOrder(){
        return this.numberNode;
    }

    public int[][] getMatrixAdj(){
        return this.matrix;
    }

    public void addEdge(String source, String destination, int weight){
        this.attMatrix(this.nodeIndex.get(source), this.nodeIndex.get(destination));
        this.weightEdges.put(source + destination, weight);
    }

    public void attMatrix(int source, int destination){
        this.matrix[source][destination] = 1;
        if(!digraph){
            this.matrix[destination][source] = 1;
        }
    }

    public void addNode(Node node){
        this.nodeList.add(node);
        node.setIndex(this.numberNode);
        this.nodeIndex.put(node.getLabel(), node.getIndex());
        this.numberNode++;
        this.createMatrix();
    }

    public int getWeightEdge(String edge){
        return this.weightEdges.get(edge);
    }
}
