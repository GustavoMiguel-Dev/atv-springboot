package biblioteca.biblioteca.Controller;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Service.LivroCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/livros")
public class LivroV1Controller {

    @Autowired
    private LivroCrudService livroCrudService;

    @PostMapping
    public ResponseEntity<LivroResponseDto> criaLivro(@RequestBody LivroPostPutDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCrudService.criaLivro(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizaLivro(@PathVariable Long id, @RequestBody LivroPostPutDto dto) {
        return ResponseEntity.ok(livroCrudService.atualizaLivro(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagaLivro(@PathVariable Long id) {
        livroCrudService.apagaLivro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroCrudService.buscaLivroPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> listaTodos() {
        return ResponseEntity.ok(livroCrudService.listaTodosLivros());
    }

    @GetMapping("/isbn")
    public ResponseEntity<List<LivroResponseDto>> buscaPorIsbn(@RequestParam String isbn) {
        return ResponseEntity.ok(livroCrudService.buscaPorIsbn(isbn));
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<LivroResponseDto>> buscaPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(livroCrudService.buscaPorTitulo(titulo));
    }
}