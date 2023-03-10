package source.domain;

import java.util.*;

public class Graph {
    public List<Node> nodeList;
    private int numberNode;
    private boolean digraph;
    private int[][] matrixAdj;
    private List<String> listEdges;
    public Map<String, Float> weightEdges;
    public Map<String, Node> nodeLabel;
    public Map<String, String> edgeNodes;

    public Graph(boolean digraph) {
        this.digraph = digraph;
        this.nodeList = new ArrayList<>();
        this.numberNode = 0;
        this.weightEdges = new HashMap<>();
        this.nodeLabel = new HashMap<>();
        this.edgeNodes = new HashMap<>();
        this.listEdges = new ArrayList<>();
    }

    public void addNode(Node node) {
        if (!nodeLabel.containsKey(node.getLabel())) {
            this.nodeList.add(node);
            this.nodeLabel.put(node.getLabel(), node);
            this.numberNode++;
        }
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
        this.nodeLabel.get(source).setDegree(1);
        this.nodeLabel.get(destination).setDegree(1);
        this.edgeNodes.put("e" + this.listEdges.size(), source + destination);
        this.weightEdges.put("e" + this.listEdges.size(), weight);
        this.listEdges.add("e" + this.listEdges.size());
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
        this.createMatrixAdj();
        int value = 0;
        for (Node node : nodeLabel.values()) {
            node.setIndex(value);
            value++;
        }
        for (String edges : listEdges) {
            this.matrixAdj[this.nodeLabel.get("" + this.edgeNodes.get(edges).charAt(0)).getIndex()]
                    [this.nodeLabel.get("" + this.edgeNodes.get(edges).charAt(1)).getIndex()] += 1;
            if (!digraph) {
                this.matrixAdj[this.nodeLabel.get("" + this.edgeNodes.get(edges).charAt(1)).getIndex()]
                        [this.nodeLabel.get("" + this.edgeNodes.get(edges).charAt(0)).getIndex()] += 1;
            }
        }
        return this.matrixAdj;
    }
    
    public boolean isSimpleGraph() {
        getMatrixAdj();
        for (int i = 0; i < this.numberNode; i++) {
            if (this.matrixAdj[i][i] != 0) {
                return false;
            }
            for (int j = 0; j < this.numberNode; j++) {
                if (!this.digraph) {
                    if (this.matrixAdj[i][j] > 1) {
                        return false;
                    }
                } else {
                    if (this.matrixAdj[i][j] + this.matrixAdj[j][i] > 1) {
                        return false;
                    }
                }
            }
        }return true;
    }

    public boolean isRegularGraph(){
        for(int i = 0; i<getNodeList().size(); i ++){
            if (i > 0 && this.nodeList.get(i).getDegree() != this.nodeList.get(i--).getDegree()){
                return false;
            }
        }return true;
    }
    public boolean isCompleteGraph(){
        for(Node node: getNodeList()){
            if(node.getDegree() != getNodeList().size()-1){
                return false;
            }
        }return true;
    }

    public boolean isThereAWay(String source, String destination){
        this.getMatrixAdj();
        boolean[] visited = new boolean[this.numberNode];
        this.checkWay(this.nodeLabel.get(source).getIndex(), visited);
        return visited[this.nodeLabel.get(destination).getIndex()];
    }

    public void checkWay(int source, boolean[] visited){
        if (visited[source]){
            return;
        }
        else{
            visited[source] = true;
        }

        for (int i = 0; i < this.numberNode; i++){
            if (this.matrixAdj[source][i] >= 1){
                checkWay(i, visited);
            }
        }
    }

    public List<String> sinkList(){
        getMatrixAdj();
        ArrayList<String> listSink = new ArrayList<>();
        int sum = 0;
        if (digraph) {
            for (int i = 0; i < numberNode; i++) {
                for (int j = 0; j < numberNode; j++) {
                    sum = sum + this.matrixAdj[i][j];
                }
                if (sum == 0) {
                    listSink.add(this.nodeList.get(i).getLabel());
                }
                sum = 0;
            }
            return listSink;
        }return listSink;
    }

    public List<String> sourceList(){
        getMatrixAdj();
        ArrayList<String> listSource = new ArrayList<>();
        int sum = 0;
        if (digraph){
            for (int j =0; j < numberNode; j++){
                for (int i= 0; i<numberNode;i++){
                    sum = sum + this.matrixAdj[i][j];
                }if (sum == 0){
                    listSource.add(this.nodeList.get(j).getLabel());
                }sum =0;
            }return listSource;
        }
        return listSource;
    }

    public List<String> checkDirectTransitivity(String node){
        this.getMatrixAdj();
        boolean[] visited = new boolean[this.numberNode];
        ArrayList<String> transitivityList = new ArrayList<>();
        this.checkWay(this.nodeLabel.get(node).getIndex(), visited);
        for (int i = 0; i < this.numberNode; i++){
            if (visited[i]){
                transitivityList.add(this.nodeList.get(i).getLabel());
            }
        }
        return transitivityList;
    }

    public  List<String> checkIndirectTransitivity(String node){
        this.getMatrixAdj();
        ArrayList<String> intransitivityList = new ArrayList<>();
        String nodeSource = "";
        for (int i= 0; i < this.numberNode; i++){
            nodeSource = this.nodeList.get(i).getLabel();
            if(this.isThereAWay(nodeSource,node)){
                intransitivityList.add(nodeSource);
            }
        }return intransitivityList;
    }

    public boolean isConnect(){
        this.getMatrixAdj();
        boolean[] visited = new boolean[this.numberNode];
        this.checkConnectivity(0, visited);
        for (int i = 0; i < this.numberNode; i++){
            if (!visited[i]){
                return false;
            }
        }
        return true;
    }

    public void checkConnectivity(int source, boolean[] visited){
        if (visited[source]){
            return;
        }
        else{
            visited[source] = true;
        }

        for (int i = 0; i < this.numberNode; i++) {
            if (this.matrixAdj[source][i] >= 1) {
                checkConnectivity(i, visited);
            }
            if (this.matrixAdj[i][source] >= 1) {
                checkConnectivity(i, visited);
            }
        }
    }

    public void printDijkstra(String node){
        float[] list = this.getDijkstra(node);
        for (int i = 0; i < this.numberNode; i++){
            System.out.println(this.nodeList.get(i).getLabel() + " - " + list[i]);
        }
    }

    public float[] getDijkstra(String node) {
        this.getMatrixAdj();
        float[] listDijkstra = new float[this.numberNode];
        boolean[] visited = new boolean[this.numberNode];
        for (int i = 0; i < this.numberNode; i++) {
            listDijkstra[i] = Integer.MAX_VALUE;
        }
        listDijkstra[nodeLabel.get(node).getIndex()] = 0;
        this.checkWay(nodeLabel.get(node).getIndex(), visited, listDijkstra);
        return listDijkstra;
    }

    public void checkWay(int source, boolean[] visited, float[] listDijkstra){
        float minValue = Integer.MAX_VALUE;
        int minValueNode = 0;

        if (visited[source]){
            return;
        }
        else{
            visited[source] = true;
        }

        for (int i = 0; i < this.numberNode; i++){
            if (this.matrixAdj[source][i] >= 1){
                for (Map.Entry<String, String> set :
                        edgeNodes.entrySet()) {
                    if (Objects.equals(set.getValue(), nodeList.get(source).getLabel() +
                            nodeList.get(i).getLabel())){
                        if (listDijkstra[i] > this.weightEdges.get(set.getKey()) + listDijkstra[source]){
                            listDijkstra[i] = this.weightEdges.get(set.getKey()) + listDijkstra[source];
                        }
                    }
                }
            }
            if (listDijkstra[i] < minValue && !visited[i]){
                minValue = listDijkstra[i];
                minValueNode = i;
            }
        }
        this.checkWay(minValueNode, visited, listDijkstra);
    }

    public float getWeightEdge(String edge) {
        return this.weightEdges.get(edge);
    }

    public int getNodeDegree(String node) {
        return this.nodeLabel.get(node).getDegree();
    }

    public boolean isDigraph() {
        return this.digraph;
    }

    public Map<String, Node> getNodeLabel() {
        return this.nodeLabel;
    }

    public List<Node> getNodeList() {
        return this.nodeList;
    }

    public List<String> getListEdges() {
        return this.listEdges;
    }

    public Map<String, Float> getWeightEdges() {
        return this.weightEdges;
    }

    public Map<String, String> getEdgeNodes() {
        return this.edgeNodes;
    }
}
