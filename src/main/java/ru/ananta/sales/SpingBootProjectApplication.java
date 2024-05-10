package ru.ananta.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpingBootProjectApplication implements CommandLineRunner {

    private static final BigDecimal AMOUNT_TO_PRINT = new BigDecimal(100);

    @Autowired
    private SalesJdbcRepository salesJdbcRepository;
    @Autowired
    private SalesJpaRepository salesJpaRepository;

    public static void main(String[] args) {
        args = new String[]{"2"};
        SpringApplication.run(SpingBootProjectApplication.class, args);
    }

    @Override
    public void run(String... args) {
        addSaleJdbc();
        printJdbc(args);
        printJdbcByAmount(AMOUNT_TO_PRINT);

        addSaleJpa();
        printJPA(args);
        printJpaByAmount(AMOUNT_TO_PRINT);
    }

    private void addSaleJpa() {
        salesJpaRepository.findAll(); //закружаю все записи для правильной генерации id
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        Sale sale = new Sale(BigDecimal.valueOf(Math.random() * 200), (int) (Math.random() * 100), date, date);
        salesJpaRepository.save(sale);
    }

    private void addSaleJdbc() {
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        Sale sale = new Sale(BigDecimal.valueOf(Math.random() * 200), (int) (Math.random() * 100), date, date);
        salesJdbcRepository.insert(sale);
    }

    private void printJdbc(String[] args) {
        System.out.println("-----JDBC-----");
        printCount(salesJdbcRepository.count());
        if (args.length > 0) {
            int saleId = Integer.parseInt(args[0]);
            Optional<Sale> sale = salesJdbcRepository.findById(saleId);
            sale.ifPresent(value -> printSale(value, saleId));
        }
    }

    public void printJPA(String[] args) {
        System.out.println("-----JPA-----");
        printCount(salesJpaRepository.count());
        if (args.length > 0) {
            int saleId = Integer.parseInt(args[0]);
            Optional<Sale> sale = salesJpaRepository.findById(saleId);
            sale.ifPresent(value -> printSale(value, saleId));
        }
    }

    private static void printSale(Sale sale, int saleId) {
        if (sale != null) {
            System.out.println("Информация о продаже с ID " + saleId + ": " + sale);
        } else {
            System.out.println("Продажа с ID " + saleId + " не найдена.");
        }
    }

    private void printJdbcByAmount(BigDecimal amount) {
        System.out.println("----- Информация по продажам больше " + amount + " -----");
        List<Sale> jpaSales =  salesJdbcRepository.findSalesWithAmountGreaterThan(amount);
        jpaSales.forEach(sale -> System.out.println(sale));
    }

    private void printJpaByAmount(BigDecimal amount) {
        System.out.println("----- Информация по продажам больше " + amount + " -----");
        List<Sale> jpaSales = salesJpaRepository.findByAmountGreaterThan(amount);
        jpaSales.forEach(sale -> System.out.println(sale));
    }

    private void printCount(long count) {
        System.out.println("Общее количество продаж: " + count);
    }
}
