package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Movies")
public class MovieController {
    @Autowired
    MovieService movieService;

@PostMapping ("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
    movieService.addMovie(movie);
return new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED);
}

@PostMapping ("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
    movieService.addDirector(director);
return new ResponseEntity<>("New director added successfully",HttpStatus.CREATED);
}

@PutMapping ("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie, @RequestParam String director){
    movieService.addMovieDirectorPair(movie,director);
    return new ResponseEntity<>("New director added successfully",HttpStatus.CREATED);
}

@GetMapping ("/get-movie-by-name")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
    Movie movie = movieService.getMovieByName(name);
    return new ResponseEntity<>(movie,HttpStatus.CREATED);
}

@GetMapping ("/get-director-by-name")
    public ResponseEntity<Director> getsDirectorByName(@PathVariable String name) {
    Director director = movieService.getDirectorByName(name);
    return new ResponseEntity<>(director,HttpStatus.CREATED);
}

@GetMapping ("/get-movies-by-director-name")
    public ResponseEntity<List<String>>getMoviesByDirectorName(@PathVariable String name) {
        List<String> movies = movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<>(movies,HttpStatus.CREATED);
    }
    @GetMapping ("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.CREATED);
    }

@DeleteMapping ("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name){
    movieService.deleteDirectorByName(name);
    return new ResponseEntity<>(name + "removed successfully",HttpStatus.CREATED);
}

    @DeleteMapping ("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>( "All directors deleted successfully",HttpStatus.CREATED);
    }


}
