package repository.impl.Detail;

import connection.DataBaseConnection;
import models.*;
import models.Detail;
import models.Detail;
import repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DetailRepositoryJDBCImpl implements Repository<Detail> {
    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }
    private Detail createDetail(ResultSet resultSet) throws SQLException {
        Detail detail = new Detail();
        detail.setDetail_id(resultSet.getInt("detalle_id"));
        Order order = new Order(); //revisar ya qe no se si se puede instanciar de esta forma el objeto para hacer la relación de objetos
        order.setOrder_id(resultSet.getInt("order_id"));
        detail.setOrder(order);
        detail.setToy(new Toy(
                resultSet.getInt("toy_id"),
                resultSet.getString("toy_name"),
                resultSet.getString("toy_category"),
                resultSet.getDouble("toy_price"),
                resultSet.getInt("toy_stock")
        ));
        detail.setQuantity(resultSet.getInt("quantity"));
        detail.setUnit_price(resultSet.getDouble("unit_price"));
        detail.setTotal_Price(resultSet.getDouble("total_price"));
        return detail;
    }

    @Override
    public List<Detail> list() {
        List<Detail> detailList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     """ 
                         /*/Codigo sql 
                         """
             )) {
            while (resultSet.next()){
                Detail order = createDetail(resultSet);
                detailList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailList;
    }

    @Override
    public Detail byId(int id) {
        Detail detail = null;
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
                detail = createDetail(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return detail;
    }

    @Override
    public void save(Detail detail) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            /*/ codigo sql
                            """
                )
        ) {
            preparedStatement.setInt(1, detail.getDetail_id());
            preparedStatement.setInt(2, detail.getOrder().getOrder_id());
            preparedStatement.setString(3, detail.getToy().getToy_name());
            preparedStatement.setInt(4, detail.getQuantity());
            preparedStatement.setDouble(5, detail.getUnit_price());
            preparedStatement.setDouble(6, detail.getTotal_Price());
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
    public void update(Detail detail) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(
                        """
                            /*/ codigo
                            """
                )) {
            preparedStatement.setInt(1, detail.getDetail_id());
            preparedStatement.setInt(2, detail.getOrder().getOrder_id());
            preparedStatement.setString(3, detail.getToy().getToy_name());
            preparedStatement.setInt(4, detail.getQuantity());
            preparedStatement.setDouble(5, detail.getUnit_price());
            preparedStatement.setDouble(6, detail.getTotal_Price());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
