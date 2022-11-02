package source.domain;

public class Edge {
    private Node senderNode;
    private Node RecipientNode;

    private Integer weigth;

    public Edge(Node senderNode, Node RecipientNode, int weigth){
        this.senderNode = senderNode;
        this.RecipientNode = RecipientNode;
        this.weigth = weigth;
    }

    public Integer getPeso() {
        return weigth;
    }
}
