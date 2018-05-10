package barbosshouse.exceptionspackage;

public class NoFreeTableException extends Exception{
    public NoFreeTableException(String s){
        super(s);
    }
}

//Создайте классы объявляемых исключений
//a. NoFreeTableException. Выбрасывается методом возвращающем номер
//свободного столика в случае, если свободных столиков нет.