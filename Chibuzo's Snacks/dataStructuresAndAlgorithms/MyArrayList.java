package dataStructuresAndAlgorithms;

public class MyArrayList {
    private int numberOfElements;
    private String[] elements = new String[5];

    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    public void add(String element) {
        if (size() == elements.length - 1) createNewArray();

        elements[numberOfElements] = element;
        numberOfElements++;
    }

    public void remove(String element) {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("List is empty");
        else if (!arrayContains(element)) throw new IllegalArgumentException(element + " is not in the list");

        elements = removeElementsBy(element);
        numberOfElements--;
    }

    public void remove(int index) {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("List is empty");

        elements = removeElementsBy(index);
        numberOfElements--;
    }

    public String get(int index) {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("List is empty");

        return elements[index -1];
    }

    public int get(String element) {
        for (int index = 0; index < numberOfElements; index++) if (elements[index].equals(element)) return index + 1;

        return -1;
    }

    public int size() {
        return numberOfElements;
    }

    public boolean arrayContains(String element) {
        for (String string : elements) if (element.equals(string)) return true;
        return false;
    }

    private String[] removeElementsBy(String element) {
        String[] temp = new String[elements.length];
        int counter = 0;

        for (int index = 0; index < numberOfElements; index++) {
            if (elements[index].equals(element)) continue;

            temp[counter] = elements[index];
            counter++;
        }
        return temp;
    }

    private String[] removeElementsBy(int index) {
        String[] temp = new String[elements.length];
        int counter = 0;

        for (int row = 0; row < numberOfElements; row++) {
            if (row == index -1) continue;

            temp[counter] = elements[row];
            counter++;
        }
        return temp;
    }

    private void createNewArray() {
        String[] newArray = new String[elements.length * 2];

        if (size() >= 0) System.arraycopy(elements, 0, newArray, 0, size());
        elements = newArray;
    }
}