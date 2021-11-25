package com.example.paymentbackendtemplate.implementation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import template.model.RequestMessage;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder(toBuilder = true, setterPrefix = "with")
@Table(name = "payment_test")
public class RequestMessageTest extends RequestMessage {

    public RequestMessageTest() {
    }
}
