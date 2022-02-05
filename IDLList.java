package hw3;

import java.util.ArrayList;


public class IDLList<E> {

	private static class Node<E> {
		// data fields
		private E data;
		private Node<E> next;
		private Node<E> prev;

		Node(E elem) {
			this.data=elem;
			this.next=null;
		}

		Node(E elem, Node<E> prev, Node<E> next) {
			this.data=elem;
			this.prev=prev;
			this.next=next;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<Node<E>>();
	}
	
	public boolean add(int index, E elem) {
		if(index == 0) {
			return add(elem);
		}else if(index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> current = indices.get(index);
		Node<E> inserted = new Node<E>(elem, current.prev, current);
		current.prev.next = inserted;
		current.prev = inserted;
		indices.add(index, inserted);
		size++;
		return true;
	}
	
	public boolean add(E elem) {
	//add element elem to head
		if(head == null) {
			head = new Node<E>(elem);
			tail = head;
			size++;
			indices.add(head);
			return true;
		}
		Node<E> current = head;
		head = new Node<E>(elem, null, current);
		current.prev = head;
		indices.add(0, head);
		size++;
		return true;
	}

	public boolean append(E elem) {
		Node<E> current = tail;
		tail = new Node<E>(elem, current, null);
		current.next = tail;
		indices.add(tail);
		size++;
		return true;
	}
	
	public E get(int index) {
		return indices.get(index).data;
	}
	
	public E getHead() {
		if(head == null) { throw new IllegalStateException(); }
		return head.data;
	}
	
	public E getTail() {
		if(head == null) { throw new IllegalStateException(); }
		return tail.data;
	}
	
	public int size() {
		return this.size;
	}
	
	public E remove() {
		//removes and returns element at head
		if(head == null) {
			throw new IllegalStateException();
		}
		Node<E> trash = head;
		head = trash.next;
		head.prev = null;
		indices.remove(0);
		size--;
		return trash.data;
	}
	
	public E removeLast() {
		if(tail == null) {
			throw new IllegalStateException();
		}
		Node<E> trash = tail;
		tail = trash.prev;
		tail.next = null;
		indices.remove(size-1);
		size--;
		return trash.data;
	}
	
	public E removeAt(int index) {
		Node<E> target = indices.get(index);
		if(target == null) {
			throw new IllegalStateException();
		}
		target.prev.next = target.next;
		target.next.prev = target.prev;
		indices.remove(index);
		size--;
		return target.data;
	}	
	
	public boolean remove(E elem) {
		Node<E> current = head;
		int index = 0;
		while(current != null) {
			if(current.data == elem) {
				removeAt(index);
				return true;
			}
			current = current.next;
			index++;
		}
		return false;
	}
	
	public String toString() {
		String result = "[";
		Node<E> current = head;
		while(current != null) {
			result = result + current.data;
			if(current.next != null) {
				result = result + ", ";
			}
			current = current.next;
		}
		result = result + "]";
		return result;
	}

}
