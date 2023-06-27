package com.onito.task.dto;

import com.onito.task.entity.Movies;
import com.onito.task.entity.Ratings;

public class DataRequest {
     
	Movies movies;
	Ratings ratings;
	public DataRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DataRequest(Movies movies, Ratings ratings) {
		super();
		this.movies = movies;
		this.ratings = ratings;
	}
	public Movies getMovies() {
		return movies;
	}
	public void setMovies(Movies movies) {
		this.movies = movies;
	}
	public Ratings getRatings() {
		return ratings;
	}
	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}
	
	
}
