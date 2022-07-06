public class ArrayList<T extends Comparable<T>> implements List<T> {
    private T[] a;
    private boolean isSorted = true;
    private int nextEmpty = 0;

    public ArrayList() {
        a = (T[]) new Comparable[2];

    }

    public boolean add(T element) {
        if (element != null) {
            if (isLastNull() == false) {
                doubleNewArray();
            }
            a[nextEmpty] = element;
            nextEmpty++;
            if ((a[1] != null) && (isSorted == true)) {
                if (a[nextEmpty - 1].compareTo(a[nextEmpty - 2]) >= 0) { isSorted = true; }
                else { isSorted = false; }
            }
            return true;
        }
        else {
            return false;
        }
    }


    public void doubleNewArray() {
        T[] b = (T[]) new Comparable[a.length * 2];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        a = b;
    }

    public boolean isLastNull() {
        if (a[a.length - 1] == null) { return true; }
        else { return false; }
    }

    public boolean add(int index, T element) {
        if (element == null || indexOutofBounds(index) == true) { return false; }
        else {
            if (isLastNull() == false) { doubleNewArray(); }

            for (int i = 0; i <= size() - (index + 1); i++) { // 6 - (3 + 1) = 2
                a[nextEmpty - i] = a[nextEmpty - (i + 1)];
            }
            if ((a[1] != null) && (isSorted == true)) {
                for (int i = 0; i < nextEmpty - 1; i++){
                    if (a[i].compareTo(a[i+1]) > 0) {
                        isSorted = false;
                    }
                }
            }
            a[index] = element;
            nextEmpty++;
            return true;
        }
    }

    public void clear() {
        T[]b = (T[]) new Comparable[2];
        a = b;
        isSorted = true;
        nextEmpty = 0;
    }

    public T get(int index) {
        if (indexOutofBounds(index) == true) { return null; }
        return a[ index];
    }

    public boolean indexOutofBounds(int index) {
        if (index < 0 || index >= nextEmpty) { return true; }
        else { return false; }
    }

    public int indexOf(T element) {
        int return_statement = -1;
        if (isSorted == false) {
            for (int i = 0; i < size(); i++) {
                if (element == a[i]) {
                    return_statement = i;
                    break;
                }
            }
        }
        else {
            for (int i = 0;i < size(); i++) {
                if(a[i].compareTo(element) > 0) {
                    return_statement = -1;
                    break;
                }
                else if (a[i].compareTo(element)  == 0) {
                    return_statement = i;
                    break;
                }
            }
        }
        return return_statement;
    }

    public int binarySearch(T element, int leftIndex, int rightIndex){
        int mid = (leftIndex + rightIndex) / 2;
        T middle = a[mid];
        if (a[mid].compareTo(element) == 0) {
            return mid;
        }
        else if (a[mid].compareTo(element) > 0) {
            return binarySearch(element, leftIndex, mid - 1);
        }
        else {
            return binarySearch(element, rightIndex, mid + 1);
        }
    }

    public boolean isEmpty() {
        if (nextEmpty == 0) { return true; }
        else { return false; }
    }

    public int size() {
        return nextEmpty;
    }

    public void sort() { // selection sort
        if (isSorted == false) {
            for (int i = 0; i < size() - 1; i++) {
                int min = i;
                for (int j = i + 1; j < size(); j++) {
                    if (a[j].compareTo(a[min]) < 0) {
                        min = j;
                    }
                }
                T temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
            isSorted = true;
        }
    }

    public T remove2(int index) {
        T removedElement = a[index];
        if (indexOutofBounds(index) == true) { return null; }
        for (int i = 0; i < size() - (index + 1); i++) {
            a[index + i] = a[index + i + 1];
        }

        nextEmpty--;
        a[nextEmpty] = null;
        if (isSorted == true) {
            return a[index];
        }
        else {
            for (int i = 0; i < size() - 1; i++) {
                if (a[i].compareTo(a[i + 1]) > 0) {
                    isSorted = false;
                    return a[index];
                }
            }
            isSorted = true;
            return removedElement;
        }

    }

    public T remove(int index) {
        T x = null;
        if (indexOutofBounds(index)){
            return x;
        }
        else {
            x = a[index];
            for (int i = 0; i < size() - index - 1; i++) {
                a[index + i] = a[index + i + 1];
            }
            nextEmpty--;
            a[nextEmpty] = null;
        }

        isSorted = true;
        for (int i = 0; i < size() - 1; i++){
            if (a[i].compareTo(a[i+1]) > 0) {
                isSorted = false;
            }
        }
        isSorted = true;
        return x;
    }

    public void equalTo(T element) {
        if (element == null) {
            return;
        }
        for (int i = 0; i < size(); i++) {
            if (a[i].compareTo(element) != 0) {
                remove(i);
                i--;
            }
        }


    }

    public void isLeftElementEqualTo(int index, T element) {
        if (index == 0) {
            if (a[0] != element) {
                remove(0);
            }
        }
        else if (a[index - 1] != element) {
            remove(index-1);
        }
    }


    //    public boolean isLeftElement (int index, T element) {
//        if (a[0] == element) { return true; }
//        else if (index == 0 || a[index - 1] != element) { return false; }
//        else { return true; }
//    }
//
//    public boolean isRightElement (int index, T element) {
//        if (a[nextEmpty] == element) { return true; }
//        if (index == nextEmpty || a[index + 1] != element) { return false; }
//        else { return true; }
//    }
    // NEED TO CHECK IF THIS USES INTERMEDIATE DATA STRUCTURES
    public void reverse(){
        for (int i = 0; i < size() * 0.5; i++) {
            T indexLeft = a[i];
            T indexRight = a[(nextEmpty - 1) - i];
            a[i] = indexRight;
            a[(nextEmpty - 1) - i] = indexLeft;
        }
    }

    public void merge(List<T> otherList){
        if (otherList != null){
            ArrayList<T> other = (ArrayList<T>) otherList;
            T[] newList = (T[]) new Comparable [size() + other.size()];
            int main = 0;
            int otherCounter = 0;
            int nextEmptyNewList = 0;
            sort();
            other.sort();
            T currMain = get(main);
            T currOther = other.get(otherCounter);
            while (currMain != null && currOther != null) {
                if (currMain.compareTo(currOther) < 0) {
                    newList[nextEmptyNewList] = currMain;
                    main++;
                    nextEmptyNewList++;
                    currMain = get(main);
                }
                else {
                    newList[nextEmptyNewList] = currOther;
                    otherCounter++;
                    nextEmptyNewList++;
                    currOther = other.get(otherCounter);
                }
            }
            while (currMain != null) {
                newList[nextEmptyNewList] = currMain;
                main++;
                nextEmptyNewList++;
                currMain = get(main);
            }
            while (currOther != null) {
                newList[nextEmptyNewList]= currOther;
                otherCounter++;
                nextEmptyNewList++;
                currOther = other.get(otherCounter);
            }
            a = newList;
            nextEmpty = size() + other.size();
            isSorted = true;
        }
    }

    public boolean rotate(int n){
        if (n <= 0 || size() <= 1) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = size() - 1; j > 0; j--) {
                T storage = a[j];
                a[j] = a[j - 1];
                a[j - 1] = storage;
            }
        }
        return true;
    }

    public String toString() {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        return " ";
    }

    public boolean isSorted() {
        return isSorted;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        // // System.out.println(list);
        list.add(2);
        // //System.out.println(list);
        list.add(3);
        list.add(3);
        list.add(3);
        // System.out.println(list.isSorted());
        list.add(5);
        list.add(6);
        list.add(7);
        // System.out.println(list.isSorted());
        // //System.out.println(list);
        // list.add(3);
        // //System.out.println(list);
        // list.add(4);
        // //System.out.println(list);
        // list.add(5);
        // // System.out.println(list.indexOutofBounds(0));
        // // System.out.println(list.indexOutofBounds(4));
        // list.add(6);
        // // System.out.println(list); // 1 1 1 1 1 1
        // // System.out.println(list.get(4)); //3
        // // System.out.println(list.get(5)); //null
        // // System.out.println(list.get(9)); //null
        // // System.out.println(list.nextEmpty); //0
        // // System.out.println(list.isEmpty()); //false
        // list.add(2, 9);
        // System.out.println(list); // 1 1 1 8 1 1 1
        // list.rotate(1);
        // System.out.println(list);
        // System.out.println(list.binarySearch(0, 0, 5));
        System.out.println(" ");
        System.out.println(list);
        list.equalTo(3);
        System.out.println(list);


    }
}



