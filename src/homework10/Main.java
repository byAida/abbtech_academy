package homework10;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserBackupService service = new UserBackupService();
        List<User> users = new ArrayList<>();

        users.add(new User("Aydin", "aydin@mail.com", 28));
        users.add(new User("Leyla", "leyla@mail.com", 24));
        users.add(new User("Nigar", "nigar@mail.com", 30));

        String dataFile = "users.dat";
        String backupDir = "backup";
        String backupFile = backupDir + "/users_backup.dat";

        System.out.println("İstifadəçilər");

        service.saveUsers(users, dataFile);

        List<User> loadedUsers = service.loadUsers(dataFile);
        System.out.println("Yüklənmiş istifadəçilər:");
        loadedUsers.forEach(System.out::println);

        service.backupFile(dataFile, backupDir);

        service.deleteBackup(backupFile);

        System.out.println("Proses uğurla tamamlandı");
    }
}

