package by.teachmeskills.enums;

public enum RequestParamsEnum {
    LOGIN("email"),
    PASSWORD("password"),
    NAME("name"),
    SURNAME("surname"),
    BIRTHDAY("birthday"),
    USER("user"),
    COMMAND("command"),
    SHOPPING_CART("cart"),
    CATEGORY("category"),
    CATEGORY_ID("category_id"),
    CATEGORIES("categories"),
    PRODUCT("product"),
    PRODUCTS("products"),
    SHOPPING_CART_PRODUCTS("cartProductsList"),
    PRODUCT_ID("product_id"),
    ADDRESS("address");

    private final String value;

    RequestParamsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}