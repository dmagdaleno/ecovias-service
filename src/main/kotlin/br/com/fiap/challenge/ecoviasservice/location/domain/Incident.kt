package br.com.fiap.challenge.ecoviasservice.location.domain

import br.com.fiap.challenge.ecoviasservice.location.functions.now
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
@Document
data class Incident (
    @Id
    val id: String?,
    val type: IncidentType,
    val title: String,
    val description: String,
    val status: IncidentStatus = IncidentStatus.PENDING,
    val user: User,
    val position: Position,
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    val timestamp: LocalDateTime?
) {
    @JsonIgnore
    fun getCompleteIncident(): Incident {
        return this.copy(
            id = UUID.randomUUID().toString(),
            status = IncidentStatus.PENDING,
            timestamp = now()
        )
    }
}