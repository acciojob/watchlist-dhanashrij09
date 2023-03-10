package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String,Movie> movieMap;
    private HashMap<String,Director> directorMap;
    private HashMap<String, List<String>> directorMovieMap;
    HashSet<String> movieSet;

    public MovieRepository() {
        this.movieMap = new HashMap<String,Movie>();
        this.directorMap = new HashMap<String,Director>();
        this.directorMovieMap = new HashMap<String,List<String>>();
        this.movieSet = new HashSet<String>();
    }

    public void addMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }

    public void addDirector(Director director){
        directorMap.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movie,String director){
     if(movieMap.containsKey(movie) && directorMap.containsKey(director)) {
         movieMap.put(movie, movieMap.get(movie));
         directorMap.put(director, directorMap.get(director));
     }
         List<String> currentMovies = new ArrayList<>();
         if(directorMovieMap.containsKey(director)){
             currentMovies = directorMovieMap.get(director);
             currentMovies.add(movie);
             directorMovieMap.put(director,currentMovies);
         }
    }
    public Movie getMovieByName(String name){
        return movieMap.get(name);
    }

    public Director getDirectorByName(String name){
        return directorMap.get(name);
    }

    public List<String> getMoviesByDirectorName(String director){
        List<String> listOfMovies = new ArrayList<>();
        if(directorMovieMap.containsKey(director)){
            listOfMovies = directorMovieMap.get(director);
        }
        return listOfMovies;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirectorByName(String director) {
        if (directorMap.containsKey(director)) {
            directorMap.remove(director);
        }

        List<String> movieList = new ArrayList();
        if (directorMovieMap.containsKey(director)) {
            movieList = directorMovieMap.get(director);
            for (String movie : movieList) {
                if (movieMap.containsKey(movie)) {
                    movieMap.remove(movie);
                }
            }
            directorMovieMap.remove(director);
        }
    }
    public void deleteAllDirectors(){
      for(String director : directorMovieMap.keySet()){
          for(String movie : directorMovieMap.get(director)){
              if(movieMap.containsKey(movie)){
                  movieSet.add(movie);
              }
          }
      }
      for(String movie : movieSet){
          if(movieMap.containsKey(movie)){
              movieMap.remove(movie);
          }
      }
    }
}
