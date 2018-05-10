package barbosshouse.io;

import barbosshouse.Order;

public interface FileSource extends  Source<Order> {
    public void setPath(String path);//изменяющий путь к файлу, в который записывается состояние объекта
    public String getPath(); //озвращающий путь к файлу, в который записывается состояние объекта
}
