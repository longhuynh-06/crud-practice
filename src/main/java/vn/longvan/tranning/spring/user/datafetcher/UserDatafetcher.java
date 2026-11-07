package vn.longvan.tranning.spring.user.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsBadRequestException;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import vn.longvan.tranning.spring.user.controller.UserController;
import vn.longvan.tranning.spring.user.exception.UserException;
import vn.longvan.tranning.spring.user.exception.UserNotExistException;
import vn.longvan.tranning.spring.user.model.User;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@DgsComponent
public class UserDatafetcher {
    @Autowired
    UserController userController;

    @DgsQuery
    public User user(@InputArgument("id") String id){
        return userController.getUser(id);
    }

    @DgsQuery
    public List<User> users(@InputArgument("page") int page, @InputArgument("size")int size){
        return userController.getUsers(page*size,size);
    }

    @DgsQuery
    public int totalUsers(){
        return (int) userController.countUsers();
    }


}
