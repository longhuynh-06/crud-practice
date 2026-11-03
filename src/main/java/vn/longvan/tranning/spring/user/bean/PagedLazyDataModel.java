package vn.longvan.tranning.spring.user.bean;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import vn.longvan.tranning.spring.user.controller.UserController;
import vn.longvan.tranning.spring.user.model.User;

import java.util.List;
import java.util.Map;

public class PagedLazyDataModel extends LazyDataModel<User> {

    private final UserController userController;

    public PagedLazyDataModel(UserController userController) {
        this.userController = userController;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return (int)userController.countUsers();
    }

    @Override
    public List<User> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        return userController.getUsers(first,pageSize);
    }
}
