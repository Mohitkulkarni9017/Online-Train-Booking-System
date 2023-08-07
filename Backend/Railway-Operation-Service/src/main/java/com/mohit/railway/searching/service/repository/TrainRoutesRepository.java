package com.mohit.railway.searching.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.railway.searching.service.entity.TrainRoutes;


@Repository
public interface TrainRoutesRepository extends JpaRepository<TrainRoutes,Integer>{

}
