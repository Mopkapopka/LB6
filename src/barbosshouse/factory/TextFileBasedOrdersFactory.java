package barbosshouse.factory;

import barbosshouse.*;
import barbosshouse.io.*;

import java.time.LocalDateTime;

public class TextFileBasedOrdersFactory extends OrdersFactory {
    private OrderManagerTextFileSource sourcePath;

    public OrderManagerTextFileSource getPath() {
        return sourcePath;
    }

    public void setPath(String path) {
        sourcePath = new OrderManagerTextFileSource();
        sourcePath.setPath(path);
    }

    public TextFileBasedOrdersFactory(String path){
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
