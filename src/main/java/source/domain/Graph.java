package source.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private List<Node> nodeList;
    private int numberNode;
    private boolean digraph;
    private int[][] matrixAdj;
    private Map<String, Float> weightEdges;
    private Map<String, Node> nodeLabel;

    public Graph(boolean digraph) {
        this.digraph = digraph;
        this.nodeList = new ArrayList<>();
        this.numberNode = 0;
        this.weightEdges = new HashMap<>();
        this.nodeLabel = new HashMap<>();
    }

    public void addNode(Node node) {
        this.nodeList.add(node);
        node.setIndex(this.numberNode);
        this.nodeLabel.put(node.getLabel(), node);
        this.numberNode++;
        this.createMatrixAdj();
    }

    public void createMatrixAdj() {
        this.matrixAdj = new int[this.numberNode][this.numberNode];
        for (int i = 0; i < this.numberNode; i++) {
            for (int j = 0; j < this.numberNode; j++) {
                this.matrixAdj[i][j] = 0;
            }
        }
    }

    public void addEdge(String source, String destination, float weight) {
        this.attMatrix(this.nodeLabel.get(source).getIndex(), this.nodeLabel.get(destination).getIndex());
        this.nodeLabel.get(source).setDegree(1);
        this.nodeLabel.get(destination).setDegree(1);
        this.weightEdges.put(source + destination, weight);
    }

    public void attMatrix(int source, int destination) {
        this.matrixAdj[source][destination] = 1;
        if (!digraph) {
            this.matrixAdj[destination][source] = 1;
        }
    }

    public int getOrder() {
        return this.numberNode;
    }

    public int[][] getMatrixAdj() {
        return this.matrixAdj;
    }

    public float getWeightEdge(String edge) {
        return this.weightEdges.get(edge);
    }

    public int getNodeDegree(String node) {
        return this.nodeLabel.get(node).getDegree();
    }
}
