package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {

		root = null;
		size = 0;

	}

	public static void main(String[] args) {

		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		BSTVisualizer bst = new BSTVisualizer("Binary Search Tree", 500, 360);

		b.add(7);
		b.add(3);
		b.add(11);
		b.add(1);
		b.add(5);
		b.add(9);
		b.add(13);
		b.printTree();
		bst.drawTree(b);

		BinarySearchTree<Integer> b1 = new BinarySearchTree<Integer>();
		BSTVisualizer bst1 = new BSTVisualizer("Binary Search Tree", 500, 360);
		for (int i = -20; i <= 20; i++)
			b1.add(i);
		b1.rebuild();
		b1.printTree();
		bst1.drawTree(b1);

	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {

		// sök element, börja i rot. Om rot är tom, lägg till en nod.
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}

		// använd boolean eftersom addNew() är en boolean
		boolean isAdded = addNew(root, x); // lägg till rot
		if (isAdded) {
			size++;
		}
		return isAdded; // returnerar false också om add inte lyckas
	}

	// hjälpmetoder kan inte lägga till något utanför klassen
	private boolean addNew(BinaryNode<E> n, E x) {
		int comp = x.compareTo(n.element);

		// find
		if (comp == 0) { // x och nodens element är samma
			return false; // returnera false eftersom vi inte gör ngt
		} else if (comp < 0) { // x är mindre än nodens element
			if (n.left == null) {
				n.left = new BinaryNode<E>(x);

				return true; // sluta här när klart
			}
			return addNew(n.left, x); // om noden inte är tom,
		} else { // gå igenom den igen
			// om comp > 0
			if (n.right == null) {
				n.right = new BinaryNode<E>(x);

				return true;
			}
			return addNew(n.right, x);
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {

		return heightOfTree(root);
	}

	private int heightOfTree(BinaryNode<E> n) {
		if (n != null) {
			// 1 + förälderns nivå, kollar vänstra och högra noden för varje nivå
			return 1 + Math.max(heightOfTree(n.left), heightOfTree(n.right));
		}
		return 0;
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {

		System.out.println(buildString(root)); // istället för return

	}

	private String buildString(BinaryNode<E> n) {
		StringBuilder sb = new StringBuilder();
		if (n != null) {
			buildString(n.left); // traversera till vänster
			sb.append(n.element.toString()); // skriv ut denna nods element
			sb.append('\n');
			buildString(n.right); // traversera till höger
		}
		return sb.toString();
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {

		E[] a = (E[]) new Comparable[size];
		toArray(root, a, 0); // börjar på root, index = 0 för att börja med vänster subträds barn

		root = buildTree(a, 0, a.length - 1);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1 (the
	 * first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {

		// index ändras
		if (n != null) {
			index = toArray(n.left, a, index); // traversera vä subträd
			a[index] = n.element; // vårt index
			index++; // ökar index innan höger traversering
			index = toArray(n.right, a, index);
		}

		return index; // returnerar 0 i nedersta noden till vänster
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in the
	 * array a are assumed to be in ascending order. Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {

		int mid = first + ((last - first) / 2);

		BinaryNode<E> b = new BinaryNode<E>(a[mid]);

		if (mid > first) { // lägg till på vänster sida
			b.left = buildTree(a, first, mid - 1); // last här är högra noden
		}
		if (mid < last) { // lägg till på höger sida
			b.right = buildTree(a, mid + 1, last);
		}
		return b;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
