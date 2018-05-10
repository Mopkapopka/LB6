package barbosshouse.io;

import barbosshouse.*;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
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
import java.util.Scanner;


public class OrderManagerTextFileSource extends OrderManagerFileSource {

//    OrderManagerTextFileSource для записи и чтения данных из файла использует символьные
//    потоки. Для записи рекомендуется использовать PrintWriter, а для чтения (и разбора)
//    рекомендуется использовать либо StreamTokenizer, либо java.util.Scanner.
/*Имя файла, содержащего информацию о заказе определяется из объекта заказа – время в
    миллисекундах.*/

    @Override
    public void load(Order menuItems) {

        Path path = Paths.get(filePath(menuItems,TXT_EXTENSION));
        Scanner sc = null;
        try {
            sc = new Scanner(path);
            menuItems.setOrderTime(LocalDateTime.ofEpochSecond( Long.parseLong(sc.nextLine()),0, ZoneOffset.UTC));
            menuItems.setCustomer(loadedCustomer(sc));
            menuItems.clear();
            menuItems.addAll(loadedItems(sc));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(Order menuItems) {
        PrintWriter printWriter = null;
        Path path = Paths.get(filePath(menuItems,TXT_EXTENSION));

        try {
            printWriter = new PrintWriter(Files.newBufferedWriter(path));

            printWriter.println(timeToMilli(menuItems.getOrderTime()));

            printWriter.println(menuItems.getCustomer().getFirstName());
            printWriter.println(menuItems.getCustomer().getSecondName());
            printWriter.println(menuItems.getCustomer().getBirthDate().toEpochDay());

            printWriter.println(menuItems.getCustomer().getAddress().getCityName());
            printWriter.println(menuItems.getCustomer().getAddress().getZipCode());
            printWriter.println(menuItems.getCustomer().getAddress().getStreetName());
            printWriter.println(menuItems.getCustomer().getAddress().getBuildingNumber());
            printWriter.println(menuItems.getCustomer().getAddress().getBuildingLetter());
            printWriter.println(menuItems.getCustomer().getAddress().getApartamentNumber());

            printWriter.println(menuItems.size());
            for (MenuItem d : menuItems){

                if( d instanceof Dish)  printWriter.println("Dish");
                else printWriter.println("Drink");
                printWriter.println(d.getCost());
                printWriter.println(d.getName());
                printWriter.println(d.getDescription());

                if( d instanceof Drink) {
                    printWriter.println(((Drink) d).getType());
                    printWriter.println(((Drink) d).getAlcoholVol());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }

    @Override
    public void delete(Order menuItems) {
        Path path = Paths.get(filePath(menuItems,TXT_EXTENSION));

        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Order menuItems) {
            File fileOrder = new File(filePath(menuItems,TXT_EXTENSION));
            store(menuItems);

    }

    private Customer loadedCustomer(Scanner sc){
        return new Customer( sc.nextLine(), sc.nextLine(),LocalDate.ofEpochDay( Long.parseLong(sc.nextLine())),loadedAddress(sc));
    }
    private Address loadedAddress (Scanner sc){
        return new Address(sc.nextLine(), Integer.parseInt(sc.nextLine()),sc.nextLine(),Integer.parseInt(sc.nextLine()),
                sc.nextLine().charAt(0),Integer.parseInt(sc.nextLine()) );
    }
    private List<MenuItem> loadedItems(Scanner sc){
        ArrayList<MenuItem> listTmpItems = new ArrayList<MenuItem>();
        int size = Integer.parseInt(sc.nextLine());

        for (int i =0; i<size; i++){
            if(sc.nextLine().equals("Dish")){
                listTmpItems.add(new Dish(
                        Double.parseDouble(sc.nextLine()),
                        sc.nextLine(),
                        sc.nextLine()
                ));
            }
            else{
                listTmpItems.add(new Drink(
                        Double.parseDouble(sc.nextLine()),
                        sc.nextLine(),
                        sc.nextLine(),
                        DrinkTypeEnum.valueOf(sc.nextLine()),
                        Double.parseDouble(sc.nextLine())
                ));

            }
        }
        return listTmpItems;
    }

}
