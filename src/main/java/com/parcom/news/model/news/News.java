package com.parcom.news.model.news;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public
class News {

    @Id
    private String id;

    @NotEmpty
    private String title;

    private String message;

    private LocalDateTime dateTime;

    @NotNull
    private Long  idUser;

    @NotNull
    private Long  idGroup;



}
