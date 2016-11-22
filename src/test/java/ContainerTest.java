//import java.util.List;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//
//public class ContainerTest
//{
//    public static void main(String[] args)
//    {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.scan("com.sivalabs");//This will load the configured components UserService, UserRepository,
//        ctx.refresh();
//
//        System.out.println(ctx);
//        UserService userService = ctx.getBean("userService", UserService.class);
//
//        List<User> allUser = userService.getAllUsers();
//        for (User u : allUser)
//        {
//            System.out.println(u);
//        }
//
//        User user = new User(null, "K.siva reddy", "hyderabad");
//        Integer id = userService.createUser(user);
//        System.out.println("Newly created User Id="+id);
//        allUser = userService.getAllUsers();
//        for (User u : allUser)
//        {
//            System.out.println(u);
//        }
//    }
//
//}