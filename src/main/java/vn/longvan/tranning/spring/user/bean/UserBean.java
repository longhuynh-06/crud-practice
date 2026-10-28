package vn.longvan.tranning.spring.user.bean;

import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;


@ManagedBean(name = "UserBean")
@SessionScoped
@Component
public class UserBean implements Serializable {

}
