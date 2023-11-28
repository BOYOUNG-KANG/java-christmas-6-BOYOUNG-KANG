package christmas.domain;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NOTHING("없음", 0);
    private String name;
    private int discount;

    Badge(String name, int discount) {
        this.name = name;
        this.discount = discount;
    }
    public static Badge get(int totalDiscount){
        if (totalDiscount >= SANTA.discount) return SANTA;
        if (totalDiscount >= TREE.discount) return TREE;
        if (totalDiscount >= STAR.discount) return STAR;
        return NOTHING;
    }

    public String getName() {
        return name;
    }
}
