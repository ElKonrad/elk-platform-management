package pl.pollub.monitoring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MonitoredAppRepository extends JpaRepository<MonitoredApp, Long> {
}
