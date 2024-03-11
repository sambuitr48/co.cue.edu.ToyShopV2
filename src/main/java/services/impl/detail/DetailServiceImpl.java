package services.impl.detail;

import models.Detail;
import repository.Repository;
import repository.impl.Detail.DetailRepositoryJDBCImpl;
import services.Service;

import java.util.List;

public class DetailServiceImpl implements Service<Detail> {
    private Repository<Detail> detailRepository;

    public DetailServiceImpl() {
        this.detailRepository = new DetailRepositoryJDBCImpl();
    }

    @Override
    public void add(Detail detail) {

    }

    @Override
    public List<Detail> listAll() {
        return null;
    }
}
