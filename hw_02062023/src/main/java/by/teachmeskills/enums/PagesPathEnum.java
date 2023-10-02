package by.teachmeskills.enums;

public enum PagesPathEnum {
    HOME_PAGE("home.jsp"),
    LOGIN_PAGE("login.jsp"),
    CATEGORY_PAGE("category.jsp"),
    CART_PAGE("cart.jsp"),
    PRODUCT_PAGE("product.jsp"),
    REGISTRATION_PAGE("registration.jsp"),
    USER_PROFILE_PAGE("userPage.jsp");
    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}