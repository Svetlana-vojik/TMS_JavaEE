package by.teachmeskills.utils;

import by.teachmeskills.commands.AddProductToCartCommand;
import by.teachmeskills.commands.BaseCommand;
import by.teachmeskills.commands.CategoryRedirectCommandImpl;
import by.teachmeskills.commands.DeleteProductFromCartCommandImpl;
import by.teachmeskills.commands.HomePageCommandImpl;
import by.teachmeskills.commands.RedirectProductPageCommandImpl;
import by.teachmeskills.commands.RedirectToShoppingCartImpl;
import by.teachmeskills.commands.RegistrationPageCommandImpl;
import by.teachmeskills.commands.SearchCommandImpl;
import by.teachmeskills.commands.SignInCommandImpl;
import by.teachmeskills.commands.UserPageCommandImpl;
import by.teachmeskills.enums.CommandsEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, BaseCommand> COMMAND_LIST = new HashMap<>();

    static {
        COMMAND_LIST.put(CommandsEnum.HOME_PAGE_COMMAND.getCommand(), new HomePageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.SIGN_IN_COMMAND.getCommand(), new SignInCommandImpl());
        COMMAND_LIST.put(CommandsEnum.CATEGORY_PAGE_COMMAND.getCommand(), new CategoryRedirectCommandImpl());
        COMMAND_LIST.put(CommandsEnum.ADD_PRODUCT_TO_CART.getCommand(), new AddProductToCartCommand());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_SHOPPING_CART_COMMAND.getCommand(), new RedirectToShoppingCartImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_PRODUCT_COMMAND.getCommand(), new RedirectProductPageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REGISTRATION_PAGE_COMMAND.getCommand(), new RegistrationPageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.DELETE_PRODUCT_FROM_CART.getCommand(), new DeleteProductFromCartCommandImpl());
        COMMAND_LIST.put(CommandsEnum.USER_PAGE_COMMAND.getCommand(), new UserPageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.SEARCH_PAGE_COMMAND.getCommand(), new SearchCommandImpl());
    }

    public static BaseCommand defineCommand(HttpServletRequest request) {
        String commandKey = request.getParameter(RequestParamsEnum.COMMAND.getValue());
        if (commandKey == null || commandKey.isEmpty()) {
            commandKey = CommandsEnum.SIGN_IN_COMMAND.getCommand();
        }
        return COMMAND_LIST.get(commandKey);
    }
}