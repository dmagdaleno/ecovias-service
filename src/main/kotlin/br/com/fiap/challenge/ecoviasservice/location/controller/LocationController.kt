package br.com.fiap.challenge.ecoviasservice.location.controller

import br.com.fiap.challenge.ecoviasservice.location.domain.Location
import br.com.fiap.challenge.ecoviasservice.location.service.LocationService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URI

@Controller
@RequestMapping("/locations")
class LocationController(private val service: LocationService) {

    @PostMapping
    fun add(@RequestBody location: Location): ResponseEntity<Location> {
        return ResponseEntity
            .created(service.add(location).buildUri())
            .build()
    }

    @GetMapping("/{id}")
    fun getLocation(@PathVariable id: String): ResponseEntity<Location> {
        val location = service.findById(id)
        return if (location != null)
            ResponseEntity.ok(location)
        else
            ResponseEntity.notFound().build()
    }

    @GetMapping()
    fun getLocations() = ResponseEntity.ok(service.findAll())

    @GetMapping("/user/{userId}")
    fun getLocationsByUserId(@PathVariable userId: String): ResponseEntity<Collection<Location>> {
        return ResponseEntity.ok(service.findAllByUserId(userId))
    }
}
private fun Location.buildUri(): URI =
    org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(this.id)
        .toUri()