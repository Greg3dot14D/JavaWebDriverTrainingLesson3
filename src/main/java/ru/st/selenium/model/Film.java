package ru.st.selenium.model;

public class Film {

	private String id;
	private String imdb;
	private String title;
	private String year;
	private String notes;
	private String duration;
	private String rating;
	
	public String getId() {
		return id;
	}
	public Film setId(String id) {
		this.id = id;
		return this;
	}
	public String getImdb() {
		return imdb;
	}
	public Film setImdb(String imdb) {
		this.imdb = imdb;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public String getDuration() {
		return duration;
	}
	public String getRating() {
		return rating;
	}
	public Film setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getYear() {
		return year;
	}
	public Film setYear(String year) {
		this.year = year ;
		return this;
	}
	
	public Film setYear(int year) {
		this.year = String.valueOf(year) ;
		return this;
	}
	
	public String getNotes() {
		return notes;
	}
	public Film setNotes(String notes) {
		this.notes = notes;
		return this;
	}
	
	public Film setDuration(String duration) {
		this.duration = duration;
		return this;
	}
	
	public Film setDuration(int duration) {
		this.duration = String.valueOf(duration);
		return this;
	}

	public Film setRating(String rating) {
		this.rating = rating;
		return this;
	}
	
	public Film setRating(int rating) {
		this.rating = String.valueOf(rating);
		return this;
	}
	
	public String getFilmFieldsValues(){
		return String.format("Title = '%2s' Year ='%2s'"
				, this.title, this.year);
	}
}
