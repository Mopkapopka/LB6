package barbosshouse;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class Customer implements Serializable {
    private String firstName;
    private String secondName;
    private LocalDate birthDate;
    private Address address;

    public static final int NOT_MATURE_DEFAULT_AGE = 14;
    private static final int MATURE_DEFAULT_AGE_21 = 21;
    public static final Customer MATURE_UNKNOWN_CUSTOMER = new Customer(MATURE_DEFAULT_AGE_21);
    public static final Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(NOT_MATURE_DEFAULT_AGE);

    private static final String DEFAULT_STRING = "";
    //todo 4 Класс Customer. Конструкторы выбрасывают java.lang.IllegalArgumentException при
//попытке передать в конструкторы дату из будущего.
    public Customer(){
        this(DEFAULT_STRING, DEFAULT_STRING,  LocalDate.now(), Address.EMPTY_ADDRESS);
    }

    public Customer(int age){
        this(DEFAULT_STRING, DEFAULT_STRING, LocalDate.now(), Address.EMPTY_ADDRESS);
    }

    public  Customer(String firstName, String secondName, LocalDate birthDate,Address address)
            throws java.lang.IllegalArgumentException{
       if(birthDate.isAfter(LocalDate.now()))
        throw new java.lang.IllegalArgumentException("invent a time machine first!!!");
        this.address = address;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
    public LocalDate getBirthDate(){
        return birthDate;
    }
    public int getAge() {
        int age = 0;
        age = LocalDateTime.now().getYear()-birthDate.getYear();
        if(LocalDateTime.now().getDayOfYear() < birthDate.getDayOfYear())  {
            age--;
        }
        return age;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString(){
        //Customer: <secondName> <firstName>, <age>, <address>
        StringBuilder finalString =new StringBuilder("Customer: ");
        if(secondName!=null &&!secondName.isEmpty()) finalString.append(secondName).append(" ");
        if(finalString!=null  && !firstName.isEmpty()) finalString.append(firstName).append(", ");
        if(birthDate!=  LocalDate.now()) finalString.append(birthDate);
        if(!address.equals(Address.EMPTY_ADDRESS)) finalString.append(' ').append(this.address.toString());

        return finalString.toString();
     }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if (obj==null && this.getClass() == this.getClass()) return false;
        Customer equalsCheck = (Customer)obj;
        return ( this.getClass().equals(equalsCheck.getClass())&&(this.firstName.equals(equalsCheck.firstName)) &&
                (this.secondName.equals(equalsCheck.secondName)) && (this.birthDate == equalsCheck.birthDate) && (this.address.equals(equalsCheck.address)));
    }

    @Override //todo 4
    public int hashCode(){
        return firstName.hashCode()^secondName.hashCode()^birthDate.hashCode()^address.hashCode();
    }
}
