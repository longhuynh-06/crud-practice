package vn.longvan.tranning.spring.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.longvan.tranning.spring.user.exception.UserErrorCode;
import vn.longvan.tranning.spring.user.exception.UserException;
import vn.longvan.tranning.spring.user.exception.UserNotExistException;
import vn.longvan.tranning.spring.user.manager.UserManager;
import vn.longvan.tranning.spring.user.model.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class UserController {
    @Autowired
    UserManager userManager;
    public User createUser(String name, Date birthDay){
        checkName(name);
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name.trim());
        user.setBirthDay(birthDay);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        userManager.createUser(user);
        return user;
    }

    public User getUser(String id){
        User user = userManager.getUser(id);
        if(user == null){
            throw new UserNotExistException(UserManager.USER_NOT_EXIST, id);
        }
        return user;
    }
    public void update(String userId,String name,Date birthDay){
        checkName(name);
        userManager.update(userId,name.trim(),birthDay);
    }

    public List<User> getAllUser(){
        return userManager.getAllUser();
    }

    public void changeUserName(String userId, String newName){
        checkName(newName);
        userManager.changeUserName(userId,newName.trim());
    }

    public void deleteUser(String id){
        userManager.deleteUser(id);
    }

    public List<User> findByName(String name){
        return userManager.findByName(name);
    }
    public void checkName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new UserException(USER_NAME_INVALID,UserErrorCode.USER_EXCEPTION);
        }
    }
    public List<User> getUsers(int first, int pageSize){
        if(first < 0 || pageSize <= 0){
            throw new UserException("Không có dữ liệu", UserErrorCode.USER_EXCEPTION);
        }
        return userManager.getUsers(first,pageSize);
    }

    public long countUsers(){
        return userManager.countUsers();
    }
    public static String USER_NAME_INVALID = "Tên người dùng không hợp lệ";


}
