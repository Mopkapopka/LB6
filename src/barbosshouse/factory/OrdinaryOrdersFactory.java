package barbosshouse.factory;

import barbosshouse.*;

import java.time.LocalDateTime;

public class OrdinaryOrdersFactory extends OrdersFactory {


    @Override
    Order createTableOrder() {
        return new TableOrder();
    }

    @Override
    Order createTableOrder(int size, Customer customer, LocalDateTime orderTime) {
        return new TableOrder(size,customer,orderTime);
    }

    @Override
    Order createTableOrder(MenuItem[] MenuItems) {
        return new TableOrder(MenuItems);
    }

    @Override
    Order createInternetOrder() {
        return new InternetOrder();
    }

    @Override
    Order createInternetOrder(MenuItem[] menuItems, Customer customer) {
        return new InternetOrder(menuItems,customer);
    }

    @Override
    OrdersManager createTableOrderManager(int size) {
        return new TableOrdersManager(size);
    }

    @Override
    OrdersManager createInternetOrderManager() {
        return new InternetOrdersManager();
    }
}
