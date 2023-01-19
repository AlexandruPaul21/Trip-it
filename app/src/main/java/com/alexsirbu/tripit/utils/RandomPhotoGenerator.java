package com.alexsirbu.tripit.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPhotoGenerator {
    private static List<String> IMAGE_URL = null;

    private static void getInstance() {
        IMAGE_URL = new ArrayList<>();
        IMAGE_URL.add("https://images.unsplash.com/photo-1531846802986-4942a5c3dd08?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3087&q=80");
        IMAGE_URL.add("https://www.investmentmonitor.ai/wp-content/uploads/sites/7/2021/10/Warsaw-skyline-2-934x657-1.jpg");
        IMAGE_URL.add("https://images.unsplash.com/photo-1477959858617-67f85cf4f1df?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1844&q=80");
        IMAGE_URL.add("https://images.unsplash.com/photo-1561488111-5d800fd56b8a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1742&q=80");
        IMAGE_URL.add("https://images.unsplash.com/photo-1516844113229-18646a422956?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1738&q=80");
        IMAGE_URL.add("https://images.unsplash.com/photo-1618503551238-7df2c50d2236?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=846&q=80");
    }

    private RandomPhotoGenerator() {}

    public static String generate() {
        if (IMAGE_URL == null) {
            getInstance();
        }
        Random random = new Random();
        return IMAGE_URL.get(random.nextInt(IMAGE_URL.size()));
    }
}
