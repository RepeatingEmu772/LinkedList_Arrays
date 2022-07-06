public class LinkedList<T extends Comparable<T>> implements List<T> {
    public Node<T> head;
    private boolean isSorted;
    private int size=0;

    public LinkedList(){
        head = null;
        isSorted=true;
    }
    public boolean add(T element) {
        boolean isAdded = false;
        if(element ==null){
            return isAdded;
        }
        else{
            if(head==null){ //when list is empty
                head = new Node<T>(element,null);
                size++;
                isAdded = true;
            }
            else{
                Node tail = head;
                while (tail.getNext() != null){
                    tail = tail.getNext();
                }
                Node newNode = new Node(element,null);
                tail.setNext(newNode);
                size++;
                isAdded = true;
            }
        }
        checkSort();
        return isAdded;
    }
    public void checkSort(){
        if (head==null||head.getNext()==null){
            isSorted=true;
        }
        else {
            Node<T> ptr = head.getNext();
            Node<T> trailer = head;
            while (ptr != null) {
                if (ptr.getData().compareTo(trailer.getData()) >= 0) {
                    isSorted = true;
                } else {
                    isSorted = false;
                    return;
                }
                trailer = trailer.getNext();
                ptr = ptr.getNext();
            }
        }
    }
    public boolean add(int index, T element){
        if(index<0||index>=size()){
            return false;
        }
        else {
            if(index==0){
               head = new Node (element, head);
               size++;
            }
            else {
                Node<T> ptr = head.getNext();
                Node<T> trailer = head;
                for (int i = 0; i < index - 1; i++) {
                    ptr = ptr.getNext();
                    trailer = trailer.getNext();
                }
                trailer.setNext(new Node<T>(element,ptr));
                size++;
            }
            checkSort();
            return true;
        }
    }
    public void clear(){
        head=null;
        isSorted=true;
    }
    public T get(int index){
        if(index<0||index>size-1){
            return null;
        }
        Node<T> ptr = head;
        for(int i=0;i<index;i++){
            ptr=ptr.getNext();
        }
        return ptr.getData();
    }

    public int indexOf(T element) {
        int i=0;
        Node<T> ptr=head;
        if(element==null){
            return -1;
        }
        while (ptr!=null){
            if(ptr.getData().compareTo(element)==0){
                return i;
            }
            i++;
            ptr=ptr.getNext();
        }
        return -1;
    }
    public boolean isEmpty(){
        return head==null;

    }
    public int size(){
        int len=0;
        Node<T> ptr=head;
        while (ptr!=null){
            len++;
            ptr=ptr.getNext();
        }
        this.size=len;
        return len;
    }
    public T remove(int index){
        T t=null;
        if(index>=size()||index<0){
            return null;
        }
        else if(index==0){
            t = head.getData();
            head=head.getNext();
        }
        else {
            Node<T> ptr = head.getNext();
            Node<T> trailer = head;
            for (int i = 0; i < index-1 ; i++) {
                ptr = ptr.getNext();
                trailer = trailer.getNext();
            }
            t = ptr.getData();
            trailer.setNext(ptr.getNext());
        }
        checkSort();
        return t;
    }

    public boolean isSorted(){
        return this.isSorted;
    }

    public void sort(){
        if(isSorted==true){

        }
        else {
            Node<T> ptr = head;
            while (ptr != null) {
                Node<T> min = ptr;
                Node<T> temp = ptr.getNext();
                while (temp != null) {
                    if (min.getData().compareTo(temp.getData()) > 0) {
                        min = temp;
                    }
                    temp = temp.getNext();
                }
                T x = ptr.getData();
                ptr.setData(min.getData());
                min.setData(x);
                ptr = ptr.getNext();
            }
            isSorted = true;
        }
    }
    public void equalTo(T element){
        Node<T> ptr=head;
        Node prevOccurance=null;
        while(ptr!=null){
            if(ptr.getData().compareTo(element)==0){
                if(prevOccurance==null){
                    head=ptr;
                    prevOccurance=ptr;
                }
                else {
                    prevOccurance.setNext(ptr);
                    prevOccurance=ptr;
                }
            }
            ptr=ptr.getNext();
        }
        //checkSort();
        if(prevOccurance!=null){
            prevOccurance.setNext(null);
        }
        isSorted=true;

    }
    public void reverse() {
        if(head==null||head.getNext()==null){
            return;
        }
        else {
            Node<T> trailer = null;
            Node<T> ptr = head;
            Node<T> next = null;
            while (ptr != null) {
                next = ptr.getNext();
                ptr.setNext(trailer);
                trailer = ptr;
                ptr = next;
            }
            head = trailer;
            }
        checkSort();
        }

    public void merge(List<T> otherList) {
        LinkedList<T> other = (LinkedList<T>) otherList;
        sort();
        other.sort();
        Node temp = new Node(0);
        Node ptr = this.head;
        Node otherptr = other.head;

        Node tail = temp;
        if (other == null) {
        } else {
            while (true) {
                if (ptr == null) {
                    tail.setNext(otherptr);
                    break;
                }
                if (otherptr == null) {
                    tail.setNext(ptr);
                    break;
                }
                if (ptr.getData().compareTo(otherptr.getData()) < 0) {
                    tail.setNext(ptr);
                    ptr = ptr.getNext();
                } else {
                    tail.setNext(otherptr);
                    otherptr = otherptr.getNext();
                }
                tail = tail.getNext();
            }
            head = temp.getNext();
        }
        checkSort();
    }
    public boolean rotate(int n){
        boolean isRotated=false;

        if(n<=0||size<=1){
            isSorted=false;
        }
        else {
            for (int i = 0; i < n; i++) {
                Node<T> ptr = head.getNext();
                Node<T> trailer = head;
                while (ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    trailer = trailer.getNext();
                }
                ptr.setNext(head);
                head=ptr;
                trailer.setNext(null);
            }
            isRotated = true;
        }
        checkSort();
        return isRotated;
    }

    public String toString() {
        if(head==null){
            return null;
        }
        else {
            Node<T> ptr = head;
            while (ptr.getNext() != null) {
                System.out.println(ptr.getData());
                ptr = ptr.getNext();
            }
            return ptr.getData().toString();
        }
    }
}
