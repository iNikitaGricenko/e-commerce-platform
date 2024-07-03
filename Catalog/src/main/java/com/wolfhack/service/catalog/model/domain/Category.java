package com.wolfhack.service.catalog.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Category implements DomainModel, Serializable {

    private Long id;

    private String name;

    private List<Product> products;

}
