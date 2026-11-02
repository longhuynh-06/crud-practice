package vn.longvan.tranning.spring.user.bean;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.longvan.tranning.spring.user.controller.UserController;
import vn.longvan.tranning.spring.user.model.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@ManagedBean(name = "UserBean")
@SessionScoped
@Component
public class UserBean implements Serializable {
    @Autowired
    UserController userController;
    private LazyDataModel<User> model;
    @PostConstruct
    public void init() {
        model = new LazyDataModel<User>() {
            @Override
            public int count(Map<String, FilterMeta> filterBy) {
                return (int) userController.countUsers();
            }

            @Override
            public List<User> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
                return userController.getUsers(first, pageSize);
            }
        };
    }
    public LazyDataModel<User> getLazyUsers(){
        return model;
    }
    public int getTotalUsers(){
        return userController.getAllUser().size();
    }
    public List<User> getUsers(){
        return userController.getAllUser();
    }

}
