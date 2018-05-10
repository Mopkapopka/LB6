package barbosshouse.io;

import barbosshouse.*;
import barbosshouse.exceptionspackage.*;


import java.util.Collection;

public class ControlledTableOrderManager extends TableOrdersManager {
    protected Source<Order> sourceOrder;

    public ControlledTableOrderManager(int size, Source<Order> source) throws NegativeSizeException {
        super(size);
        sourceOrder = source;
    }

    public Source<Order> getSourceOrder() {
        return sourceOrder;
    }

    public void setSourceOrder(Source<Order> sourceOrder) {
        this.sourceOrder = sourceOrder;
    }

@Override
 public int removeOrder(TableOrder order){
     ControlledTableOrder controlledTableOrder = new ControlledTableOrder(order);
     sourceOrder.delete(controlledTableOrder);
     return super.removeOrder(controlledTableOrder);
     }

    @Override
    public int removeOrders(TableOrder order){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(order);
        sourceOrder.delete(controlledTableOrder);
        return super.removeOrders(controlledTableOrder);}

    @Override
    public void add(Order tableOrder, int tableNumber) throws AlreadyAddedException{
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(tableOrder);
        sourceOrder.create(controlledTableOrder);
        super.add(tableNumber, controlledTableOrder);
}


    @Override
    public void removeOrder(int tableNumber){
        int i =0;
        for (Order o :tableOrders){
            if (i==tableNumber)
            {ControlledTableOrder controlledTableOrder = new ControlledTableOrder(o);
                sourceOrder.delete(controlledTableOrder);
            }
            i++;
        }
        super.removeOrder(tableNumber);
    }

    @Override
    public boolean add(Order menuItems){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(menuItems);
        sourceOrder.create(controlledTableOrder);
        super.add(controlledTableOrder);
        return true;
    }

    @Override
    public boolean remove(Object o){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder((Order)o);
        sourceOrder.delete(controlledTableOrder);
        super.remove(controlledTableOrder);
        return true;
    }

    @Override
  public boolean addAll(Collection<? extends Order> c){ // todo ?????
        for (Order o : c){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(o);
        sourceOrder.create(controlledTableOrder);}
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Order> c){// todo ????
        for (Order o : c){
            ControlledTableOrder controlledTableOrder = new ControlledTableOrder(o);
            sourceOrder.create(controlledTableOrder);}
        return super.addAll(index,c);
    }

    @Override
    public boolean removeAll(Collection<?> c){// todo ????
        for (Object  o : c){
            ControlledTableOrder controlledTableOrder = new ControlledTableOrder((ControlledTableOrder)o);
            sourceOrder.delete(controlledTableOrder);}
            return removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c){ // todo ????
        for (Object  o : c){
            ControlledTableOrder controlledTableOrder = new ControlledTableOrder((ControlledTableOrder)o);
            sourceOrder.delete(controlledTableOrder);}
        return retainAll(c);
    }

    @Override
    public void add(int index, Order element){
        ControlledTableOrder controlledTableOrder = new ControlledTableOrder(element);
        sourceOrder.create(controlledTableOrder);
        super.add(index,controlledTableOrder);
    }

    @Override
    public Order remove(int index){ // todo ????
        int i =0;
        for (Order o :tableOrders){
            if (i==index)
            {ControlledTableOrder controlledTableOrder = new ControlledTableOrder(o);
                sourceOrder.delete(controlledTableOrder);
            }
            i++;
        }
        return super.remove(index);
    }

    @Override
    public void clear(){
        super.clear();
    }

    @Override
    public Order set(int index, Order element){
        return super.set(index,element);
    }


/*
    add() *//*, сначала, на основе заказа создается экземпляр ControlledTableOrder, затем вызывается
    метод источника create(), а потом только реализация метода add() в суперклассе.*//*

    remove() //сначала вызывается метод источника delete(order), a затем реализация метода в cуперклассе.
    addAll()
    removeAll()
    retainAll() //аналогично.*/



    public void store(){
/* todo    проходит по всем заказам, определяет, изменился ли тот или иной заказ (isChanged),
            и если изменился – перезаписывает файл заказа вызывая метод store(order) на объекте
    Source<Order>.*/

    for (Order o : tableOrders){
        if( ((ControlledTableOrder)o).isChanged() ) sourceOrder.store(o);
        }
    }

    public void  load(){
        for (Order o : tableOrders){
             sourceOrder.load(o);
        }
    }

/*
        load() – проходит по всем заказам и восстанавливает их состояние из источника, вызывая
    метод load(order).*/
}
