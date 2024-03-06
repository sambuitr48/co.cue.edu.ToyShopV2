package models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Detalle {
    private int detalle_id;
    private Order order;
    private Toy toy;
    private int quantity;
    private Double unit_price;
    private Double total_Price;
}
