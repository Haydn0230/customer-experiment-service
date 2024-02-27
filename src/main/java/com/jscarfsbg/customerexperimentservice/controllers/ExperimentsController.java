package com.jscarfsbg.customerexperimentservice.controllers;


import com.jscarfsbg.customerexperimentservice.dto.*;
import com.jscarfsbg.customerexperimentservice.exception.InputFailedValidation;
import com.jscarfsbg.customerexperimentservice.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("experiment")
public class ExperimentsController {
    private final ExperimentService experimentService;

    @Autowired
    public ExperimentsController(ExperimentService experimentService) {
        this.experimentService = experimentService;
    }

    @GetMapping("/user")
    public Mono<ExperimentsResponse> getExperimentsByUser() {
        return Mono.fromSupplier(() -> this.experimentService.getExperimentsByUser(UUID.randomUUID().toString()));
    }

    @GetMapping("/user/{userId}")
    public Mono<ExperimentsResponse> getExperimentsByUserId(@PathVariable String userId) {
        return Mono.fromSupplier(() -> this.experimentService.getExperimentsByUser(userId));
    }

    @GetMapping("")
    public Flux<Experiment> getAllExperiments() {
        return this.experimentService.getAllExperiments();
    }

    @PostMapping("")
    public Mono<SetExperimentResponse> setExperiments(@RequestBody SetExperimentRequest experimentRequest) {
        InputFailedValidation isValid = experimentRequest.validate();
        if (isValid == null)
            return Mono.just(this.experimentService.setExperiments(experimentRequest));
        else
            return Mono.error(isValid);
    }

    @DeleteMapping("/{experimentId}")
    public Mono<DeleteExperimentResponse> deleteExperiment(@PathVariable String experimentId) {
        if (experimentId == null)
            return Mono.error(new InputFailedValidation("id is needed", 400));
        else
            return Mono.just(this.experimentService.deleteExperiment(experimentId));
    }
}
