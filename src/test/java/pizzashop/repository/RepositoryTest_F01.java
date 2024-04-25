package pizzashop.repository;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryTest_F01 {
    private static List<Payment> payments;
    private static PaymentRepository paymentRepository;

    @BeforeAll
    @Timeout(1000)
    static void setUp() {
        payments = new ArrayList<>();
        payments.add(new Payment(1, PaymentType.Card, 10));
        payments.add(new Payment(1, PaymentType.Card, 10));
        payments.add(new Payment(-1,PaymentType.Card, 10));
        payments.add(new Payment(1, PaymentType.Card, -10));
        payments.add(new Payment(2, PaymentType.Card, 10));
        payments.add(new Payment(7, PaymentType.Card, 10));
        payments.add(new Payment(0, PaymentType.Card, 10));
        payments.add(new Payment(9, PaymentType.Card, 0));

        paymentRepository = new PaymentRepository();
    }

    @AfterAll
    @Timeout(1000)
    static void tearDown() {
        paymentRepository.writeAll();
    }


    @Test
    void TC1() {
        Payment payment = payments.get(0);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().get(paymentRepository.getAll().size()-1), payment);
        paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
    }


    @Test
    void TC2() {
        Payment payment = payments.get(1);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().get(paymentRepository.getAll().size()-1), payment);
        paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
    }


    @Test
    void TC3() {
        Payment payment = payments.get(2);
        paymentRepository.add(payment);
        if(paymentRepository.getAll().get(paymentRepository.getAll().size()-1) == payment){
            paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
            assert(true);
        } else {
            assert(false);
        }
    }


    @Test
    void TC4() {
        Payment payment = payments.get(3);
        paymentRepository.add(payment);
        if(paymentRepository.getAll().get(paymentRepository.getAll().size()-1) == payment){
            paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
            assert(true);
        } else {
            assert(false);
        }
    }


    @Test
    void TC5() {
        Payment payment = payments.get(4);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().get(paymentRepository.getAll().size()-1), payment);
        paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
    }


    @Test
    void TC6() {
        Payment payment = payments.get(5);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().get(paymentRepository.getAll().size()-1), payment);
        paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
    }


    @Test
    void TC7() {
        Payment payment = payments.get(6);
        paymentRepository.add(payment);
        if(paymentRepository.getAll().get(paymentRepository.getAll().size()-1) == payment){
            paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
            assert(true);
        } else {
            assert(false);
        }
    }

    @Test
    void TC8() {
        Payment payment = payments.get(7);
        paymentRepository.add(payment);
        if(paymentRepository.getAll().get(paymentRepository.getAll().size()-1) == payment){
            paymentRepository.getAll().remove(paymentRepository.getAll().size()-1);
            assert(true);
        } else {
            assert(false);
        }
    }

    @Disabled
    @Test
    void TC9() {
        assert(false);
    }
}
