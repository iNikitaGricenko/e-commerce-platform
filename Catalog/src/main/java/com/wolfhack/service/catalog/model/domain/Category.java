package com.wolfhack.service.catalog.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import com.wolfhack.service.catalog.model.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
public class Category implements DomainModel, Serializable {

    private Long id;

    private String name;

    private List<ProductEntity> products;

}
