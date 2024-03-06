package models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Order {
    private int order_id;
    private Clients client;
    private Employees employee;
    private LocalDateTime purchase_date; //Revisar
}
