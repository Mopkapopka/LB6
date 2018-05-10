package barbosshouse.io;

import barbosshouse.*;

import java.util.Collection;


public class ControlledInternetOrderManager extends InternetOrdersManager {
    protected Source<Order> sourceOrder;

    public ControlledInternetOrderManager(Source<Order> source){
        super();
        sourceOrder = source;
    }
    public Source<Order> getSourceOrder() {
        return sourceOrder;
    }

    public void setSourceOrder(Source<Order> sourceOrder) {
        this.sourceOrder = sourceOrder;
    }



    @Override
    public void clear() {
        for (Order o : this){
            ControlledTableOrder controlledTableOrder = new ControlledTableOrder(o);
            sourceOrder.delete(controlledTableOrder);
        }
        super.clear();
    }

    @Override
    public void addFirst(Order menuItems){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(menuItems);
        sourceOrder.create(controlledTableOrder);
        super.addFirst(menuItems);
    }

    @Override
    public Order pollLast() {
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(this.getLast());
        sourceOrder.delete(controlledTableOrder);
        return super.pollLast();
    }

    @Override
    public boolean addAll(Collection<? extends Order> c){
        for (Order o : c){
            ControlledTableOrder controlledTableOrder = new ControlledTableOrder(o);
            sourceOrder.create(controlledTableOrder);}
        return super.addAll(c);
    }
    @Override
    public boolean removeAll(Collection<?> c){
        for (Object  o : c){
            ControlledTableOrder controlledTableOrder = new ControlledTableOrder((ControlledTableOrder)o);
            sourceOrder.delete(controlledTableOrder);}
        return removeAll(c);
    }

    @Override
    public Order remove(){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(this.getFirst());
        sourceOrder.delete(controlledTableOrder);
        return super.remove();
    }

    @Override
    public boolean add(Order order){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(order);
        sourceOrder.create(controlledTableOrder);
        return super.add(controlledTableOrder);

    }
    @Override
    public boolean remove(Object o){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder((Order)o);
        sourceOrder.delete(controlledTableOrder);
        return super.remove(controlledTableOrder);
    }


    public void store(){
/* todo    проходит по всем заказам, определяет, изменился ли тот или иной заказ (isChanged),
            и если изменился – перезаписывает файл заказа вызывая метод store(order) на объекте
    Source<Order>.*/

        for (Order o : this){
            if( ((ControlledTableOrder)o).isChanged() ) sourceOrder.store(o);
        }
    }

    public void  load(){
        for (Order o : this){
            sourceOrder.load(o);
        }
    }

}
