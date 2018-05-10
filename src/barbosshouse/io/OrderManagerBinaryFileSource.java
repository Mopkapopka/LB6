package barbosshouse.io;

import barbosshouse.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


public class OrderManagerBinaryFileSource extends OrderManagerFileSource {
/*    OrderManagerBinaryFileSource для записи и чтения данных из файла использует байтовые
    потоки. Для записи рекомендуется использовать DataOutputStream, а для чтения –
    DataInputStream, соответсвенно.
    Имя файла, содержащего информацию о заказе определяется из объекта заказа – время в
миллисекундах.


public void load(T t) - восстановление состояния объекта из некоторого источника
- public void store(T t) – запись состояния объекта в некоторый источник
- delete(T t) – удаляющий информацию о состоянии объекта из некоторого
источника
- create(T t) – создающий новый источник, хранящий состояние объекта

    */

    @Override
    public void load(Order menuItems) {
        Path path = Paths.get(filePath(menuItems,BIN_EXTENSION));

        DataInputStream stream = null;

        try {
            stream = new DataInputStream(Files.newInputStream(path));
            menuItems.setOrderTime(LocalDateTime.ofEpochSecond( stream.readLong() ,0, ZoneOffset.UTC));

            menuItems.setCustomer(loadedCustomer(stream));
            menuItems.clear();
            menuItems.addAll(loadedItems(stream));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void store(Order menuItems) {

        Path path = Paths.get(filePath(menuItems,BIN_EXTENSION));
        DataOutputStream stream = null;

        try {
            stream = new DataOutputStream(Files.newOutputStream(path));

            stream.writeLong(timeToMilli(menuItems.getOrderTime()));


            stream.writeUTF(menuItems.getCustomer().getFirstName());
            stream.writeUTF(menuItems.getCustomer().getSecondName());
            stream.writeLong(menuItems.getCustomer().getBirthDate().toEpochDay());

            stream.writeUTF(menuItems.getCustomer().getAddress().getCityName());
            stream.writeInt(menuItems.getCustomer().getAddress().getZipCode());
            stream.writeUTF(menuItems.getCustomer().getAddress().getStreetName());
            stream.writeInt(menuItems.getCustomer().getAddress().getBuildingNumber());
            stream.writeChar(menuItems.getCustomer().getAddress().getBuildingLetter());
            stream.writeInt(menuItems.getCustomer().getAddress().getApartamentNumber());

            stream.writeInt(menuItems.size());
            for (MenuItem d : menuItems){

                if( d instanceof Dish) stream.writeUTF("Dish");
                else stream.writeUTF("Drink");
                stream.writeDouble(d.getCost());
                stream.writeUTF(d.getName());
                stream.writeUTF(d.getDescription());

                if( d instanceof Drink) {
                    stream.writeUTF(((Drink) d).getType().toString());
                    stream.writeDouble(((Drink) d).getAlcoholVol());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Order menuItems) {
        Path path = Paths.get(filePath(menuItems,BIN_EXTENSION));

        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Order menuItems) {
        File fileOrder = new File(filePath(menuItems,BIN_EXTENSION));
        store(menuItems);

    }

    private Customer loadedCustomer( DataInputStream stream) throws IOException {
        return new Customer( stream.readUTF(), stream.readUTF(),LocalDate.ofEpochDay(stream.readLong()),loadedAddress(stream));
    }
    private Address loadedAddress ( DataInputStream stream) throws IOException {
        return new Address(stream.readUTF(), stream.readInt(),stream.readUTF(),stream.readInt(),
                stream.readChar(),stream.readInt() );
    }
    private List<MenuItem> loadedItems( DataInputStream stream) throws IOException {
        ArrayList<MenuItem> listTmpItems = new ArrayList<MenuItem>();
        int size = stream.readInt();

        for (int i =0; i<size; i++){
            if(stream.readUTF().equals("Dish")){
                listTmpItems.add(new Dish(
                        stream.readDouble(),
                        stream.readUTF(),
                        stream.readUTF()
                ));
            }
            else{
                listTmpItems.add(new Drink(
                        stream.readDouble(),
                        stream.readUTF(),
                        stream.readUTF(),
                        DrinkTypeEnum.valueOf(stream.readUTF()),
                        stream.readDouble()
                ));

            }
        }
        return listTmpItems;
    }
}
