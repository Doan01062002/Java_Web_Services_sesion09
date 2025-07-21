package com.example.session09.controller;

import com.example.session09.model.entity.Movie;
import com.example.session09.repository.MovieRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping
    public ResponseEntity<?> addMovie(@Valid @RequestBody Movie movie) {
        try {
            Movie savedMovie = movieRepository.save(movie);
            logger.info("Thêm phim thành công: {} - Thời gian: {}", savedMovie.getTitle(), java.time.LocalDateTime.now());
            return ResponseEntity.ok(savedMovie);
        } catch (Exception e) {
            logger.error("Lỗi khi thêm phim: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Lỗi khi thêm phim: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @Valid @RequestBody Movie updatedMovie) {
        try {
            return movieRepository.findById(id)
                    .map(existingMovie -> {
                        // Ghi log thông tin cũ (màu vàng)
                        logger.info("[YELLOW] Thông tin cũ của phim: id={}, title={}, description={}, releaseDate={}, poster={}",
                                existingMovie.getId(), existingMovie.getTitle(), existingMovie.getDescription(),
                                existingMovie.getReleaseDate(), existingMovie.getPoster());

                        // Cập nhật thông tin phim
                        existingMovie.setTitle(updatedMovie.getTitle());
                        existingMovie.setDescription(updatedMovie.getDescription());
                        existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
                        existingMovie.setPoster(updatedMovie.getPoster());

                        Movie savedMovie = movieRepository.save(existingMovie);

                        // Ghi log thông tin mới (màu xanh)
                        logger.info("[GREEN] Thông tin mới đã được thay đổi: id={}, title={}, description={}, releaseDate={}, poster={}",
                                savedMovie.getId(), savedMovie.getTitle(), savedMovie.getDescription(),
                                savedMovie.getReleaseDate(), savedMovie.getPoster());

                        return ResponseEntity.ok(savedMovie);
                    })
                    .orElseGet(() -> {
                        logger.error("[RED] Lỗi khi sửa phim: Không tìm thấy phim với id={}", id);
                        return ResponseEntity.badRequest().body(new Movie()); // Trả về Movie rỗng
                    });
        } catch (Exception e) {
            logger.error("[RED] Lỗi khi sửa phim: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new Movie()); // Trả về Movie rỗng
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            return movieRepository.findById(id)
                    .map(movie -> {
                        movieRepository.delete(movie);
                        // Ghi log xóa thành công
                        logger.info("[RED] Xóa thành công [GREEN] Thông tin phim đã bị xóa: id={}, title={}, description={}, releaseDate={}, poster={}",
                                movie.getId(), movie.getTitle(), movie.getDescription(),
                                movie.getReleaseDate(), movie.getPoster());
                        return ResponseEntity.ok("Xóa phim thành công với id: " + id);
                    })
                    .orElseGet(() -> {
                        logger.error("[RED] Lỗi khi xóa phim: Không tìm thấy phim với id={}", id);
                        return ResponseEntity.badRequest().body("Không tìm thấy phim với id: " + id);
                    });
        } catch (Exception e) {
            logger.error("[RED] Lỗi khi xóa phim: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Lỗi khi xóa phim: " + e.getMessage());
        }
    }
}
