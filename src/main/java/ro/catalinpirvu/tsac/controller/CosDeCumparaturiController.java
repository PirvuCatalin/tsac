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
import ro.catalinpirvu.tsac.service.CosDeCumparaturiService;

import java.util.Map;

@Controller
public class CosDeCumparaturiController {
    private final CosDeCumparaturiService cosDeCumparaturiService;

    public CosDeCumparaturiController(CosDeCumparaturiService cosDeCumparaturiService) {
        this.cosDeCumparaturiService = cosDeCumparaturiService;
    }

    @Operation(summary = "Adauga un produs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produsul a fost adaugat",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Produsul nu a putut fi adaugat",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))})
    })
    @PostMapping("/cos-de-cumparaturi/adauga-produs")
    public ResponseEntity<String> adaugaProdus(@Parameter(description = "numele produsului")
                                                @RequestParam(required = true) String nume,
                                                @Parameter(description = "numarul de produse de adaugat")
                                                @RequestParam(required = true) Integer cantitate) {
        boolean produsAdaugat = cosDeCumparaturiService.adaugaProdus(nume, cantitate);
        if (produsAdaugat) {
            return ResponseEntity.ok().body("Produsul a fost adaugat.");
        }
        return ResponseEntity.badRequest().body("Produsul nu a putut fi adaugat!");
    }

    @Operation(summary = "Scoate un produs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produsul a fost scos",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Produsul nu a putut fi scos",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))})
    })
    @GetMapping("/cos-de-cumparaturi/scoate-produs")
    public ResponseEntity<String> scoateProdus(@Parameter(description = "numele produsului")
                                                  @RequestParam(required = true) String nume) {
        boolean produsScos = this.cosDeCumparaturiService.scoateProdus(nume);
        if (produsScos) {
            return ResponseEntity.ok().body("Produsul a fost scos.");
        }
        return ResponseEntity.badRequest().body("Produsul nu a putut fi scos!");
    }

    @Operation(summary = "Afla costul total al cosului de cumparaturi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Costul total al cosului de cumparaturi",
                    content = {@Content(mediaType = "text/html",
                            schema = @Schema(implementation = String.class))})
    })
    @GetMapping("/cos-de-cumparaturi/cost-total")
    public ResponseEntity<String> costTotal() {
        return ResponseEntity.ok().body(this.cosDeCumparaturiService.getCostTotal().toString());
    }

    @Operation(summary = "Afla componenta cosului de cumparaturi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Componenta cosului de cumparaturi",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))})
    })
    @GetMapping("/cos-de-cumparaturi/componenta")
    public ResponseEntity<Map<Produs, Integer>> componentaCosDeCumparaturi() {
        return ResponseEntity.ok().body(this.cosDeCumparaturiService.getComponentaCosDeCumparaturi());
    }
}
