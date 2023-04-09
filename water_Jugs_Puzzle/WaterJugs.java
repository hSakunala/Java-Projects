import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.Stack;
 import java.util.Scanner;
 public class WaterJugs
 {
 public static void main(String args[])
 {
 Queue<State> q = new LinkedList<State>();
 Stack<State> st = new Stack<State>();
 ArrayList<State> visited = new ArrayList<State>();
 System.out.println("Enter Input Values: ");
 Scanner scan = new Scanner(System.in);
 int[] cap = new int[3];
 for (int i = 0; i < 3; ++i)
 {
 cap[i] = scan.nextInt();
 }
 int d = scan.nextInt();
 State start = new State(cap);
 State.setCapacities(cap);
 visited.add(start);
 q.add(start);
 visited.add(start);
 while(q.peek() != null)
 {
 State next = q.poll();
 if (next.reachedGoal(d)) {
 for (State x = next; x != null; x = x.pred)
 st.push(x);
 for (State y = next; y != null; y = y.pred)
 {
 st.pop().display();
 }
 break;
 }
 for(int i = 0; i <= 2; i++)
 for(int j = 0; j <= 2; j++)
 {
 if(j == i) continue;
 State p = next.move(i, j);
 if(p == null || visited.contains(p)) continue;
 visited.add(p);
 q.add(p);

 }
 }
 }
 }
 class State
 {
 static int[] capacity;
 int[] contents;
 State pred;
 static void setCapacities(int[] c)
 {
 capacity = c;
 }
 State(int[] c) {
 contents = new int[3];
 contents[0] = c[0];
 contents[1] = 0;
 contents[2] = 0;
 }
 public State(State state)
 {
 contents = new int[3];
 contents[0] = state.contents[0];
 contents[1] = state.contents[1];
 contents[2] = state.contents[2];
 }
 State move(int src, int dest)
 {
 State newState = new State(this);
 if((contents[src] == 0) || (contents[dest] == capacity[dest])==true)
 return null;
 else{
 int diff = capacity[dest] - newState.contents[dest];
 if(newState.contents[src] >= diff)
 {   
 newState.contents[dest] += diff;   
 newState.contents[src] -= diff;   
 }
 else
 {
 if(newState.contents[dest] + newState.contents[src] > capacity[dest]) return null;
 newState.contents[dest] += newState.contents[src];
 newState.contents[src] = 0;
 }
 }
 newState.pred = this;
 return newState;
 }
 boolean reachedGoal(int d)
 {
 return(contents[0] == d || contents[1] == d || contents[2] == d);
 }
 void display()
 {
 System.out.println(contents[0] + " " + contents[1] + " " + contents[2]);
 }
 }