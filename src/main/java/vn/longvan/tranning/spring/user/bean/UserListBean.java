package vn.longvan.tranning.spring.user.bean;

import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.longvan.tranning.spring.user.controller.UserController;
import vn.longvan.tranning.spring.user.exception.UserException;
import vn.longvan.tranning.spring.user.model.User;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
public class UserListBean implements Serializable {
    @Autowired
    UserController userController;
    private String name;
    private Date birthDay;
    private LazyDataModel<User> model;

    @PostConstruct
    public void init(){
        model = new PagedLazyDataModel(userController);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public LazyDataModel<User> getLazyUsers(){ //list Lazy
        return model;
    }

    public void createUser(){
        try{
            userController.createUser(name,birthDay);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Người dùng được thêm thành công","Tên: "+name +"-"+birthDay));
            name = null;
            birthDay =null;
            PrimeFaces.current().ajax().addCallbackParam("saved",true);

        }catch(UserException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Loi", e.getMessage()));
        }

    }
    public int getTotalUsers(){
        return userController.getAllUser().size();
    }

    public List<User> getUsers(){
        return userController.getAllUser();
    }

}
