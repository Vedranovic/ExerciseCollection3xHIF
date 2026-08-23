package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
    @NonNull
    private Customer customer;
    private Long id;
    @NonNull
    private LocalDate orderDate;
}
