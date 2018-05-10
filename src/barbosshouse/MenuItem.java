package barbosshouse;

import java.io.Serializable;

public abstract class MenuItem implements Comparable<MenuItem>,Serializable {
    private double cost;
    private String name;
    private String  description;
    private static final double DEFAULT_COST = 0.0;


    protected MenuItem(String name, String description){
    this(DEFAULT_COST,name,description);
}
    protected MenuItem(double cost, String name, String description) throws java.lang.IllegalArgumentException  {
        if(cost < 0) throw new java.lang.IllegalArgumentException("Illegal cost");
    this.name = name;
    this.description = description;
    this.cost = cost;
}
public String getName(){
    return name;
}
public double getCost(){
    return cost;
}
public String getDescription(){
    return description;
}

    @Override
    public String toString(){
        //“<name>, <cost>р.
        return String.format("%s, %d p.", name, cost);
    }
    public StringBuilder getString(){
        return new StringBuilder(this.toString());
    }

    @Override
    public boolean equals(Object obj){
        /*  //return Double.compare(this.cost, o.cost);*/
        if(this == obj) return true;
        if  (obj==null && !this.getClass().equals(obj.getClass())) return false;
                MenuItem otherItem = (MenuItem)obj;
        return(this.name.equals(otherItem.name) && (this.cost == otherItem.cost));
    }

    @Override
    public int hashCode(){
        return name.hashCode()^description.hashCode()^Double.hashCode(cost);
    }


    //todo 5 Класс MenuItem должен реализовывать интерфейс java.lang.Comparable<MenuItem>. Для этого
    //реализуйте метод compareTo (MenuItem o), который определяет порядок возрастания цены, то есть
    //возвращает отрицательное значение, если цена объекта o больше цены объекта, у которого вызывается
    //этот метод.

@Override
    public int compareTo(MenuItem o){
        return Double.compare (this.cost,o.cost);
}
}
