package vn.longvan.tranning.spring.user.bean;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.longvan.tranning.spring.user.controller.UserController;
import vn.longvan.tranning.spring.user.exception.UserException;
import vn.longvan.tranning.spring.user.exception.UserNotExistException;
import vn.longvan.tranning.spring.user.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Date;

@Component
public class UserDetailBean {
    private User user;
    private String id,name;
    private Date birthDay;
    @Autowired
    UserController userController;
    public void load(){

        try{
            user = userController.getUser(id);

        }catch (UserNotExistException e){
            user=null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("không tìm thấy người dùng",e.getMessage()));
        }
    }
    public void prepareUpdate(){
        if(user==null) return;
        name = user.getName();
        birthDay = user.getBirthDay();
    }
    public void update(){
        try{
            userController.update(id,name,birthDay);
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Đã cập nhập thành công", "Tên: "+user.getName()+"- Ngày sinh: "+user.getBirthDay()));
            PrimeFaces.current().ajax().addCallbackParam("saved",true);
        }catch (UserNotExistException e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Lỗi: ",e.getMessage()));
        }catch(UserException e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Lỗi: ",e.getMessage()));
        }
    }
    public Date getBirthDay() {
        return birthDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
