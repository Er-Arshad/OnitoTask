package com.onito.task.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onito.task.dto.DataRequest;
import com.onito.task.dto.LongDurationMovies;
import com.onito.task.dto.TopRatedMovies;
import com.onito.task.dto.genere;
import com.onito.task.entity.Movies;
import com.onito.task.entity.Ratings;
import com.onito.task.service.MoviesService;

import ch.qos.logback.core.model.Model;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class MoviesController {
	   @Autowired
	   MoviesService moviesService;

       @GetMapping("/longest-duration-movies")
	   public List<LongDurationMovies> getLongestDurationMovies() {
	        return moviesService.getLongestDurationMovies();
	    }
       
       @GetMapping("/top-rated-movies")
       public List<TopRatedMovies> getTopRatedMovies() {
    	   return moviesService.getTopRatedMovies();
       }

	    @PostMapping("/new-movie")
	    public ResponseEntity<String> addNewMovie(@RequestBody DataRequest dataRequest ) {
	        String savedMovie = moviesService.addNewMovie(dataRequest);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
	    }



	    @GetMapping("/genre-movies-with-subtotals")
	    public String getGenreMoviesWithSubtotals(org.springframework.ui.Model model) {
	         return moviesService.getGenreMoviesWithSubtotals(model);
	    }

	    @PostMapping("/update-runtime-minutes")
	    public ResponseEntity<String> updateRuntimeMinutes() {
	        moviesService.updateRuntimeMinutes();
	        return ResponseEntity.ok("success");
	    }

	    
}
