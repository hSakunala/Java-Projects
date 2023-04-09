import java.util.ArrayList;
import java.util.Iterator;

/** Adjacency list graph implementation */
public class Graphl implements Graph {
	private ArrayList[] vertex; // The vertex list
    private int numEdge; // Number of edges
    public int[] Mark; // The mark array
    public Iterator[] it;


	public Graphl(int n) // Constructor
	{
		Init(n);
	}

	public void Init(int n) {
        Mark = new int[n];
        it = new Iterator[n];
        vertex = new ArrayList[n];
        for (int i = 0; i < n; i++)
            vertex[i] = new ArrayList();
        numEdge = 0;
    }

	public int n() {
		return Mark.length;
	} // # of vertices

	public int e() {
		return numEdge;
	} // # of edges

	/** @return v's first neighbor */
	public int first(int v) {

		if (vertex[v].size() == 0)
			return Mark.length; // No neighbor
		else {
			it[v] = vertex[v].iterator();
			int w = (int) it[v].next();
			return w;
		}
	}

	/** @return v's next neighbor after w */
	public int next(int v, int w) {
		if (it[v].hasNext()) {
			return (int) it[v].next();
		} else
			return Mark.length;
	}

	public void setEdge(int i, int j, int weight) {
        int[] temp={j,weight};
        vertex[i].add(temp);
        ++numEdge;
    }


	/** Delete an edge */
	public void delEdge(int i, int j) {
        if (isEdge(i, j)) {
            int wt=weight(i,j);
            int[] temp={j,wt};
            vertex[i].remove(temp);
            numEdge--;
        }
    }


	/** Determine if an edge is in the graph */
	public boolean isEdge(int v, int w) {
        ArrayList<int[]> temp=this.neighbors(v);
        for(int i=0; i<temp.size(); i++){
            int[] list=temp.get(i);
            if(list[0]==w){
                return true;
            }
        }
        return false;
    }


	/** @return an edge's weight */
	public int weight(int v, int j) {
        ArrayList<int[]> temp=this.neighbors(v);
        for(int i=0; i<temp.size(); i++){
            int[] list=temp.get(i);
            if(list[0]==j){
                return list[1];
            }
        }
        return 0;
    }


	/** Set/Get the mark value for a vertex */
	public void setMark(int v, int val) {
		Mark[v] = val;
	}

	public int getMark(int v) {
		return Mark[v];
	}

	/** @return iterable object for neighbors */
	public ArrayList<int[]> neighbors(int v) {
        return vertex[v];
	}

}
