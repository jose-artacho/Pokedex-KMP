package domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val description: String,
    val weight: Int,
    val height: Int,
    val experience: Int,
    val url: String,
    val types: String,
)
