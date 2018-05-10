package barbosshouse;

import java.io.Serializable;

public final class Address implements Serializable {


    private final String cityName; //поля final
    private int zipCode;
    private String streetName;
    private int buildingNumber;
    private char buildingLetter;
    private int apartamentNumber;

    public static final Address EMPTY_ADDRESS = new Address();

    private static final String DEFAULT_ADDRESS = "";
    private static final char DEFAULT_CHAR = ' ';
    private static final int DEFAULT_NUMBER = -1;
    private static final String DEFAULT_CITY = "Samara";

    public Address()  {
        this(DEFAULT_ADDRESS, DEFAULT_NUMBER, DEFAULT_ADDRESS, DEFAULT_NUMBER, DEFAULT_CHAR, DEFAULT_NUMBER);
    }
    public Address(String streetName,int buildingNumber, char buildingLetter, int apartamentNumber)  throws java.lang.IllegalArgumentException{
        this(DEFAULT_CITY, DEFAULT_NUMBER, streetName, buildingNumber, buildingLetter, apartamentNumber);
        if(buildingNumber<0 || !Character.isLetter(buildingLetter) || apartamentNumber <0) throw new java.lang.IllegalArgumentException("Illegal address!!");
    }

    public Address(String cityName,int zipCode, String streetName, int buildingNumber, char buildingLetter, int apartamentNumber)throws java.lang.IllegalArgumentException{
        /*if(zipCode<0 | buildingNumber<0 | !Character.isLetter(buildingLetter) | apartamentNumber <0)
            throw new java.lang.IllegalArgumentException("Illegal address!!");*/
        if(zipCode<0 & zipCode!= -1 )
            throw new java.lang.IllegalArgumentException("Illegal address!! 0");
        if( buildingNumber<0 & buildingNumber!= -1 )
            throw new java.lang.IllegalArgumentException("Illegal address!! 1");
        if( !Character.isLetter(buildingLetter) & buildingLetter!=DEFAULT_CHAR)
            throw new java.lang.IllegalArgumentException("Illegal address!! 2");

        if(apartamentNumber <0 & apartamentNumber!=-1)
            throw new java.lang.IllegalArgumentException("Illegal address!! 3" + apartamentNumber);

        this.cityName = cityName;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.buildingNumber=buildingNumber;
        this.buildingLetter=buildingLetter;
        this.apartamentNumber=apartamentNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public char getBuildingLetter() {
        return buildingLetter;
    }

    public int getApartamentNumber() {
        return apartamentNumber;
    }

    @Override
    public String toString(){
        return String.format("Addres: %s, %d, %s %d %c - %d",
                cityName, zipCode,streetName,buildingNumber,buildingLetter,apartamentNumber);

        /**
         StringBuilder finalString =new StringBuilder("Addres: ");
        if(cityName!=null && !cityName.isEmpty()) finalString.append(cityName).append(" ");
        if(zipCode!=-1) finalString.append(zipCode).append(", ");
        if(cityName!=null && !streetName.isEmpty()) finalString.append(streetName).append(" ");
        if(buildingNumber!= DEFAULT_NUMBER) finalString.append(buildingNumber).append(" ");
        if(buildingLetter!= DEFAULT_CHAR) finalString.append(buildingLetter).append(" ");
        if(apartamentNumber!= DEFAULT_NUMBER) finalString.append("-").append( apartamentNumber);
        return finalString.toString();*/
                //Address: <city> <zipCode>, <street> <buildingNumber><Literal>-<apartmentNumber>
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null && (this.getClass()!=obj.getClass()) ) return false;
        Address equalsCheck = (Address)obj;
        return( (this.cityName.equals(equalsCheck.cityName))
                && (this.zipCode == equalsCheck.zipCode) && (this.streetName.equals(equalsCheck.cityName))
                && (this.buildingNumber == equalsCheck.buildingNumber) && (this.buildingLetter == equalsCheck.buildingLetter)&&
                (this.apartamentNumber==equalsCheck.apartamentNumber) ) ;
    }

    @Override
    public int hashCode(){
        return cityName.hashCode()^zipCode^streetName.hashCode()^buildingNumber^buildingLetter^apartamentNumber;
    }
}
