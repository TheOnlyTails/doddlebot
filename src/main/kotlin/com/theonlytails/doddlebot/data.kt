package com.theonlytails.doddlebot

import com.theonlytails.doddlebot.Role.RoleType.*

data class Role(val name: String, val type: RoleType, val id: Long) {
    enum class RoleType(val title: String) {
        Pronouns("Pronoun"),
        Identity("Identity"),
        Sexuality("Sexuality"),
        Romantic("Romantic"),
        Color("Color"),
        Mentionable("Mentionable"),
        Extra("Extra"),
    }
}

const val doddlecordId = 337013993669656586L

val roleList = listOf(
    Role("He/Him", Pronouns, 399633081155452939),
    Role("She/Her", Pronouns, 399633116739665940),
    Role("They/Them", Pronouns, 399633143675486219),
    Role("Xe/Xem", Pronouns, 815628065727447040),

    Role("Female", Identity, 399633209417138186),
    Role("Male", Identity, 399633179314749440),
    Role("Non Binary", Identity, 399633884993683468),
    Role("Agender", Identity, 407926177534312449),
    Role("Queer", Identity, 790320965497651251),
    Role("Gender Fluid", Identity, 399706766914486282),
    Role("Trans", Identity, 399633051111653376),

    Role("Straight", Sexuality, 399632983675502593),
    Role("Gay", Sexuality, 399632904872787968),
    Role("Lesbian", Sexuality, 423265882358284291),
    Role("Bisexual", Sexuality, 399633024062455809),
    Role("Asexual", Sexuality, 399688708921622530),
    Role("Pansexual", Sexuality, 399689139131514891),

    Role("Heteroromantic", Romantic, 669929026797830147),
    Role("Homoromantic", Romantic, 669929034750230538),
    Role("Biromantic", Romantic, 669929119621840916),
    Role("Aromantic", Romantic, 669929121031258133),
    Role("Panromantic", Romantic, 669929122612641803),

    Role("Lime", Color, 398201146461782016),
    Role("Rose", Color, 398201181987667971),
    Role("Violet", Color, 398201219367305216),
    Role("Blue", Color, 398301340691857408),
    Role("Aqua", Color, 422861972241907722),
    Role("Yellow", Color, 467428923592933378),
)