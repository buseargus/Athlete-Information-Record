package AthleteInformationRecord;

import java.util.NoSuchElementException;

public class DoublyLinkedList {

    private Node head, tail;
    private int size = 0;

    public DoublyLinkedList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    //The purpose of this method is to add athletes in alphabetical order.
    public void addNode(Info info) {
        Node node = new Node(info);
        if (size == 0) {
            head = node;
            tail = node;
            size++;
        } else {
            ListIterator itr = new ListIterator();
            String second = node.data.getNameSurname();
            for (int j = 0; j < size; j++) {
                String first = itr.prev.data.getNameSurname();
                String third = itr.curr.data.getNameSurname();
                if (Compare(first, second).equals(second) && head.data.getNameSurname().equals(first)) {
                    head = node;
                    head.prevLink = null;
                    head.nextLink = itr.prev;
                    itr.prev.prevLink = node;
                    size++;
                    break;
                } else if (Compare(first, second).equals(first) && Compare(third, second).equals(second)) {
                    itr.prev.nextLink = node;
                    node.prevLink = itr.prev;
                    itr.curr.prevLink = node;
                    node.nextLink = itr.curr;
                    size++;
                    break;
                } else if (Compare(third, second).equals(third) && tail.data.getNameSurname().equals(third)) {
                    tail = node;
                    tail.prevLink = itr.curr;
                    itr.curr.nextLink = node;
                    size++;
                    break;
                }
                itr.next();
            }
        }
    }

    //The purpose of this method is to delete the node with the information of the athlete
    // whose name is sent to the "delete athlete" button.
    public void deleteNode(String sporcu) {
        if (findNode(sporcu) != null) {
            Node node = findNode(sporcu);
            if (node.prevLink == null) {
                node.nextLink.prevLink = null;
                head = node.nextLink;
            } else if (node.nextLink == null) {
                node.prevLink.nextLink = null;
                tail = node;
            } else {
                node.prevLink.nextLink = node.nextLink;
                node.nextLink.prevLink = node.prevLink;
            }
        }
    }

    //The purpose of this method is to search and return if the athlete
    // whose name and surname is entered has a node.
    public Node findNode(String nameSurname) {
        nameSurname = nameSurname.trim();
        ListIterator itr = new ListIterator();
        while (itr.hasNext()) {
            boolean found = (itr.curr.data.getNameSurname().equals(nameSurname));
            if (found) {
                return itr.curr;
            }
            itr.next();
        }
        return null;
    }

    //The purpose of this method is to return the information in the nodes as a String(from beginning to end),
    //for printing on the screen in the TextArea.
    public String printList() {
        String print = "";
        ListIterator itr = new ListIterator();
        while (itr.hasNext()) {
            print = print + itr.curr + "\n";
            itr.next();
        }
        return print;
    }

    //The purpose of this method is to return the information in the nodes as a String(from end to beginning),
    //for printing on the screen in the TextArea.
    public String reversedList() {
        String print = "";
        ListIterator itr = new ListIterator(tail);
        while (itr.hasPrevious()) {
            print = print + itr.prev + "\n";
            itr.previous();
        }
        return print;
    }

    //The purpose of this method is to compare two string variables and return
    // the previous one in alphabetical order.
    private String Compare(String str1, String str2) {
        int length;
        if (str1.length() < str2.length()) {
            length = str1.length();
        } else {
            length = str2.length();
        }
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) < str2.charAt(i)) {
                return str1;
            } else if (str1.charAt(i) > str2.charAt(i)) {
                return str2;
            }
        }
        return "Is Equal";
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public class ListIterator {

        private Node curr, prev;

        public ListIterator(Node node) {
            prev = node;
            curr = node.nextLink;
        }

        public ListIterator() {
            restart();
        }

        //The purpose of this method is to restart the iterator.
        public void restart() {
            prev = head;
            curr = head;
        }

        //The purpose of this method is to check if the next node is null.
        public boolean hasNext() {
            return curr != null;
        }

        //The purpose of this method is to check if the previous node is null.
        public boolean hasPrevious() {
            return prev != null;
        }

        // The purpose of this method is to move prev and curr nodes to the next ones.
        public void next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prev = curr;
            curr = prev.nextLink;
        }

        // The purpose of this method is to move prev and curr nodes to the previous ones.
        public void previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            curr = prev;
            prev = curr.prevLink;
        }

        //This method returns the node after the one at curr.
        public String peek() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }
            return curr.toString();
        }

        public Node getCurr() {
            return curr;
        }

        public Node getPrev() {
            return prev;
        }

        public void setCurr(Node curr) {
            this.curr = curr;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    public class Node {

        private Info data;
        private Node prevLink, nextLink;

        public Node (Info data) {
            this.data = data;
            prevLink = null;
            nextLink = null;
        }

        public Node(Node node) {
            data = node.data;
            prevLink = node.prevLink;
            nextLink = node.nextLink;
        }

        public Node() {
            data = null;
            prevLink = null;
            nextLink = null;
        }

        public Info getData() {
            return data;
        }

        public Node getPrevLink() {
            return prevLink;
        }

        public Node getNextLink() {
            return nextLink;
        }

        public void setData(Info data) {
            this.data = data;
        }

        public void setPrevLink(Node prevLink) {
            this.prevLink = prevLink;
        }

        public void setNextLink(Node nextLink) {
            this.nextLink = nextLink;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            } else if (getClass() != obj.getClass()) {
                return false;
            } else {
                Node other = (Node) obj;
                return other.data.equals(data)
                        && other.prevLink.equals(prevLink)
                        && other.nextLink.equals(nextLink);
            }
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
