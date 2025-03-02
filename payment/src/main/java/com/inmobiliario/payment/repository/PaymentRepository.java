package com.inmobiliario.payment.repository;

import com.inmobiliario.payment.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();

    public List<Payment> findAll() {
        return payments;
    }

    public Optional<Payment> findById(Long id) {
        return payments.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void save(Payment payment) {
        payments.add(payment);
    }

    public void update(Payment payment) {
        deleteById(payment.getId());
        save(payment);
    }

    public void deleteById(Long id) {
        payments.removeIf(p -> p.getId().equals(id));
    }
}
