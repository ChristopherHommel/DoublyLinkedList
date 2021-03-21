/**
 * An implementation of a doubly linked list
 * @author Christopher
 *
 */
public class DoublyLinkedList {

	public static void main(String[] args) {
		
	}
	//Declare variables
	private Node head = null;
	private Node tail = null;
	private int count = 0;
	
	/**
	 * The size of the list
	 * @return the size of the list
	 */
	private int size() {
		return this.count;
	}
	
	/**
	 * Is the list empty
	 * @return true if the list is empty false if not
	 */
	public boolean isEmpty() {
		if(size() != 0) {
			return false;
		}
		return true;
	}

	/**
	 * Sort the list in ascending order
	 */
	public void sort() {
		//Start at the front
		Node current = head;
		//While not at the end of the list
		while(current.getNext() != null) {
			//Get the next node
			Node next = current.getNext();
			//If the current nodes value is greater than the nexts nodes value
			//Then swap them
			if(current.getValue() > next.getValue()) {
				//Store the current nodes value
				int temp = current.getValue();
				//Set the current nodes value to the next nodes value
				current.setValue(next.getValue());
				//Set the next nodes value to the temp value
				next.setValue(temp);
				//Keep sorting until the list is sorted
				sort();
			}
			//Traverse to the next node
			current = current.getNext();
		}
		
		
		
	}
	
	/**
	 * Checks to see if a number is contained inside this list
	 * @param value Integer parameter to pass
	 * @return True if the value is found
	 */
	public boolean contains(Integer value) {
		Node current = head;
		
		//Base case for if the first value in the list is the correct value
		if(current.getValue() == value) {
			return true;
		}
		//Search through each node in the list and compare their values
		while(current != null) {
			if(current.getValue() == value) {
				//Return true if the value is found
				return true;
			}
			//Go to the next node
			current = current.getNext();
		}
		//Retruns false if the node is not found and at the end of the list
		return false;
	}
	
	/**
	 * Adds a value at a given index, if that index is less than the length of the list
	 * it gets added to the front, if that index is greater than the length of the list
	 * it gets added to the end
	 * @param value Integer to add to the list
	 * @param index The location to insert the value
	 */
	public void addAtIndex(Integer value, int index) {
		//Check that the value is not null
		if(value == null) {
			return;
		}
		//Insert at the start of the list
		if(index <= 0) {
			addToFront(value);
			return;
		}
		//Insert at the start of the list
		if(index >= size()) {
			addToEnd(value);
			return;
		}
		
		//Create a new node for insertion
		Node newNode = new Node(value);
		Node current = head;
		//Keep visting the next nnode until the correct index is found
		for (int i = 0; i <= index - 1 && current.getNext() != null; i++) {
			current = current.getNext();
		}
		
		//Link the new node to the previous and next node 
		//Update the pointers of next and perviousnode to the current node
		//And update the counter
		newNode.setNext(current);
	    current.getPrevious().setNext(newNode);
	    newNode.setPrevious(current.getPrevious());
	    current.setPrevious(newNode);
	    count++;    
		
	}
	
	/**
	 * Add a value to the end of the list
	 * @param value Integer to add 
	 */
	public void addToEnd(Integer value) {
		//New node to be inserted
		Node current = new Node(value);
		current.setNext(null);
		Node previous = head;
		
		//Base case if there is nothing in the list
		if(head == null) {
			current.setPrevious(null);
			head = current;
			return;
		}
		//Cycle though the list until we are at the end node
		while(previous.getNext() != null) {
			previous = previous.getNext();
			
		}
		//Link the new node to the end of the list
		previous.setNext(current);
		current.setPrevious(previous);
		//Increase the counter
		count++;
	}
	
	/**
	 * Add a new value to the front of the lsit
	 * @param value Integer to add
	 */
	public void addToFront(Integer value) {
		//New node to insert
		Node current = new Node(value);
		current.setNext(head);
		current.setPrevious(tail);
		//Always runs in O(1) as you only have to visit the first node
		if(head != null) {
			head.setPrevious(current);
		}
		head = current;
		//Increase the counter
		count++;
		
	}
	
	/**
	 * Delete a value at a given position
	 * @param index location to delete node starting at 0
	 */
	public void deleteAt(int index) {
		//If the given index does not exist in the list then push a error message
		if(index < 0 || index > size()) {
			System.out.println("Remove a valid index number between: "+ 0 + " and " + size());
			return;
		}
		//If at the start of the list delete that node
		if(index == 0) {
			deleteFirst();
			return;
		}
		//If at the end of the list delete that node
		if(index == size()) {
			deleteLast();
			return;
		}
		//Start at the begining of the list
		Node delete = head;
		//Create a new counter to test against the index position
		int counter = 0;
		//Keep visiting each node until the correct position is reached
		while(delete.getNext() != null && counter != index) {
			delete = delete.getNext();
			counter++;
		}
		//Store the previous and next nodes to relink
		Node next = delete.getNext();
		Node previous = delete.getPrevious();	
		
		//Set the node to be deleted to null
		delete.setNext(null);
		delete.setPrevious(null);
		delete.setValue(null);
		//Relink the two adjacent nodes pointers to eachother
		next.setPrevious(previous);
		previous.setNext(next);
		
	}
	
	/**
	 * Delete the first node 
	 */
	public void deleteFirst() {
		Node delete = head;
		//Base case if there only exists one node in the list
		//Remove it in O(1)
		if(size() == 1) {
			delete.setValue(null);
			delete.setNext(null);
			delete.setPrevious(null);	
			count--;
			return;
		}
		//Move to the next node
		head = head.getNext();
		//Unlink node from list
		head.setPrevious(null);
		delete.setNext(null);
		delete.setPrevious(null);
		//Decrease counter
		count--;
	}
	
	/**
	 * Delete last node in the list
	 */
	public void deleteLast() {
		Node delete = head;
		//Base case if there only exists one node in the list
		//Remove it in O(1)
		if(size() == 1) {
			delete.setValue(null);
			delete.setNext(null);
			delete.setPrevious(null);	
			count--;
			return;
		}
		//Traverse list until you are at the last node
		while(delete.getNext() != null) {
			delete = delete.getNext();
		}
		//Get the previous node
		Node previous = delete.getPrevious();
		//Unlink the node from the list
		delete.setValue(null);
		delete.setNext(null);
		delete.setPrevious(null);
		//Update the last node to point at null
		previous.setNext(null);
		count--;
	}
	
	/**
	 * Reverse all the nodes 
	 */
	public void reverse() {
		 Node temp = null;
	     Node current = head;
	     	//If not at the end of the list
	        while (current != null) {
	        	//Store the currents pervious node
	            temp = current.getPrevious();
	            //Update the current node we are visiting and relink
	            current.setPrevious(current.getNext());
	            current.setNext(temp);
	            current = current.getPrevious();
	        }
	 
	        // Before changing head, check for the cases like
	        // empty list and list with only one node 
	        if (temp != null) {
	            head = temp.getPrevious();
	        }
		
	}
	/**
	 * Print the values of each node starting at the front of the list
	 */
	public void dumpForwards() {
		//If there is nodes in the list
		if (head != null) {
			Node current = head;
			//If not at the end
			while (current != null) {
				//Print the value
				System.out.println(current.getValue());
				//Traverse to the next node
				current = current.getNext();
			}
		}
	}
	
	/**
	 * Print the values of each not starting the at the end of the list
	 */
	public void dumpBackwards() {
		if (head != null) {
			Node current = head;
			//Traverse until at the end of the list
			while (current.getNext() != null) {							
				current = current.getNext();
			}
			//Traverse back down the list
			while(current.getPrevious() != null) {		
				//Print each nodes value
				System.out.println(current.getValue());
				//Go to the previous node
				current = current.getPrevious();
			}
			//Print the last node
			System.out.print(current.getValue());
		}
	}
	
	/**
	 * Each node in the list
	 * @author Christopher
	 *
	 */
	private class Node {
		private Node _previous;
		private Node _next;
		private Integer _value;
		
		/**
		 * Constructor 
		 * @param value Integer value of each node
		 */
		public Node(Integer value) {
			_value = value;
		}
		
		/**
		 * Get the value of each node
		 * @return this value
		 */
		public Integer getValue() {
			return this._value;
		}
		
		/**
		 * Get the next node
		 * @return the next node
		 */
		public Node getNext() {
			return this._next;
		}
		
		/**
		 * Get the previous node
		 * @return the previous node
		 */
		public Node getPrevious() {
			return this._previous;
		}
		
		/**
		 * Update a new value for this node
		 * @param value New integer value
		 */
		public void setValue(Integer value) {
			this._value = value;
		}
		
		/**
		 * Set a new next node to point to
		 * @param next update next node
		 */
		public void setNext(Node next) {
			this._next = next;
		}
		
		/**
		 * Set a new previous node to point to
		 * @param previous update previous node
		 */
		public void setPrevious(Node previous) {
			this._previous = previous;
		}
	}

}
