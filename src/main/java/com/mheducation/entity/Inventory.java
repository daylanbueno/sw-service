package com.mheducation.entity;

import com.mheducation.enums.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private InventoryType tipo;
    private String url;
    private int count;
}
