package services;

import mapping.dto.DetailDTO;
import mapping.dto.ToyDTO;

import java.util.List;

public interface Service {
    void addToy(ToyDTO storeDto) throws Exception;
    List<ToyDTO> listToys();
    //Hacer el showByType
    Integer totalToys();
    Integer totalPrice();
}
