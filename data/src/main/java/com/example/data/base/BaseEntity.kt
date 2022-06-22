package com.example.data.base

interface BaseEntity<out T> {
    fun toDomainModel(): T
}