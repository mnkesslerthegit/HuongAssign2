
public class ListDT<T extends Comparable<T>> implements ListInterface<T> {

	private Node<T> head;

	private Node<T> last;

	private Node<T> current;

	public ListDT() {
		head = new Node<T>(null);
		last = head;

		// set current to head
		reset();

	}

	/**
	 * Size is equal to the number of children the head has, + 1
	 */
	public int size() {
		int result = head.getNumChildren();
		if (head.data != null) {
			result++;
		}
		return result;
	}

	@Override
	public void add(T element) {

		// handle the case where the list is empty

		if (head.data == null) {

			head.data = element;
		} else {

			reset();
			int size = size();

			// handle the case where the head can be swapped

			if (head.data.compareTo(element) > 0) {
				// create new node
				Node<T> next = new Node<>(element);
				// have it point at the head
				next.next = head;
				// it becomes the new head
				head = next;
				return;
			}

			// System.out.print("last is: " + last.data);

			for (int i = 0; i < size; i++) {
				// start at the second node
				
				int relation;

				try {
					relation = element.compareTo(current.next.data);
				} catch (NullPointerException e) {
					// again, if we hit null, it means we are at the end of the
					// list

					last.next = new Node<>(element);
					last = last.next;
					return;
				}

				if (relation > 0) {
					getNext();
					continue;
				} else {
					Node<T> next = new Node<>(element);

					// set the new node to point to the node after the current
					// one,
					next.next = current.next;
					// and set the current node to point to the new node
					current.next = next;
					if (next.next == null) {
						last = next;
					}
					return;

				}
				
			}

		}

	}

	@Override
	public boolean contains(T element) {
		if (size() == 0) {
			return false;
		}

		if (head.data.equals(element)) {
			return true;
		}

		int size = size();
		reset();
		for (int i = 0; i < size; i++) {
			if (getNext().equals(element)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean remove(T element) {

		// only remove if we contain the given elemtent
		if (!contains(element)) {
			return false;
		}
		// first, handle the case where the only node is the head, or the list
		// is empty

		if (head.next == null) {
			if (head.data == null) {
				return false;
			} else if (head.data.equals(element)) {
				head.data = null;
				return true;
			}

		}

		// handle the case where we remove the head
		if (head.data.equals(element)) {
			head = head.next;
			return true;
		}

		// if the list contains more than one node, loop through the nodes until
		// we find the data
		reset();
		int size = size();

		int location = 0;
		for (int i = 0; i < size; i++) {
			// stop here to make current equal to the node we want to find
			if (getNext().equals(element)) {
				location = i;
				break;
			}
		}

		reset();
		for (int i = 0; i < location - 1; i++) {

			// stop here to make current equal to the node before the node we
			// want to find

			getNext();
		}

	//	System.out.print("current is: " + current.data);
		try {
			// replace the node's child with its child's child
			current.next = current.next.next;

		} catch (NullPointerException e) {
			// this just means the data we removed was the last
			current.next = null;
		}
		// we should be on last node after the process is finished. Set last to
		// current.
		last = current;

	//	System.out.print("this method finished");
		return true;
	}

	@Override
	public T get(T element) {
		if (!contains(element)) {
			return null;
		}

		Node<T> temp = head;
		if (temp.data.equals(element)) {
			return temp.data;
		}
		while (temp != last) {
			temp = temp.next;
			if (temp.data.equals(element)) {
				return temp.data;
			}

		}

		return null;
	}

	@Override
	public void reset() {
		current = head;

	}

	public T getNext() {
		T result = current.data;
		if (current == last) {
			current = head;
		} else {
			if (current.next != null) {
				current = current.next;
			}
		}
		return result;
	}

	/**
	 * Nodes hold data, and a reference to the next node.
	 * 
	 * @author Max
	 *
	 * @param <T>
	 */
	private static class Node<T> {

		private Node<T> next;
		private T data;

		public void add(Node<T> nextNode) {
			next = nextNode;
		}

		public Node(T data) {
			this.data = data;

		}

		public int getNumChildren() {
			int result = 0;
			Node<T> temp = next;
			while (temp != null) {
				temp = temp.next;
				result++;
			}
			return result;
		}

	}

}
