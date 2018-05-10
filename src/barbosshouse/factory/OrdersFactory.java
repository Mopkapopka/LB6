package barbosshouse.factory;

import barbosshouse.Customer;
import barbosshouse.MenuItem;
import barbosshouse.Order;
import barbosshouse.OrdersManager;
import barbosshouse.io.OrdersFactoryTypesEnumeration;

import java.time.LocalDateTime;

public abstract class OrdersFactory {
/*    Order createTableOrder ()
    Order createInternetOrder()
    OrderManager createTableOrderManager()
    OrderManager createInternetOrderManager()
    Для каждого метода определите перегрузки в соответствии с параметрами конструкторов
    классов TableOrder, InternetOrder, TableOrderManager, InternetOrderManager.
    Статичный метод
    static public OrdersFactory getOrderFactory(OrdersFactoryTypesEnumeration type),
    возвращающий указанную реализауию фабрики.*/

    abstract Order createTableOrder();

    abstract Order createTableOrder(int size, Customer customer, LocalDateTime orderTime);

    abstract Order createTableOrder(MenuItem[] MenuItems);

    abstract Order createInternetOrder();

    abstract Order createInternetOrder(MenuItem[] menuItems, Customer customer);

    abstract OrdersManager createTableOrderManager(int size);

    abstract OrdersManager createInternetOrderManager();


    static public OrdersFactory getOrderFactory(OrdersFactoryTypesEnumeration factoryType, String path) {
        switch (factoryType) {
            case ORDINARY_ORDERS_FACTORY:
                return new OrdinaryOrdersFactory();
            case TEXT_FILE_BASED_ORDERS_FACTORY:
                return new TextFileBasedOrdersFactory(path);
            case BINARY_FILE_BASED_ORDERS_FACTORY:
                return new BinaryFileBasedOrdersFactory(path);
            case SERIALIZED_FILE_BASED_ORDERS_FACTORY:
                return new SerializedFileBasedOrdersFactory(path);
            case SOCKET_BASED_ORDERS_FACTORY:
                return null;
        }
        return null;
    }
}
