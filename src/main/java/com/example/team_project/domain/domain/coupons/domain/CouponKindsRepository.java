package com.example.team_project.domain.domain.coupons.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponKindsRepository extends JpaRepository<CouponKinds, String> {
    Optional<CouponKinds> findByName(String name);
}
