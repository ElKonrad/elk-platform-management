package pl.pollub.monitoring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApplicationAdded {

    private final Long id;
    private final String name;
    private final String filepath;
}
