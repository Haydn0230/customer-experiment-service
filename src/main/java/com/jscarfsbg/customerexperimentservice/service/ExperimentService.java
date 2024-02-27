package com.jscarfsbg.customerexperimentservice.service;

import com.jscarfsbg.customerexperimentservice.dto.DeleteExperimentResponse;
import com.jscarfsbg.customerexperimentservice.dto.Experiment;
import com.jscarfsbg.customerexperimentservice.dto.ExperimentsResponse;
import com.jscarfsbg.customerexperimentservice.dto.SetExperimentRequest;
import com.jscarfsbg.customerexperimentservice.dto.SetExperimentResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class ExperimentService {
    private final List<Experiment> experiments = new ArrayList<>();
    private final Random random = new Random();
    private final HashMap<String, List<Experiment>> userExperiments = new HashMap<>();

    public ExperimentService() {
        experiments.add(new Experiment("colour", "brown"));
        experiments.add(new Experiment("colour", "yellow"));

        userExperiments.put("123", List.of(new Experiment("colour", "brown")));
    }

    public ExperimentsResponse getExperimentsByUser(String userId) {
        List<Experiment> currentExperiment = userExperiments.get(userId);
        if (currentExperiment == null) {
            currentExperiment = List.of(experimentRandomiser());
            userExperiments.put(userId, currentExperiment);
        }
        return new ExperimentsResponse(currentExperiment, userId);
    }

    public Flux<Experiment> getAllExperiments() {
        return Flux.fromIterable(experiments);
    }

    public SetExperimentResponse setExperiments(SetExperimentRequest experimentRequest) {
        Experiment experiment = new Experiment(experimentRequest.getName(), experimentRequest.getType());
        experiments.add(experiment);
        return new SetExperimentResponse(experiment.getId());
    }

    public DeleteExperimentResponse deleteExperiment(String experimentId) {
        return new DeleteExperimentResponse(
                deleteExperimentFromList(experimentId),
                deleteExperimentFromUsers(experimentId),
                experimentId
        );
    }

    private long deleteExperimentFromUsers(String experimentId) {
        return userExperiments
                .values()
                .stream()
                .map(
                        userExperiments ->
                                userExperiments.removeIf(
                                        experiment -> Objects.equals(experiment.getId(), experimentId))
                ).count();
    }

    private boolean deleteExperimentFromList(String experimentId) {
        return experiments.removeIf((experiment -> Objects.equals(experiment.getId(), experimentId)));
    }

    private Experiment experimentRandomiser() {
        return experiments.get(random.nextInt(experiments.size()));
    }
}
