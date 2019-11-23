package br.com.fiap.challenge.ecoviasservice.location.functions

import java.time.LocalDateTime
import java.time.ZoneId

fun now(): LocalDateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))