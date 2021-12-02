package com.example.paymentbackendtemplate.repository;

import com.example.backendtemplate.model.RequestMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<RequestMessage, UUID> {

    List<RequestMessage> findAllByType(String type);
}
