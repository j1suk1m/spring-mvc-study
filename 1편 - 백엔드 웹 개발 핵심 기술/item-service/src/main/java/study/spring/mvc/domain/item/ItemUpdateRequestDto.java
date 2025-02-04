package study.spring.mvc.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemUpdateRequestDto {
    private String itemName;
    private Integer price;
    private Integer quantity;
}