package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import by.teachmeskills.model.Category;
import by.teachmeskills.model.User;
import by.teachmeskills.utils.CRUDUtils;
import by.teachmeskills.utils.ValidatorUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.teachmeskills.enums.PagesPathEnum.HOME_PAGE;
import static by.teachmeskills.enums.PagesPathEnum.REGISTRATION_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.CATEGORY;
import static by.teachmeskills.utils.CRUDUtils.getCategoriesFromDB;
import static by.teachmeskills.utils.HttpRequestParamValidator.validateParamNotNull;
import static by.teachmeskills.utils.ValidatorUtil.validateRegistration;


public class RegistrationPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String name = request.getParameter(RequestParamsEnum.NAME.getValue());
        String surname = request.getParameter(RequestParamsEnum.SURNAME.getValue());
        String birthday = request.getParameter(RequestParamsEnum.BIRTHDAY.getValue());
        String email = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());
        try {
            ValidatorUtil.validateRegistration(email, name, surname, password, birthday);
            checkUser(email,password);
        } catch (Exception e) {
               return REGISTRATION_PAGE.getPath();
        }
        User user = new User(email, password, name, surname, birthday);

        CRUDUtils.addUser(user);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        List<Category> categories = CRUDUtils.getCategoriesFromDB();
            request.setAttribute("categories", getCategoriesFromDB());
        return PagesPathEnum.HOME_PAGE.getPath();

    }

    private void checkUser(String email, String password) throws Exception {
        User user = CRUDUtils.getUser(email,password);
        if (user != null) {
            throw new Exception("Пользователь с email - " + email + " уже зарегистрирован." +
                    " Перейдите на страницу входа");
        }
    }
}