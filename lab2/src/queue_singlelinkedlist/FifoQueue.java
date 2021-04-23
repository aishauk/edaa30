package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element
	 * 			to this queue, else false
	 */
	
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);
		if (last == null) {
			last = n;
			n.next = n;
		} else {				//lägger till element i en lista med fler element
			n.next = last.next;	//last.next är första elementet i listan
			last.next = n;	//nytt element är n
			last = n;		//n sist i listan
		}
		size++;
		return true;


	}

	/**
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue,
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null
	 * 			if this queue is empty
	 */
	public E peek() {
		if (last != null) {
			return last.next.element;	//returnera elementet i noden
		}
		return null;
	}

	/**
	 * Retrieves and removes the head of this queue,
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty
	 */
	public E poll() {

		if (last == null) {
			return null;
		}
		
		if (last == last.next) {
			QueueNode<E> onlyNode = last;
			size--;
			last = null;
			return onlyNode.element;
		}

		if (last != null) {
			QueueNode<E> first = last.next;
			last.next = first.next;
			size--;
			return first.element;
		}
		
		return null;
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	

	
	public void append(FifoQueue<E> q) {
		if(this==q) {
			throw new IllegalArgumentException();
		}
		else {
			if (q.last != null) {		//måste peka på q.last
				if (this.last == null) {
					this.last = q.last;
					this.size = q.size;

				} else {
					QueueNode<E> n = this.last.next;		//sparar i en queuenode för att kunna länka
					this.last.next = q.last.next;			//next används för att peka på nästa element
					q.last.next = n;						//sista elementet i listan q pekar på första i this
					this.last = q.last;						//här flyttar vi över hela listan
					this.size += q.size;					//adderar listornas stl
				
				}
					q.last = null;		//tömmer q
					q.size = 0;
			}
			
		}
		
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */

	public Iterator<E> iterator() {
		return new QueueIterator();
	}

private class QueueIterator implements Iterator<E> {

	private QueueNode<E> pos;
	private int itrNo;		//antal element i kön

	private QueueIterator() {
		itrNo=size;
		if(last==null) {	//inget i listan
			pos=null;
		} else {
			pos = last.next;	//första plats i listan
		}

	}

	@Override
	public boolean hasNext() {	//kolla om det finns en till nod
		/*if (pos==null) {
			return null;
		} else {
			return pos = last.next;
		} */
		
		return itrNo != 0;

	}

	@Override
	public E next() {		//returnera elementet i noden, om det finns
		if (hasNext()) {
			itrNo--;
			QueueNode<E> temp = pos;	//skapar temporär nod och returnerar (är det för att metoden raderar noden?)
			pos = pos.next;
			return temp.element;
		} else {
			throw new NoSuchElementException();	//enl javadoc
		}
	}



	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
