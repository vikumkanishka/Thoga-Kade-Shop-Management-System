package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDto {
    private String itemId;
    private String itemName;
    private Double price;
    private String description;
    private String category;
    private String packSize;
    private Integer quantityOnHand;
}
