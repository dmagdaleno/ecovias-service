package br.com.fiap.challenge.ecoviasservice.location.service

import br.com.fiap.challenge.ecoviasservice.location.domain.Incident
import br.com.fiap.challenge.ecoviasservice.location.domain.IncidentStatus
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

    fun findAllPending(): Collection<Incident> = repository.findAllByStatus(IncidentStatus.PENDING)

    fun findAllByUserId(userId: String): Collection<Incident> =
        repository.findAllByUserIdAndStatus(userId, IncidentStatus.PENDING)

    fun resolveIncident(id: String): Incident? {
        val incident = findById(id) ?: return null
        return repository.save(incident.copy(status = IncidentStatus.RESOLVED))
    }
}