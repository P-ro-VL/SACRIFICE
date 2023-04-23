package neu.cs.sacrifice.api.utils;

/**
 * Một class hỗ trợ tạo ra một cặp {@code [key, value]}. {@link Pair} khác với
 * {@link java.util.HashMap HashMap} ở chỗ là HashMap có thể chứa nhiều cặp
 * {@code [key, value]} khác nhau, nhưng Pair thì chỉ có một {@code key} và một
 * {@code value} tương ứng với {@code key} đó.<br>
 * Pair không cần thiết phải rút {@code value} từ {@code key}, có thể rút trực
 * tiếp cả 2 mà không cần thông qua cái còn lại.
 */
public class Pair<K, V> implements Cloneable, Comparable<Pair<K, V>> {

	/**
	 * Create a new {@link Pair} instance.
	 */
	public static <K, V> Pair<K, V> of(K key, V value) {
		return new Pair<K, V>(key, value);
	}

	private K key;
	private V value;

	public Pair() {
	}

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "[" + getKey().toString() + ", " + getValue().toString() + "]";
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	/**
	 * Thay đổi giá trị {@code key}
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * Thay đổi giá trị {@code value}
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * So sánh hai {@code Pair<K, V>} với nhau, trả lại bằng {@code 1} nếu giống
	 * nhau, {@code 0} nếu ngược lại.
	 */
	@Override
	public int compareTo(Pair<K, V> o) {
		return (o.getKey().equals(getKey()) && o.getValue().equals(getValue())) ? 1 : 0;
	}

}
