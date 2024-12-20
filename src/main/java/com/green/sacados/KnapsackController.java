package com.green.sacados;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/knapsack")
public class KnapsackController {

    @GetMapping("/solve")
    public List<Item> solveKnapsack() {
        // Exemple de données
        List<Item> items = Arrays.asList(
                new Item("Item1", 3, 60),
                new Item("Item2", 2, 50),
                new Item("Item3", 4, 70),
                new Item("Item4", 5, 80)
        );

        int capacity = 8;

        KnapsackSolver solver = new KnapsackSolver(items, capacity);
        return solver.solve();
    }
}
