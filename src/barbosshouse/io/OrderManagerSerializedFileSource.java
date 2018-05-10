package barbosshouse.io;

import barbosshouse.MenuItem;
import barbosshouse.Order;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class OrderManagerSerializedFileSource extends OrderManagerFileSource {
/*    для записи и чтения используется механизм
    сериализации. Для этого все классы должны быть подготовлены к сериализации.
    Имя файла, содержащего информацию о заказе определяется из объекта заказа – время в
миллисекундах. */

    @Override
    public void load(Order menuItems) {
        Path path = Paths.get(filePath(menuItems, OBJ_EXTENSION));
        try {
            ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(path));
            Order loadedMenuItems = (Order) stream.readObject();
            stream.close();

            menuItems.setCustomer(loadedMenuItems.getCustomer());
            menuItems.setOrderTime(loadedMenuItems.getOrderTime());
            menuItems.clear();
            menuItems.addAll(Arrays.asList((MenuItem[])loadedMenuItems.getMenuItems()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

        @Override
    public void store(Order menuItems) {
    Path path = Paths.get(filePath(menuItems,OBJ_EXTENSION));
        try {
            ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(path));
            stream.writeObject(menuItems);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order menuItems) {
        Path path = Paths.get(filePath(menuItems,OBJ_EXTENSION));

        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Order menuItems) {
        File fileOrder = new File(filePath(menuItems,OBJ_EXTENSION));
        store(menuItems);
    }



}
