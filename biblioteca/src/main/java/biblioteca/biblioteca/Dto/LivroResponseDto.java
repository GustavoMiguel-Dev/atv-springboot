package biblioteca.biblioteca.Dto;

import biblioteca.biblioteca.Model.Autor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("ano")
    private Integer ano;

    @JsonProperty("edicao")
    private Integer edicao;

    @JsonProperty("autor")
    private Autor autor;
}