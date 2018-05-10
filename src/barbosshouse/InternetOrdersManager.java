package barbosshouse;

import java.time.LocalDateTime;
import java.util.*;

public class InternetOrdersManager implements OrdersManager, Deque<Order> {
    private QueueNode head, tail;
    private int size;

    public InternetOrdersManager() {
        size = 0;
    }

    public int ordersTodayQuantity(){
        QueueNode current = head;
        int count =0;
        for(int i =0; i<size; i++){
            if (current.value.getOrderTime().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                count++;
            }
            current = current.getNext();
        }
        return count;
    }
    public Order[] ordersToday(LocalDateTime day){
        QueueNode current = head;
        Order[] ordersToday = new Order[ordersTodayQuantity()];

        for(int i =0, j=0; i<size; i++){
            if (current.value.getOrderTime().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
                ordersToday[j] = current.value;
            }
            current = current.getNext();
        }
        return ordersToday;
    }
    public Order[] ordersOfCustomer(Customer customer){
        QueueNode current = head;
        int count =0;

        for(int i =0; i<size; i++){
            if (current.value.getCustomer().equals(customer)){
                count++;
            }
            current = current.getNext();
        }

        Order[] customerOrders = new Order[count];
        for(int i =0, j =0; i<size; i++){
            if (current.value.getCustomer().equals(customer)){
                customerOrders[j] = current.value;
                j++;
            }
            current = current.getNext();
        }
    return customerOrders;
    }




    public boolean add(Order order){
        QueueNode queue = new QueueNode(order);
        QueueNode current = head;

        if (head == null) { //если первый
            head = queue;
            tail = queue;
            head.prev = null;
        } else {
            tail.setNext(queue); // становится следующим для конца
            tail.setPrev(tail); // хвост становится предудыщим для нового хвоста
            tail = queue; //cстановится в конец
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        QueueNode current = head;

        while (current.next != null) {
            if (current.value.equals(o)) {
                if (size == 1) {
                    remove();
                    size--;
                    return true;
                } else if (current.next == tail) {
                    removeLast();
                    size--;
                    return true;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    current = null;
                    size--;
                    return true;
                }
            }

            current = current.next;
        }

        return false;
    }


    public int ordersQuantity(){
        return size;
    }
     Order order(){
        return head.value;
    }
    public Order[] getOrderItems() {
        Order[] ordersClear = new Order[size];
        QueueNode queue = head;
        for (int i=0; i<size; i++){
            ordersClear[i]= queue.value;
            queue=queue.getNext();
        }

        return ordersClear;
    }

    public double costTotal() {
        double cost =0.0;
        QueueNode queue = head;
        while (queue!=null)
        {
            cost+=queue.value.costTotal();
            queue=queue.getNext();
        }
        return cost;
    }

    public int ordersQuantity(MenuItem item) {
        int count = 0;
        QueueNode queue = head;

        if (size !=0) {
            for (int i = 0; i < size; i++) {
                count += queue.value.itemsQuantity(item);
                queue = queue.getNext();
            }
            return count;
        }
        else return 0;
    }

    public int itemsQuantity(String itemName) {
        int count = 0;
        QueueNode queue = head;

        if (size !=0) {
            for (int i = 0; i < size; i++) {
                count += queue.value.itemsQuantity(itemName);
                queue = queue.getNext();
            }
            return count;
        }
        else return 0;
    }

    private class QueueNode{

        private QueueNode  prev,next;
        private Order value;

        QueueNode(Order value) {
            this.value = value;
        }

        public void setValue(Order value) {
            this.value = value;
        }
        public Order getValue() {
            return value;
        }

         void setNext(QueueNode next) {
            this.next = next;
        }
         QueueNode getNext() {
            return next;
        }

         void setPrev(QueueNode prev) {
            this.prev = prev;
        }
        public QueueNode getPrev() {
            return prev;
        }
    }

    //todo 5 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void addFirst(Order menuItems) { //todo 5
        if (menuItems == null)
            throw new NullPointerException();

        QueueNode h = head;
        QueueNode newQ = new QueueNode(menuItems);
        if (head == null) {
            head = newQ;
            size++;
        } else {
            head.prev = newQ;
            head = newQ;
            newQ.next = h;
            size++;
        }

    }

    @Override
    public void addLast(Order menuItems) {
        if (menuItems == null)
            throw new NullPointerException();
        add(menuItems);
    }

    @Override
    public boolean offerFirst(Order menuItems) {
        addFirst(menuItems);
        return true;
    }

    @Override
    public boolean offerLast(Order menuItems) {
        addLast(menuItems);
        return true;

    }

    @Override
    public Order removeFirst() {
        Order x = remove();
        if (x == null) throw new NoSuchElementException();
        return x;
    }

    @Override
    public Order removeLast() {
        Order order = pollLast();
        if (order == null) throw new NoSuchElementException();
        return order;
    }

    @Override
    public Order pollFirst() {
        return poll();
    }

    @Override
    public Order pollLast() {
        Order polledElement = tail.value;
        tail = tail.prev;
        tail.prev = null;
        size--;
        return polledElement;
    }

    @Override
    public Order getFirst() {
        Order o = order();
        if (o == null) { throw new NoSuchElementException(); }
        return o;
    }

    @Override
    public Order getLast() {
        if (tail == null) { throw new NoSuchElementException(); }
        return tail.value;
    }

    @Override
    public Order peekFirst() {
        return order();
    }

    @Override
    public Order peekLast() {
        return tail.value;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        QueueNode current = tail;

        while (current != null) {
            if (current.value.equals(o)) {
                if (size == 1) {
                    remove();
                    size--;
                    return true;
                } else if (current.next == tail) {
                    removeLast();
                    size--;
                    return true;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                    return true;
                }
            }

            current = current.prev;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Order x : this) {
            if (x.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Order> iterator() {
        return new Iterator<Order>() {
            QueueNode current = head;

            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public Order next() {
                if (hasNext() && current != head)
                    current = current.next;
                return current.value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Order[] orders = new Order[size];
        QueueNode node = head;
        for (int i = 0; i < size; i++) {
            orders[i] = node.value;
            node = node.next;
        }

        return orders;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Order[] items = (Order[]) toArray();

        if (a.length < size)
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Order> c) {
        for (Order o : c) {
            add(o);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean offer(Order menuItems) {
        addFirst(menuItems);
        return false;
    }

    public Order remove(){ //переименовано
        Order o = head.value;
        if (size != 0) {
            //Order o = head.value;
            head = head.getNext();
            head.setPrev(null); //tail = null;
            size--;
            return o;
        }
        return o;
    }

    @Override
    public Order poll() {
        return remove();
    }

    @Override
    public Order element() {
        return head.value;
    }

    @Override
    public Order peek() {
        return head.value;
    }

    @Override
    public void push(Order menuItems) {
        addFirst(menuItems);
    }

    @Override
    public Order pop() {
        return removeFirst();
    }

    @Override
    public Iterator<Order> descendingIterator() {
        return new Iterator<Order>() {
            QueueNode current = tail;

            @Override
            public boolean hasNext() {
                return current.prev != null;
            }

            @Override
            public Order next() {
                if (hasNext() && current != tail)
                    current = current.prev;
                return current.value;
            }
        };
    }

}
