package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import java.util.List;

public interface LivroCrudService {
    LivroResponseDto criaLivro(LivroPostPutDto livroPostPutDto);
    LivroResponseDto atualizaLivro(Long id, LivroPostPutDto livroPostPutDto);
    void apagaLivro(Long id);
    LivroResponseDto buscaLivroPorId(Long id);
    List<LivroResponseDto> listaTodosLivros();
    List<LivroResponseDto> buscaPorIsbn(String isbn);
    List<LivroResponseDto> buscaPorTitulo(String titulo);
}