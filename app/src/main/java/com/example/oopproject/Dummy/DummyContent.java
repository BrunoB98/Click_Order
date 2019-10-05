package com.example.oopproject.Dummy;

import com.example.oopproject.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample product.
     */
    public List<Product> ITEMS = new ArrayList<Product>();

    /**
     * A map of sample product, by ID.
     */
    public static final Map<Integer, Product> ITEM_MAP = new HashMap<Integer, Product>();

    private static final int COUNT = 12;
    public List<String> s = new ArrayList<String>(Arrays.asList("lasagne", "pasta col tonno", "porchetta", "hot dog", "pasta al sugo", "carbonara",
            "pasta e ceci", "bolognese", "bistecca", "orata al forno", "profiterols", "insalata greca"));
    public List<String> ing = new ArrayList<String>(Arrays.asList("Io", "sono", "un", "mito"));

    public DummyContent() {
        // Add some sample items.
        for (Integer i = 0; i <COUNT; i++) {
            Product p = new Product(i, s.get(i), (float) 7.5, ing);
            ITEMS.add(p);
            ITEM_MAP.put(i, p);
        }
    }
/*
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Product: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore ingredients information here.");
        }
        return builder.toString();
    }
*/


}
