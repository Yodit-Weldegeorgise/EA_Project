package edu.miu.cs544.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.cs544.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
