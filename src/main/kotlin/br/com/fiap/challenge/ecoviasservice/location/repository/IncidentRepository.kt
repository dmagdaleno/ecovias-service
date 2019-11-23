package br.com.fiap.challenge.ecoviasservice.location.repository

import br.com.fiap.challenge.ecoviasservice.location.domain.Incident
import br.com.fiap.challenge.ecoviasservice.location.domain.IncidentStatus
import org.springframework.data.mongodb.repository.MongoRepository

interface IncidentRepository: MongoRepository<Incident, String> {
    fun findAllByStatus(status: IncidentStatus): Collection<Incident>

    fun findAllByUserIdAndStatus(userId: String, status: IncidentStatus): Collection<Incident>
}