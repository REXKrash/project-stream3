package com.stream;

import com.stream.models.*;
import java.util.*;


public class FileReader {

    private static FileReader instance;
    private List<Media> mediaList;

    public FileReader() {
        mediaList = readAllMedia();
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    /**
     * Loads a list of all media types
     */
    public List<Media> readAllMedia() {
        List<Media> mediaList = new ArrayList<>();

        for (Media media : readMedia(MediaType.MOVIE)) {
            mediaList.add(media);
        }
        for (Media media : readMedia(MediaType.SERIES)) {
            mediaList.add(media);
        }
        return mediaList;
    }

    /**
     * Loads a list of the specific media type using Scanner
     * @param mediaType The specific media type
     */
    public List<Media> readMedia(MediaType mediaType) {
        List<Media> mediaList = new ArrayList<>();

        String path = "res/data/film.txt";
        if (mediaType == MediaType.SERIES) {
            path = "res/data/serier.txt";
        }
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream(path));

        sc.useDelimiter("\\s*;\\s*");
        sc.useLocale(Locale.FRENCH);

        while (sc.hasNext()) {
            String title = sc.next();
            String yearStr = sc.next().trim();
            String categoryStr = sc.next().trim();
            double rating = sc.nextDouble();

            int yearFrom = -1;
            int yearTo = -1;

            HashMap<Integer, Integer> seasonMap = new HashMap<>();
            List<String> categories = new ArrayList<>();

            for (String str : categoryStr.split(",")) {
                str = str.trim();
                categories.add(str);
            }

            if (yearStr.contains("-")) {
                String[] years = yearStr.split("-");

                Calendar cal = Calendar.getInstance();
                int yearToday = cal.get(Calendar.YEAR);

                if (years.length > 1) {
                    String firstYear = years[0];
                    String secondYear = years[1];

                    if (Utility.isInt(firstYear)) {
                        yearFrom = Integer.parseInt(firstYear);
                    }
                    if (Utility.isInt(secondYear)) {
                        yearTo = Integer.parseInt(secondYear);
                    }
                } else {
                    String firstYear = years[0].trim();

                    if (Utility.isInt(firstYear)) {
                        yearFrom = Integer.parseInt(firstYear);
                    }
                    yearTo = yearToday;
                }

            } else {
                if (Utility.isInt(yearStr)) {
                    yearFrom = Integer.parseInt(yearStr);
                }
                yearTo = yearFrom;
            }

            if (mediaType == MediaType.SERIES) {
                String seasonStr = sc.next().trim();

                for (String str : seasonStr.split(",")) {
                    str = str.trim();
                    String[] seasonEpisodes = str.split("-");

                    String seasonNumberStr = seasonEpisodes[0];
                    String episodeAmountStr = seasonEpisodes[1];

                    if (Utility.isInt(seasonNumberStr) && Utility.isInt(episodeAmountStr)) {

                        int seasonNumber = Integer.parseInt(seasonNumberStr);
                        int episodeAmount = Integer.parseInt(episodeAmountStr);

                        seasonMap.put(seasonNumber, episodeAmount);
                    }
                }
                Series series = new Series(title, rating, categories, yearFrom, yearTo, seasonMap);
                mediaList.add(series);

            } else if (mediaType == MediaType.MOVIE) {

                Movie movie = new Movie(title, rating, categories, yearFrom);
                mediaList.add(movie);
            }
        }
        return mediaList;
    }

    /**
     * Reads sample text from a file at the given path
     * @return text from file
     */
    public String readSampleText(){
        String path = "res/data/sampleText.txt";
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream(path));
        return sc.nextLine();
    }

    /**
     * Creates an instance of FileReader if it does not exist yet
     * @return singleton instance of FileReader
     */
    public static FileReader getInstance() {
        if (instance == null) {
            instance = new FileReader();
        }
        return instance;
    }
}
