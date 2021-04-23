package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	
	Entry<K,V>[] table;						//skapar ny tabell
	final static double loadFactor = 0.75;
	int capacity, size;
	
	/** Constructs an empty hashmap with the default initial capacity (16)
	and the default load factor (0.75). */
	public SimpleHashMap() {
		this(16);
	}
	
	/** Constructs an empty hashmap with the specified initial capacity
	and the default load factor (0.75). */
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
	}
	
	private int index(K key) { 
		return Math.abs(key.hashCode() % table.length);
		}
	
	private Entry<K,V> find(int index, K key) {
		
		Entry<K,V> e = table[index];	//hämtar index från listan
		
		while (e != null) {
			if (e.getKey().equals(key)) {
				return e;
			}
			
			e = e.next;			//om nyckeln inte finns på given plats, gå vidare till nästa
		}
		
		return null;
	}
	
	public String show() {
		
		StringBuilder sb = new StringBuilder();		//skapa för att kunna returnera
		
		int index = 0;
		for (Entry<K,V> e : table) {
			if (e == null) sb.append(Integer.toString(index));
			
			
			while (e != null) {
			sb.append(index + " ");
			sb.append(e.toString() + " ");		//e.toString() från metoden ovan	
			e = e.next;
			}
			
			sb.append('\n');
			index++;
			
		}
		
		return sb.toString();

	}
	
	public static class Entry<K,V> implements Map.Entry<K,V> {
		
		private K key;
		private V value;
		public Entry<K, V> next;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {

			return key;
		}

		@Override
		public V getValue() {

			return value;
		}

		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}

		@Override
		public String toString() {

			return key.toString() + "=" + value.toString();
		}
		
		
	}
	
	
	

	@Override
	public V get(Object object) {
		K key = (K) object;
		
		Entry<K,V> e = find(index(key), key);	//hitta key
		if (e != null) {
			return e.value;		//returnera nyckelns värde om den hittas
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		
		int index = index(key);
		Entry<K,V> e = find(index, key);
		if (e != null) {
			V val = e.value;
			e.setValue(value);		//skriver över det gamla värdet med samma nyckel, med det nya
			return val;
		} else {
			if (table[index] != null) {
				e = table[index];

				Entry<K,V> newE = new Entry<K,V>(key, value); //sätts före första
				newE.next = e;
				table[index] = newE;
				size++;
			} else {
				e = new Entry<K,V>(key, value);
				table[index] = e;
				size++;
			}
			
			if ( size > table.length*loadFactor) {		//rehasha om kapaciteten överstigs
				rehash();
			}
		
		}
		
		
		
		return null;
	}
	
	private void rehash() {
	
		Entry<K,V>[] h = table;
		capacity *= 2;
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
		for (Entry<K,V> e: h) {
			while (e != null) {
				put(e.getKey(), e.getValue());
				e = e.next;
			}
			
		}
	}

	@Override
	public V remove(Object obj) {

		Entry<K,V> e = table[index((K) obj)];
		
		if (e != null) {
			if (e.getKey().equals(obj)) {		//kollar om nyckel finns i listan
				table[index((K) obj)] = e.next;		//ersätt med nästa
				size--;							//minska antalet element
				return e.getValue();			
			} else {
				while (e.next != null) {		//leta vidare om nyckel ej matchar obj
					if (e.next.getKey().equals(obj)) {		//om nästa nyckel matchar obj
						V v = e.next.getValue();		
						e.next = e.next.next;		//länka nästa till elementet efter
						size--;
						return v;
					}
					
				
				}
			}
		}
		
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	
	
	public static void main(String[] args) {
		SimpleHashMap<String, Integer> s = new SimpleHashMap<String, Integer>();
		s.put("Aisha", 1);
		s.put("Sabah", 1);
		s.put("Nadjma", 1);
		s.put("Fanny", -10);
		s.put("Li", 23);
		
		System.out.println(s.show());
	}
}
