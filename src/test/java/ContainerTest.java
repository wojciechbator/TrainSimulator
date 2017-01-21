import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import trainSimulator.models.User;
import trainSimulator.services.UserService;

import java.util.List;


public class ContainerTest
{
    //Spring context simple test
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("trainSimulator");//This will load the configured components UserService, UserRepository,
        ctx.refresh();

        System.out.println(ctx);
        UserService userService = ctx.getBean("userService", UserService.class);

        List<User> allUser = userService.findAll();
        // No users at all
        for (User u : allUser)
        {
            System.out.println(u);
        }

        User user = new User();
        user.setId(20);
        System.out.println("Newly created User Id = " + user.getId());
        allUser = userService.findAll();
        //No users, no DAO method invoked
        for (User u : allUser)
        {
            System.out.println("Users: " + u.getId() + "\n");
        }
    }

}