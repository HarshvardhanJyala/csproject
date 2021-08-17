package com.h2.example.h2db.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.h2.example.h2db.entity.LogFileEntity;

@Repository
@Transactional
public class EventJPARepository {

	@PersistenceContext
	EntityManager entityManager;

	public LogFileEntity findById(long l) {
		return entityManager.find(LogFileEntity.class, l);
	}

	public LogFileEntity update(LogFileEntity logFileEntity) {
		return entityManager.merge(logFileEntity);

	}

	public LogFileEntity insert(LogFileEntity logFileEntity) {
		return entityManager.merge(logFileEntity);

	}

}
