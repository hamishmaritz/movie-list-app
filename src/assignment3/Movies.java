package assignment3;

public class Movies 
{

   private String title;
   private String year;
   private String director;
   


  public Movies(String title, String year, String director)
  {

	  this.title = title;
	  this.year = year;
	  this.director = director;
	  
}
  
  public void setMovie(String title)
  {
	  this.title = title;
  }
  public void setTitle(String title)
  {
	  this.title = title;
  }
  public void setDirector(String director)
  {
	  this.director = director;
  }
 
  
  public String getDirector()
  {
	  return director;

  }
  
  public String getTitle()
  {
	  return title;
  }
  
  public String getYear()
  {
	  return year;
  }

  
}
