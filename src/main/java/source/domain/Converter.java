package source.domain;

import java.io.*;

public abstract class Converter {
    public static Graph importGraph(String filePath) throws IOException {
        filePath = filePath.replaceAll("\\\\", "/");
        Graph graph = new Graph(true);
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bfReader = new BufferedReader(fileReader);
        String line = bfReader.readLine();

        if (!line.contains("digraph")) {
            graph = new Graph(false);
        }
        while (bfReader.ready()) {
            line = bfReader.readLine();
            if (line.contains("}")) {
                return graph;
            }
            if (!line.contains("-")) {
                line = line.replaceAll(";", " ");
                if (!line.trim().equals("")) {
                    graph.addNode(new Node(line.trim()));
                }
            } else {
                String[] splitLine = line.split("--");
                if (graph.isDigraph()) {
                    splitLine = line.split("->");
                }
                graph.addNode(new Node(splitLine[0].trim()));
                graph.addNode(new Node("" + splitLine[1].trim().charAt(0)));

                if (line.contains("label=")) {
                    char[] chars = line.toCharArray();
                    StringBuilder weightString = new StringBuilder();
                    for (char character : chars) {
                        if (Character.isDigit(character) || character == '.') {
                            weightString.append(character);
                        }
                    }
                    float weight = Float.parseFloat(weightString.toString());
                    graph.addEdge(splitLine[0].trim(), "" + splitLine[1].trim().charAt(0), weight);
                } else {
                    graph.addEdge(splitLine[0].trim(), "" + splitLine[1].trim().charAt(0), 0);
                }
            }
        }
        return graph;
    }
}
