package assignment3;

import java.awt.Dimension;

// TO DO:
// SEARCH BOX
// READ IN FROM FILE INPUT
// SAVE TO FILE INPUT(CHECK EXERCISES FROM WEEK9/10 OF LAB)
// CREATE LIST OF ACTUAL MOVIES AND POPULATE
// PROPER DATABASE
// DATABASE FUNCTIONALITY
// ALGORITHIMS
// JAVA DOC COMMENTING
//JOPTION PANE

// NEED TO DO Add Director/ Add Year/ Cast Etc Then Get And Set MEthods
// From There Need To Do JTextField that Works With The Add Movie Button
// See Event Button Add Movie 
// 12/10
// SEARCH BAR
// FILTER
// TEXT READ/SAVE TO DB
// SAVE FILE

// 13/10

// FILTER LIST, FLITER JLIST? MAYBE UPDATE PANEL VIA J
// NEED TO FIX SEARCH BOX AS IT ONLY TAKES IN THE TEXT IN THE JTEXTFIELD
//FIND OUT WHERE JLIST IS BEING STORED?

// LOOK AT WEEK 10 PERSON DATABASE EXAMPLE

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FilmDatabaseApp extends JFrame {

	/**
	 * Global Default Variables
	 * @author Hamish Maritz, 1383425
	 */
	private FilmDatabase model;
	private FilmDatabasePanel viewPanel;
	public String input; 
	public String director;
	public String year;
	public Object rating;
	public String search;

/**
 * This Handles The Get Remove Movie from the selected index
 * @author Hamish Maritz, 1383425
 */
	private void eventHandleListSelection() {
		this.viewPanel.getRemoveMovie().setEnabled(this.viewPanel.getMovieList().getSelectedIndex() != -1);

	}

	private void eventHandleKeyReleased(KeyEvent e) {
		this.viewPanel.getAddMovie().setEnabled(!this.viewPanel.getTextLine().getText().isEmpty());
		this.viewPanel.update();
	}

	/**
	 * This Method Handles The Remove Button
	 * For Movies, It Removes It From The getMovieList()
	 * 
	 * @author Hamish Maritz, 1383425
	 */
	private void eventHandleRemoveButton() {
		int index = this.viewPanel.getMovieList().getSelectedIndex();
		if (index != -1) {
			this.model.removeMovie(index);
		}
		this.viewPanel.update();
	}

	/**
	 * This Displays "Window Opened"
	 * Inside the Console Window
	 * 
	 * @author Hamish Maritz, 1383425
	 */
	private void eventHandleWindowOpened() {
		System.out.println("Window opened!");
		this.viewPanel.update();
	}

	/** This Method Adds Movies To The Database
	 * It takes in the input from the TextFields 
	 * And Adds It To the JLIST
	 * 
	 * @author Hamish Maritz, 1383425
	 */
	public void eventHandleAddButton() {

		String input = viewPanel.getTextLine().getText();
		String director = viewPanel.getDirector().getText();
		String year = viewPanel.getYear().getText();
		Object rating = viewPanel.getRating().getSelectedItem();

		input = input.trim();
		director = director.trim();
		year = year.trim();

		for (int i = 0; i < viewPanel.getMovieList().getModel().getSize(); i++) {
			Object item = viewPanel.getMovieList().getModel().getElementAt(i);
			System.out.println(item);
		}
		System.out.println("------------UPDATED LIST--------------");

		/**
		 * If there is already a duplicate inside the database
		 * then the user is returned with a JOptionPane
		 * Stating that there is already a Movie
		 * 
		 * @author Hamish Maritz, 1383425
		 */
		if (model.contains(input)) {
			JOptionPane.showMessageDialog(this, input + " is already in the database!");
		} else {
			if (!input.isEmpty()) {
				model.addMovie("Title: " + input + " Director: " + director + " Year: " + year + " Rating: " + rating);

				/**
				 * This PrintWriter saves the database and outputs to
				 * a text file with the Title, Director, Year and Rating
				 * 
				 * @author Hamish Maritz, 1383425
				 */
				
				PrintWriter writer;
				try {
					writer = new PrintWriter("filmdatabase.txt");
					for (int i = 0; i < viewPanel.getMovieList().getModel().getSize(); i++) {
						Object item = viewPanel.getMovieList().getModel().getElementAt(i);

						writer.println(item);
					}
					writer.close();

				} catch (IOException e) {
					System.out.println("Exception");
				}
			}
		}

		viewPanel.update();

	}


	/**
	 * This Method handles the Search Bar
	 * Currently the method is only working 
	 * with User Input inside the JTextFields
	 * If Queried When Input Is In Textbox
	 * Then A Search Box Returns And Displays
	 * The Title, Rating, Year, Director
	 * 
	 * If Nothing is found then null is returned
	 * And The User Is Told Nothing Is Found
	 * @author Hamish Maritz
	 */
	private void eventHandleSearch() {
	
		String search = viewPanel.getSearchBox().getText();
		String input = viewPanel.getTextLine().getText();
		String director = viewPanel.getDirector().getText();
		String year = viewPanel.getYear().getText();
		Object rating = viewPanel.getRating().getSelectedItem();

		input = input.trim();
		director = director.trim();
		year = year.trim();
		String total = viewPanel.getSize().toString();

		if (search.contains(input) || search.contains(director) || search.contains(year)) {
			System.out.println("Result Found!");
			System.out.println(total);
			JFrame frame = new JFrame("RESULTS");

			JOptionPane.showMessageDialog(frame, "TITLE:" + input + '\n' + "DIRECTOR:" + director + '\n' + "YEAR: "
					+ year + '\n' + "GENRE: " + rating);

		} else {
			System.out.println("Result Not Found");
			JFrame frame2 = new JFrame("RESULTS");

			JOptionPane.showMessageDialog(frame2, "Result Not Found!");
		}

	}

	public FilmDatabaseApp(String name) {
		
		super(name);
		
		this.model = new FilmDatabase();
	
		this.viewPanel = new FilmDatabasePanel(this.model);

		this.viewPanel.getTextLine().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				eventHandleKeyReleased(e);
			}
		});

		this.viewPanel.getRemoveMovie().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eventHandleRemoveButton();
			}
		});

		this.viewPanel.getAddMovie().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eventHandleAddButton();
			}
		});

		/**
		 * Adds a Action Listener to the Search Box 
		 * And awaits Users Input before
		 * Initalizing the eventHandleSearch method
		 * 
		 * @author Hamish Maritz, 1383425
		 */
		this.viewPanel.getSearch().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eventHandleSearch();
			}
		});

		/**
		 * This Adds an Actin Listener to handle the Movie List and update the pane
		 * Once a movie has been added to it
		 * 
		 * @author Hamish Maritz, 1383425
		 */
		this.viewPanel.getMovieList().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				eventHandleListSelection();
			}

		});	
		this.getContentPane().add(this.viewPanel);

		// Set program to stop when window closed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(550, 500); // manually computed sizes
		setResizable(false);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				eventHandleWindowOpened();
			}
		});
	}

	public static void main(String[] args) {

		/**
		 * This File Reader Reads In The Text Stored In
		 * The filmdatabase.txt and prints out to console
		 * 
		 * @author Hamish Maritz, 1383425
		 */
		File filmdatabase = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			filmdatabase = new File("filmdatabase.txt");
			fr = new FileReader(filmdatabase);
			br = new BufferedReader(fr);

			Vector<String> lines = new Vector<String>();
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				lines.add(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		JFrame frame = new FilmDatabaseApp("Film Database Application");
		frame.setVisible(true);
	}

}
