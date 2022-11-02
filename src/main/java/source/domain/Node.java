package source.domain;

public class Node {
    private String label;
    private Integer degree;

    public Node(String label){
        this.label = label;
        this.degree = 0;
    }

    public Integer getGrau() {
        return degree;
    }

}
