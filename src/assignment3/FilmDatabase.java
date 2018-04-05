package assignment3;

import java.util.ArrayList;

import javax.swing.ListModel;

public class FilmDatabase {
	
	/**
	 * This ArrayList is the Array for the movies
	 * that are held within the database
	 * 
	 * @author Hamish Maritz, 1383425
	 */
	
	private ArrayList<String> movies;

	/** 
	 * This method populates and initalizes
	 * the movies Array list
	 * 
	 * @author Hamish Maritz, 1383425
	 */
	public FilmDatabase() {
		this.movies = new ArrayList<String>();
	}

	/**
	 * 
	 * This Method adds movies to the database 
	 * @param movie
	 * @author Hamish Maritz, 1383425
	 */
	public void addMovie(String movie) {
		this.movies.add(movie);
	}

	/** This Method removes movies from the database 
	 * using an if statement that checks the size of the movies
	 * array
	 * 
	 * @param rMovie
	 * @author Hamish Maritz, 1383425
	 */
	public void removeMovie(int rMovie) {
		if ((rMovie >= 0) && (rMovie < this.movies.size())) {
			this.movies.remove(rMovie);
		}
	}

	/**
	 * This String Method adds a movie to the database and
	 * populates the list using a for loop checking against the array
	 * size and returns the array
	 * @return
	 * @author Hamish Maritz, 1383425
	 */
	public String[] toMovieList() {
		String[] array = new String[this.movies.size()];

		for (int i = 0; i < array.length; i++) {
			array[i] = this.movies.get(i);
		}

		return array;
	}

	/** This Accesses and Gets the Size 
	 * Of the Array
	 * @return
	 * @author Hamish Maritz, 1383425
	 */
	public int getSize() {
		return this.movies.size();
	}

	/**
	 * This Boolean returns the details of the Movie
	 * 
	 * @param details
	 * @return
	 * @author Hamish Maritz, 1383425
	 */
	public boolean contains(String details) {
		return movies.contains(details);
	}

}
