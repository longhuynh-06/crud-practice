package vn.longvan.tranning.user.test;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.longvan.tranning.spring.user.controller.UserController;
import vn.longvan.tranning.spring.user.manager.UserManager;

@Configuration
public class TrainingServiceConfiguration {
    @Bean
    UserController getUserController(){ return new UserController();}

    @Bean
    UserManager getUserService(){
       return new UserManager();
   }
}
