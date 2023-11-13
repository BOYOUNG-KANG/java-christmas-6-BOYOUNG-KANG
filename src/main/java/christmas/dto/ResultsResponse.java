package christmas.dto;

import christmas.domain.Badge;
import christmas.domain.Results;
import java.util.EnumMap;

public record ResultsResponse(int payment, EnumMap<Results, Integer> results, Badge badge) {
    public int totalDiscount(){
        return results.get(Results.CHRISTMAS_DISCOUNT) + results.get(Results.WEEKDAY_DISCOUNT) +
                results.get(Results.WEEKEND_DISCOUNT) + results.get(Results.SPECIAL_DISCOUNT);
    }
}
