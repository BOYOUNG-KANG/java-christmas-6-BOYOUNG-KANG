package christmas.domain;

public enum Menu {
    MUSHROOM_SOUP(Category.APPETIZER, 6000),
    TAPAS(Category.APPETIZER,5500),
    CAESAR_SALAD(Category.APPETIZER,8000),
    T_BONE_STEAK(Category.MAIN,55000),
    BBQ_RIBS(Category.MAIN,54000),
    SEAFOOD_PASTA(Category.MAIN,35000),
    CHRISTMAS_PASTA(Category.MAIN,25000),
    CHOCO_CAKE(Category.DESSERT,15000),
    ICE_CREAM(Category.DESSERT,5000),
    ZERO_COKE(Category.DRINK,3000),
    RED_WINE(Category.DRINK,60000),
    CHAMPAGNE(Category.DRINK,25000);
    private Category category;
    private int price;

    Menu(Category category, int price) {
        this.category = category;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}