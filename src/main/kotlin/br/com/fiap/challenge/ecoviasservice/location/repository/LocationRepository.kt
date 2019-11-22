package br.com.fiap.challenge.ecoviasservice.location.repository

import br.com.fiap.challenge.ecoviasservice.location.domain.Location
import org.springframework.data.mongodb.repository.MongoRepository

interface LocationRepository: MongoRepository<Location, String> {
    fun findAllByUserId(userId: String): Collection<Location>
}