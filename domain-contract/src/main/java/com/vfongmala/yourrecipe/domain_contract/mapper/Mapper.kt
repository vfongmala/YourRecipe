package com.vfongmala.yourrecipe.domain_contract.mapper

interface Mapper<T, R> {
    fun map(input: T): R
}