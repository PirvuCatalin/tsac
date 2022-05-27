package ro.catalinpirvu.tsac.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.catalinpirvu.tsac.service.RandomService;

import java.awt.print.Book;

@Controller
public class RandomController {
    private final RandomService randomService;

    public RandomController(RandomService randomService) {
        this.randomService = randomService;
    }

    @Operation(summary = "Get a random integer with an optional upper bound")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Generated the random number",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid upper bound supplied",
                    content = @Content)})
    @GetMapping("/getRandomNumber")
    public ResponseEntity<String> getRandomNumber(@Parameter(description = "the upper limit of the generated number")
                                                  @RequestParam(required = false) Integer upperBound) {
        Integer number;
        try {
            number = randomService.getRandomNumber(upperBound);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Upper bound must be a positive integer!");
        }
        return ResponseEntity.ok(number.toString());
    }
}
