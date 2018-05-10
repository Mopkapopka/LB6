package barbosshouse.io;

import barbosshouse.Customer;
import barbosshouse.InternetOrder;
import barbosshouse.MenuItem;
import barbosshouse.Order;

import java.time.LocalDateTime;
import java.util.Collection;

public class ControlledInternetOrder extends InternetOrder {
    protected boolean isChanged = false;

    public ControlledInternetOrder() {
        super();
    }

    public ControlledInternetOrder(MenuItem[] menuItems, Customer customer){
        super(menuItems,customer);
    }
    public ControlledInternetOrder(Order o){
        super(o.getMenuItems(), o.getCustomer());
    }

    protected boolean isChanged(){
        return isChanged;
    }

    @Override
    public void setOrderTime(LocalDateTime orderTime) {
        isChanged=true;
        super.setOrderTime(orderTime);

    }

    @Override
    public void setCustomer(Customer customer) {
        isChanged=true;
        super.setCustomer(customer);
    }

    @Override
    public boolean add(MenuItem menuItem){
        isChanged=true;
        return super.add(menuItem);
    }


    @Override
    public boolean remove(MenuItem menuItem){
        isChanged=true;
        return super.remove(menuItem);
    }

    @Override
    public boolean remove(String menuItemName){
        isChanged=true;
        return super.remove(menuItemName);
    }

    @Override
    public int removeAll(MenuItem menuItem){
        isChanged=true;
        return super.removeAll(menuItem);
    }

    @Override
    public int removeAll(String menuItemName){
        isChanged=true;
        return super.removeAll(menuItemName);
    }

    @Override
    public boolean remove(Object o){
        isChanged=true;
        return super.remove(o);
    }

    @Override
    public boolean addAll(Collection<? extends MenuItem> c){
        isChanged=true;
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends MenuItem> c){
        isChanged=true;
        return super.addAll(index,c);
    }

    @Override
    public boolean removeAll(Collection<?> c){
        isChanged=true;
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c){
        isChanged=true;
        return super.retainAll(c);
    }

    @Override
    public void clear(){
        super.clear();
        isChanged=true;
    }

    @Override
    public MenuItem set(int index, MenuItem element){
        isChanged=true;
        return super.set(index,element);
    }

    @Override
    public void add(int index, MenuItem element){
        isChanged=true;
        super.add(index,element);
    }

    @Override
    public MenuItem remove(int index){
        isChanged=true;
        return super.remove(index);
    }
}
