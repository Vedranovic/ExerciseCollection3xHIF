package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @NonNull
    private Category category;
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
}
