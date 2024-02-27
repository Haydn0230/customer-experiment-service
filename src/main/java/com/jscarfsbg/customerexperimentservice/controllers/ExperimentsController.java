package com.jscarfsbg.customerexperimentservice.controllers;


import com.jscarfsbg.customerexperimentservice.dto.DeleteExperimentResponse;
import com.jscarfsbg.customerexperimentservice.dto.Experiment;
import com.jscarfsbg.customerexperimentservice.dto.ExperimentsResponse;
import com.jscarfsbg.customerexperimentservice.dto.SetExperimentRequest;
import com.jscarfsbg.customerexperimentservice.dto.SetExperimentResponse;
import com.jscarfsbg.customerexperimentservice.exception.InputFailedValidation;
import com.jscarfsbg.customerexperimentservice.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
