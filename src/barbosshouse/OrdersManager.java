package barbosshouse;

import java.time.LocalDateTime;
import java.util.Collection;


public interface OrdersManager extends Collection<Order> { int ordersQuantity();
   int ordersQuantity(MenuItem item);
   double costTotal() ;
   Order[] getOrderItems();
   int ordersTodayQuantity();
   Order[] ordersToday(LocalDateTime day);
   Order[] ordersOfCustomer(Customer customer);

}


/*   Возвращающий количество заказов, выполненных в заданный день (принимает
        экземпляр LocalDate в качестве параметра)
        b. Возвращающий список заказов, выполненных в заданный день (принимает
        экземпляр LocalDate в качестве параметра)
        c. Возвращающий список заказов заданного клиента (принимает клиента в
        качестве параметра).*/



