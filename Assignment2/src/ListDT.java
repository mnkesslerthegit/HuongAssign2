
public class ListDT<T extends Comparable<T>> implements ListInterface<T> {

	private Node<T> head;

	private Node<T> last;

	private Node<T> current;

	public ListDT() {
		head = new Node<T>(null);
		last = head;
		reset();

	}

	@Override
	public int size() {
		return head.getNumChildren();
	}

	@Override
	public void add(T element) {
		last.add(new Node<T>(element));
		last = last.next;

	}

	@Override
	public boolean contains(T element) {
		if (head.data.equals(element)) {
			return true;
		}

		Node<T> temp = head;
		while (temp != last) {
			if (temp.data.equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(T element) {

		// first, handle the case where the only node is the head, or the list
		// is empty
		Node<T> temp = head;
		if (head.next == null) {
			if (head.data == null) {
				return false;
			} else if (head.data.equals(element)) {
				head.data = null;
				return true;
			}

		}

		// if the list contains more than one node, loop through the nodes until
		// we find the second to last
		while (temp.next != last) {
			temp = temp.next;
		}
		temp.next = null;
		last = temp;
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
