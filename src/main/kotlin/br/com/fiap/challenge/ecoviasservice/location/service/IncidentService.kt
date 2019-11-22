package br.com.fiap.challenge.ecoviasservice.location.service

import br.com.fiap.challenge.ecoviasservice.location.domain.Incident
import br.com.fiap.challenge.ecoviasservice.location.repository.IncidentRepository
import org.springframework.stereotype.Service

@Service
class IncidentService(private val repository: IncidentRepository) {

    fun add(incident: Incident): Incident {
        return repository.save(incident.getCompleteIncident())
    }

    fun findById(id: String): Incident? {
        return repository.findById(id).orElse(null)
    }

    fun findAll(): Collection<Incident> = repository.findAll()

    fun findAllByUserId(userId: String): Collection<Incident> = repository.findAllByUserId(userId)
}