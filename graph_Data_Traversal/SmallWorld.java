import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;
import javax.management.Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;


public class SmallWorld {
    public static void main(String[] args) throws Exception {
        String fname = "ActorRoles";
        BufferedReader in;
		String content;

        in = new BufferedReader(new InputStreamReader(new FileInputStream(fname), "UTF-8"));
        

        ArrayList<String> actorlist = new ArrayList<>();
        BST<String, MovieRecord> movies = new BST<>();
        
        int count = 0;
        while ((content = in.readLine()) != null) {
            String[] tkn = content.split("\\t");
            for(String movie : Arrays.copyOfRange(tkn, 1, tkn.length)) {
                if(movies.find(movie) == null) {
                    MovieRecord record = new MovieRecord(movie);
                    record.addActor(count);
                    movies.insert(movie, record);
                } else {
                    movies.find(movie).addActor(count);
                }
            }
            actorlist.add(tkn[0]);
            count++;
        }
        in.close();



        /*
        for(MovieRecord movie : movies.values()) {
            movie.actors.forEach( s -> System.out.print(s + "; ") );
            System.out.println("");
        }
        */
        
        Graphl g;
        int movieValue= 0;
        g = new Graphl(actorlist.size());
        for(MovieRecord movie: movies.values()){
            movieValue++;
            ArrayList<Integer> actList= movie.getActors();
            for(int i : actList) {
                for(int j : actList) {
                    if(i!=j && !g.isEdge(i, j)){
                        g.setEdge(i, j, movieValue);
                        g.setEdge(j, i, movieValue);
                    }
                }
            }
        }
        
        
        Scanner input= new Scanner(System.in);
    
        ArrayList<String> movielist= (ArrayList<String>)movies.keys();

        while(true){
            System.out.println("Enter source and destination indices:");
            int ind1= input.nextInt();
            int ind2=input.nextInt();
            if(ind1<=0 && ind2<=0){
                break;
            }
            else if(ind1==ind2){
                System.out.println("no chain");
            }
            else{
                ArrayList<Integer> path= findShortestPath(g, ind1, ind2);
                if(path==null){
                    System.out.println("No path between "+ actorlist.get(ind1)+ " and "+ actorlist.get(ind2));
                }
                else{
                    System.out.println("Shortest path between "+ actorlist.get(ind1)+ " and "+ actorlist.get(ind2));
                    System.out.println("Distance: " +(path.size()-1)+ "; the chain is:");
                    
                    for(int i=0; i<path.size()-1; i++){
                        int firstActor= path.get(i);
                        int secondActor= path.get(i+1);
                        System.out.println(actorlist.get(firstActor)+" appeared with "+ actorlist.get(secondActor)+ " in "+ movielist.get(g.weight(firstActor, secondActor)-1));
                    }

                }
            }
        }
        input.close();
    }
        
        



public static ArrayList<Integer> findShortestPath(Graphl g, int i, int j){
    boolean[] visited= new boolean[g.n()];
    Queue<Integer> queue = new LinkedList<>();
    ArrayList<Integer> path= new ArrayList<>();
    g.setMark(i, -1);
    visited[i] = true;
    queue.add(i);
    while(!queue.isEmpty()){
        int first= queue.poll();
        ArrayList<int[]> neighb= g.neighbors(first);
        for(int a =0; a< neighb.size(); a++){
            int[] temp= neighb.get(a);
            int num= temp[0];
            if(!visited[num]){
                visited[num]=true;
                g.setMark(num, first);
                queue.add(num);
                if(num == j){
                    while(num!=-1){
                        path.add(0,num);
                        num = g.getMark(num);
                    }
                    return path;
                }
            }
            
        }
    }
    return null;
    }
}