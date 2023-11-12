package christmas.domain;

public enum Menu {
    MUSHROOM_SOUP("양송이스프", Category.APPETIZER, 6000),
    TAPAS("타파스", Category.APPETIZER,5500),
    CAESAR_SALAD("시저샐러드", Category.APPETIZER,8000),
    T_BONE_STEAK("티본스테이크", Category.MAIN,55000),
    BBQ_RIBS("바비큐립", Category.MAIN,54000),
    SEAFOOD_PASTA("해산물파스타", Category.MAIN,35000),
    CHRISTMAS_PASTA("크리스마스파스타", Category.MAIN,25000),
    CHOCO_CAKE("초코케이크", Category.DESSERT,15000),
    ICE_CREAM("아이스크림", Category.DESSERT,5000),
    ZERO_COKE("제로콜라", Category.DRINK,3000),
    RED_WINE("레드와인", Category.DRINK,60000),
    CHAMPAGNE("샴페인", Category.DRINK,25000);
    private String name;
    private Category category;
    private int price;

    Menu(String name, Category category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}