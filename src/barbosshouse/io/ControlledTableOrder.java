package barbosshouse.io;

import barbosshouse.*;

import java.time.LocalDateTime;
import java.util.Collection;

/*
Добавьте один конструктор, принимающий объект по ссылке Order, и инициализирующий
        текущий объект данными из переданного в качестве параметра заказа.
        Он добавляет одно поле
        - protected boolean isChanged
        , отражающее тот факт, что состояние объекта после создания было изменено. Значение по-
        умолчанию, разумеется, false.
        и один метод
        - protected boolean isChanged(), возвращающий непосредственно значение переменной
        isChanged.
        , а также переопределяет методы, которые так или иначе меняют состояние класса
        (изменение, добавление, удаление позиций заказа, изменение клиента, времени заказа) – в
        этих методах сначала вызывается реализация метода в суперклассе, а затем изменяется
        isChanged на tru
*/

public class ControlledTableOrder extends TableOrder {
    protected boolean isChanged = false;

    public ControlledTableOrder() {
        super(-1, Customer.MATURE_UNKNOWN_CUSTOMER,LocalDateTime.now());
    }

    public ControlledTableOrder(int size, Customer customer, LocalDateTime orderTime){
    super(size, customer, orderTime);
    }

    public ControlledTableOrder(MenuItem[] MenuItems) {
        super(MenuItems);
    }

    public ControlledTableOrder(Order o){
        this(o.getMenuItems());
        this.setCustomer(o.getCustomer());
        this.setOrderTime(o.getOrderTime());
    }

    protected boolean isChanged(){
        return isChanged;
    }

    @Override
    public void setCustomer(Customer customer){
        super.setCustomer(customer);
        isChanged=true;
    }

    @Override
    public boolean add(MenuItem MenuItem){
        isChanged=true;
        return super.add(MenuItem);
    }

    public boolean remove(MenuItem menuItem){
        isChanged=true;
        return super.remove(menuItem);
    }

    public int removeAll(MenuItem menuItem){
        isChanged=true;
        return super.removeAll(menuItem);
    }

    public boolean addAll(Collection<? extends MenuItem> c){
        isChanged=true;
        return super.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends MenuItem> c){
        isChanged=true;
        return super.addAll(index,c);
    }

    public boolean removeAll(Collection<?> c){
        isChanged=true;
        return super.removeAll(c);
    }

    public boolean retainAll(Collection<?> c){
        isChanged=true;
        return super.retainAll(c);
    }
    public void clear(){
        super.clear();
        isChanged=true;
    }
    public MenuItem set(int index, MenuItem element){
        isChanged=true;
        return super.set(index,element);
    }
    public void add(int index, MenuItem element){
        super.add(index,element);
        isChanged=true;
    }
    public MenuItem remove(int index){
        isChanged=true;
        return super.remove(index);
    }
}
