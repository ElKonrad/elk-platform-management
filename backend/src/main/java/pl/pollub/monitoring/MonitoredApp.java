package pl.pollub.monitoring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.pollub.monitoring.dto.ApplicationAdded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MONITORED_APPS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
class MonitoredApp {

    @Id
    @GeneratedValue
    @Column(name = "MONITORED_APP_ID")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String filepath;

    enum Status {
        CREATED, CONFIGURED, REMOVED
    }

    public void created() {
        status = Status.CREATED;
    }

    public void configured() {
        status = Status.CONFIGURED;
    }

    public ApplicationAdded dto() {
        return new ApplicationAdded(id, name, filepath);
    }
}
