package barbosshouse.exceptionspackage;

public class AlreadyAddedException extends Exception{
    public AlreadyAddedException(String s){
        super(s);
    }
}
//b. AlreadyAddedException. Выбрасывается методами добавления заказа в случае,
//если клиент пытается добавить заказ к столику, который не свободен. Или когда
//добавляется интернет заказ, а в списке заказов уже существует заказ с таким же
//клиентом и временем заказа.
