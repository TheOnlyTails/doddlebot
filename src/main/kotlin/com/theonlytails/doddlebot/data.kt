package com.theonlytails.doddlebot


@Suppress("SpellCheckingInspection")
enum class Role(
    val roleName: String,
    val type: Type,
    val id: String,
) {
    HeHim("He/Him", Type.Pronouns, "399633081155452939"),
    SheHer("She/Her", Type.Pronouns, "399633116739665940"),
    TheyThem("They/Them", Type.Pronouns, "399633143675486219"),
    XeXem("Xe/Xem", Type.Pronouns, "815628065727447040"),

    Female("Female", Type.Gender, "399633209417138186"),
    Male("Male", Type.Gender, "399633179314749440"),
    NonBinary("Non Binary", Type.Gender, "399633884993683468"),
    Agender("Agender", Type.Gender, "407926177534312449"),
    Queer("Queer", Type.Gender, "790320965497651251"),
    GenderFluid("Gender Fluid", Type.Gender, "399706766914486282"),
    Trans("Trans", Type.Gender, "399633051111653376"),

    Straight("Straight", Type.Sexuality, "399632983675502593"),
    Gay("Gay", Type.Sexuality, "399632904872787968"),
    Lesbian("Lesbian", Type.Sexuality, "423265882358284291"),
    Bisexual("Bisexual", Type.Sexuality, "399633024062455809"),
    Asexual("Asexual", Type.Sexuality, "399688708921622530"),
    Pansexual("Pansexual", Type.Sexuality, "399689139131514891"),

    Heteroromantic("Heteroromantic", Type.Romantic, "669929026797830147"),
    Homoromantic("Homoromantic", Type.Romantic, "669929034750230538"),
    Biromantic("Biromantic", Type.Romantic, "669929119621840916"),
    Aromantic("Aromantic", Type.Romantic, "669929121031258133"),
    Panromantic("Panromantic", Type.Romantic, "669929122612641803"),

    Lime("Lime", Type.Color, "398201146461782016"),
    Rose("Rose", Type.Color, "398201181987667971"),
    Violet("Violet", Type.Color, "398201219367305216"),
    Blue("Blue", Type.Color, "398301340691857408"),
    Aqua("Aqua", Type.Color, "422861972241907722"),
    Yellow("Yellow", Type.Color, "467428923592933378");

    enum class Type(val title: String) {
        Pronouns("Pronoun"),
        Gender("Gender"),
        Sexuality("Sexuality"),
        Romantic("Romantic"),
        Color("Color"),
    }
}
