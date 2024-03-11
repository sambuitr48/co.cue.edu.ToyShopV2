package services.impl.toy;


import models.Toy;
import repository.Repository;
import repository.impl.toy.ToyRepositoryJDBCImpl;
import services.Service;

import java.util.List;

public class ToyServiceImpl implements Service<Toy> {
    private Repository<Toy> toyRepository;

    public ToyServiceImpl() {
        this.toyRepository = new ToyRepositoryJDBCImpl();
    }

    @Override
    public void add(Toy toy) {

    }

    @Override
    public List<Toy> listAll() {
        return null;
    }
}
