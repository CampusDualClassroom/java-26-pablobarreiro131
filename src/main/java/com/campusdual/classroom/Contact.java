package com.campusdual.classroom;
import java.text.Normalizer;
import java.util.Locale;

public class Contact implements ICallActions{

    private String name;
    private String surnames;
    private String phone;
    private String code;
    Phonebook phonebook;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void generateCode(){
        String normalizedName = Normalizer.normalize(this.name, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase(Locale.ROOT);
        String normalizedSurnames = Normalizer.normalize(this.surnames, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase(Locale.ROOT);
        String [] parts = normalizedSurnames.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(normalizedName.charAt(0));
        if(parts.length == 1){
            sb.append(parts[0]);
        }
        else {
                sb.append(parts[0].charAt(0));
                for (int i = 1; i < parts.length; i++) {
                    sb.append(parts[i]);
            }
        }
        this.code = sb.toString();
    }

    public Contact(String name, String surnames, String phone){
        this.name= name;
        this.surnames= surnames;
        this.phone= phone;
        generateCode();
    }

    public String getCode() {
        return code;
    }
    public void setPhonebook(Phonebook phonebook) {
        this.phonebook = phonebook;
    }


    public void callMyNumber() {
        System.out.println("Te estas llamando a ti mismo... " + this.getName() + " " + this.getSurnames() + " Numero: "+ this.getPhone());
    }

    public void callOtherNumber(String phone) {
        String calledName = "Contacto desconocido";

        if (phonebook != null) {
            Contact calledContact = phonebook.getContactByPhone(phone);
            if (calledContact != null) {
                calledName = calledContact.getName() + " " + calledContact.getSurnames();
            }
        }

        System.out.println(name + " " + surnames + " está llamando a " + calledName + " (Número: " + phone + ")");
    }



    public void showContactDetails() {
        System.out.println("Nombre: " + this.name);
        System.out.println("Apellidos: " + this.surnames);
        System.out.println("Telefono: " + this.phone);
        System.out.println("Codigo: " + this.code);
    }
}
