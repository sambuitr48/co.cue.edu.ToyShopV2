package models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Toy {
    private int toy_id;
    private String toy_name;
    private int toy_category;
    private Double toy_price;
    private int toy_stock;
}
