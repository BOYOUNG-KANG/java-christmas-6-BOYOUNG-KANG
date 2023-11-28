package christmas.dto;

import christmas.domain.Event;
import java.util.Arrays;
import java.util.EnumMap;

public record EventResultsDto(EnumMap<Event, Integer> eventResults) {
    public int totalDiscount(){
        return Arrays.stream(Event.values())
                .mapToInt(event -> eventResults.getOrDefault(event, 0))
                .sum();
    }
}
