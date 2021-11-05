package com.example.paymentbackendtemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import template.model.RequestMessage;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<RequestMessage, Long> {
}
