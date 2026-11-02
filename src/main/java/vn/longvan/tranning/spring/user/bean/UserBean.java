package vn.longvan.tranning.spring.user.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.longvan.tranning.spring.user.controller.UserController;
import vn.longvan.tranning.spring.user.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name = "UserBean")
@SessionScoped
@Component
public class UserBean implements Serializable {
    @Autowired
    UserController userController;
    public int getTotalUsers(){
        return userController.getAllUser().size();
    }
    public List<User> getUsers(){
        return userController.getAllUser();
    }

}
