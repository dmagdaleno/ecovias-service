package br.com.fiap.challenge.ecoviasservice.location.controller

import br.com.fiap.challenge.ecoviasservice.location.domain.Incident
import br.com.fiap.challenge.ecoviasservice.location.service.IncidentService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@Controller
@RequestMapping("/incidents")
class IncidentController(private val service: IncidentService) {

    @PostMapping
    fun add(@RequestBody incident: Incident): ResponseEntity<Incident> {
        return ResponseEntity
            .created(service.add(incident).buildUri())
            .build()
    }

    private fun buildUri(incident: Incident): URI =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(incident.id)
            .toUri()

    @GetMapping("/{id}")
    fun getIncident(@PathVariable id: String): ResponseEntity<Incident> {
        val incident = service.findById(id)
        return if (incident != null)
            ResponseEntity.ok(incident)
        else
            ResponseEntity.notFound().build()
    }

    @GetMapping()
    fun getIncidents() = ResponseEntity.ok(service.findAll())

    @GetMapping("/user/{userId}")
    fun getIncidentsByUserId(@PathVariable userId: String): ResponseEntity<Collection<Incident>> {
        return ResponseEntity.ok(service.findAllByUserId(userId))
    }
}

private fun Incident.buildUri(): URI =
    ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(this.id)
        .toUri()