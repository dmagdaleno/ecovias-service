package br.com.fiap.challenge.ecoviasservice.location.service

import br.com.fiap.challenge.ecoviasservice.location.domain.Location
import br.com.fiap.challenge.ecoviasservice.location.repository.LocationRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class LocationService(private val repository: LocationRepository) {

    fun add(location: Location): Location {
        return repository.save(location.getCompleteLocation())
    }

    fun findById(id: String): Location? {
        return repository.findById(id).orElse(null)
    }

    fun findAll(): Collection<Location> = repository.findAll()

    fun findAllByUserId(userId: String): Collection<Location> = repository.findAllByUserId(userId)
}