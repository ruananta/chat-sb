package ru.ananta.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class SalesJdbcRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static class SaleRowMapper implements RowMapper<Sale> {
        @Override
        public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sale sale = new Sale();
            sale.setId(rs.getInt("id"));
            sale.setAmount(rs.getBigDecimal("amount"));
            sale.setProductId(rs.getInt("product_id"));
            sale.setArrivalDate(rs.getDate("arrival_date"));
            sale.setSaleDate(rs.getDate("sale_date"));
            return sale;
        }
    }

    public List<Sale> findAll() {
        return jdbcTemplate.query("select * from sales", new SaleRowMapper());
    }

    public Optional<Sale> findById(int id) {
        try {
            Sale sale = jdbcTemplate.queryForObject(
                    "select * from sales where id = ?",
                    new SaleRowMapper(),
                    id
            );
            return Optional.ofNullable(sale);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from sales where id=?", id);
    }

    public int insert(Sale sale) {
        return jdbcTemplate.update("insert into sales (id, amount, product_id, arrival_date, sale_date) "
                        + "values(?, ?, ?, ?, ?)",
                sale.getId(), sale.getAmount(), sale.getProductId(), sale.getArrivalDate(), sale.getSaleDate());
    }

    public int count() {
        Integer i = jdbcTemplate.queryForObject("select count(*) from sales", Integer.class);
        return i != null ? i : 0;
    }

    public List<Sale> findSalesWithAmountGreaterThan(BigDecimal amount) {
        return jdbcTemplate.query(
                "SELECT * FROM sales WHERE amount > ?",
                new SaleRowMapper(),
                amount
        );
    }
}
