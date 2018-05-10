package barbosshouse.factory;

import barbosshouse.Customer;
import barbosshouse.MenuItem;
import barbosshouse.Order;
import barbosshouse.OrdersManager;
import barbosshouse.io.*;

import java.time.LocalDateTime;

public class BinaryFileBasedOrdersFactory extends OrdersFactory {

    private OrderManagerBinaryFileSource sourcePath;

    public OrderManagerBinaryFileSource getPath() {
        return sourcePath;
    }

    public void setPath(String path) {
        sourcePath = new OrderManagerBinaryFileSource();
        sourcePath.setPath(path);
    }

    public BinaryFileBasedOrdersFactory(String path){
        setPath(path);
    }

    @Override
    Order createTableOrder() {
        return new ControlledTableOrder();
    }

    @Override
    Order createTableOrder(int size, Customer customer, LocalDateTime orderTime) {
        return new ControlledTableOrder(size,customer,orderTime);
    }

    @Override
    Order createTableOrder(MenuItem[] MenuItems) {
        return new ControlledTableOrder(MenuItems);
    }

    @Override
    Order createInternetOrder() {
        return new ControlledTableOrder();
    }

    @Override
    Order createInternetOrder(MenuItem[] menuItems, Customer customer) {
        return new ControlledInternetOrder(menuItems,customer);
    }

    @Override
    OrdersManager createTableOrderManager(int size) {
        return new ControlledTableOrderManager(size,sourcePath);
    }

    @Override
    OrdersManager createInternetOrderManager() {
        return new ControlledInternetOrderManager(sourcePath);
    }
}
