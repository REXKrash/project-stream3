package models;

import java.util.List;

public class Movie extends Media {

    private int year;

    public Movie(String title, double rating, List<String> categories, int year) {
        this.title = title;
        this.rating = rating;
        this.categories = categories;
        this.year = year;
    }
}
