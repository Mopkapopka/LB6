package barbosshouse.exceptionspackage;

public class NegativeSizeException extends java.lang.NegativeArraySizeException{
    public NegativeSizeException(String s){
        super(s);
    }
}

//todo 4 Создайте класс не объявляемого исключения NegativeSizeException, наследуемого от
//java.lang.NegativeArraySizeException. Это исключение должно выбрасываться
//конструкторами классов TableOrder и TableOrdersManager, при попытке передать в
//конструктор отрицательное значение размера массива.
