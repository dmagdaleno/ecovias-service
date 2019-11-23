package br.com.fiap.challenge.ecoviasservice.location.controller

import br.com.fiap.challenge.ecoviasservice.location.domain.Incident
import br.com.fiap.challenge.ecoviasservice.location.service.IncidentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@CrossOrigin(origins = ["*"])
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
        return returnIncident(service.findById(id))
    }

    private fun returnIncident(incident: Incident?): ResponseEntity<Incident> {
        return if (incident != null)
            ResponseEntity.ok(incident)
        else
            ResponseEntity.notFound().build()
    }

    @GetMapping
    fun getIncidents() = ResponseEntity.ok(service.findAll())

    @GetMapping("/pending")
    fun getPendingIncidents() = ResponseEntity.ok(service.findAllPending())

    @GetMapping("/user/{userId}")
    fun getIncidentsByUserId(@PathVariable userId: String): ResponseEntity<Collection<Incident>> {
        return ResponseEntity.ok(service.findAllByUserId(userId))
    }

    @PutMapping("/{id}")
    fun resolveIncident(@PathVariable id: String): ResponseEntity<Incident> {
        return returnIncident(service.resolveIncident(id))
    }
}

private fun Incident.buildUri(): URI =
    ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(this.id)
        .toUri()