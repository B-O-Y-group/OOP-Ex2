import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class open_json {
    public static void main(String[] args) {
        File input = new File("C:\\Users\\oron\\Desktop\\GitHub\\OOP-Ex2\\Ex2\\data");
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfEdge = fileObject.get("Edges").getAsJsonArray();
            List<Json_edge> edges = new ArrayList<>();
            for (JsonElement EdgesElement : jsonArrayOfEdge){
                JsonObject EdgesObjects = EdgesElement.getAsJsonObject();

                int src = EdgesObjects.get("src").getAsInt();
                double weight = EdgesObjects.get("weight").getAsDouble();
                int dest = EdgesObjects.get("dest").getAsInt();

                Json_edge edge = new Json_edge(src, weight, dest);
                edges.add(edge);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
