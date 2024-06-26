package com.nhnacademy.store99.bookstore.address.entity;

import com.nhnacademy.store99.bookstore.user.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Address Entity
 *
 * @author seunggyu-kim
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "addresses", schema = "staff99_bookstore")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long id;

    @Column(name = "address_general", nullable = false)
    private String addressGeneral;

    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

    @Column(name = "address_alias", length = 50)
    private String addressAlias;

    @Column(name = "address_code", nullable = false)
    private Integer addressCode;

    @Column(name = "is_default_address", nullable = false)
    private Boolean isDefaultAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setDefaultAddress(boolean isDefault) {
        this.isDefaultAddress = isDefault;
    }

    public void updateAddress(String addressGeneral, String addressDetail, String addressAlias, Integer addressCode) {
        this.addressGeneral = addressGeneral;
        this.addressDetail = addressDetail;
        this.addressAlias = addressAlias;
        this.addressCode = addressCode;
    }

}