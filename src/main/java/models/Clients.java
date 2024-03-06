package models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Clients {
    private int client_cedula;
    private String client_name;
    private Date client_age; //Revisar
}
