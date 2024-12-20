package com.green.sacados;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KnapsackSolver {
    private List<Item> items;
    private int capacity;

    public KnapsackSolver(List<Item> items, int capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    public List<Item> solve() {
        // Solution initiale (aléatoire)
        List<Item> currentSolution = generateRandomSolution();
        List<Item> bestSolution = new ArrayList<>(currentSolution);

        int bestValue = calculateValue(bestSolution);

        // Recherche locale
        for (int i = 0; i < 1000; i++) { // Nombre max d'itérations
            List<Item> neighbor = generateNeighbor(currentSolution);
            int neighborValue = calculateValue(neighbor);

            if (neighborValue > bestValue && calculateWeight(neighbor) <= capacity) {
                bestSolution = neighbor;
                bestValue = neighborValue;
            }

            currentSolution = neighbor;
        }

        return bestSolution;
    }

    private List<Item> generateRandomSolution() {
        Random random = new Random();
        List<Item> solution = new ArrayList<>();
        for (Item item : items) {
            if (random.nextBoolean() && calculateWeight(solution) + item.getWeight() <= capacity) {
                solution.add(item);
            }
        }
        return solution;
    }

    private List<Item> generateNeighbor(List<Item> currentSolution) {
        Random random = new Random();
        List<Item> neighbor = new ArrayList<>(currentSolution);

        if (random.nextBoolean() && !items.isEmpty()) {
            // Ajouter un élément
            Item randomItem = items.get(random.nextInt(items.size()));
            if (!neighbor.contains(randomItem) && calculateWeight(neighbor) + randomItem.getWeight() <= capacity) {
                neighbor.add(randomItem);
            }
        } else if (!neighbor.isEmpty()) {
            // Retirer un élément
            neighbor.remove(random.nextInt(neighbor.size()));
        }

        return neighbor;
    }

    private int calculateWeight(List<Item> solution) {
        return solution.stream().mapToInt(Item::getWeight).sum();
    }

    private int calculateValue(List<Item> solution) {
        return solution.stream().mapToInt(Item::getValue).sum();
    }
}
