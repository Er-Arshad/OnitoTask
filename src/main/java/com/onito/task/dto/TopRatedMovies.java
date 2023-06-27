package com.onito.task.dto;

public class TopRatedMovies {
    
	 private String tconst;
	 private String primaryTitle;
	 
	 private String genres;
	 private double averageRating;
	public TopRatedMovies() {
		super();
		
	}
	public TopRatedMovies(String tconst, String primaryTitle, String genres, double averageRating) {
		super();
		this.tconst = tconst;
		this.primaryTitle = primaryTitle;
		this.genres = genres;
		this.averageRating = averageRating;
	}
	public String getTconst() {
		return tconst;
	}
	public void setTconst(String tconst) {
		this.tconst = tconst;
	}
	public String getPrimaryTitle() {
		return primaryTitle;
	}
	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	@Override
	public String toString() {
		return "TopRatedMovies [tconst=" + tconst + ", primaryTitle=" + primaryTitle + ", genres=" + genres
				+ ", averageRating=" + averageRating + "]";
	}
	 
	 
}
