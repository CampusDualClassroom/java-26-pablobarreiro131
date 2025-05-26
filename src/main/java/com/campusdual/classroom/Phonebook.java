package com.campusdual.classroom;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {

    private HashMap<String, Contact> data = new HashMap<>();
    Scanner teclado = new Scanner(System.in);
    Contact tuMismo;

    public Phonebook(){
        this.tuMismo = new Contact("Pepe", "Perez Gomez", "666555444");
        this.tuMismo.setPhonebook(this);
    }

    public void addContact(Contact c) {
        data.put(c.getCode(),c);
        c.setPhonebook(this);
    }
    public void showPhonebook() {
        for (Contact c: data.values()){
            System.out.println("Nombre: " + c.getName());
            System.out.println("Apellidos: " + c.getSurnames());
            System.out.println("Telefono: " + c.getPhone());
            System.out.println("Codigo: " + c.getCode());
            System.out.println("------------------------------------------");
        }
    }

    public void deleteContact(String code){
        data.remove(code);
    }

    public void menu(){
        boolean fin = true;
        do{
            System.out.println("||| Menu principal |||");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Seleccionar contacto: ");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Mostrar mis datos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int eleccion = teclado.nextInt();
            teclado.nextLine();
            switch(eleccion){
                case 1: {
                    System.out.println("Añadiendo contacto...");
                    System.out.print("Nombre: ");
                    String name = teclado.nextLine();
                    System.out.print("Apellidos: ");
                    String surnames = teclado.nextLine();
                    System.out.print("Telefono: ");
                    String phone = teclado.nextLine();
                    Contact c = new Contact(name, surnames, phone);
                    addContact(c);
                    System.out.println("Contacto añadido con código: " + c.getCode()); break;
                }
                case 2: showPhonebook(); break;
                case 3:
                    System.out.print("Introduce el c del contacto: ");
                    String codigo = teclado.nextLine();
                    menuContacto(data.get(codigo)); break;
                case 4: {
                    System.out.print("Introduce el codigo del contacto a eliminar:  ");
                    String code = teclado.nextLine();
                    deleteContact(code);
                    System.out.println("Contacto eliminado."); break;
                }
                case 5: tuMismo.showContactDetails(); break;
                case 0: fin = false; break;
                default:
                    System.out.println("La opción elegida no es valida."); break;
            }
        }
        while(fin);
    }

    public void menuContacto(Contact c){
        boolean salida = true;
        int opc;

        do{
            System.out.println("1. Llamarte a ti mismo");
            System.out.println("2. Llamar al contacto introducido");
            System.out.println("3. Informacion del contacto.");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opc = teclado.nextInt();
            teclado.nextLine();
            switch (opc){
                case 1: tuMismo.callMyNumber(); break;
                case 2: {
                    tuMismo.callOtherNumber(c.getPhone()); break;
                }
                case 3: c.showContactDetails();
                case 0: salida = false; break;
            }

        }
        while(salida);

    }

    public Map<String, Contact> getData() {
        return this.data;
    }
    public Contact getContactByPhone(String phone) {
        for (Contact contact : data.values()) {
            if (contact.getPhone().equals(phone)) {
                return contact;
            }
        }
        return null;
    }
}
