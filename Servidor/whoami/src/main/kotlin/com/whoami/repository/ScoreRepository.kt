package com.whoami.repository

import com.whoami.entity.Score
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScoreRepository: JpaRepository<Score, String> {
    fun save(score: Score?)
    fun findAllByOrderByTotalVitorias(): List<Score>
}