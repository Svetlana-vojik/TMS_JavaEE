package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import by.teachmeskills.exceptions.RequestParamNullException;
import by.teachmeskills.model.User;
import by.teachmeskills.utils.CRUDUtils;
import by.teachmeskills.utils.ValidatorUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.teachmeskills.enums.PagesPathEnum.REGISTRATION_PAGE;

public class RegistrationPageCommandImpl implements BaseCommand {
    private final static Logger log = LogManager.getLogger(RedirectProductPageCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) throws RequestParamNullException {

        String email = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());
        String name = request.getParameter(RequestParamsEnum.NAME.getValue());
        String surname = request.getParameter(RequestParamsEnum.SURNAME.getValue());
        String birthday = request.getParameter(RequestParamsEnum.BIRTHDAY.getValue());

        try {
            ValidatorUtil.validateParamNotNull(email, password, name, surname, birthday);
        } catch (RequestParamNullException e) {
            return PagesPathEnum.REGISTRATION_PAGE.getPath();
        }

        if (ValidatorUtil.validateRegistration(email, name, surname, birthday)) {
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
                log.warn(e.getMessage());
            }
        } else {
            request.setAttribute("info", "Некорректные данные.");
        }

        return REGISTRATION_PAGE.getPath();
    }
}