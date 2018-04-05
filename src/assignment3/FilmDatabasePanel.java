package assignment3;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import assignment3.FilmDatabasePanel.Rating;

public class FilmDatabasePanel extends JPanel {

	private FilmDatabase movieModel;
	private JList<String> movieList;
	private JTextField newItemField;
	private JButton addMovie;
	private JButton removeMovie;
	private JTextField textLine;
	private JTextField director;
	private JTextField movieYear;
	private JScrollPane scrollPane;
	private JButton readText;
	private JButton saveText;
	private JLabel labelDisplay;
	private JButton search;
	private JButton filter;
	private JComboBox<Rating> rating;
	private JTextField searchbox;

	/**
	 * 
	 * These Are The Get Methods for All JSwing Panes within the application
	 * 
	 * @author Hamish Maritz, 1383425
	 * @return
	 */
	
	public JTextField getSearchBox() {
		return this.searchbox;
	}

	public JComboBox<Rating> getRating() {
		return this.rating;
	}

	public JTextField getDirector() {
		return this.director;
	}

	public JTextField getYear() {
		return this.movieYear;
	}

	// Filter
	public JButton getFilter() {
		return this.filter;
	}

	// Search Bar
	public JTextField getTextField() {
		return this.textLine;
	}

	// MovieList
	public JList<String> getMovieList() {
		return this.movieList;
	}

	// Get New Item Field
	public JTextField getNewItemField() {
		return this.newItemField;
	}

	// Remove Movie
	public JButton getRemoveMovie() {
		return this.removeMovie;
	}

	// Add Movie
	public JButton getAddMovie() {
		return this.addMovie;
	}

	// Read Text File
	public JButton getReadText() {
		return this.readText;
	}

	// Save Text File
	public JButton getSaveText() {
		return this.saveText;
	}

	// Scroll Pane
	public JScrollPane getScrollPane() {
		return this.scrollPane;
	}
	// Get Text Box
	public JTextField getTextLine() {
		return this.textLine;
	}

	public JLabel getLabel() {
		return this.labelDisplay;
	}

	public JButton getSearch() {
		return this.search;
	}

	enum Rating {
		ACTION, COMEDY, THRILLER, ROMANCE;
	}

	
	/** 
	 * 
	 * This FilmDatabasePanel adds the Panels 
	 * to the Application
	 * 
	 * @author Hamish Maritz, 1383425
	 * @param model
	 */
	public FilmDatabasePanel(FilmDatabase model) {

		super();

		this.movieModel = model;
		setLayout(null);
		// -----------------------------------------------------------------
		// Display
		this.labelDisplay = new JLabel("FILM DATABASE APPLICATION");
		this.labelDisplay.setLocation(180, -30);
		this.labelDisplay.setSize(200, 100);
		this.labelDisplay.setFont(new Font("Arial", Font.BOLD, 13));
		add(labelDisplay);
		// TITLE
		this.labelDisplay = new JLabel("TITLE");
		this.labelDisplay.setLocation(20, 20);
		this.labelDisplay.setSize(100, 100);
		this.labelDisplay.setFont(new Font("Arial", Font.BOLD, 12));
		add(labelDisplay);
		// YEAR
		this.labelDisplay = new JLabel("YEAR");
		this.labelDisplay.setLocation(20, 50);
		this.labelDisplay.setSize(100, 100);
		this.labelDisplay.setFont(new Font("Arial", Font.BOLD, 12));
		add(labelDisplay);

		// DIRECTOR
		this.labelDisplay = new JLabel("DIRECTOR");
		this.labelDisplay.setLocation(225, 20);
		this.labelDisplay.setSize(100, 100);
		this.labelDisplay.setFont(new Font("Arial", Font.BOLD, 12));
		add(labelDisplay);

		// GENRE
		this.labelDisplay = new JLabel("GENRE");
		this.labelDisplay.setLocation(225, 50);
		this.labelDisplay.setSize(100, 100);
		this.labelDisplay.setFont(new Font("Arial", Font.BOLD, 12));
		add(labelDisplay);
		// SEARCH
		this.labelDisplay = new JLabel("SEARCH VIA TITLE/DIRECTOR/YEAR/GENRE");
		this.labelDisplay.setSize(280, 20);
		this.labelDisplay.setLocation(145, 360);
		this.labelDisplay.setFont(new Font("Arial", Font.BOLD, 12));
		add(labelDisplay);

		// -------------------------------------------------------------------------

		// Movie
		this.textLine = new JTextField("");
		this.textLine.setLocation(70, 60);
		this.textLine.setSize(150, 20);
		add(textLine);

		this.director = new JTextField("");
		this.director.setLocation(290, 60);
		this.director.setSize(150, 20);
		add(director);

		this.movieYear = new JTextField("");
		this.movieYear.setLocation(70, 90);
		this.movieYear.setSize(150, 20);
		add(movieYear);

		this.rating = new JComboBox<Rating>(Rating.values());
		this.rating.setLocation(290, 75);
		this.rating.setSize(150, 50);
		add(rating);

		// Add Movie
		this.addMovie = new JButton("Add Movie");
		this.addMovie.setLocation(450, 70);
		this.addMovie.setSize(100, 25);
		add(addMovie);
		// Remove Movie
		this.removeMovie = new JButton("Remove Movie");
		this.removeMovie.setLocation(10, 335);
		this.removeMovie.setSize(140, 25);
		add(removeMovie);
		// Read Text File
		this.search = new JButton("Search");
		this.search.setLocation(200, 420);
		this.search.setSize(100, 25);
		add(search);
		// Filter
		this.filter = new JButton("Filter");
		this.filter.setLocation(400, 335);
		this.filter.setSize(140, 25);
		add(filter);
		this.searchbox = new JTextField("");
		this.searchbox.setLocation(165, 380);
		this.searchbox.setSize(200, 30);
		add(searchbox);

		movieList = new JList<String>(movieModel.toMovieList());

		scrollPane = new JScrollPane(movieList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPane.setLocation(10, 120);
		scrollPane.setSize(530, 200);
		add(scrollPane);

		this.update();
	}

	/** 
	 * This Void Method Refreshes the Panel
	 * And updates it with the Added/Removed Movie
	 * And the Movie List
	 * 
	 * @author Hamish Maritz, 1383425
	 * 
	 */
	public void update() {

		this.movieList.setListData(this.movieModel.toMovieList());
		this.addMovie.setEnabled(!this.textLine.getText().isEmpty());
		this.removeMovie.setEnabled(this.movieList.getSelectedIndex() != -1);
	}

}
