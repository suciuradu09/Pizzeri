package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest_F02 {
    private static List<Payment> payments;
    private static PizzaService pizzaService;

    @BeforeAll
    @Timeout(1000)
    public static void setUp() {
        MenuRepository repoMenu = new MenuRepository();
        PaymentRepository payRepo = new PaymentRepository();
        pizzaService = new PizzaService(repoMenu, payRepo);
        pizzaService.getPayments().clear();

        payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.Card, 12.33));
        payments.add(new Payment(1, null, 15));
    }

    @AfterEach
    public void clear() {
        pizzaService.getPayments().clear();
    }

    @Test
    public void TC1() {
        Payment p = payments.get(0);
        pizzaService.addPayment(p.getTableNumber(), p.getType(), p.getAmount());

        assertEquals(pizzaService.getTotalAmount(PaymentType.Card), 12.33);
    }

    @Test
    public void TC2() {
        Payment p = payments.get(1);
        pizzaService.addPayment(p.getTableNumber(), p.getType(), p.getAmount());

        try {
            assertEquals(pizzaService.getTotalAmount(PaymentType.Card), 0);
            assert (false);
        } catch (NullPointerException | IllegalArgumentException e) {
            assert (true);
        }
        try {
            assertEquals(pizzaService.getTotalAmount(PaymentType.Cash), 0);
            assert (false);
        } catch (NullPointerException | IllegalArgumentException e) {
            assert (true);
        }
    }
}