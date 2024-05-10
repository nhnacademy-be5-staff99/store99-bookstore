package com.nhnacademy.store99.bookstore.point_policies.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PointPolicyRepository extends JpaRepository<PointPolicy, Long> {
    PointPolicy findSavingPointByPolicyType(String policyType);
}
