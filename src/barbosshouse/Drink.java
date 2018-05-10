package barbosshouse;

public class Drink extends MenuItem implements Alcoholable {
    private double alcoholVol;
    private DrinkTypeEnum type;

    private static final double ALCHOGOL_VOLUME_DEFAULT = 0.0;
    private static final String DESCRIPTION_DEFAULT = "";
    private static final double DEFAULT_COST = 0.0;


    public Drink(String name, DrinkTypeEnum type){
    super(name,"");
    alcoholVol = ALCHOGOL_VOLUME_DEFAULT;
       // this(DEFAULT_COST,name,type,DESCRIPTION_DEFAULT,ALCHOGOL_VOLUME_DEFAULT);

    }

    public  Drink(double cost, String name, DrinkTypeEnum type, String description){
        this(cost,name,description,type,ALCHOGOL_VOLUME_DEFAULT);
    }

  public  Drink(double cost, String name, String description,DrinkTypeEnum type, double alcoholVol) throws java.lang.IllegalArgumentException {
        super(cost,name,description);
        if(alcoholVol>100 || alcoholVol<0) throw new java.lang.IllegalArgumentException("Alcohol volume  can be only between 0 and 100%");
      this.type = type;
        this.alcoholVol = alcoholVol;
    }

    public DrinkTypeEnum getType() {
        return type;
    }

    public double getAlcoholVol(){
        return alcoholVol;
    }
    public boolean isAlcoholicDrink(){
/*            if (type.ordinal()<11) return true;
            else return false;*/
        return alcoholVol>0;
    }
    @Override
    public String toString(){
        //Drink: <type> <name>, <cost>р. Alcholol: <alcoholVol>%. <description>”
        return String.format("DRINK: %s  Alcohol: %s %. %s" , super.getString(),alcoholVol,getDescription());
    }
@Override
    public boolean equals(Object obj) {
    return super.equals(obj);
    }
@Override
public int hashCode(){
    return Double.hashCode(alcoholVol)^getDescription().hashCode()^super.hashCode();
}
}
