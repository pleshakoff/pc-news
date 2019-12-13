package com.parcom.news.model.news;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/news",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@Api(tags="News")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;


    @GetMapping("/all")
    @ApiOperation(value = "Get all group news ")
    public List<NewsResource> getAllStudents()
    {
        return newsService.getNews();
    }


    @PostMapping
    @ApiOperation(value = "Create news post")
    public NewsResource create(@Valid @RequestBody NewsDto newsDto,
                               BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return newsService.create(newsDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update news post")
    public NewsResource update(@PathVariable Long id, @Valid @RequestBody NewsDto newsDto,
                               BindingResult bindingResult) throws BindException
    {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return newsService.update(id,newsDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete news post")
    public void delete(@PathVariable Long id)
    {
        newsService.delete(id);
    }




}
