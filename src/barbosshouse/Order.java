package barbosshouse;

import barbosshouse.exceptionspackage.UnlawfulActionException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public interface Order extends List<MenuItem>,Serializable {
    public void setCustomer(Customer customer);

    LocalDateTime getOrderTime();
    void setOrderTime(LocalDateTime orderTime);

    public Customer getCustomer();

    public boolean add(MenuItem MenuItem)  throws UnlawfulActionException;
    public boolean remove(MenuItem menuItem);
    public int removeAll(MenuItem menuItem);

    public int itemsQuantity();
    public int itemsQuantity(MenuItem menuItem);
    public int itemsQuantity(String menuItemName);

    public MenuItem[] getMenuItems();

    public double costTotal();

    public String[] itemsNames();

    public MenuItem[] sortedItemsByCostDesc();

    public String toString();
    public boolean equals(Object obj);
    public int hashCode();
}