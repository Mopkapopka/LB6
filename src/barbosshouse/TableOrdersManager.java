package barbosshouse;


import barbosshouse.exceptionspackage.AlreadyAddedException;
import barbosshouse.exceptionspackage.NegativeSizeException;
import barbosshouse.exceptionspackage.NoFreeTableException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

public class TableOrdersManager implements OrdersManager, List<Order> {

    protected Order[] tableOrders;

    public TableOrdersManager(int size) throws NegativeSizeException {
        if(size < 0 ) throw new NegativeSizeException("Size must be > 0 !");
        this.tableOrders = new Order[size];
    }


    public int ordersTodayQuantity(){
    int count = 0;
        for (Order o: tableOrders) {
            if(o!=null &&
                    o.getOrderTime().toLocalDate().equals(LocalDateTime.now().toLocalDate()))
                count++;
        }
        return count;
    }

    public Order[] ordersToday(LocalDateTime day){
        Order[] ordersToday = new Order[ordersTodayQuantity()];
        for (int i = 0,j=0; i< tableOrders.length; i++){
            if (tableOrders[i]!=null && tableOrders[i].getOrderTime().toLocalDate().equals(day.toLocalDate())){
                ordersToday[j] = tableOrders[i];
                j++;
            }
        }
        return ordersToday;
    }

    public Order[] ordersOfCustomer(Customer customer){
        int countOrders=0;

        for (int i = 0; i< tableOrders.length; i++){
            if (tableOrders[i] != null && tableOrders[i].getCustomer().equals(customer)){
                countOrders++;
            }
        }
        Order[] customerOrders = new Order[countOrders];
        for (int i = 0,j=0; i< tableOrders.length; i++){
            if (tableOrders[i] != null && tableOrders[i].getCustomer().equals(customer)){
                customerOrders[j]=tableOrders[i];
                j++;
            }
        }

        return customerOrders;
    }

    public int ordersQuantity() {
        int count = 0;
        for (Order o : tableOrders) if (o != null) count++;
        return count;
    }

    public int ordersQuantity(MenuItem item) {
        int count = 0;
        for (int i = 0; i < tableOrders.length; i++) {
            if (tableOrders[i] != null) {
                count += tableOrders[i].itemsQuantity(item);
            }
        }
        return count;
    }

    public int removeOrder(TableOrder order) {
        for (int i = 0; i < tableOrders.length; i++) {
            if (tableOrders[i] != null && tableOrders[i].equals(order)) {
                tableOrders[i] = null;
                return i;
            }
        }
        return -1;
    }

    public int removeOrders(TableOrder order) {
        int deleted = 0;
        for (int i = 0; i < tableOrders.length; i++) {
            if (tableOrders[i] != null && tableOrders[i].equals(order)) {
                tableOrders[i] = null;
                deleted++;
            }
        }
        return deleted == 0 ? -1 : deleted;
    }


    public void add(Order tableOrder, int tableNumber) throws AlreadyAddedException {
        if (tableOrders[tableNumber] != null) throw new AlreadyAddedException("This table is busy!!");
        tableOrders[tableNumber] = tableOrder;
    }

    public Order getOrder(int tableNumber) {
        if (tableOrders[tableNumber] != null)
            return tableOrders[tableNumber];
        else return null;
    }

/*    public void addDish(MenuItem MenuItem, int tableNumber)  {
        if (tableOrders[tableNumber] != null)
            tableOrders[tableNumber].add(MenuItem);

    }*/

    public void removeOrder(int tableNumber) {
        tableOrders[tableNumber] = null;
    }


    public int freeTableNumber() {
        int i;
        for (i = 0; i < tableOrders.length; i++) {
            if (tableOrders[i] == null) return i;
        }
        return -1;
    }

    private int nullOrders(Predicate<Order> p) {
        int count = 0;
        for (int i = 0; i < tableOrders.length; i++) {
            if (p.test(tableOrders[i])) count++;
        }
        return count;
    }

    private int[] tableNumbers(Predicate<Order> p) {
        int[] status = new int[nullOrders(p)];
        int i = 0, j = 0;
        while (i < tableOrders.length) {
            if (p.test(tableOrders[i])) {
                status[j] = i;
                j++;
            }
            i++;
        }
        return status;
    }


    public int[] freeTableNumbers() throws NoFreeTableException {
        if(nullOrders(o -> Objects.isNull(o)) == 0) throw new NoFreeTableException("There is no free tables!!");
        return tableNumbers(o -> Objects.isNull(o));
    }

    public int[] busyTableNumbers() {
        return tableNumbers(o -> !Objects.isNull(o));
    }

    public Order[] getOrderItems() {
        int count = 0;
        for (int i = 0; i < tableOrders.length; i++) {
            if (tableOrders[i] != null) {
                count++;
            }
        }
        Order[] ordersNow = new TableOrder[count];
        int i = 0, j = 0;
        while (i < this.tableOrders.length) {
            if (this.tableOrders[i] != null) {
                ordersNow[j] = this.tableOrders[i];
                j++;
            }
            i++;
        }
        return ordersNow;
    }

    public double costTotal() {
        int cost = 0;
        for (int i = 0; i < tableOrders.length; i++) {
            if (tableOrders[i] != null) cost += tableOrders[i].costTotal();
        }
        return cost;
    }
//todo 5 ///////////////////////////////////////////////////////////////////////////
    @Override
    public int size() {
        return tableOrders.length;
    }

    @Override
    public boolean isEmpty() {
        for (Order o : tableOrders){
            if( o != null)
                return false;
        }

        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (Order order : tableOrders){
            if(order.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<Order> iterator() {
        return new Iterator<Order>() {
            int index = -1;
            @Override
            public boolean hasNext() {
                return index< tableOrders.length;
            }

            @Override
            public Order next() {
                if(hasNext()) return tableOrders[index+1];
                return tableOrders[index];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return tableOrders;
    }

    @Override
    public <T> T[] toArray(T[] a) { //todo 5
        if (a.length < tableOrders.length) {
            return (T[]) Arrays.copyOf(tableOrders, tableOrders.length - 1, a.getClass());
        }
        System.arraycopy(tableOrders, 0, a, 0, tableOrders.length);
        if (a.length > tableOrders.length)
            a[tableOrders.length - 1] = null;
        return a;
    }

    @Override
    public boolean add(Order menuItems) {
        for (int i=0; i< tableOrders.length;i++){
            if (tableOrders[i]== null){
                try {
                    add(menuItems, i);
                    return true;
                }
                catch (AlreadyAddedException e){
                    System.out.print("Already added!");
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
       int i = removeOrder((TableOrder) o);
       return i>0;
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
            if (c.size() > nullOrders(o -> Objects.isNull(o)))
                throw new ArrayIndexOutOfBoundsException();
        try {
            int[] freeTableNumbers = freeTableNumbers();
            int i = 0;
            for (Order o : c) {
                tableOrders[freeTableNumbers[i]] = o;
                i++;
            }
        } catch (NoFreeTableException e) {
            System.out.print("Already added!");
        }

        return true;
    }
//todo 5! //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean addAll(int index, Collection<? extends Order> c) {
        if (c.size() > tableOrders.length-index){
            TableOrder[] newItems = new TableOrder[tableOrders.length + c.size()];
            System.arraycopy(tableOrders, 0, newItems, 0, index);
            tableOrders = newItems;
        }
        //сдвигаем, освобождая место под новые
        System.arraycopy(tableOrders, index,tableOrders,c.size()+index,c.size());
        //вставляем
        int i = index;
        for (Order to : c){
            tableOrders[i]=to;
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            removeOrders((TableOrder)o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Order order : tableOrders) {
            if (!c.contains(order)) {
                remove(order);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        //tableOrders = new Order[tableOrders.length];
        for (int i=0;i<tableOrders.length;i++) {
            tableOrders[i] = null;
        }
    }

    @Override
    public Order get(int index) {
            return tableOrders[index];
        }

    @Override
    public Order set(int index, Order element) {
        Order old = tableOrders[index];
        tableOrders[index]=element;
        return old;
    }

    @Override
    public void add(int index, Order element) {
        if (index == tableOrders.length){
            Order[] ordersTemp = new Order[tableOrders.length +1];
            System.arraycopy(tableOrders, 0, ordersTemp, 0, index);
            tableOrders = ordersTemp;
        }
        //сдвигаем, освобождая место под новые (точнее копируем :о) )
        System.arraycopy(tableOrders, index,tableOrders,index+1,tableOrders.length - index-1);
        tableOrders[index] = element;
    }

    @Override
    public Order remove(int index) {
        Order old = tableOrders[index];
        remove(tableOrders[index]);
        return old;
    }

    @Override
    public int indexOf(Object o) {
        for(int i=0;i<tableOrders.length; i++){
            if(tableOrders[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
       for(int i=tableOrders.length;i>0; i--){
           if(tableOrders[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public ListIterator<Order> listIterator() {
        return listIterator(-1);
    }

    @Override
    public ListIterator<Order> listIterator(int index) {
        return new ListIterator<Order>() {
            int current = index;
            @Override
            public boolean hasNext() {
                return current < tableOrders.length -1;
            }

            @Override
            public Order next() {
                if (hasNext())
                    return tableOrders[current++];
                return tableOrders[current];
            }

            @Override
            public boolean hasPrevious() {
                return current>0;
            }

            @Override
            public Order previous() {
                if (hasPrevious())
                    return tableOrders[current--];
                return tableOrders[current];
            }

            @Override
            public int nextIndex() {
                if (hasNext())
                    return current+1;
                return current;
            }

            @Override
            public int previousIndex() {
                if(hasPrevious())
                    return current-1;
                return current;
            }

            @Override
            public void remove() {
                removeOrder(current);
/*                System.arraycopy(tableOrders, current + 1, tableOrders, current, (tableOrders.length - current));
                tableOrders[tableOrders.length] = null;*/
            }

            @Override
            public void set(Order menuItem) {
                tableOrders[current] = menuItem;

            }

            @Override
            public void add(Order menuItem) {
                TableOrdersManager.this.add(index, menuItem);
            }
        };
    }

    @Override
    public List<Order> subList(int fromIndex, int toIndex) {
        List<Order> list = new TableOrdersManager(toIndex-fromIndex);
        for (int i = fromIndex; i<=toIndex; i++){
            list.add(tableOrders[i]);
        }
        return list;
    }


}
