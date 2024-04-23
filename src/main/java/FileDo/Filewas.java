package FileDo;

import exceptions.ReadElementException;

import java.io.File;

public class Filewas {

    public boolean canReadFile(File file) throws ReadElementException {
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("Файл не может быть считан. Причина: файл является папкой");
                return false;
            } else {
                System.out.println("Файл считан");
                System.out.println("Файл содержит" +" " + file.length() + " " + "элементов");
                if(file.length()==0){
                    System.out.println("Файл пуст");
                    return false;
                }
                else {
                    return true;
                }
            }
        } else {
            System.out.println("Файл не может быть считан, ввиду его отрицательного существования");
            return false;
        }

    }
}

