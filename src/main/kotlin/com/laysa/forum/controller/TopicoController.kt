package com.laysa.forum.controller

import com.laysa.forum.dto.AtualizacaoNovoTopicoForm
import com.laysa.forum.dto.NovoTopicoForm
import com.laysa.forum.dto.TopicoView
import com.laysa.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/topicos")
class TopicoController(
    private val service: TopicoService,
) {

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView{
       return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid topico: NovoTopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView>{
        val topicoView = service.cadastrar(topico)
        val uri = uriBuilder.path("/topico/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView)
    }

    @PutMapping
    fun atualizar(@RequestBody @Valid topico: AtualizacaoNovoTopicoForm): ResponseEntity<TopicoView>{
        return ResponseEntity.ok(service.atualizar(topico))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long){
        service.deletar(id)
    }
}