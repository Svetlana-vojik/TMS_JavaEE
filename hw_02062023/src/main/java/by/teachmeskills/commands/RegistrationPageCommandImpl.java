package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import by.teachmeskills.exceptions.CommandException;
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
import static by.teachmeskills.utils.CRUDUtils.getUser;
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
            validateParamNotNull(name);
            validateParamNotNull(surname);
            validateParamNotNull(birthday);
            validateParamNotNull(email);
            validateParamNotNull(password);
        } catch (CommandException e) {
            return PagesPathEnum.REGISTRATION_PAGE.getPath();
        }

        if (ValidatorUtil.validateRegistration(email, name, surname, password, birthday)) {
            try {
                User user = CRUDUtils.getUser(email, password);
                if (user != null) {
                    request.setAttribute("info", "Данный пользователь уже зарегистрирован. Войдите в систему.");
                } else {
                    user = new User(email, password, name, surname, birthday);
                    CRUDUtils.addUser(user);
                    request.setAttribute("info", "Пользователь успешно зарегистрирован. Войдите в систему.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            request.setAttribute("info", "Некорректные данные.");
        }

        return PagesPathEnum.HOME_PAGE.getPath();
    }
}