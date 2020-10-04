package com.company.exercises.greedy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Suppose you’re starting a radio show. You want to
 * reach listeners in all 5 states. You have to decide what
 * stations to play on to reach all those listeners. It costs
 * money to be on each station, so you’re trying to minimize the
 * number of stations you play on.
 */
public class SetCoveringProblem {

    private static final Set<String> statesNeeded =
            new HashSet<>(asList("mt", "wa", "or", "id", "nv", "ut", "ca", "az"));

    private static final Map<String, Set<String>> stations = Map.of(
            "k1", Set.of("id", "nv", "ut"),
            "k2", Set.of("wa", "id", "mt"),
            "k3", Set.of("or", "nv", "ca"),
            "k4", Set.of("nv", "ut"),
            "k5", Set.of("ca", "az")
    );

    private static final Set<String> finalStations = new HashSet<>();

    public static void main(String[] args) {
        setCoveringProblem();
        System.out.println(finalStations.toString());
    }

    /**
     * Time complexity: O(n^2)
     */
    private static void setCoveringProblem() {
        while (!statesNeeded.isEmpty()) {
            String bestStation = "";
            Set<String> statesCovered = new HashSet<>();
            for (Map.Entry<String, Set<String>> entry : stations.entrySet()) {
                Set<String> covered = entry.getValue().stream().filter(statesNeeded::contains).collect(Collectors.toSet());
                if (covered.size() > statesCovered.size()) {
                    bestStation = entry.getKey();
                    statesCovered = covered;
                }
            }
            statesNeeded.removeAll(statesCovered);
            finalStations.add(bestStation);
        }
    }

}
