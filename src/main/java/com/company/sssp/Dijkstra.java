package com.company.sssp;

import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {

    private static final String START_NODE = "start";

    private static final Map<String, Map<String, Integer>> graph =
            Map.of(START_NODE, Map.of("a", 5, "b", 2),
                    "a", Map.of("c", 4, "d", 2),
                    "b", Map.of("a", 8, "d", 7),
                    "c", Map.of("finish", 3, "d", 6),
                    "d", Map.of("finish", 1),
                    "finish", Map.of());

    private static final Map<String, Integer> costs = getCostsTable();

    private static final Map<String, String> parents = getParentsTable();

    private static final List<String> processed = new ArrayList<>();

    public static void main(String[] args) {
        String node = findLowestCostNode();
        while (node != null) {
            int cost = costs.get(node);
            for (var entry : graph.get(node).entrySet()) {
                int newCost = cost + entry.getValue();
                if (costs.get(entry.getKey()) > newCost) {
                    costs.put(entry.getKey(), newCost);
                    parents.put(entry.getKey(), node);
                }
            }
            processed.add(node);
            node = findLowestCostNode();
        }
        System.out.println(parents.toString());
    }

    private static String findLowestCostNode() {
        int lowestValue = Integer.MAX_VALUE;
        String lowestCostNode = null;
        for (Map.Entry<String, Integer> entry : costs.entrySet()) {
            if (entry.getValue() < lowestValue && !processed.contains(entry.getKey())) {
                lowestCostNode = entry.getKey();
                lowestValue = entry.getValue();
            }
        }
        return lowestCostNode;
    }

    private static Map<String, Integer> getCostsTable() {
        int infinity = Integer.MAX_VALUE;
        return graph.keySet().stream().filter(s -> !s.equals(START_NODE)).collect(Collectors.toMap(
                key -> key,
                key -> graph.get(START_NODE).getOrDefault(key, infinity),
                (a, b) -> b));
    }

    private static Map<String, String> getParentsTable() {
        return graph.keySet().stream().filter(s -> !s.equals(Dijkstra.START_NODE)).collect(Collectors.toMap(
                key -> key,
                key -> (graph.get(Dijkstra.START_NODE).containsKey(key)) ? Dijkstra.START_NODE : ""));
    }

}
