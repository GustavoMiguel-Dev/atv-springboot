package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {

    @JsonProperty("isbn")
    @NotBlank(message = "O ISBN do livro não pode ser vazio")
    private String isbn;

    @JsonProperty("titulo")
    @NotBlank(message = "O Título do livro não pode ser vazio")
    private String titulo;

    @JsonProperty("ano")
    @NotNull(message = "O Ano de publicação é obrigatório")
    private Integer ano;

    @JsonProperty("edicao")
    @NotNull(message = "A Edição do livro é obrigatória")
    private Integer edicao;

    @JsonProperty("autorId")
    @NotNull(message = "O ID do Autor é obrigatório")
    private Long autorId;
}