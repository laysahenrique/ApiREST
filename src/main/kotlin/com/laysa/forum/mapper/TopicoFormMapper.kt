package com.laysa.forum.mapper

import com.laysa.forum.dto.NovoTopicoForm
import com.laysa.forum.model.Topico
import com.laysa.forum.service.AutorService
import com.laysa.forum.service.CursoService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private var cursoService: CursoService,
    private var autorService: AutorService,
) : Mapper<NovoTopicoForm, Topico> {
    override fun map(topico: NovoTopicoForm): Topico {
        return Topico(
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            curso = cursoService.buscarPorId(topico.idCurso),
            autor = autorService.buscarPorId(topico.idAutor)
        )
    }
}
