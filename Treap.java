/**
 * @author Claudia MacRae
 * I pledge my honor that I have abided by the Stevens Honor System.
 */
package hw5;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>>{

	public static class Node<E>{
		// Data fields
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		// Constructor
		/**
		 * Creates a new Node with null left and right pointers
		 * @param data The key of the node
		 * @param priority The random priority number
		 */
		public Node(E data, int priority) {
			if(data == null) {
				//throw an exception
			}
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		//Methods
		/**
		 * Rotates the Treap right and updates respective data and priorities 
		 */
		Node<E> rotateRight(){
			Node<E> head = this.left;
			Node<E> leftover = head.right;
			head.right = this;
			this.left = leftover;
			return head;
		}
		/**
		 * Rotates the Treap left and updates respective data and priorities 
		 */
		Node<E> rotateLeft(){
			Node<E> head= this.right;
			Node<E> leftover = head.left;
			head.left = this;
			this.right = leftover;
			return head;
		}
		public String toString(){
			return "(key=" + data + ", priority=" + priority + ")";
		}
	}
	//Data fields
	private Random  priorityGenerator;
	private Node<E> root;
	
	//Constructors
	/**
	 * Creates a new empty Treap
	 */
	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();
	}
	/**
	 * Creates a new empty Treap that uses seed for the priority generator
	 * @param seed The seed key for random priority generation
	 */
	public Treap(long seed) {
		this.root = null;
		this.priorityGenerator = new Random(seed);
	}
	
	//Methods
	/**
	 * Adds new node to Treap and bubbles node up to maintain heap invariant
	 * @param key The key for the new Node to be added
	 * @return True if successful
	 */
	public boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}
	//helper for add
	public boolean add(E key, int priority) {
		if(root == null) {
			root = new Node<E>(key, priority);
			return true;
		}
		Node<E> toAdd = new Node<E>(key, priority);
		Stack<Node<E>> path = new Stack<Node<E>>();
		Node<E> current = root;
		while(current != null) {
			int comparison = key.compareTo(current.data);
			if(comparison == 0) { return false;} //if key already exists in treap
			path.push(current);
			if(comparison > 0) {
				if(current.right == null) {
					current.right = toAdd;
					reheap(toAdd, path);
					return true;
				}
				current = current.right;
			}else {
				if(current.left == null) {
					current.left = toAdd;
					reheap(toAdd, path);
					return true;
				}
				current = current.left;
			}
		}
		return false;
	}
	/**
	 * Follows path from Node to root to bubble up Node to its proper location
	 * @param child Starting Node
	 * @param path Stack storing Nodes that create a path from node to root
	 */
	public void reheap(Node<E> child, Stack<Node<E>> path) {
		while (!path.isEmpty()) {
			Node<E> parent = path.pop();
			if (parent.priority < child.priority){
				if (parent.data.compareTo(child.data) > 0) {
					child = parent.rotateRight();
				} else {
					child = parent.rotateLeft();
				}
				if (!path.isEmpty()) {
					if (path.peek().left == parent) {
						path.peek().left = child;
					} else {
						path.peek().right = child;
					}
				} else { 
					this.root = child;
				}
			} else {
				break;
			}
		}
	}
	/**
	 * Finds node with given key, brings node to bottom of treap as a leaf and removes it
	 * @param key The key of the node to be deleted
	 * @return True if successful
	 */
	public boolean delete(E key) {
		if(root == null || !find(key)) { return false;}
		root = delete(key, root);
		return true;
	}
	//helper for delete
	private Node<E> delete(E key, Node<E> current) {
		if(current == null) { return current;}
		int compare = key.compareTo(current.data);
		if(compare < 0) {
			current.left = delete(key, current.left);
		}else if(compare > 0) {
			current.right = delete(key, current.right);
		}
		if(compare == 0) {
			if(current.left == null && current.right == null) {
				return null;
			}else {
				if(current.left == null) {
					current = current.rotateLeft();
					current.left = delete(key, current.left);
				}else if(current.right == null) {
					current = current.rotateRight();
					current.right = delete(key, current.right);
				}else if(current.left.priority < current.right.priority) {
					current = current.rotateRight();
					current = delete(key, current.right);
				}else { 
					current = current.rotateLeft();
					current = delete(key, current.left);
				}
			}
		}
		return current;
	}
	/**
	 * Locates node with given key
	 * @param key The key of the node to be found
	 * @return True if node is found, false if no node with given key is in the Treap
	 */
	public boolean find(E key) {
		return find(key, root);
	}
	private boolean find(E key, Node<E> root) {
		if(root == null) {
			return false;
		}
		if(key.compareTo(root.data) == 0) {
			return true;
		}
		return find(key, root.left) || find(key, root.right);
	}
	//helper for toString
	private StringBuilder toString(Node<E> current, int level) {
		 StringBuilder r = new StringBuilder();
		 
		 for (int i=0; i<level; i++) {
			 r.append("--");
		 }
		 if (current==null) {
			 r.append("null\n");
			 return r;
		 }
		 r.append(current.toString()+"\n");
		 r.append(toString(current.left,level+1));
		 r.append(toString(current.right,level+1));
		 return r;
	}
	public String toString() {
		return toString(root,0).toString();
	}
	
}
