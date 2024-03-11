package repository.impl.toy;

import connection.DataBaseConnection;
import models.Toy;
import models.Toy;
import models.Toy;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//Aquí van los métodos en donde se usa SQL
public class ToyRepositoryJDBCImpl implements Repository<Toy> {
    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }
    private Toy createToy(ResultSet resultSet) throws SQLException {
        Toy toy = new Toy();
        toy.setToy_id(resultSet.getInt("toy_id"));
        toy.setToy_name(resultSet.getNString("toy_name"));
        toy.setToy_category(resultSet.getString("toy_category"));
        toy.setToy_price(resultSet.getDouble("toy_price"));
        toy.setToy_stock(resultSet.getInt("toy_stock"));
        return toy;
    }

    @Override
    public List<Toy> list() {
        List<Toy> toyList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     """ 
                         /*/Codigo sql 
                         """
             )) {
            while (resultSet.next()){
                Toy toy = createToy(resultSet);
                toyList.add(toy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toyList;
    }
    @Override
    public Toy byId(int id) {
        Toy toy = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            /*/codigo sql
                            """
                )
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                toy = createToy(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toy;
    }

    @Override
    public void save(Toy toy) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            /*/ codigo sql
                            """
                )
        ) {
            preparedStatement.setInt(1, toy.getToy_id());
            preparedStatement.setString(2, toy.getToy_name());
            preparedStatement.setString(3, toy.getToy_category());
            preparedStatement.setDouble(4, toy.getToy_price());
            preparedStatement.setInt(5, toy.getToy_stock());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            /*/codigo sql
                            """
                )) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Toy toy) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            /*/ codigo 
                            """
                )
        ) {

            preparedStatement.setInt(1, toy.getToy_id());
            preparedStatement.setString(2, toy.getToy_name());
            preparedStatement.setString(3, toy.getToy_category());
            preparedStatement.setDouble(4, toy.getToy_price());
            preparedStatement.setInt(5, toy.getToy_stock());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
