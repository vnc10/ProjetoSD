package com.whoami.entity

import lombok.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "score")
data class Score(
    @Id
    var nome: String,
    var totalVitorias: Int = 0,
    var totalPontos: Int = 0
)