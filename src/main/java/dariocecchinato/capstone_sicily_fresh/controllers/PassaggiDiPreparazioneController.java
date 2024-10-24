package dariocecchinato.capstone_sicily_fresh.controllers;

import dariocecchinato.capstone_sicily_fresh.entities.PassaggioDiPreparazione;
import dariocecchinato.capstone_sicily_fresh.exceptions.BadRequestException;
import dariocecchinato.capstone_sicily_fresh.payloads.PassaggiDiPreparazionePayloadDTO;
import dariocecchinato.capstone_sicily_fresh.payloads.PassaggiDiPreparazioneResponseDTO;
import dariocecchinato.capstone_sicily_fresh.services.PassaggiDiPreparazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/passaggidipreparazione")
public class PassaggiDiPreparazioneController {
    @Autowired
    private PassaggiDiPreparazioneService passaggiDiPreparazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'FORNITORE')")
    public PassaggiDiPreparazioneResponseDTO creaPassaggio(@RequestBody PassaggiDiPreparazionePayloadDTO body){
        String immagine = "https://placehold.co/400";
        PassaggioDiPreparazione passaggio = new PassaggioDiPreparazione(body.descrizione(), immagine,body.ordinePassaggio());
        PassaggioDiPreparazione savedPassaggio = passaggiDiPreparazioneService.salvaPassaggio(body);
        return new PassaggiDiPreparazioneResponseDTO(savedPassaggio.getId());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'FORNITORE', 'CLIENTE')")
    public Page<PassaggioDiPreparazione> findAll (@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "15") int size,
                                                  @RequestParam(defaultValue = "id") String sortBy){
        return this.passaggiDiPreparazioneService.findAll(page, size, sortBy);
    }

    @GetMapping("/{ricettaId}")
    public List<PassaggioDiPreparazione> getPassaggiByRicettaId(@PathVariable UUID ricettaId) {
        return passaggiDiPreparazioneService.findPassaggiByRicettaId(ricettaId);
    }

    @PutMapping("/{passaggioDiPreparazioneId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'FORNITORE')")
    public PassaggioDiPreparazione findByIdAndUpdate (@PathVariable UUID passaggioDiPreparazioneId, @RequestBody @Validated PassaggiDiPreparazionePayloadDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            String messages = validation.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));

            throw new BadRequestException("ci sono stati errori nel payload: " + messages);
        }
        return this.passaggiDiPreparazioneService.findByIdAndUpdate(passaggioDiPreparazioneId,body);
    }

    @DeleteMapping("/{passaggioDiPreparazioneId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'FORNITORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID passaggioDiPreparazioneId) {
        this.passaggiDiPreparazioneService.findByIdAndDelete(passaggioDiPreparazioneId);
    }

    @PatchMapping("/{passaggioDiPreparazioneId}/immaginePassaggio")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'FORNITORE')")
    public PassaggioDiPreparazione uploadImmagginePassaggio(@PathVariable UUID passaggioDiPreparazioneId, @RequestParam("immaginePassaggio")MultipartFile immaginePassaggio) throws IOException{
        return this.passaggiDiPreparazioneService.uploadimmaginePassaggio(passaggioDiPreparazioneId,immaginePassaggio);
    }

    }

