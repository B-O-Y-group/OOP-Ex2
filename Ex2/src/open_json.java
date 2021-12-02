public class open_json {
    public static void main(String[] args) {














//        File input = new File("C:\\Users\\oron\\Desktop\\GitHub\\OOP-Ex2\\Ex2\\data");
//        try {
//            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
//            JsonObject fileObject = fileElement.getAsJsonObject();
//
//            JsonArray jsonArrayOfEdge = fileObject.get("Edges").getAsJsonArray();
//            List<Json_edge> edges = new ArrayList<>();
//            for (JsonElement EdgesElement : jsonArrayOfEdge){
//                JsonObject EdgesObjects = EdgesElement.getAsJsonObject();
//
//                int src = EdgesObjects.get("src").getAsInt();
//                Double weight = EdgesObjects.get("weight").getAsDouble();
//                int dest = EdgesObjects.get("dest").getAsInt();
//
//                Json_edge edge = new Json_edge(src, weight, dest);
//                edges.add(edge);
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("Error input file not found!");
//            e.printStackTrace();
//        }
//        catch (Exception e){
//            System.err.println("Error processing input file!");
//            e.printStackTrace();
//        }
    }
}
