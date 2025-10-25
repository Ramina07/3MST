package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class JsonHandler {

    public static class GraphData {
        public int id;
        public String description;
        public List<String> nodes;
        public List<EdgeData> edges;
    }

    public static class EdgeData {
        public String from;
        public String to;
        public double weight;
    }

    public static List<GraphData> readGraphsFromJson(String inputPath) {
        try (Reader reader = new FileReader(inputPath)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            Type listType = new TypeToken<List<GraphData>>(){}.getType();
            return gson.fromJson(jsonObject.get("graphs"), listType);
        } catch (IOException e) {
            System.err.println("Error reading JSON: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static void writeResultsToJson(String outputPath, List<JsonObject> results) {
        try (Writer writer = new FileWriter(outputPath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject root = new JsonObject();
            JsonArray resultsArray = new JsonArray();
            for (JsonObject obj : results) {
                resultsArray.add(obj);
            }
            root.add("results", resultsArray);
            gson.toJson(root, writer);
            System.out.println("✅ Results saved to " + outputPath);
        } catch (IOException e) {
            System.err.println("Error writing JSON: " + e.getMessage());
        }
    }
}

