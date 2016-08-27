package com.webstore.controller;

import com.constant.Constant;
import com.webstore.dto.UserSearch;
import com.webstore.entity.User;
import com.webstore.security.UserLogin;
import com.webstore.serviceImpl.UserRoleServiceImpl;
import com.webstore.serviceImpl.UserServiceImpl;
import com.webstore.serviceImpl.UserStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRoleServiceImpl userRoleService;

    @Autowired
    UserStatusServiceImpl userStatusService;

    private String errorMessage;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = Constant.USERS_URL, method = RequestMethod.GET)
    public String listUsers(Model model, @ModelAttribute("UserSearch") UserSearch userSearch){
        model.addAttribute(Constant.USERS, userService.getAllUsers(userSearch.getUsername(),
                userSearch.getName(), userSearch.getRole(), userSearch.getStatus()));
        model.addAttribute(Constant.ROLES, userRoleService.getUserRoles());
        model.addAttribute(Constant.STATUSES, userStatusService.getUserStatuses());
        model.addAttribute("usersUrl", Constant.USERS_URL);
        model.addAttribute("userAddUrl", Constant.USER_ADD_URL);
        model.addAttribute("userEditNoIdUrl", Constant.USER_EDIT_NO_ID_URL);
        model.addAttribute("userDeactivateNoIdUrl", Constant.USER_DEACTIVATE_NO_ID_URL);
        return Constant.USERS;
    }

    @RequestMapping(value = Constant.USER_URL, method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, Model model){
        Integer loggedUserId = ((UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        boolean successCreateUser;
        boolean successEditUser;
        if (user.getId() == null) {
            successCreateUser = userService.createUser(user);
            if (successCreateUser) {
                return  Constant.USERS_REDIRECT_URL;
            } else {
                errorMessage = userService.showErrorMessage();
                model.addAttribute(Constant.ROLES, userRoleService.getUserRoles());
                model.addAttribute(Constant.STATUSES, userStatusService.getUserStatuses());
                model.addAttribute(Constant.ERROR_MESSAGE, errorMessage);
                return addUser(user, model);
            }
        } else {
            successEditUser = userService.updateUser(user);
            if (successEditUser) {
                if (user.getId() == loggedUserId) {
                    return Constant.LOGIN_REDIRECT_URL;
                } else {
                    return  Constant.USERS_REDIRECT_URL;
                }
            } else {
                errorMessage = userService.showErrorMessage();
                model.addAttribute(Constant.ROLES, userRoleService.getUserRoles());
                model.addAttribute(Constant.STATUSES, userStatusService.getUserStatuses());
                model.addAttribute(Constant.ERROR_MESSAGE, errorMessage);
                return editUser(user.getId(), model);
            }
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(Constant.USER_ADD_URL)
    public String addUser(@ModelAttribute("user")  User user, Model model){
        model.addAttribute(Constant.ROLES, userRoleService.getUserRoles());
        model.addAttribute(Constant.STATUSES, userStatusService.getUserStatuses());
        model.addAttribute("userEditNoIdUrl", Constant.USER_EDIT_NO_ID_URL);
        model.addAttribute("userUrl", Constant.USER_URL);
        return Constant.USER_FORM_URL;
    }

    @RequestMapping(Constant.USER_EDIT_URL)
    public String editUser(@PathVariable Integer id, Model model){
        model.addAttribute(Constant.ROLES, userRoleService.getUserRoles());
        model.addAttribute(Constant.STATUSES, userStatusService.getUserStatuses());
        model.addAttribute(Constant.USER, userService.getUserById(id));
        model.addAttribute("userEditNoIdUrl", Constant.USER_EDIT_NO_ID_URL);
        model.addAttribute("userUrl", Constant.USER_URL);
        return Constant.USER_FORM_URL;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(Constant.USER_DEACTIVATE_URL)
    public String deactivateUser(@PathVariable Integer id){
        userService.deactivateUser(id);
        return Constant.USERS_REDIRECT_URL;
    }

    @RequestMapping(Constant.USER_EDIT_OWN_URL)
    public String editUserOwnCredentials(Model model){
        Integer loggedUserId = ((UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute(Constant.ROLES, userRoleService.getUserRoles());
        model.addAttribute(Constant.STATUSES, userStatusService.getUserStatuses());
        model.addAttribute(Constant.USER, userService.getUserById(loggedUserId));
        model.addAttribute("userUrl", Constant.USER_URL);
        return Constant.USER_FORM_URL;
    }



}
