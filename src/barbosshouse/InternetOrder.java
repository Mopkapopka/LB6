package barbosshouse;

import barbosshouse.exceptionspackage.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class InternetOrder implements Order {
    private int size;
    private ListNode head;
    private ListNode tail;
    private Customer customer;
    private LocalDateTime orderTime;

    public InternetOrder() {
        size = 0;
        orderTime = LocalDateTime.now();
    }

    public InternetOrder(MenuItem[] menuItems, Customer customer){
        this.customer = customer;
        orderTime = LocalDateTime.now();

        for (MenuItem o : menuItems) {
                add(o);
            size++;
        }
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }


    public boolean add(MenuItem menuItem) throws UnlawfulActionException {
            if(customer.getAge()< customer.NOT_MATURE_DEFAULT_AGE || (LocalTime.now().getHour()>22)){
                throw new UnlawfulActionException("U cant buy alcholol now!!");
            }
            boolean searchMenuItem;
            ListNode current = head;
            while (current != null){

                current=current.getNext();
            }

            ListNode node = new ListNode(menuItem);

            if (head == null) {
                head = node;
                tail = node;
            } else {
                //tail.next = node;
                tail.setNext(node);
                tail = node;
            }
            size++;
        return true;
    }


    public boolean remove(MenuItem menuItem) {
        ListNode current = head;
        ListNode previous = null;

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (current.value.equals(menuItem)) {
                    current.Remove(previous, current);
                    return true;
                }
                previous = current;
                current = current.getNext();
            }
        }
        return false;
    }

    public boolean remove(String menuItemName) {
        ListNode current = head;
        ListNode previous = null;

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (current.value.getName().equals(menuItemName)) {
                    current.Remove(previous, current);
                    return true;
                }
                previous=current; current=current.getNext();
            }
        }
        return false;
    }

    public int removeAll(MenuItem menuItem) {
        int deleted =0;
        ListNode current = head;
        ListNode previous = null;

        // 1: Пустой список: ничего не делать.
        // 2: Один элемент: установить Previous = null.
        // 3: Несколько элементов:
        //    a: Удаляемый элемент первый.
        //    b: Удаляемый элемент в середине или конце.
        while (current!=null)
        {
            if (current.value.equals(menuItem))
            {
                // Узел в середине или в конце.
                if (previous != null)
                {
                    // Случай 3b.
                    // До:    Head -> 3 -> 5 -> null
                    // После: Head -> 3 ------> null
                    previous.next = current.next;
                    current=null;
                    // Если в конце, то меняем _tail.
                    if (current.next == null)
                    {
                        tail = previous;
                    }
                }
                else
                {
                    // Случай 2 или 3a.
                    // До:    Head -> 3 -> 5
                    // После: Head ------> 5

                    // Head -> 3 -> null
                    // Head ------> null
                    head = head.next;
                    // Список теперь пустой?
                    if (head == null)
                    {
                        tail = null;
                    }
                }
                size--;
                deleted++;
            }

            previous = current;
            current = current.next;
        }

        return deleted;
    }


    public int removeAll(String menuItemName) {
        ListNode current = head;
        ListNode previous = null;
        int deleted =0;

        while (current!=null)
        {
            if(current.value.getName().equals(menuItemName)){
                current.Remove(previous,current);
                deleted++;
            }
            previous=current; current=current.getNext();
        }
        return deleted;
    }

    public int itemsQuantity() {
        return size;
    }

    public int itemsQuantity(String menuItemName) {
        int count = 0;
        ListNode current = head;

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (current.value.getName().equals(menuItemName))
                    count++;
                current = current.getNext();
            }
        }
        return count;
    }

    public int itemsQuantity(MenuItem menuItem) {
        int count = 0;
        ListNode current = head;

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (current.value.equals(menuItem))
                    count++;
                current = current.getNext();
            }
        }
        return count;
    }


    public MenuItem[] getMenuItems() {
        MenuItem[] itemsClear = new MenuItem[size];
        ListNode current = head;
        for (int i = 0; i < size; i++) { //надо ли проверку на null
            itemsClear[i] = current.value;
            current = current.getNext();
        }
        return itemsClear;
    }

    public double costTotal() { //я уже ненавижу тебя ✅
        double cost = 0;
        ListNode current = head;

        for (int i = 0; i < size; i++) {
            cost += current.value.getCost();
            current = current.getNext();
        }
        return cost;
    }

    public String[] itemsNames() {
        int uniqNames = size;
        ListNode current = head;

        int t, unique = 1;
        while (current!=null ){
            t = 0;
            for (int j = 0; j < size - 1; j++)
                if (current.getNext()!= null &&(!current.value.getName().equals(current.getNext().value.getName())))
                    t++;
            current = current.getNext();
            if (t == 1)
                unique++;
        }
        String[] Names = new String[unique];
        current = head;
        int j=0;
        while (current!=null & j<size){
            if (isUniq(current.value, Names)) {
                Names[j] = current.value.getName();
                j++;
            }
            current = current.getNext();
        }
        return Names;
    }
    /**        int k=1;
     Names[0]=current.value.getName();
     while (current!=null ){
     t = 0;
     for (int j = 0; j < size - 1; j++)
     if (current.getNext()!= null &&(!current.value.getName().equals(current.getNext().value.getName())))
     t++;
     current = current.getNext();
     if (t == 1)
     {Names[k]=current.value.getName(); k++;}
     }
     return Names;*/
    private boolean isUniq(MenuItem MenuItem, String[] Names) {
        if (Names[0] == null) return true;
        else {
            for (int j = 0; j < Names.length; j++) {
                if (MenuItem.getName().equals(Names[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] dishesC = getMenuItems();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (dishesC[j].getCost() < dishesC[j + 1].getCost()) {
                    MenuItem tmp = dishesC[j];
                    dishesC[j] = dishesC[j + 1];
                    dishesC[j + 1] = tmp;
                }
            }
        }
        return dishesC;
    }



    @Override
    public String toString() {
/*“InternetOrder:
<строковое представление заказчика>
<size>
<строковое представление item 1>
<строковое представление item 2>
…
<строковое представление item N>”*/
        StringBuilder finalString =new StringBuilder("InternetOrder:\n");
        if (customer != null) {
            finalString.append(getCustomer().toString()).append("\n");
        }

        if (itemsQuantity()!=0) {finalString.append(itemsQuantity()).append("\n");
            ListNode current = head;
            while (current!=null){
                finalString.append(current.value.toString()).append("\n");
            current=current.getNext();
            }
        }
        return finalString.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if( obj==null || !(obj instanceof InternetOrder)) return false;

        InternetOrder equalsCheck = (InternetOrder) obj;
        if (!equalsCheck.customer.equals(customer)) return false;
        for(int i =0; i<size; i++) {
            if (this.itemsQuantity(this.getMenuItems()[i]) != equalsCheck.itemsQuantity(this.getMenuItems()[i]))
                return false;
        }
        return (size == equalsCheck.size);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        ListNode current = head;
        while (current!=null) {
            hash ^= current.value.hashCode();
        }
        return Integer.hashCode(size) ^ head.hashCode() ^ hash^orderTime.hashCode();
    }

    private class ListNode {
        private ListNode next;
        private MenuItem value;

        public ListNode(MenuItem value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public void Remove(ListNode previous, ListNode current)
        {

            if (previous != null) {
                //previous.next = current.next;

                if (current.getNext() == null) {
                    tail = previous;
                }
                previous.setNext(current.getNext());

            } else {
                if (head.next == null) {
                    head = null;
                    tail = null;
                }
                head = head.next;
                // Список теперь пустой?

            }
            size--;
        }

    }
    //todo 5 //////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        for (MenuItem mi : this){
            if (mi.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return new Iterator<MenuItem>() {
            int index = 0;
            ListNode current = head;
            @Override
            public boolean hasNext() {
                return current.getNext() !=null;
            }

            @Override
            public MenuItem next() {
                if (hasNext()) current = current.getNext();
                return current.value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        MenuItem[] dishesClear = new MenuItem[size];
        ListNode current = head;
        int i =0;
        while ( current!= null){
            dishesClear[i] = current.value;
            current=current.getNext();
            i++;
        }

        return dishesClear;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] menuItems = (T[]) toArray();

        if (a.length < size)
            return (T[]) Arrays.copyOf(menuItems, size, a.getClass());
        System.arraycopy(menuItems, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean remove(Object o) {
        ListNode current = head;
        ListNode previous = null;

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (current.value.equals(o)) {
                    current.Remove(previous, current);
                    return true;
                }
                previous = current;
                current = current.getNext();
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c ) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c) {
        for( MenuItem mi : c ){
            add(mi);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c) {
        for (MenuItem mi : c){
            add(index, mi);
            index++;
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
        ListNode current = head;

       while (current!= null){
            if (!c.contains(current.value)){
                removeAll(current.value);
                current=current.getNext();
            }
        }
        return true;
    }

    @Override
    public void clear() {
        head = null;
        head.next = null;
        tail=null;

        /*ListNode current = head;
        ListNode next;
    while (current.next != null){
        next = current.next;
        current.next = null;
        current.value = null;
        current = next;
    }
        head = null;
        tail = null;
        size =0;
*/
    }

    @Override
    public MenuItem get(int index) {
        ListNode current = head;
        int i =0;
        while (current!=null){
            if (i==index) return current.value;
            current=current.getNext();
            i++;
        }
        return null;
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        MenuItem old = head.value;
        ListNode current = head;
        int i =0;
        while (current!= null){
            if (i == index){
                current.value = element;
                return old;
            }
            current = current.getNext();
            old = current.value;
            i++;
        }
        return null;
    }

    @Override
    public void add(int index, MenuItem element) {

        ListNode current = head;
        int i=0;
        while (current != null){
            if (i==index){
                break;
            }
            i++;
        }

        ListNode node = new ListNode(element);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            //tail.next = node;
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    @Override
    public MenuItem remove(int index) {
        ListNode current = head;
        ListNode previous = null;

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if(i==index){
                    current.Remove(previous, current);
                    return previous.value;
                }
                previous=current; current=current.getNext();
            }
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        for (MenuItem m : this) {
            index++;
            if (m.equals(o))
                return index;
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1, lastIndex = -1;
        for (MenuItem m : this) {
            index++;
            if (m.equals(o))
                lastIndex = index;
        }
        return lastIndex;
    }

    @Override
    public ListIterator<MenuItem> listIterator() {
        return listIterator(-1);
    }

    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        return new ListIterator<MenuItem>() {
            private ListNode current = head;
            private ListNode prev = null;
            private ListNode prevPrev = null;
            private int currentI = index;
            @Override
            public boolean hasNext() {
                return current.getNext()!=null;
            }

            @Override
            public MenuItem next() {
                if (hasNext() && currentI!=-1){
                    currentI++;
                    prev=current;
                    current = current.getNext();
                }
                else if (hasNext() && current != head) {
                    currentI++;
                    prevPrev=prev;
                    prev=current;
                    current=current.getNext();
                }
                return current.value;
            }

            @Override
            public boolean hasPrevious() {
                return prev!=null;
            }

            @Override
            public MenuItem previous() {
                if (hasPrevious()) {
                    currentI--;
                    current = prev;
                    prev = prevPrev;
                }

                return current.value;
            }

            @Override
            public int nextIndex() {
                if (hasNext())
                    return currentI+1;
                return currentI;
            }

            @Override
            public int previousIndex() {
                if(hasPrevious())
                    return currentI-1;
                return currentI;
            }

            @Override
            public void remove() {
                InternetOrder.this.remove(currentI);
            }

            @Override
            public void set(MenuItem menuItem) {
                InternetOrder.this.set(currentI,menuItem);

            }

            @Override
            public void add(MenuItem menuItem) {
                InternetOrder.this.add(index, menuItem);
            }
        };
    }

    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex) {
        List<MenuItem> list = new InternetOrder();
        int i = -1;
        for (MenuItem mi : this){
            i++;
            if (i>=fromIndex && i <= toIndex){
                list.add(mi);
            }
        }
        return list;
    }
}