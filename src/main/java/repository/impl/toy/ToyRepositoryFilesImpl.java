package repository.impl.toy;

import models.Toy;
import repository.Repository;

import java.util.List;
//Aquí van los métodos en donde se usan los archivos
public class ToyRepositoryFilesImpl implements Repository<Toy> {

    @Override
    public List<Toy> list() {
        return null;
    }

    @Override
    public Toy byId(int id) {
        return null;
    }

    @Override
    public void save(Toy toy) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Toy toy) {

    }
}
