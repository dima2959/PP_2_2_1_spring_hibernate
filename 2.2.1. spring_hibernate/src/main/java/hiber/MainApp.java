package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        carService.add(new Car("Honda", 159357));
        carService.add(new Car("Suzuki", 357159));
        carService.add(new Car("Subaru", 258456));
        carService.add(new Car("Mitsubishi", 852654));

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", carService.getCar(3L)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", carService.getCar(2L)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", carService.getCar(1L)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", carService.getCar(4L)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println();
        System.out.println(userService.getUserByCar("Honda", 159357));
        System.out.println();
        System.out.println(userService.getUserByCar("Suzuki", 357159));
        System.out.println();
        System.out.println(userService.getUserByCar("Subaru", 258456));
        System.out.println();
        System.out.println(userService.getUserByCar("Mitsubishi", 852654));
        System.out.println();

        context.close();
    }
}