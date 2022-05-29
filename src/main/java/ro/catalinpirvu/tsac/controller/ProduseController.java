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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.catalinpirvu.tsac.model.Produs;
import ro.catalinpirvu.tsac.service.ProduseService;

@Controller
public class ProduseController {
    private final ProduseService produseService;

    public ProduseController(ProduseService produseService) {
        this.produseService = produseService;
    }

    @Operation(summary = "Creeaza un produs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produsul a fost creat",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Produsul nu a putut fi creat",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))})
    })
    @PostMapping("/produse/creeaza-produs")
    public ResponseEntity<String> creeazaProdus(@Parameter(description = "numele produsului")
                                                @RequestParam(required = true) String nume,
                                                @Parameter(description = "costul produsului")
                                                @RequestParam(required = true) Float cost) {
        boolean produsCreat = produseService.creeazaProdus(nume, cost);
        if (produsCreat) {
            return ResponseEntity.ok().body("Produsul a fost creat.");
        }
        return ResponseEntity.internalServerError().body("Produsul nu a putut fi creat!");
    }

    @Operation(summary = "Sterge un produs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produsul a fost sters",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Produsul nu a putut fi sters",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))})
    })
    @PostMapping("/produse/sterge-produs")
    public ResponseEntity<String> stergeProdus(@Parameter(description = "numele produsului")
                                                @RequestParam(required = true) String nume) {
        boolean produsCreat = produseService.stergeProdus(nume);
        if (produsCreat) {
            return ResponseEntity.ok().body("Produsul a fost sters.");
        }
        return ResponseEntity.internalServerError().body("Produsul nu a putut fi sters!");
    }

    @Operation(summary = "Cauta un produs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produsul in format json",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produs.class))}),
            @ApiResponse(responseCode = "400", description = "Produsul nu a putut fi gasit",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})
    })
    @GetMapping("/produse/cauta-produs")
    public ResponseEntity<Object> cautaProdus(@Parameter(description = "numele produsului")
                                                  @RequestParam(required = true) String nume) {
        Produs produs;
        try {
            produs = produseService.cautaProdus(nume);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(String.format("{\"error\":\"%s\"}", e.getMessage()));
        }
        return ResponseEntity.ok().body(produs);
    }
}
