package vn.longvan.tranning.user.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.longvan.tranning.spring.user.controller.UserController;
import vn.longvan.tranning.spring.user.exception.UserErrorCode;
import vn.longvan.tranning.spring.user.exception.UserException;
import vn.longvan.tranning.spring.user.exception.UserNotExistException;
import vn.longvan.tranning.spring.user.model.User;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringTrainingApplication.class)
@AutoConfigureDataMongo
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserController {
    @Autowired
    UserController userController;

    User created;

    @Test
    @Order(1)
    public void testCreateUser(){
        created = userController.createUser("minh long", new Date());
        assertNotNull(created.getId());
        assertNotNull(created.getCreated());
        assertEquals("minh long", created.getName());
        UserException ex = assertThrows(UserException.class,()->
                userController.createUser(" ", new Date()));
        assertEquals(UserErrorCode.USER_EXCEPTION, ex.getErrorCode());
    }

    @Test
    @Order(2)
    public void testUpdateUserName(){
        Date checkUpdate = created.getUpdated();
        assertDoesNotThrow(()->userController.changeUserName(created.getId(),"new minh long"));
        User found = userController.getUser(created.getId());
        assertEquals("new minh long",found.getName());
        assertNotEquals(checkUpdate, found.getUpdated());
    }

    @Test
    @Order(3)
    public void testDeleteUser(){
        assertDoesNotThrow(()->userController.deleteUser(created.getId()));
        assertThrows(UserNotExistException.class, ()-> userController.getUser(created.getId()));
        assertThrows(UserNotExistException.class,()->userController.deleteUser(UUID.randomUUID().toString()));
    }


}
