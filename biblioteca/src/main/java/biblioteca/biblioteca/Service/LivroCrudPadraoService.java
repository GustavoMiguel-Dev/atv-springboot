package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public LivroResponseDto criaLivro(LivroPostPutDto dto) {
        if (livroRepository.existsByIsbn(dto.getIsbn())) {
            throw new RuntimeException("Já existe um livro cadastrado com este ISBN.");
        }

        Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Livro livro = Livro.builder()
                .isbn(dto.getIsbn())
                .titulo(dto.getTitulo())
                .ano(dto.getAno())
                .edicao(dto.getEdicao())
                .autor(autor)
                .build();

        Livro livroSalvo = livroRepository.save(livro);
        return mapToResponseDto(livroSalvo);
    }

    @Override
    public LivroResponseDto atualizaLivro(Long id, LivroPostPutDto dto) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (!livroExistente.getIsbn().equals(dto.getIsbn()) && livroRepository.existsByIsbn(dto.getIsbn())) {
            throw new RuntimeException("Já existe outro livro cadastrado com este ISBN.");
        }

        Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        livroExistente.setIsbn(dto.getIsbn());
        livroExistente.setTitulo(dto.getTitulo());
        livroExistente.setAno(dto.getAno());
        livroExistente.setEdicao(dto.getEdicao());
        livroExistente.setAutor(autor);

        Livro livroAtualizado = livroRepository.save(livroExistente);
        return mapToResponseDto(livroAtualizado);
    }

    @Override
    public void apagaLivro(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        livroRepository.delete(livro);
    }

    @Override
    public LivroResponseDto buscaLivroPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return mapToResponseDto(livro);
    }

    @Override
    public List<LivroResponseDto> listaTodosLivros() {
        return livroRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LivroResponseDto> buscaPorIsbn(String isbn) {
        return livroRepository.findByIsbnContainingIgnoreCase(isbn).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LivroResponseDto> buscaPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    // Método auxiliar para converter a Entidade no DTO de resposta
    private LivroResponseDto mapToResponseDto(Livro livro) {
        return LivroResponseDto.builder()
                .id(livro.getId())
                .isbn(livro.getIsbn())
                .titulo(livro.getTitulo())
                .ano(livro.getAno())
                .edicao(livro.getEdicao())
                .autor(livro.getAutor())
                .build();
    }
}