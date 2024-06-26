package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import static org.mockito.Mockito.*;
import pizzashop.validators.VPayment;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceIntegrationTest {
    private static PaymentRepository payRepo;
    private static MenuRepository menuRepo;
    private static PizzaService service;
    private static VPayment validator;
    private static Payment payment;

    @BeforeAll
    public static void setUp(){
        payRepo = new PaymentRepository();
        payRepo.getAll().clear();
        menuRepo = new MenuRepository();
        service = new PizzaService(menuRepo, payRepo);
        validator = new VPayment();
        payment = mock(Payment.class);
    }

    @AfterEach
    public void clear(){
        payRepo.getAll().clear();
    }

    @Test
    public void add_step2() {
        when(payment.getTableNumber()).thenReturn(4);
        when(payment.getType()).thenReturn(PaymentType.Card);
        when(payment.getAmount()).thenReturn(10.5);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());

        assertEquals(service.getPayments().get(0).getAmount(), payment.getAmount());
        assertEquals(service.getPayments().get(0).getTableNumber(), payment.getTableNumber());
        assertEquals(service.getPayments().get(0).getType(), payment.getType());
    }

    @Test
    public void getAll_step2() {
        when(payment.getTableNumber()).thenReturn(4);
        when(payment.getType()).thenReturn(PaymentType.Card);
        when(payment.getAmount()).thenReturn(10.5);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());

        double totalAmountCard = service.getTotalAmount(PaymentType.Card);

        assertEquals(totalAmountCard,payment.getAmount());
    }

    @Test
    public void add_step3() {
        Payment payment = new Payment(1,PaymentType.Card,21.5);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());

        int tableNr = service.getPayments().get(0).getTableNumber();
        assertEquals(tableNr,payment.getTableNumber());
    }

    @Test
    public void getAll_step3() {
        Payment payment=new Payment(1,PaymentType.Card,12.0);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());


        double totalAmountCash = service.getTotalAmount(PaymentType.Cash);
        double totalAmountCard = service.getTotalAmount(PaymentType.Card);

        assertEquals(totalAmountCard,payment.getAmount());
    }
}