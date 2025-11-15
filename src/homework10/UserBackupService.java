package homework10;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class UserBackupService {

    public void saveUsers(List<User> users, String filePath) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(filePath))) {

            oos.writeObject(users);
            System.out.println("İstifadəçilər uğurla yadda saxlanıldı: " + filePath);

        } catch (IOException e) {
            System.err.println("Fayla yazılarkən xəta baş verdi: " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    public List<User> loadUsers(String filePath) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(filePath))) {

            List<User> users = (List<User>) ois.readObject();
            System.out.println("İstifadəçilər uğurla oxundu: " + filePath);
            return users;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Fayldan oxunarkən xəta baş verdi: " + e.getMessage());
        }
        return null;
    }


    public void backupFile(String sourceFile, String backupDir) {
        try {
            Path backupDirectory = Paths.get(backupDir);

            if (!Files.exists(backupDirectory)) {
                Files.createDirectory(backupDirectory);
            }

            Path source = Paths.get(sourceFile);
            Path destination = Paths.get(backupDir + "/users_backup.dat");

            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup yaradıldı: " + destination);

        } catch (IOException | InvalidPathException e) {
            System.err.println("Backup yaradılarkən xəta baş verdi: " + e.getMessage());
        }
    }

    public void deleteBackup(String backupFilePath) {
        try {
            Path path = Paths.get(backupFilePath);

            if (Files.exists(path)) {
                Files.delete(path);
                System.out.println("Backup faylı silindi: " + backupFilePath);
            } else {
                System.out.println("ℹSilinəcək backup faylı tapılmadı.");
            }

        } catch (IOException | InvalidPathException e) {
            System.err.println("Backup faylını silmək mümkün olmadı: " + e.getMessage());
        }
    }
}

