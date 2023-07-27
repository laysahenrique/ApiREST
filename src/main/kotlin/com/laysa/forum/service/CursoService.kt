package com.laysa.forum.service

import com.laysa.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(
    var cursos: List<Curso>
) {
    init {
        val curso = Curso(
            id = 1,
            nome = "Curso de merda",
            categoria = "Sla"
        )
        cursos = mutableListOf(curso)
    }

    fun buscarPorId(id: Long): Curso{
      return cursos.stream().filter { c ->
          c.id == id
      }.findFirst().get()
    }
}
