package com.cg.onlineadvapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.onlineadvapi.domain.Address;
@Repository
public class AddressRepository extends JpaRepository<Address, Integer>{

}
