import java.util.ArrayList;

/*
 * Record movie title and list of actors who had a role.
 */

public class ActorRecord {
  public String name;
  public ArrayList<String> movies;

  public ActorRecord(String n) {
    name = n;
    movies = new ArrayList<String>();
  }

  /* Add a movie to the list for this actor.  */
  public void addMovie(String m) {
    movies.add(m);
  }
  
  public boolean AppearedIn(String m){
    return movies.contains(m);
  }
}
