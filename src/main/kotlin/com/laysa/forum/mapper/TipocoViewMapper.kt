package com.laysa.forum.mapper

import com.laysa.forum.dto.TopicoView
import com.laysa.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TipocoViewMapper: Mapper<Topico, TopicoView> {
    override fun map(topico: Topico): TopicoView {
        return TopicoView(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            dataCriacao = topico.dataCriacao,
            status = topico.status
        )
    }
}