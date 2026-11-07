package vn.longvan.tranning.spring.user.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
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
import java.util.TimeZone;

@DgsComponent
public class UserMutation {
    @Autowired
    UserController userController;
    @DgsMutation
    public User createUser(@InputArgument("name") String name, @InputArgument("birthDay") String birthDay){
        return userController.createUser(name, formatDate(birthDay));
    }

    @DgsMutation
    public User changeUserName(@InputArgument("id") String id, @InputArgument("newName") String newName){

        userController.changeUserName(id,newName);
        return userController.getUser(id);


    }

    @DgsMutation
    public boolean deleteUser(@InputArgument("id") String id){
        userController.deleteUser(id);
        return true;


    }

    private Date formatDate(String birthDay){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        TimeZone timeZoneVN = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        if(birthDay == null || birthDay.trim().isEmpty()){
            return null;
        }
        simpleDateFormat.setTimeZone(timeZoneVN);
        simpleDateFormat.setLenient(false);
        ParsePosition pos = new ParsePosition(0);
        Date date = simpleDateFormat.parse(birthDay.trim(), pos);
        if(date ==null || pos.getIndex() != birthDay.trim().length()){
            throw new DgsBadRequestException("sai dau vao");
        }
        return date;
    }

}
