package com.github.rluisb.session.repository;

import com.github.rluisb.session.domain.entity.SessionEntity;
import com.github.rluisb.session.domain.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends MongoRepository<SessionEntity, String> {
    Optional<SessionEntity> findByAgenda_Id(String agendaId);
}
