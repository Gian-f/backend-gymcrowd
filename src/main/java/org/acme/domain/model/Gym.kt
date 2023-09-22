package org.acme.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*

@Entity(name = "Gym")
@RegisterForReflection
open class Gym(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long = 0,
        @Column(name = "nome_academia")
        open val nomeAcademia: String = "",

        @OneToMany(mappedBy = "gym", cascade = [CascadeType.ALL], orphanRemoval = true)
        @Column(name = "endereco_sedes")
        open val enderecoSedes: List<Address> = mutableListOf(),

        @Column(name = "lotacao_media")
        open val lotacaoMedia: Int = 0,

)
