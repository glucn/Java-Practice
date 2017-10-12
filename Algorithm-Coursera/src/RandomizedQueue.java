import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>  {

	private Item[] array;
	private int n = 0;
	private int size = 0;
	private Deque<Integer> blankNodes;
	
    public RandomizedQueue() { // construct an empty randomized queue
        array = (Item[]) new Object[1];
        blankNodes = new Deque<Integer>();
        // StdRandom.setSeed(System.currentTimeMillis());
    }
    
    public boolean isEmpty() { // is the randomized queue empty?
    	return (size == 0);
    }
    
    public int size() { // return the number of items on the randomized queue
    	return (this.size);
    }
    
    public void enqueue(Item item) { // add the item
    	if (item == null) {
    	    throw new IllegalArgumentException("null cannot be add into deque");
    	}
    	if (blankNodes.isEmpty()) {
    		if (n == array.length) resize(2 * array.length);
    		array[n++] = item;
        	size++;
    	}
    	else {
    		array[blankNodes.removeLast()] = item;
    		size++;
    	}
    }
    
    public Item dequeue() { // remove and return a random item
        if (this.isEmpty()) {
        	throw new NoSuchElementException("queue is empty");
        }
    	int random = StdRandom.uniform(n);
    	Item result = array[random];
    	
    	while (result == null) {
    		// random = StdRandom.uniform(n);
    		random++;
    		if (random == n) random = 0;
    		result = array[random];
    	}
    	
    	array[random] = null;
    	if (random == (n-1)) {
    		size--;
    		n--;
    	}
    	else {
    		blankNodes.addLast(random);
    	    size--;
    	}
    	
    	if (n > 0 && (n-blankNodes.size()) <= (array.length/4)) resize(array.length/2);
    	
    	return result;
    }
    
    private void resize(int capacity) {
    	Item[] copy = (Item[]) new Object[capacity];
    	int j = 0;
    	for (int i = 0; i < n; i++) {
    		if (array[i] != null) { copy[j++] = array[i]; }
    	}
    	array = copy;
    	n = j;
    	size = j;
    	blankNodes = new Deque<Integer>();
    }
    public Item sample() { // return a random item (but do not remove it)
        if (this.isEmpty()) {
        	throw new NoSuchElementException("queue is empty");
        }
    	int random = StdRandom.uniform(n);
    	Item result = array[random];
    	
    	while (result == null) {
    		// random = StdRandom.uniform(n);
    		random++;
    		if (random == n) random = 0;
    		result = array[random];
    	}
    	return result;
    }
    
    public Iterator<Item> iterator() { // return an independent iterator over items in random order
    	return new RandomizedQueueIterator();
    }
    
    public static void main(String[] args) {  // unit testing (optional)

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        /*
        System.out.println(rq.size());
        rq.enqueue(1);
        System.out.println(rq.dequeue()); 
        rq.enqueue(0);
        System.out.println(rq.dequeue()); 
        */
        
        for (int i = 0; i < 16; i++) rq.enqueue(i);
        
        for (int i: rq) System.out.println(i);
	}

    private class RandomizedQueueIterator implements Iterator<Item> {

    	Item[] arrayForIterator;
    	private int iterator = n;
    	
    	public RandomizedQueueIterator() {
    		int j = 0;
    		arrayForIterator = (Item[]) new Object[size];
    		while (j < size) {
    			int random = StdRandom.uniform(n);
    			while (array[random] == null) {
    				// random = StdRandom.uniform(n);
    				random++;
    				if (random == n) random = 0;
    			}
    			arrayForIterator[j] = array[random];
    			array[random] = null;
    			j++;
    		}
    		array = arrayForIterator;
    		blankNodes = new Deque<Integer>();
    		n = j;
    	}
    	
		public boolean hasNext() {
			return (iterator > 0);
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("queue is empty");
			}
			return (array[--iterator]);
		}
    	
		public void remove() {
			throw new UnsupportedOperationException("remove() is not supported");
		}
    }
}
