package com.laysa.forum.service

import com.laysa.forum.dto.AtualizacaoNovoTopicoForm
import com.laysa.forum.dto.NovoTopicoForm
import com.laysa.forum.dto.TopicoView
import com.laysa.forum.exception.NotFoundException
import com.laysa.forum.mapper.TipocoViewMapper
import com.laysa.forum.mapper.TopicoFormMapper
import com.laysa.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private var topicoViewMapper: TipocoViewMapper,
    private var topicoFormMapper: TopicoFormMapper,
    private var notFoundMessage: String = "Topico não encontrado!"
) {
    fun listar(): List<TopicoView> {
        return topicos.stream().map { topico ->
            topicoViewMapper.map(topico)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow { NotFoundException(notFoundMessage) }
        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoNovoTopicoForm): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == form.id
        }.findFirst().orElseThrow { NotFoundException(notFoundMessage) }
        val topicoAtt = Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            autor = topico.autor,
            curso = topico.curso,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao,
        )
        topicos = topicos.minus(topico).plus(topicoAtt)
        return topicoViewMapper.map(topicoAtt)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow { NotFoundException(notFoundMessage) }
        topicos = topicos.minus(topico)
    }
}