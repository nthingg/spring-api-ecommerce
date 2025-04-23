package com.thingg.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", unique = true, nullable = false)
    private String itemId;

    private String name;

    private BigDecimal price;

    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
}
