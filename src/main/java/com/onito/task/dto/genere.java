package com.onito.task.dto;

public class genere {
	private String genre;
    private String title;
    private int numVotes;
    
	public genere() {
		super();
		
	}
	public genere(String genre, String title, int numVotes) {
		super();
		this.genre = genre;
		this.title = title;
		this.numVotes = numVotes;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumVotes() {
		return numVotes;
	}
	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}
    
    
}
