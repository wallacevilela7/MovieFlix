CREATE TABLE tb_movie_streaming (
    movie_id BIGINT,
    streaming_id BIGINT,
    CONSTRAINT fk_tb_movie_streaming_tb_movie FOREIGN KEY(movie_id) REFERENCES tb_movie(id),
    CONSTRAINT fk_tb_movie_streaming_tb_streaming FOREIGN KEY(streaming_id) REFERENCES tb_streaming(id)
);