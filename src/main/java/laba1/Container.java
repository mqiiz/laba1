package laba1;

/**
 * A container class that stores an arbitrary number of objects
 *
 * @param <T> Stored object type
 * @author mqiiz
 */
public class Container<T> {

    /**
     * @value Default value of the array size when using the constructor with no parameters
     */
    private static final int INIT_SIZE = 10;

    /**
     * @value Array of objects
     */
    private Object[] array;

    /**
     * @value The current position of the index in the array
     */
    private int index;

    /**
     * @value The current array size
     */
    private int size;

    /**
     * A constructor with no parameters that initializes an array with a default value {@link Container#INIT_SIZE}
     */
    public Container() {
        this.array = new Object[INIT_SIZE];
        this.index = 0;
        this.size = INIT_SIZE;
    }

    /**
     * A constructor that initializes an array with parameter size
     *
     * @param size Size of an array
     */
    public Container(int size) {
        this.array = new Object[size];
        this.index = 0;
        this.size = size;
    }

    /**
     * A method that adds an object to the end of an array
     *
     * @param object Object to insert
     */
    public void add(T object) {
        if (isArrayFull())
            expand();
        array[index++] = object;
    }

    /**
     * A method that adds an object to an array by shifting elements and inserting that element to free space
     *
     * @param object Object to insert
     * @param index  Index where to insert object
     */
    public void add(T object, int index) {
        checkIndex(index);
        if (isArrayFull())
            expand();
        shiftRight(index);
        array[index] = object;
    }

    /**
     * A method that removes an object by index from an array by shifting elements
     *
     * @param index Index of object to remove
     */
    public void remove(int index) {
        checkIndex(index);
        shiftLeft(index);
    }

    /**
     * A method that removes an object from an array by shifting elements
     *
     * @param object Object to remove
     */
    public void remove(T object) {
        for (int i = 0; i < size; i++)
            if (object.equals(array[i]))
                remove(i);
    }

    /**
     * A method that returns an object by index
     *
     * @param index Index of object to return
     * @return Object of type T by input parameter index
     */
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    /**
     * A method that rewrites an object in array by index
     *
     * @param object Object to rewrite
     * @param index  Index of object to rewrite
     */
    public void set(T object, int index) {
        checkIndex(index);
        array[index] = object;
    }

    /**
     * A method that returns the index of the passed object
     *
     * @param object Object to find index
     * @return int index
     */
    public int getIndexOf(T object) {
        for (int i = 0; i < size; i++)
            if (object.equals(array[i]))
                return i;
        return -1;
    }

    /**
     * A method that returns the current size of an array
     *
     * @return int size
     */
    public int getSize() {
        return size;
    }

    /**
     * A method that overrides method {@link Object#toString()} to represent a Container object as string
     *
     * @return String container
     */
    @Override
    public String toString() {
        String str = "[";
        for (Object element : array)
            if (element != null)
                str = str.concat(element.toString()).concat(", ");
        return str.substring(0, str.length() - 2).concat("]");
    }

    /**
     * A method that checks if the passed index is correct. Otherwise throws {@link IncorrectIndexException}
     *
     * @param index Index to check
     */
    private void checkIndex(int index) {
        if (index >= size || index < 0)
            try {
                throw new IncorrectIndexException("There is no such an index in array");
            } catch (IncorrectIndexException e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * A method that checks if all array cells are filled
     *
     * @return <b>boolean</b> if array is full
     */
    private boolean isArrayFull() {
        return index >= size;
    }

    /**
     * A method that expands an array by 1 when it is full
     */
    private void expand() {
        Object[] newArray = new Object[++size];
        copy(array, newArray);
        array = newArray;
    }

    /**
     * A method that copies array "a" to array "b". The size of an array a must not exceed that of array b
     *
     * @param a Array to copy
     * @param b Array where to copy
     */
    private void copy(Object[] a, Object[] b) {
        if (b.length >= a.length) {
            for (int i = 0; i < a.length; i++)
                b[i] = a[i];
        }
    }

    /**
     * A method that shifts array elements to the right by 1 from passed index
     *
     * @param index Index from which to shift
     */
    private void shiftRight(int index) {
        Object[] arr = new Object[size];
        int counter = 0;
        for (int i = index; i < size; i++)
            arr[counter++] = array[i];
        counter = 0;
        expand();
        for (int i = index + 1; i < size; i++)
            array[i] = arr[counter++];
        array[index] = null;
        this.index++;
    }

    /**
     * A method that shifts array elements to the left by 1 from passed index
     *
     * @param index Index from which to shift
     */
    private void shiftLeft(int index) {
        Object[] arr = new Object[size];
        int counter = 0;
        for (int i = index + 1; i < size; i++)
            arr[counter++] = array[i];
        counter = 0;
        for (int i = index; i < size; i++)
            array[i] = arr[counter++];
        array[size - 1] = null;
        this.index--;
    }

}