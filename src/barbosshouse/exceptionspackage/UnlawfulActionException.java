package barbosshouse.exceptionspackage;

public class UnlawfulActionException extends RuntimeException{
    public UnlawfulActionException(String s){
        super(s);
    }
}

//todo 4 Создайте класс не объявляемого исключения UnlawfulActionException. Оно
//выбрасывается при попытке добавить алкогольный напиток к заказу, если клиент не
//совершеннолетний или текущее время больше 22-00.



