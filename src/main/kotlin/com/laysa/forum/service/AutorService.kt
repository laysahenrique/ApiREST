package com.laysa.forum.service

import com.laysa.forum.model.Autor
import org.springframework.stereotype.Service

@Service
class AutorService(
    var autores: List<Autor>
) {
    init {
        val autor = Autor(
            id = 1,
            nome = "Curso de merda",
            email = "Sla"
        )
        autores = mutableListOf(autor)
    }

    fun buscarPorId(id: Long): Autor {
        return autores.stream().filter { a ->
            a.id == id
        }.findFirst().get()
    }
}
