package barbosshouse;

import barbosshouse.exceptionspackage.NegativeSizeException;

import java.time.LocalDateTime;
import java.util.*;

public class TableOrder implements Order {
    private static final int SIZE_DEFAULT=16;
    private MenuItem[] menuItems;
    private int size = 0;
    private Customer customer;
    private LocalDateTime orderTime;

    public TableOrder() {
    this(SIZE_DEFAULT,Customer.MATURE_UNKNOWN_CUSTOMER, LocalDateTime.now());
    }

    public TableOrder(int size, Customer customer, LocalDateTime orderTime)
            throws NegativeSizeException {
        this.menuItems = new MenuItem[size];
        if(size < 0 ) throw new NegativeSizeException("Size must be >= 0 !");
        this.customer=customer;
        this.orderTime = orderTime;
    }

    public TableOrder(MenuItem[] MenuItems) {
        //System.arraycopy(MenuItems,0,this.menuItems,0, MenuItems.length);
        menuItems = MenuItems;
        size = menuItems.length;
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



    public boolean add(MenuItem MenuItem){
       boolean add;
        //Увеличение массива в 2 раза
        if ((size == menuItems.length)& (size !=0)) {
           MenuItem[] menuItemResize = new MenuItem[size *2];
           System.arraycopy(menuItems,0, menuItemResize,0, size);
           this.menuItems = menuItemResize;
       }
       menuItems[size] = MenuItem; size++;
       add=true;
       return add;
    }



    public boolean remove(MenuItem menuItem) {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (menuItems[i].equals(menuItem)) {
                    System.arraycopy(menuItems, i + 1, menuItems, i, (size - i));
                    menuItems[size] = null;
                    size--;
                    return true;
                }
            }
        }
    return false;
    }

    public int removeAll(MenuItem menuItem){
        int removed = 0;
        int count;
        int pos;

        for (int i = 0; i < size; i++) {
            if (menuItems[i].equals(menuItem)) {
                count=0;
                pos =i;
                while(pos<size && menuItems[pos].equals(menuItem)){
                    count++; pos++;
                }
                System.arraycopy(menuItems,i+count, menuItems,i,size-i);
                size-=count; removed+=count;
            }
        }
        for (int i = size; i < menuItems.length; i++) menuItems[i]=null;
        return removed;
    }


  public int itemsQuantity(){
        return size;
    }

    public int itemsQuantity(MenuItem menuItem) {
        int count = 0;
        if (size !=0) {
            for (int i = 0; i < size; i++) {
                if (getMenuItems()[i].equals(menuItem))
                    count++;
            }
            return count;
        }
        else return 0;
    }
    public int itemsQuantity(String menuItemName) {
        int count = 0;
        if (size !=0) {
            for (int i = 0; i < size; i++) {
                if (getMenuItems()[i].getName().equals(menuItemName))
                    count++;
            }
            return count;
        }
        else return 0;
    }

    public MenuItem[] getMenuItems(){
    MenuItem[] dishesClear = new MenuItem[size];
    System.arraycopy(menuItems,0,dishesClear,0, size);
        return dishesClear;
    }

    public double costTotal(){
    double cost = 0;
    for (int i = 0; i< size; i++) {
        cost+= menuItems[i].getCost();
    }
    return cost;
    }

    public String[] itemsNames(){
        int uniqNames = size;
        int t, unik =1;
        for(int i = 0; i < size; i++)
        {
            t=0;
            for( int j = 0; j < size-1; j++)
                if ((menuItems[i].getName().equals(menuItems[j].getName())))
                    t++;
            if(t==1)
                unik++;
        }

        String[] Names = new String[unik];
        for(int i =0, j=0; i<size;i++){
            if(isUniq(menuItems[i],Names)) { Names[j]= menuItems[i].getName();j++;}
        }
        return Names;
        }

        private boolean isUniq(MenuItem MenuItem, String[] Names){
        if (Names[0]==null) return true; else {
                for(int j=0;j<Names.length;j++){
                    if(MenuItem.getName().equals(Names[j])) {
                        return false;
                    }
            }
        }
        return true;
        }

        //todo 5 В методе заказа, возвращающего блюда, отсортированные в порядке убывания цены, использовать
        //метод Arrays.sort(), принимающий компаратор, реализующий соответствующий порядок
    public MenuItem[] sortedItemsByCostDesc(){
        MenuItem[] dishesC = new MenuItem[size];
        System.arraycopy(getMenuItems(),0,dishesC,0,size);
        Arrays.sort(dishesC, Comparator.comparingDouble(MenuItem::getCost).reversed());
    return dishesC;
    }

    public String toString(){
/*        TableOrder: <size>
    <строковое представление item 1>
    <строковое представление item 2>
            …
    <строковое представление item N>”*/
        StringBuilder resultString = new StringBuilder("Table order: ");
        if(size!=0) {
            resultString.append(size).append('\n');
            for(MenuItem o : menuItems) {
                resultString.append(o.toString()).append('\n');
            }
        }
        return resultString.toString();
    }

    public boolean equals(Object obj){

        if (this == obj) return true;
        if( obj==null || !(obj instanceof TableOrder)) return false;
        
        TableOrder equalsCheck = (TableOrder)obj;
        for(int i =0; i<size; i++){
            if (this.itemsQuantity(this.getMenuItems()[i]) != equalsCheck.itemsQuantity(this.getMenuItems()[i]) )
                return false;
        }
        return (this.customer.equals(equalsCheck.customer)
                && this.size==equalsCheck.size);
    }

    public int hashCode(){
        int hash = 0;
        for (MenuItem i: getMenuItems()
             ) {
            hash^=i.hashCode();
        }
        return size^customer.hashCode()^hash^orderTime.hashCode();
    }

    //todo 5 //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for( MenuItem mi : menuItems)
            if (mi != null) return false;
        return true;
    }

    @Override
    public boolean contains(Object o) {
       for (MenuItem mi : menuItems){
           if (mi.equals(o))
               return true;
       }
        return false;
    }

    @Override
    public Iterator<MenuItem> iterator() {
        return new Iterator<MenuItem>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public MenuItem next() {
                if (hasNext()) return menuItems[index++];
                return menuItems[index];
            }
        };
    }

    @Override
    public Object[] toArray() {
     /*   MenuItem[] dishesClear = new MenuItem[size];
        System.arraycopy(menuItems,0,dishesClear,0, size);
        return dishesClear;*/
        return menuItems;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(menuItems, size, a.getClass());
        System.arraycopy(menuItems, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean remove(Object o) {

/**        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (menuItems[i].equals((MenuItem)o)) {
                    System.arraycopy(menuItems, i + 1, menuItems, i, (size - i));
                    menuItems[size] = null;
                    size--;
                    return true;
                }
            }
        }
        return false;**/
        return remove((MenuItem)o);
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
        if (c.size() > menuItems.length-index){
            MenuItem[] newItems = new MenuItem[menuItems.length + c.size()];
            System.arraycopy(menuItems, 0, newItems, 0, index);
            menuItems = newItems;
        }
        //сдвигаем, освобождая место под новые
        System.arraycopy(menuItems, index,menuItems,c.size()+index,c.size());
        //вставляем
        int i = index;
        for (MenuItem mi : c){
            menuItems[i]=mi;
            i++;
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
            for (int i =0; i<size; i++){
                if (!c.contains(menuItems[i])) {
                    System.arraycopy(menuItems, i + 1, menuItems, i, (size - i));
                    menuItems[size] = null;
                    size--;
                }
            }


        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i< size; i++){
            menuItems[i]=null;
        }
        size =0;

    }

    @Override
    public MenuItem get(int index) {
        return menuItems[index];
    }

    @Override
    public MenuItem set(int index, MenuItem element) {
        MenuItem old = menuItems[index];
        menuItems[index]=element;
        return old;
    }

    @Override
    public void add(int index, MenuItem element) {

        if (size == menuItems.length){
            MenuItem[] newItems = new MenuItem[menuItems.length +1];
            System.arraycopy(menuItems, 0, newItems, 0, index);
            menuItems = newItems;
        }
        //сдвигаем, освобождая место под новые
        System.arraycopy(menuItems, index,menuItems,index+1,size - index);
        menuItems[index] = element;
        size++;
    }

    @Override
    public MenuItem remove(int index) {
        MenuItem old = menuItems[index];
        System.arraycopy(menuItems, index + 1, menuItems, index, (size - index));
        menuItems[size] = null;
        size--;
        return old;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (menuItems[i].equals(o))
                return i;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size; i > 0; i--) {
            if (menuItems[i].equals(o))
                return i;
        }

        return -1;
    }

    @Override
    public ListIterator<MenuItem> listIterator() {
        return listIterator(-1);
    }

    @Override
    public ListIterator<MenuItem> listIterator(int index) {
        return new ListIterator<MenuItem>() {
            int current = index;
            @Override
            public boolean hasNext() {
                return current < size -1;
            }

            @Override
            public MenuItem next() {
                if (hasNext())
                    return menuItems[current++];
                return menuItems[current];
            }

            @Override
            public boolean hasPrevious() {
                return current>0;
            }

            @Override
            public MenuItem previous() {
                if (hasPrevious())
                    return menuItems[current--];
                return menuItems[current];
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
/*                System.arraycopy(menuItems, current + 1, menuItems, current, (size - current));
                menuItems[size] = null;
                size--;*/
                TableOrder.this.remove(current);
            }

            @Override
            public void set(MenuItem menuItem) {
                menuItems[current] = menuItem;

            }

            @Override
            public void add(MenuItem menuItem) {
                TableOrder.this.add(index, menuItem);
            }
        };
    }

    @Override
    public List<MenuItem> subList(int fromIndex, int toIndex) {
        List<MenuItem> list = new TableOrder(toIndex-fromIndex, this.customer, this.orderTime);
        for (int i = fromIndex; i<=toIndex; i++){
            list.add(menuItems[i]);
        }
        return list;
    }
}


