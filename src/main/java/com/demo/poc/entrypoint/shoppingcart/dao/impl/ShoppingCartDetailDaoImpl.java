package com.demo.poc.entrypoint.shoppingcart.dao.impl;

import static com.demo.poc.commons.constants.Constant.PURPLE;
import static com.demo.poc.commons.constants.Constant.RESET;
import static com.demo.poc.commons.sql.SQLResourceHelper.closeResources;

import com.demo.poc.commons.sql.MySQLConnection;
import com.demo.poc.entrypoint.shoppingcart.dao.ShoppingCartDetailDao;
import com.demo.poc.entrypoint.shoppingcart.entity.ShoppingCartDetailEntity;
import com.demo.poc.entrypoint.shoppingcart.mapper.ShoppingCartDetailMapper;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ShoppingCartDetailDaoImpl implements ShoppingCartDetailDao {

  @Override
  public List<ShoppingCartDetailEntity> findShoppingCartDetailByShoppingCardId(Long shoppingCartId) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      String sqlStatement = "SELECT product_id, shopping_cart_id, quantity FROM products_shopping_carts WHERE shopping_cart_id = ?";
      statement = connection.prepareStatement(sqlStatement);
      statement.setLong(1, shoppingCartId);
      result = statement.executeQuery();
      log.info(PURPLE + sqlStatement + RESET);

      List<ShoppingCartDetailEntity> summaryList = new ArrayList<>();
      while (result.next()) {
        ShoppingCartDetailEntity summary = ShoppingCartDetailMapper.toEntity(result);
        summaryList.add(summary);
      }
      return summaryList;
    } catch (SQLException exception) {
      throw new RuntimeException("Error to find elements: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

}
