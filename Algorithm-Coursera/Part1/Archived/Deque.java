import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first = null;
	private Node last = null;
	private int size = 0;
	
    public Deque() { // construct an empty deque
        
    }
    
    public boolean isEmpty() { // is the deque empty?
        return (first == null);    
    }
    
    public int size() { // return the number of items on the deque
        return this.size;
    }
    
    public void addFirst(Item item) { // add the item to the front
    	if (item == null) {
    	    throw new IllegalArgumentException("null cannot be add into deque");
    	}
        Node oldFirst = this.first;
        first = new Node(item, null, oldFirst);
        if (oldFirst == null) { last = first; }
        else { oldFirst.setPrevious(first); }
        size++;
    }
    
    public void addLast(Item item) { // add the item to the end
    	if (item == null) {
    	    throw new IllegalArgumentException("null cannot be add into deque");
    	}
        Node oldLast = this.last;
        last = new Node(item, oldLast, null);
        if (oldLast == null) { first = last; }
        else { oldLast.setNext(last); }
        size++;
    }
    
    public Item removeFirst() { // remove and return the item from the front
        if (this.isEmpty()) {
        	throw new NoSuchElementException("deque is empty");
        }
        Item value = first.getValue();
        first = first.getNext();
        if (first == null) { last = null; }
        else { first.setPrevious(null); }
        size--;
        return value;
    }
    
    public Item removeLast() { // remove and return the item from the end
        if (this.isEmpty()) {
        	throw new NoSuchElementException("deque is empty");
        }
        Item value = last.getValue();
        last = last.getPrevious();
        if (last == null) { first = null; }
        else { last.setNext(null); }
        size--;
        return value;
    }

    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new DequeIterator();
    }
	   
    public static void main(String[] args) { // unit testing (optional)
        Deque<Integer> test = new Deque<Integer>();
        // for (int i = 0; i < 10; i++) test.addLast(i);
        // for (int i: test) System.out.println(i);
        
        test.addFirst(1);
        System.out.println(test.isEmpty());

	}

    private class Node {
    	private final Item value;
    	private Node previous;
    	private Node next;
    	
    	Node(Item value, Node previous, Node next) {
    		this.value = value;
    		this.previous = previous;
    		this.next = next;
    	}
    	
    	Item getValue() { return (this.value); }
    	// void setValue(Item value) {this.value = value;}
    	
    	Node getPrevious() { return (this.previous); }
    	void setPrevious(Node previous) { this.previous = previous; }
    	
    	Node getNext() { return (this.next); }
    	void setNext(Node next) { this.next = next; }
    }
    
    private class DequeIterator implements Iterator<Item> {

    	private Node current = first;
    	
    	public boolean hasNext() { return (current != null); }
    	
    	public void remove() {
    		throw new UnsupportedOperationException("remove() is not supported");
    	}
    	
    	public Item next() {
    		if (current == null) {
    			throw new NoSuchElementException("deque iterator reach the end");
    		}
    		Item item = current.getValue();
    		current = current.getNext();
    		return item;
    	}
		
    }
}
