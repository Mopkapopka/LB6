package barbosshouse;

public class Dish extends MenuItem {

    private static final double DEFAULT_COST = 0.0;

    public Dish(String name, String description){
        super(DEFAULT_COST,name,description);
    }
    public Dish(double cost, String name, String description){
       super(cost,name,description);
    }


    @Override
    public String toString(){
    return String.format("%s \n Описание: \n%s",super.getString(), getDescription());
    }
    @Override
    public boolean equals(Object obj){
        return super.equals(obj);
    }

    @Override
    public int hashCode(){
        return super.hashCode()^getDescription().hashCode();
    }
}
