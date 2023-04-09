import java.util.ArrayList;

/*
 * Record movie title and list of actors who had a role.
 */

public class MovieRecord {
  public String name;
  public ArrayList<Integer> actors;

  public MovieRecord(String n) {
    name = n;
    actors = new ArrayList<Integer>();
  }

  /* Add a movie to the list for this actor.  */
  public void addActor(Integer m) {
    actors.add(m);
  }

  public ArrayList<Integer> getActors(){
    return(actors);
  }
  
  public String toString(){
    String s = name+": ";
    for(Integer m : actors)
      s += m+"; ";
    return s;
  }
}
