package by.teachmeskills.commands;

import by.teachmeskills.entities.User;
import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import by.teachmeskills.exceptions.RequestParamNullException;
import by.teachmeskills.services.UserService;
import by.teachmeskills.services.impl.UserServiceImpl;
import by.teachmeskills.utils.ValidatorUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

import static by.teachmeskills.enums.PagesPathEnum.REGISTRATION_PAGE;

public class RegistrationPageCommandImpl implements BaseCommand {
    private final static Logger log = LogManager.getLogger(RedirectProductPageCommandImpl.class);
    private final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws RequestParamNullException {

        String email = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());
        String name = request.getParameter(RequestParamsEnum.NAME.getValue());
        String surname = request.getParameter(RequestParamsEnum.SURNAME.getValue());
        String birthday = request.getParameter(RequestParamsEnum.BIRTHDAY.getValue());
        String address = request.getParameter(RequestParamsEnum.ADDRESS.getValue());

        try {
            ValidatorUtil.validateParamNotNull(email, password, name, surname, birthday);
        } catch (RequestParamNullException e) {
            return PagesPathEnum.REGISTRATION_PAGE.getPath();
        }

        if (ValidatorUtil.validateRegistration(email, name, surname, birthday, address)) {
            if (userService.findByEmailAndPassword(email, password) != null) {
                request.setAttribute("info", "Данный пользователь уже зарегистрирован. Войдите в систему.");
            } else {
               User user= new User(email, password, name, surname, LocalDate.parse(birthday), 0, address);
               userService.create(user);
                request.setAttribute("info", "Пользователь успешно зарегистрирован. Войдите в систему.");
            }
        } else {
            request.setAttribute("info", "Некорректные данные.");
        }
        return REGISTRATION_PAGE.getPath();
    }
}