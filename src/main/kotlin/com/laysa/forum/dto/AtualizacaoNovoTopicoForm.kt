package com.laysa.forum.dto

import jakarta.validation.constraints.NotEmpty

data class AtualizacaoNovoTopicoForm (
    @field:NotEmpty
    val id: Long,
    @field:NotEmpty
    val titulo: String,
    @field:NotEmpty
    val mensagem: String,
)