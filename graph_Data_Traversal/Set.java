
public interface Set<E> {
	public void clear(); // reset to empty set

	public void insert(E e); // insert e if it is not there; else do nothing

	public E remove(E e); // Null if e is not in set.

	public boolean contains(E e); // true if e is in the set 

        public Iterable<E> values();  // returns a collection of all elements

	public int size();
};

