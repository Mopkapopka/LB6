package barbosshouse.io;

public interface Source<T> {
    public void load(T t);  //- восстановление состояния объекта из некоторого источника
    public void store(T t); // – запись состояния объекта в некоторый источник
    void delete(T t); // – удаляющий информацию о состоянии объекта из некоторого источника
    void create(T t);  //– создающий новый источник, хранящий состояние объекта
}
