package br.com.fiap.challenge.ecoviasservice.location.repository

import br.com.fiap.challenge.ecoviasservice.location.domain.Incident
import org.springframework.data.mongodb.repository.MongoRepository

interface IncidentRepository: MongoRepository<Incident, String> {
    fun findAllByUserId(userId: String): Collection<Incident>
}