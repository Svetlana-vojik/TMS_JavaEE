package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.model.User;
import by.teachmeskills.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.utils.HttpRequestParamValidator.validateParamNotNull;

public class SignInCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());
        validateParamNotNull(email);
        validateParamNotNull(password);

        User user = CRUDUtils.getUser(email, password);
        return checkReceivedUser(user, request);
    }

    private String checkReceivedUser(User user, HttpServletRequest request) {
        if (user != null) {
            request.getSession().setAttribute("user", user);
            request.setAttribute("categories", CRUDUtils.getCategoriesFromDB());
            return PagesPathEnum.HOME_PAGE.getPath();
        } else {
            request.setAttribute("error", "Пользователь не зарегистрирован!");
            return PagesPathEnum.LOGIN_PAGE.getPath();
        }
    }
}